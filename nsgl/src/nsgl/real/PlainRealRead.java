/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nsgl.real;
import java.io.IOException;

import nsgl.integer.PlainIntRead;
import nsgl.iterator.Backable;

/**
 *
 * @author jgomez
 */
public class PlainRealRead implements RealRead{

	public static String number(Backable<Integer> reader) throws IOException{
		StringBuilder sb = new StringBuilder();
		PlainIntRead.removeSpaces(reader);
		sb.append( PlainIntRead.number(reader) );
		if( !reader.hasNext() ) return sb.toString();
		int c = reader.next();
		if( c == '.' ){
			sb.append((char)c);
			sb.append( PlainIntRead.number(reader) );
			if( !reader.hasNext() ) return sb.toString();
			c = reader.next();
		}
		if( c!='e' && c!='E' ){
			reader.back();
			return sb.toString();
		}
		sb.append((char)c);
		sb.append( PlainIntRead.number(reader) );
		return sb.toString();
    }

	public Double read(Backable<Integer> reader) throws IOException{ return Double.parseDouble(number(reader)); }
	
	@Override
	public String toString(){ return "DoublePlainRead"; }	
}