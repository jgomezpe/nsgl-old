package nsgl.web;

import nsgl.type.json.JSON;

public class WebResponse {
	public static final String _JSON = "json"; 
	public static final String TXT = "txt"; 
	public static final String BLOB = "blob";
	public static final String HEADER = "extra";
	
	protected String type;
	protected byte[] blob;
	protected String data;
	protected String header;
	
	public WebResponse( byte[] blob ){
		type = BLOB;
		this.blob = blob;
	}

	public WebResponse( String txt ){
		type = TXT;
		this.data = txt;
	}
	
	public WebResponse( JSON json ){
		type = _JSON;
		this.data = json.toString();
	}
	
	public WebResponse( byte[] blob, String header ){
		this( blob );
		this.header = header;
	}

	public WebResponse( String txt, String header ){
		this( txt );
		this.header = header;
	}
	
	public WebResponse( JSON json, String header ){
		this( json );
		this.header = header;
	}
	
	public byte[] blob(){ return blob; }
	
	public String data(){ return data; }
	
	public String type(){ return type; }
	public String header(){ return header; }
}