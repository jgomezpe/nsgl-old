package nsgl.type.real.sparse;

import java.io.IOException;
import java.io.Writer;

import nsgl.service.io.Write;

public interface RealSparseWrite extends Write{
	public void write(RealSparse obj, Writer out) throws IOException; 
	default void write(Object obj, Writer out) throws IOException{ write((RealSparse)obj,out); } 
}