package nsgl.type.bplus;

import nsgl.service.Order;
import nsgl.type.collection.Mutable;
import nsgl.type.collection.Searchable;

public abstract class BPlus<T> extends ImmutableBPlus<T> implements Mutable<T>, Searchable<Location<T>,T> {
	public static final int MIN_BRANCHING = 6;
	protected int BRANCHING;
    public BPlus( Order order ){
        this( MIN_BRANCHING, order );
    }

    public BPlus( int BRANCHING, Order order ){
        super( order );
        this.BRANCHING = BRANCHING<MIN_BRANCHING?MIN_BRANCHING:BRANCHING;
    }

    public abstract InnerNode<T> innerNode();
    
    public abstract LeafNode<T> leafNode();
    
	public boolean del( Node<T> node, T data ){
    	if( node==null ) return false;
    	boolean flag;
        if( node instanceof InnerNode){
			InnerNode<T> inode = (InnerNode<T>)node;
            Node<T> theNode;
            if(inode.n()>1){
                int k = search(inode.next(), data, node.n())-1;
                if( k<0 ) return false;
                theNode = inode.next(k);
            }else{
                theNode = inode.next(0);
            }
            flag = del(theNode, data);            
        }else{
			LeafNode<T> lnode = (LeafNode<T>)node;
            flag = lnode.remove(data, this);
            if( flag ) node.checkEmpty(this);
        }
        return flag;
    }
    
    @Override
    public boolean del( T key ){
        return del(root, key);
    }

	public LeafNode<T> locate( Node<T> node, T data ){
        if( node instanceof InnerNode){
            InnerNode<T> inode = (InnerNode<T>)node;
            int k = search(inode.next(), data, node.n())-1;
            if( k<0 ) return (LeafNode<T>)inode.mostLeft();
            return locate(inode.next(k), data);
        }else{
        	return (LeafNode<T>)node;
        }
	}
	    
	@Override
    public boolean add( T key ){
    	if( this.root == null ){
            this.root = innerNode();
            LeafNode<T> leaf = leafNode();
            leaf.add(key);
            ((InnerNode<T>)this.root).add(leaf);
    		return true;
    	}
    	LeafNode<T> leaf = locate(this.root, key);
        if( leaf.add(key, this) ){
        	((Node<T>)leaf).checkFull(this);
        	if( root.parent() != null ){
        		root = root.parent();
        	}
        	return true;
        }
        return false;
        //return add(root,key);
    }

    @Override
    public void clear() {
    	root = null;
    }
}