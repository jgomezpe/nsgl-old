package nsgl.type.integer.array;

import java.io.*;

import nsgl.exception.IO;


/**
 * <p>Integer array persistent method that uses a given character for separating the array values</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public class PlainIntArrayWrite implements IntArrayWrite{
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';
    
    protected boolean write_dimension = true;

    /**
     * Creates an integer array persistent method that uses an space for separating the array values
     */
    public PlainIntArrayWrite() {}

    /**
     * Creates an integer array persistent method that uses the give character for separating the array values
     * @param separator Character used for separating the array values
     */
    public PlainIntArrayWrite(char separator) {
        this.separator = separator;
    }
    
    public PlainIntArrayWrite(char separator, boolean write_dimension) {
        this.separator = separator;
        this.write_dimension = write_dimension;
    }

    /**
     * Writes an array to the given writer (writes the size and the values) using the associated separator char
     * @param obj array to write
     * @param out The writer object
     * @throws ParamsException ParamsException
     */
    public void write(int[] obj, Writer out) throws IOException {
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
	public String toString(){ return "IntArrayPlainWrite"; }	        
}