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
public abstract class MemoryNode<T> implements Node<T> {
    protected int n;
    protected Node<T> left = null;
    protected Node<T> right = null;
    protected InnerNode<T> parent = null;  
    
    // Size    
    @Override
    public int n(){ return n; }

    @Override
    public void setn( int n ){
        this.n = n;
    }
    
    //Siblings
    @Override
    public Node<T> left(){
        return left;
    }

    @Override
    public void setLeft( Node<T> node ){
        left = node;
    }

    @Override
    public Node<T> right(){
        return right;
    }
    
    @Override
    public void setRight( Node<T> node ){
        right = node;
    }

    //Parent
    @Override
    public InnerNode<T> parent(){
        return parent;
    }

    @Override
    public void setParent( InnerNode<T> node ){
        parent = node;
    }        
}