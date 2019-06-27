package nsgl.type.sparse.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

import nsgl.type.object.Pair;
import nsgl.type.sparse.SparseArray;
import nsgl.type.sparse.SparseMatrix;

public class SparseMatrixElementsIterator<T> implements Iterator<SparseMatrixElement<T>>{
	protected SparseMatrix<T> matrix;
	protected T value;
	protected Iterator<Pair<Integer,SparseMatrix<T>>> iter=null;
	protected SparseMatrixElementsIterator<T> inner_iter = null;
	protected int[] reduce;
	protected int[] order;
	protected int k;
	protected int i=-1;
	
	protected int[]  indices;
	
	protected static int[] reduce( int[] order ){
		int[] corder = order.clone();
		for( int i=0; i<corder.length; i++ ){
			for( int j=i+1; j<corder.length; j++ ){
				if( corder[j] > corder[i] ) corder[j]--;
			}
		}
		return corder;
	}
	
	public SparseMatrixElementsIterator( SparseMatrix<T> matrix, int[] order ) {
		this(matrix, order, reduce(order), 0, new int[order.length]);
	}
	
	protected SparseMatrixElementsIterator( SparseMatrix<T> matrix, int[] order, int[] reduce, int k, int[] indices ) {
		this.indices = indices;
		this.order = order;
		this.k = k;
		this.reduce = reduce;
		if( k<order.length ){
			SparseArray<SparseMatrix<T>> vector = matrix.dim_data()[reduce[k]];
			this.iter = (Iterator<Pair<Integer,SparseMatrix<T>>>)vector.pairs().iterator();
		}else{
			value = matrix.data();
		}	
	}		
	
	@Override
	public boolean hasNext() {
		if(k==order.length) return true;
		if( k+1<order.length && inner_iter != null && inner_iter.hasNext() ) return true;
		boolean flag = false;
		while(!flag && iter.hasNext() ){
			Pair<Integer,SparseMatrix<T>> element = iter.next();
			indices[order[k]] = element.a();
			inner_iter = new SparseMatrixElementsIterator<T>(element.b(), order, reduce, k+1, indices);
			flag = inner_iter.hasNext();				
		}
		return flag;
	}

	@Override
	public SparseMatrixElement<T> next() throws NoSuchElementException {
		if( inner_iter != null ){
			inner_iter.next();
			value = inner_iter.value;
		}
		return new SparseMatrixElement<T>(indices, value);
	}
}