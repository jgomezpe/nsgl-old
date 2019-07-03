package nsgl.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6212331970764438847L;
	protected WebServer server;
	public WebServlet( WebServer server ){ this.server = server; }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ doPost(request, response); }
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try{
			WebCommand cmd = new WebCommand(request.getHeader("object"), request.getHeader("method"), request.getInputStream());
			cmd.setClient( new WebClient(request.getRemoteAddr(), request.getHeader("navigator"), request.getHeader("platform") ) );
			WebResponse resp = server.process(cmd);
			if( resp == null ){
				response.getOutputStream().print("");
			}else{
				response.setContentType(resp.type());
				if( resp.header() != null ) response.setHeader(WebResponse.HEADER, resp.header());
				if( resp.type().equals(WebResponse.BLOB) ) response.getOutputStream().write(resp.blob());
				else response.getOutputStream().print(resp.data());
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getOutputStream().print(e.getMessage());
		}
	}
}