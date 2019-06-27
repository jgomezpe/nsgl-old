package nsgl.type.real.matrix;


import java.io.IOException;

import nsgl.service.Read;
import nsgl.type.collection.iterator.Backable;
import nsgl.type.integer.PlainIntRead;
import nsgl.type.real.PlainRealRead;

public class PlainRealMatrixRead implements RealMatrixRead{
	public static final String integer = "Read.integer";
	public static final String real = "Read.double";

	protected boolean read_dimension = true;
	
	protected int n;
	protected int m;

	protected PlainIntRead ri = new PlainIntRead();
	protected PlainRealRead rr = new PlainRealRead();
	
	/**
	 * Character used for separating the values in the array
	 */
	protected char separator = ' ';

	/**
	 * Creates an integer array persistent method that uses an space for separatng the array values
	 */
	public PlainRealMatrixRead(){ this(' '); }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public PlainRealMatrixRead(char separator) { this.separator = separator; }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public PlainRealMatrixRead(int n, int m) { this(n,m,' '); }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public PlainRealMatrixRead(int n, int m, char separator) {
		this.separator = separator;
		this.read_dimension = false;
		this.n=n;
		this.m=m;
	}

	public void setReadInt(PlainIntRead ri ){ this.ri = ri; }
	public void setReadDouble( PlainRealRead rr ){ this.rr = rr; }
	
	/**
	 * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
	 * @param reader The reader object
	 * @throws IOException IOException
	 */
	public double[][] read(Backable<Integer> reader) throws IOException {
		if( read_dimension ){
			n = ri.read(reader);
			Read.readSeparator(reader, separator);
			m = ri.read(reader);
			Read.readSeparator(reader, separator);            
		}
		double[][] a = new double[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				a[i][j] = rr.read(reader);
		return a;
	}
	
	@Override
	public String toString(){ return "DoubleMatrixPlainRead"; }	
}