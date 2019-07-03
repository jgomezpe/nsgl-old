package nsgl.web.app;

import nsgl.app.QueueFrontEnd;

public class WebServerMainController extends WebServerController{
	public static final String SERVER = "server";
	public WebServerMainController() { super(SERVER); }
	
	public String pull(){ return ((QueueFrontEnd)front()).queue(); }
}