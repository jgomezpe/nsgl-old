package nsgl.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6212331970764438847L;
	protected Server server;
	public Servlet( Server server ){ this.server = server; }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ doPost(request, response); }
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try{
			Command cmd = new Command(request.getHeader("object"), request.getHeader("method"), request.getInputStream());
			cmd.session(request.getRemoteAddr(), request.getHeader("navigator"), request.getHeader("platform"));
			Response resp = server.process(cmd);
			if( resp == null ){
				response.getOutputStream().print("");
			}else{
				response.setContentType(resp.type());
				if( resp.header() != null ) response.setHeader(Response.HEADER, resp.header());
				if( resp.type().equals(Response.BLOB) ) response.getOutputStream().write(resp.blob());
				else response.getOutputStream().print(resp.data());
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getOutputStream().print(e.getMessage());
		}
	}
}