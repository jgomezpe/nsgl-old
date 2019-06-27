/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse;

import java.io.IOException;
import java.io.Writer;

import nsgl.exception.IO;
import nsgl.type.collection.Collection;
import nsgl.type.object.Pair;

/**
 *
 * @author jgomez
 */
public class PlainRealSparseWrite implements RealSparseWrite{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';
    
    protected boolean write_dimension = true;

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public PlainRealSparseWrite() {}

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public PlainRealSparseWrite(boolean write_dim ) {
        write_dimension = write_dim;
    }

    /**
     * Creates a double array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainRealSparseWrite(char separator) {
        this.separator = separator;
    }

    /**
     * Creates a double array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainRealSparseWrite(char separator, boolean write_dim ) {
        this.separator = separator;
        this.write_dimension = write_dim;
    }
    
    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws IOException IOException
     */
    @Override
    public void write(RealSparse obj, Writer out) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = obj.size();
        if( write_dimension ){
            sb.append(n);
            sb.append(separator);
        }
        Collection<Pair<Integer,Double>> col = obj.pairs();
        for( Pair<Integer,Double> kv : col ){
            sb.append(kv.a());
            sb.append(separator);
            sb.append(kv.b());            
            sb.append(separator);
        }        
        try{ out.write(sb.toString()); }catch(IOException e){ throw IO.exception(IO.OTHER, e); }
    }
}
