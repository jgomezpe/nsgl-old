/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse;

import java.io.IOException;

import nsgl.service.Read;
import nsgl.type.collection.iterator.Backable;
import nsgl.type.integer.IntRead;
import nsgl.type.integer.PlainIntRead;
import nsgl.type.real.PlainRealRead;
import nsgl.type.real.RealRead;

/**
 *
 * @author jgomez
 */
public class PlainRealSparseRead implements RealSparseRead{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';

    protected boolean read_dimension = true;
    protected int n = -1;
	protected IntRead ri = new PlainIntRead();
	protected RealRead rr = new PlainRealRead();

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public PlainRealSparseRead() {}

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainRealSparseRead(char separator) {
        this.separator = separator;
    }

    public PlainRealSparseRead(int n) {
        this.n = n;
        read_dimension = n<=0;
    }

    public PlainRealSparseRead(int n, char separator) {
        this.n = n;
        read_dimension = n<=0;
        this.separator = separator;
    }

	public void setReadInt( IntRead ri ){ this.ri = ri; }
	public void setReadDouble( RealRead rr ){ this.rr = rr; }
	
	public RealSparse read( Backable<Integer> reader ) throws IOException{
        if( read_dimension ){
        	n = ri.read(reader);
        	Read.readSeparator(reader, separator);        	
        }
        RealSparse d = new RealSparse(n);
        int k;
        double v;
        while( reader.hasNext() ){
            k = ri.read(reader);
            Read.readSeparator(reader, separator);
            v = rr.read(reader);
            Read.readSeparator(reader, separator);
            d.set(k,(Double)v);
        }
        return d;
    }
}