package nsgl.app.server;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nsgl.app.Controller;
import nsgl.app.js.JSFrontEnd;
import nsgl.java.reflect.Command;
import nsgl.service.io.FileResource;
import nsgl.type.array.Vector;

public class VCManager extends JSFrontEnd {
	public static final String timer="unalcol.timer";
	public static final String pull="unalcol.pull()";

	protected PullServerController serverc = null; 
	protected Vector<String> commands_queue = new Vector<String>();
	
	@Override
	public void execute(String command){ commands_queue.add(command); }
	
	public String queue(){
		if( commands_queue.size() == 0 ) return "";
		StringBuilder sb = new StringBuilder();
		for(String c:commands_queue ){
			sb.append(c);
			sb.append('\n');
		}
		commands_queue.clear();
		return sb.toString();
	}
	
	public String javacall( Command command ){
		String id = command.id();
		String method = command.method();
		Object[] args = command.args();
		
		if( id.equals(PullServerController.SERVER) ){
			if(method.equals("setParams")){
				try{
					Page page = new Page((String)command.args()[0]);
					System.out.println("[Servlet]"+page.server());
					System.out.println("[Servlet]xml/"+page.packPath()+"main.xml");
					String txt = FileResource.txt("xml/"+page.packPath()+"main.xml");
					System.out.println("[Servlet]"+txt);
					return "unalcol.plugins.loadTxt("+Util.store(txt)+")";
				}catch(Exception e){
					return "";
				}
			}
		}

		if( serverc==null ) serverc = new PullServerController(backend());
		if( id.equals(serverc.id())) return serverc.pull();
		Controller c = backend().controller(id);
		if( c== null ) return null;
		
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[args.length];
		for( int k=0; k<types.length; k++ ) types[k] = args[k].getClass();
		try{
			Object obj;
			Method m ;
			m = c.getClass().getMethod(method, types);
			obj = m.invoke(c, args);
			if( obj == null ) return pull;
			return obj.toString();	
		}catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			e.printStackTrace();
		}	
		return null;
	}

	public String timer( Controller e, String type, int delay ){
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		sb.append("('");
		sb.append(e.id().replace(',', 'X'));
		sb.append("',");
		sb.append(delay);
		sb.append(')');
		return sb.toString();
	} 
	
	@Override
	public String addTimer( Controller c, int delay ){ return timer(c, timer+".add", delay); }
	
	@Override
	public String delTimer( Controller c, int delay ){ 
		String cmd = timer(c, timer+".del", delay);
		execute(cmd);
		return cmd;
	}		
}