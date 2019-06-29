package nsgl.server;

import nsgl.type.json.JSON;

public class Response {
	public static final String _JSON = "json"; 
	public static final String TXT = "txt"; 
	public static final String BLOB = "blob";
	public static final String HEADER = "extra";
	
	protected String type;
	protected byte[] blob;
	protected String data;
	protected String header;
	
	public Response( byte[] blob ){
		type = BLOB;
		this.blob = blob;
	}

	public Response( String txt ){
		type = TXT;
		this.data = txt;
	}
	
	public Response( JSON json ){
		type = _JSON;
		this.data = json.toString();
	}
	
	public Response( byte[] blob, String header ){
		this( blob );
		this.header = header;
	}

	public Response( String txt, String header ){
		this( txt );
		this.header = header;
	}
	
	public Response( JSON json, String header ){
		this( json );
		this.header = header;
	}
	
	public byte[] blob(){ return blob; }
	
	public String data(){ return data; }
	
	public String type(){ return type; }
	public String header(){ return header; }
}