/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.bplus;

import java.util.NoSuchElementException;
import java.util.Iterator;

import nsgl.array.BinarySearch;
import nsgl.bplus.memory.MemoryLeafNode;
import nsgl.collection.Searchable;
import nsgl.service.Order;

/**
 *
 * @author jgomez
 */
public class ImmutableBPlus<T> implements Searchable<Location<T>,T> {
    protected InnerNode<T> root;
    protected Order order;
    protected NodeOrder<T> node_order;
    protected BinarySearch<T> search;
    protected BinarySearch<Node<T>> node_search;

    public ImmutableBPlus( Order order ){
    	this( order, null );
    }
    
    public ImmutableBPlus( Order order, InnerNode<T> root ){
        this.root = root;
        this.order = order;
        this.node_order = new NodeOrder<T>(order);
        this.search = new BinarySearch<T>(null,order);
        this.node_search = new BinarySearch<Node<T>>(null,node_order);
    }

    public int search( T[] keys, T key, int n ){
    	search.set(keys);
        return search.findRight(0, n, key);
    }

    public Order key_order(){ return order; }
    public Order node_order(){ return node_order; }
    
    @SuppressWarnings("unchecked")
	protected MemoryLeafNode<T> search_aux = new MemoryLeafNode<T>( (T[])new Object[1], 1);
    public int search( Node<T>[] keys, T key, int n ){
    	search_aux.remove();
        search_aux.add(key);
        node_search.set(keys);
        return node_search.findRight(0, n, search_aux);
    }

    @Override
    public boolean isEmpty() {
        return root.mostLeft().n()==0;
    }

    @Override
    public Iterator<T> iterator(){ return new nsgl.bplus.Iterator<T>(root.mostLeft()); }

    
    protected Location<T> find( Node<T> node, T data ){
        if( node!=null){
            if( node instanceof InnerNode){
                InnerNode<T> inode = (InnerNode<T>)node;
                if(inode.n()>1){
                    int k = search(inode.next(), data, node.n())-1;
                    if( k<0 ) throw new NoSuchElementException();
                    return find(inode.next(k), data);
                }else
                    return find(inode.next(0), data);
            }else{
                LeafNode<T> lnode = (LeafNode<T>)node;
                int k = search(lnode.keys(), data, node.n());
                if( k<0 ) throw new NoSuchElementException();
                return new Location<>(k, lnode);
            }
        }    
        throw new NoSuchElementException();
    }

    @Override
    public Location<T> find(T data) {
        return find(root,data);
    }

    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	if( root != null )
    		sb.append(root.toString(0));
    	return sb.toString();
    }
}