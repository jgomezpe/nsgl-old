/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nsgl.bplus.memory;
import nsgl.bplus.LeafNode;
import nsgl.bplus.Node;

/**
 *
 * @author jgomez
 */
public class MemoryLeafNode<T> extends MemoryNode<T> implements LeafNode<T>{
    protected T[] keys;
    
    @SuppressWarnings("unchecked")
	public MemoryLeafNode( int SIZE ){
        keys = (T[])new Object[SIZE];
        n = 0;
    }
    
    public MemoryLeafNode( T[] _keys, int _n ){
        keys = _keys;
        n = _n;
    }
    
    @Override
    public Node<T> newInstance(int SIZE){
        return new MemoryLeafNode<>(SIZE);
    }
    
    @Override
    public LeafNode<T> newInstance( T[] keys, int n ){
       return new MemoryLeafNode<>(keys, n);
    }

    public void setKeys( T[] keys ){
    	this.keys = keys;
    }

    public void setKey( T key, int index ){
    	keys[index] = key;
    }

    @Override
    public T[] keys(){
        return keys;
    }
        
    // Size
    @Override
    public int size(){
        return keys.length;
    }
}