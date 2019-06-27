/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.bplus;

import nsgl.service.Order;

/**
 *
 * @author jgomez
 */
public class NodeOrder<T> implements Order{
    protected Order inner;
    public NodeOrder( Order _inner ){ inner = _inner; }
    
    public int compare(Node<T> a, Node<T> b){ return inner.compare(a.leftKey(), b.leftKey()); }

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object a, Object b){ return compare((Node<T>)a, (Node<T>)b); }
}