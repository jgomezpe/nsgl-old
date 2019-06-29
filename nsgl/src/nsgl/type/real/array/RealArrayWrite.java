package nsgl.type.real.array;

import java.io.IOException;
import java.io.Writer;

import nsgl.service.io.Write;

public interface RealArrayWrite extends Write{
	public void write(double[] obj, Writer out) throws IOException; 
	default void write(Object obj, Writer out) throws IOException{ write((double[])obj,out); } 
}