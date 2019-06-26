/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nsgl.bplus.memory;
import nsgl.bplus.InnerNode;
import nsgl.bplus.Node;

/**
 *
 * @author jgomez
 */
public class MemoryInnerNode<T> extends MemoryNode<T> implements InnerNode<T>{
    protected Node<T>[] next;
    protected T leftKey;
    
    @SuppressWarnings("unchecked")
	public MemoryInnerNode( int SIZE ){
        next = new Node[SIZE];
        n = 0;
    }

    public MemoryInnerNode( Node<T>[] next, int n ){
        this.next = next;
        this.n = n;
        for( int i=0; i<n; i++) next[i].setParent(this);
    }

    @Override
    public Node<T> newInstance(int SIZE){
        return new MemoryInnerNode<T>(SIZE);
    }
    
    @Override
    public InnerNode<T> newInstance( Node<T>[] next, int n ){
       return new MemoryInnerNode<T>(next,n);
    }
    
    // Size
    @Override
    public int size(){
        return next.length;
    }
    
    @Override
    public Node<T>[] next(){
        return next;
    }

    public void setNext( Node<T>[] next ){
    	this.next = next;
    }
    
    public void setNext( Node<T> node, int index ){
    	this.next[index] = node;
    }    
}