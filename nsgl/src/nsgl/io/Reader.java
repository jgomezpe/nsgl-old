package nsgl.io;

import java.io.Closeable;
import java.util.Iterator;

import nsgl.collection.Collection;
import nsgl.iterator.BTWrap;
import nsgl.iterator.Iterator2DPosition;
import nsgl.iterator.IteratorPosition;

public abstract class Reader implements Collection<Integer>, Closeable{
	/**
	 * Linefeed character
	 */
	public static int LINEFEED = (int) '\n';
	/**
	 * Carriage return character
	 */
	public static int CARRIAGERETURN = (int) '\r';
	
	protected boolean closed = false;
	protected int c;
	protected int src;
	
	public Reader(){ this(0); }
	public Reader(int src){ this.src=src; }
	
	public int src(){ return src; }
	public void setSrc( int src ){ this.src = src; }	
	
	protected Iterator<Integer> iter = new  Iterator<Integer>(){
		@Override
		public boolean hasNext() { return c!=-1; }

		@Override
		public Integer next() {
			int t = c;
			c=get();
			return t;
		}		
	};
	
	protected class ReaderIterator extends BTWrap<Integer>{
		public ReaderIterator(Iterator<Integer> iter) {
			super(iter);
			extra[0]=new Iterator2DPosition(src(),0,0,0);
		}

		@Override
		protected IteratorPosition pos(Integer c) {
			Iterator2DPosition p = (Iterator2DPosition)extra[(n+pos-1)%n];
			if (c == CARRIAGERETURN || (c == LINEFEED && (int)data [pos] != CARRIAGERETURN)) {
				return new Iterator2DPosition(src(),0,p.row() + 1, 0);
			}else{
				int row = p.row();
				int col = p.column();
				if (c != LINEFEED) col++;
				return new Iterator2DPosition(src(),0,row,col);
			}		
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new ReaderIterator(iter);
	}
		
	@Override
	public boolean isEmpty() { return closed; }
	
	public abstract int get(); 
}