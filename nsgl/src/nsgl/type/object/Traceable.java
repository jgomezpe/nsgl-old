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

package nsgl.type.object;

import java.io.IOException;
import java.util.Vector;

import nsgl.cast.Cast;
import nsgl.cast.toTraceable;
import nsgl.service.Tracer;
/**
 * <p>Title: Traceable</p>
 *
 * <p>Description: An object that can be traced</p>
 *
 */

public class Traceable {
	protected Vector<Tracer> tracers = new Vector<Tracer>();
	protected boolean tracing = false;
	protected Object caller;
	
	public Traceable(){ caller = this; }
	
	public Traceable( Object caller ){ this.caller = caller; }
	
	public boolean tracing(){ return tracing; }

	/**
	 * Starts the tracing of objects process
	 * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
	 */
	public void startTrace(){ tracing = true; }

	/**
	 * Stops the tracing of objects process
	 * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
	 */
	public void stopTrace(){ tracing = false; }

	/**
	 * Adds an object sent by an object to the tracer
	 * @param obj Traced information to be added
	 */
	public void trace(Object... obj){ if( tracing() ) for( Tracer t:tracers ) t.add(caller, obj); }

	
	public void addTracer( Tracer t ){ tracers.add(t); }
	
	public void delTracer( Tracer t ){ tracers.remove(t); }
	
	/**
	 * Cleans the traced information
	 */
	public void initTracers(){ for( Tracer t:tracers ) t.clear(); }

	/**
	 * Closes the tracer
	 */
	public void closeTracers(){ for( Tracer t:tracers ) try{ t.close(); }catch(IOException e){}; }	
	
	public Traceable cast( Object obj ){
		if( obj instanceof Traceable ) return (Traceable)obj;
    	@SuppressWarnings("unchecked")
		Cast<Traceable> cast = (Cast<Traceable>)Cast.service(obj,Traceable.class);
		if( cast == null ){
			cast = new toTraceable();
			Cast.set(Object.class, Traceable.class, cast);
		}
    	return cast.apply(obj); 
	}
}