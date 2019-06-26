/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package nsgl.array;
/**
 * <p>Title: Fibo</p>
 *
 * <p>Description: An abstract dynamic array that grows and shrinks using the Fibonacci numbers</p>
 *
 */

public abstract class Fibo {
	protected int size;
	protected int a, b, c;

	protected static final int DEFAULT_C = 144;
	protected static final int DEFAULT_B = 89;
	protected static final int DEFAULT_A = 55;

	protected final void find_fib( int s ){
		a = DEFAULT_A;
		b = DEFAULT_B;
		c = DEFAULT_C;
		while(s>c){
			a=b;
			b=c;
			c=a+b;
		}
	}    

	public void clear(){
		a = DEFAULT_A;
		b = DEFAULT_B;
		c = DEFAULT_C;
		resize();
		size = 0;
	}

	protected void grow(){
		// It requires than a > buffer.length/2
		a = b;
		b = c;
		c = a+b;
		resize();        
	}

	protected void shrink(){
		// It maintains a > buffer.length/2
		if( a >= DEFAULT_B ){
			c = b;
			b = a;
			a = c-b;
		}    
		if(size()!=c) resize();
	}

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize( int n ){
		int x = c;
		find_fib(n);
		if( x!=c ) resize();
		this.size = n; 
	}

	public abstract void resize();
	
	public int size(){ return size; }
	
}
