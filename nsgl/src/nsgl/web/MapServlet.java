package nsgl.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nsgl.type.array.Vector;

public class MapServlet extends WebServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9076973270503422633L;

	public MapServlet(WebServer server) { super(server); }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Vector<String> map = server.map();
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
		for( String u:map ) sb.append(u);
		sb.append("</urlset>");
		System.out.println("[MapServlet]");
		System.out.println(sb.toString());
		response.getOutputStream().print(sb.toString());
	}
}