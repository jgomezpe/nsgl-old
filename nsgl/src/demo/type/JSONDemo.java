package demo.type;

import nsgl.exception.IO;
import nsgl.exception.ProcessException;
import nsgl.exception.ProcessExceptionWithJSON;
import nsgl.type.json.JSON;
import nsgl.type.json.parser.JSONParser;

public class JSONDemo {
	public static JSON exception_manager(){
		JSON json = new JSON();
		json.set(IO.IOEXCEPTION,IO.IOEXCEPTION);
		json.set(JSONParser.JSONEXCEPTION,JSONParser.JSONEXCEPTION);
		json.set(JSONParser.NOVALID,"%s definition no valid. %s ");
		json.set(IO.NOFOUND,"%s no found after %s");
		json.set(IO.NOSTRING,"Not valid key starting with %s, must start with \"");
		json.set(IO.NOESCAPE,"Invalid escape code %s at %d");
		json.set(IO.NOCHAR,"Not valid character %");
		json.set(JSONParser.WORD,"Reserved word");
		json.set(JSONParser.OBJECT,"Object");
		json.set(JSONParser.OPTION," one of the following symbols");
		json.set(IO.UNEXPECTED,"Unexpected symbol %s at %d");
		json.set(IO.EOI,"Unexpected end of input");
		return json;
	}

	public static String[] jsons = new String[]{
			"{\"code\",123.89e-23, \"array_\\uAA00\":[]}",
			"{\"code\":123.89e-23:x, \"array_\\uAA00\":[]}",
			"{code:123.89e-23, \"array_\\uAA00\":[]}",
			"{\"code\":123.89e--23, \"array_\\uAA00\":[]}",
			"{\"code\":123.89e-23, \"array_\\uAA00\":[]",
			"{\"code\":123.89e-23, array:[]}",
			"{\"code\":123.89e-23, \"array_\\uAA00\":[]}"
	};
	
	public static void main( String[] args ){
		ProcessException pexception = new ProcessExceptionWithJSON(exception_manager());
		for( int i=0; i<jsons.length; i++ ){
			System.out.println("***********************"+i+"*******************");
			String code = jsons[i];
			try{ 
				JSON json = new JSON(code);
				for( String k : json.keys() ) System.out.println(k + ":" + json.get(k));
				System.out.println(json);
			}catch( Exception e ){
				System.err.println(pexception.apply(e.getMessage()));
			}
		}	
	}
}