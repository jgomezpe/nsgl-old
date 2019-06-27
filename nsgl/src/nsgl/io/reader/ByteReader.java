package nsgl.io.reader;

import java.io.IOException;
import java.io.InputStream;

import nsgl.io.Reader;

public class ByteReader extends Reader{
	protected InputStream is;
	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public ByteReader(InputStream is){ this( is, 0 ); }
	
	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public ByteReader(InputStream is, int src) {
		super( src );
		this.is=is; 
		c=get();
		if( c==-1 ) close();
	}

	@Override
	public int get(){ try{ return is.read(); }catch(IOException e){ return -1; } }

	@Override
	public void close(){
		try{ 
			closed = true;
			is.close(); 
		}catch(IOException e){}
	}
}