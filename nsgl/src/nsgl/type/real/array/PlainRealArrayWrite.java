package nsgl.type.real.array;

import java.io.*;

import nsgl.exception.IO;

/**
 * <p>Title: DoubleArraySimplePersistent </p>
 * <p>Description: A double array persistent method that uses a given charater for separating the array values</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class PlainRealArrayWrite implements RealArrayWrite {
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';
    
    protected boolean write_dimension = true;

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public PlainRealArrayWrite() {}

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public PlainRealArrayWrite(boolean write_dim ) {
        write_dimension = write_dim;
    }

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainRealArrayWrite(char separator) {
        this.separator = separator;
    }

    /**
     * Creates a double array persistent method that uses the give charater for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainRealArrayWrite(char separator, boolean write_dim ) {
        this.separator = separator;
        this.write_dimension = write_dim;
    }
    
    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws ParamsException ParamsException
     */
    public void write(double[] obj, Writer out) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = obj.length;
        if( write_dimension ){
            sb.append(n);
        }
        if( n > 0 ){
            if( write_dimension ) sb.append(separator);
            sb.append(obj[0]);
        }
        for (int i = 1; i < n; i++) {
            sb.append(separator);
            sb.append(obj[i]);
        }
        try{ out.write(sb.toString()); }catch(IOException e){ throw IO.exception(IO.OTHER, e.getMessage()); }
    }
    
	@Override
	public String toString(){ return "DoubleArrayPlainWrite"; }    
}