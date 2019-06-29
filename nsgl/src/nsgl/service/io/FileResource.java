package nsgl.service.io;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

import nsgl.exception.IO;

public class FileResource {
	public static final String CFG = "cfg/";
	public static final String resources="resources/";
	public static final String images="imgs/";

	public static byte[] toByteArray( InputStream is ) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int MAX = 1000000;
		int k=0;
		byte[] buffer = new byte[MAX];  
		do{
			k=is.read(buffer);
			if( k>0 ) os.write(buffer, 0, k);
		}while(k>0);
		return os.toByteArray();
	}
		
	public static InputStream get(ClassLoader loader, String name)  throws IOException{
		try{ if( name.startsWith("http://") || name.startsWith("https://")) return new URL(name).openStream(); }catch(Exception ex){}				
		try{ return new FileInputStream(name); }catch( Exception e ){}
		try{ return loader.getResourceAsStream(name); }catch( Exception e ){}
		throw IO.exception(IO.NOFOUND,name);
	}
	
	public static InputStream get(String name) throws IOException{ return get(FileResource.class.getClassLoader(),name); }
	
	public static Image image(ClassLoader loader, String name) throws IOException{ return ImageIO.read(get(loader,name)); }

	public static Image image(String name) throws IOException{ return image(FileResource.class.getClassLoader(), name ); }
	
	public static String txt_read( InputStream is ) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = reader.readLine();
		while( line != null ){ 
			sb.append(line);
			line = reader.readLine();
			if( line != null ) sb.append('\n');
		}
		return sb.toString();
	}
	
	public static String txt(ClassLoader loader, String name ) throws IOException{ return txt_read( get(loader,name) );	}
	public static String txt( String name ) throws IOException{ return txt(FileResource.class.getClassLoader(),name); }	
}