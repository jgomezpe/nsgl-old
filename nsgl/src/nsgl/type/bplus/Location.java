/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.bplus;

/**
 *
 * @author jgomez
 */
public class Location<T>{
    protected int pos = -1;
    protected LeafNode<T> node;
    
    public Location(int pos, LeafNode<T> node){
        this.pos = pos;
        this.node = node;
    } 
    
    public int pos(){ return pos; }
    
    public LeafNode<T> node(){ return node; }
    
    public T get(){ return node.key(pos); }
}