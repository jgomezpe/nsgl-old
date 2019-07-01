package nsgl.java.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import nsgl.exception.IO;
import nsgl.language.Token;
import nsgl.language.lexeme.IDLexeme;
import nsgl.service.io.FileResource;
import nsgl.type.array.ArrayUtil;
import nsgl.type.array.Vector;

public class Command {
	public static final String UPLOAD = "upload";
	protected String id;
	protected String method;
	protected Object[] args;
	
	public Command( String command ) throws Exception{
		IDLexeme id = new IDLexeme();
		Token t = id.token(command);
		if( t == null ) throw IO.exception(IO.UNEXPECTED, command, 0 );
		this.id = t.value();
		int n = this.id.length();
		command = command.substring(n);
		if( command.charAt(0)!='.') throw IO.exception(IO.UNEXPECTED, command.charAt(0), t.value() );
		n++;
		command = command.substring(1);
		t = id.token(command);
		if( t == null ) throw IO.exception(IO.UNEXPECTED, command, n );
		method = t.value();
		n += method.length();
		command = command.substring(method.length());
		if(command.charAt(0)!='(') throw IO.exception(IO.UNEXPECTED, command.charAt(0), n );
		int m = command.length()-1;
		if(command.charAt(m)!=')') throw IO.exception(IO.UNEXPECTED, command.charAt(m), n+m );
		command = "[" + command.substring(1, m-1) + ']';
		args = ArrayUtil.parse(command);
	}
	
	public Command( String id, String method, Object... args ) {
		this.id = id;
		this.method = method;
		this.args = args;
	}
	
	public Command( String id, String method, InputStream is ){
		this.id = id;
		if( method.startsWith(UPLOAD+':') ){
			this.method = UPLOAD;
			this.args = new Object[2];
			this.args[0] = method.substring(UPLOAD.length()+1);
			try { this.args[1] = FileResource.toByteArray(is); } catch (IOException e) { e.printStackTrace(); }
		}else{
			this.method = method;
			if( is!= null ) try{ this.args = args(FileResource.txt_read(is)); } catch (IOException e) { e.printStackTrace(); }
			else this.args = new Object[0];
		}
	}
	
	protected Object[] args( String arg ){
		StringBuilder sb;
		Vector<Object> parse = new Vector<Object>();
		int i=0;
		while( i<arg.length() ){
			switch( arg.charAt(i) ){
				case ',': case ' ': i++; break;
				case '"':
					sb = new StringBuilder();
					i++;
					while( arg.charAt(i) != '"' ){
						if( arg.charAt(i) == '\\' )	i++;
						sb.append(arg.charAt(i));
						i++;
					}
					parse.add( sb.toString() );
					i++;
				break;
				case '\'':
					i++;
					if( arg.charAt(i) == '\\' )	i++;
					parse.add(arg.charAt(i));
					i+=2;
				break;
				default:
					sb = new StringBuilder();
					while(i<arg.length() && arg.charAt(i)!=' ' && arg.charAt(i)!=',' ){ 
						sb.append(arg.charAt(i));
						i++; 
					}
					String number = sb.toString();
					try{ parse.add(Integer.parseInt(number)); }
					catch( NumberFormatException e ){ parse.add(Double.parseDouble(number)); }
			}
		}
		args = new Object[parse.size()];
		try{ for( int k=0; k<args.length; k++ ) args[k] = parse.get(k); }catch(Exception e){} 
		return args;
	}
	
	public String id(){ return id; }
	public String method(){ return method; }
	public Object[] args(){ return args; }
	
	public Object arg( int i ){ return args[i]; }
	public int arity(){ return args.length; }
	
	public Object run( Object obj  ) throws Exception{ return run(obj.getClass()); }
	
	public Object run( Class<?> cl ) throws Exception{
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[args.length];
		for( int k=0; k<types.length; k++ ) types[k] = args[k].getClass();
		Method m ;
		m = cl.getClass().getMethod(method, types);
		return m.invoke(cl, args);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append('.');
		sb.append(method);
		sb.append('(');
		String a = ArrayUtil.store(args);
		sb.append(a.substring(1,a.length()-1));
		sb.append(')');
		return sb.toString();
	}
}