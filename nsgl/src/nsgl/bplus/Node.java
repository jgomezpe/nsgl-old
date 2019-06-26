package nsgl.bplus;

public interface Node<T>{    
    Node<T> newInstance(int SIZE);
    
    //key's methods
    // Left key
    T leftKey();  
    T updateLeftKey();

    // Size analysis
    int n();
    int size();        
    default int underFillSize(){ return size()/3; }
    default boolean isFull(){ return n()==size(); }
    default boolean underFill(){ return n()<underFillSize(); }

    // Parent
    InnerNode<T> parent();    
    
    // Siblings    
    Node<T> left();
    Node<T> right();
    
    String toString( int level );

	// Size 
    void setn( int n );
    
    // Parent
    void setParent( InnerNode<T> node );
    
    // Siblings    
    void setLeft( Node<T> node );
    void setRight( Node<T> node );

	default void checkFull(ImmutableBPlus<T> tree){
		if( this.isFull() ){
	    	InnerNode<T> parent = (InnerNode<T>)this.parent();
			Node<T> right = this.split();
			if( parent==null ){
				parent = (InnerNode<T>)this.newInstance(this.size());
				parent.add(this);
			}
			parent.add(right, tree);
			((Node<T>)parent).checkFull( tree );
		}
	}
	
	default void checkEmpty( ImmutableBPlus<T> tree ){
    	if( this.underFill() ){
        	InnerNode<T> parent = (InnerNode<T>)this.parent();
    		if( parent==null && n()==0){
    			((BPlus<T>)tree).clear();
    			return;
    		} 
    		Node<T> left = (Node<T>)this.left(); 
    		if(left != null ){
    			if( left.n() + this.n() <= (this.underFillSize()<<1) ){    		
	    			((Node<T>)left).merge();    			
	    			if( parent != null ){
	    				parent.remove(this, tree);
	    				((Node<T>)parent).checkEmpty( tree );
	    			}
    			}else{
    				left.rightShift();
    			}	
    		}else{
	    		Node<T> right = (Node<T>)this.right(); 
	    		if(right != null ){
	    			if( right.n()+this.n() <= (this.underFillSize()<<1)){		    		
		    			this.merge();
		    			parent = (InnerNode<T>)right.parent();
		    			if( parent != null){
		    				parent.remove(right, tree);
		    				((Node<T>)parent).checkEmpty( tree );
		    			}
	    			}else{
	    				right.leftShift();
	    			}	
	    		}
    		}
    	}
	}	

    // Balance
    void leftShift();
    void rightShift();
    void merge();
    Node<T> split();       
}