/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.bplus;

import java.util.NoSuchElementException;

/**
 *
 * @author jgomez
 */
public class Iterator<T> implements java.util.Iterator<T>{
    protected int pos = -1;
    protected LeafNode<T> node;

    public Iterator( LeafNode<T> node ){
        this.node = node;
    }
    
    public Iterator( Location<T> loc ){
        node = loc.node;
        pos = loc.pos - 1;
    }

    @Override
    public boolean hasNext(){
        return (pos+1<node.n() || node.right()!=null);
    }

    @Override
    public T next()  throws NoSuchElementException{
        try{
            while(node!=null&&pos+1==node.n()){
                pos = -1;
                node = (LeafNode<T>)node.right();
            }
            if(pos+1<node.n()){
                pos++;
            }    
            return node.key(pos);
        }catch( Exception e ){
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
    }    
    
}
