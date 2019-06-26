/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nsgl.real.array;

import java.io.IOException;

import nsgl.integer.PlainIntRead;
import nsgl.iterator.Backable;
import nsgl.real.PlainRealRead;
import nsgl.service.Read;

/**
 *
 * @author jgomez
 */
public class PlainRealArrayRead implements RealArrayRead{
	protected boolean read_dimension = true;
	protected char separator = ' ';
	protected int n=-1;
	protected PlainIntRead ri = new PlainIntRead();
	protected PlainRealRead rr = new PlainRealRead();
	
	public PlainRealArrayRead(){}
	
	public PlainRealArrayRead( char separator ){
		this.separator = separator;
	}
	
	public PlainRealArrayRead( int n ){
		this.n = n;
		read_dimension = (n <=0 );
	}
	
	public PlainRealArrayRead( int n, char separator ){
		this.n = n;
		this.separator = separator;
		read_dimension = (n <=0 );
	}

	public void setReadInt(PlainIntRead ri ){ this.ri = ri; }
	public void setReadDouble( PlainRealRead rr ){ this.rr = rr; }
	
    @Override
    public double[] read( Backable<Integer> reader ) throws IOException{
        if( read_dimension ){
        	n = ri.read(reader);
        	Read.readSeparator(reader, separator);        	
        }
		double[] a = new double[n];
        for (int i = 0; i < n-1; i++) {
            a[i] = rr.read(reader);
            Read.readSeparator(reader, separator);        	
        }
        if( n-1 >= 0 ) a[n-1] = rr.read(reader);
        return a;
    }
    
	@Override
	public String toString(){ return "DoubleArrayPlainRead"; }    
}