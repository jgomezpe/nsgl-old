package nsgl.vc.js;
import nsgl.type.string.StringUtil;
import nsgl.vc.View;

public interface JSView extends View{
	default void execute( String command, Object... args ){
		StringBuilder sb = new StringBuilder();
		sb.append(command);
		sb.append('(');
		sb.append(StringUtil.store(jsId()));
		for( Object obj : args ){
			sb.append(',');
			if( obj instanceof String ){
				sb.append(StringUtil.store((String)obj));
			}else sb.append(obj.toString());
		}
		sb.append(')' );
		execute(sb.toString());
	}
	default void execute( String js_command ){ ((JSFrontEnd)front()).execute(js_command); }
	
	default String jsId(){ return id(); }
}