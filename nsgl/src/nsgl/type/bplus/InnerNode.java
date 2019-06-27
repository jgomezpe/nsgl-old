package nsgl.type.bplus;

import nsgl.type.array.ArrayUtil;

public interface InnerNode<T> extends Node<T>{
    
	public InnerNode<T> newInstance( Node<T>[] nodes, int n );        
    
	// Next methods
    public Node<T>[]   next();    
 
    public default Node<T> next(int i){ return next()[i]; }

    public default LeafNode<T> mostLeft(){
    	Node<T> node = next(0);
        if(node instanceof InnerNode)
            return ((InnerNode<T>)node).mostLeft();
        else
            return (LeafNode<T>)node;    	
    }

    //Keys
    @Override
    public default T leftKey(){ return updateLeftKey(); }
    
    @Override
    public default T updateLeftKey(){
    	Node<T> node = next(0);
        T leftKey = (n()>0 && node !=null)?node.updateLeftKey():null;
        return leftKey;
    }

	
	public void setNext( Node<T>[] next );
    public void setNext( Node<T> node, int index );
	
    public default boolean add( Node<T> key ){
		return add( key, n() );    	
    }    
    
    public default boolean add( Node<T> key, int index ){
    	int n = n();
		if( n < size() ){
			key.setParent(this);
			Node<T>[] next = (Node<T>[])next();
			ArrayUtil.insert(n, next, key, index);
			setNext(next);
			setn(n+1);
			return true;
		}
		return false;    	
    }    
    
    public default boolean add( Node<T> key, ImmutableBPlus<T> tree ){
		return add( key, ArrayUtil.indexForAddToSortArray(n(), next(), key, tree.node_order()) );
    }   
    
    public default boolean remove(){
		return remove(n()-1);
    }   
    
    public default boolean remove( int index ){
    	int n = n();
		if( 0<=index && index<n ){
			Node<T>[] next = next();
			Node<T> node = next[index];
			if(node.left()!=null) node.left().setRight(node.right());
			
			if(node.right()!=null) node.right().setLeft(node.left());
			
			ArrayUtil.del(n, next, index);
			setn(n-1);
			setNext(next);
			return true;
		}
		return false;
    }   
    
    public default boolean remove( Node<T> key, ImmutableBPlus<T> tree ){
		return remove( ArrayUtil.findInSortArray(n(), next(), key, tree.node_order()) );
    }  
    
    // Balance
	@Override
    public default void leftShift(){
        ((InnerNode<T>)left()).add(this.next(0));
        this.remove(0);
    }
    
    @Override
    public default void rightShift(){
		InnerNode<T> iright = ((InnerNode<T>)right());
        iright.add(next(n()-1));
        this.remove();
    }    

    @Override
    public default Node<T> split(){
    	int n = n();
        @SuppressWarnings("unchecked")
		Node<T>[] rnext = new Node[size()];
        System.arraycopy(next(), n/2, rnext, 0, n-n/2);
        InnerNode<T> r = (InnerNode<T>)this.newInstance(rnext,n-n/2);
        r.setLeft(this);
        r.setRight(this.right());
        if( r.right() != null ){
        	((Node<T>)r.right()).setLeft(r);
        }
        this.setRight(r);
        this.setn(n/2);
        //r.updateLeftKey();
        return r;
    }
    
	@Override
    public default void merge(){
		int n = n();
		Node<T> right = right();
		int nr = right.n();
		Node<T>[] next = next();
        System.arraycopy(((InnerNode<T>)right).next(), 0, next, n, nr );
        setn(n+nr);
        setNext(next);
        setRight(right.right());
    }
      
    public default String toString( int level ){
    	StringBuilder sb = new StringBuilder();
    	for( int i=0; i<level;i++){ sb.append(' '); }
    	level++;
    	int n = n();  
    	Node<T>[] next = next();
    	for( int i=0; i<n; i++ ){
    		sb.append('|');
    		sb.append(next[i].toString(level));
    	}
    	return sb.toString();
    }	
}