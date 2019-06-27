package nsgl.vc.js;

import java.net.URL;
import java.net.URLClassLoader;

import nsgl.vc.BackEnd;
import nsgl.vc.FrontEnd;
import nsgl.vc.VCModel;
import nsgl.vc.js.fx.FXManager;
import nsgl.vc.js.server.VCManager;

public class JSModel extends VCModel{
	protected JSModelLoader modelLoader=null;
	
	public JSModel( String url ){
		loader(url);
		init( backend(url), frontend(url) ); 
	}
	
	protected void loader( String url ){
		try {
			Page page = new Page(url);
			@SuppressWarnings("resource")
			URLClassLoader loader =  new URLClassLoader(new URL[]{new URL(page.server()+"plugins/"+page.pack()+page.model())}, JSModel.class.getClassLoader());
			Class<?> cl = loader.loadClass("js.ModelLoader");
			modelLoader = (JSModelLoader)cl.newInstance();
		} catch(Exception e) { e.printStackTrace(); };
	}
	
	public BackEnd backend( String url ){ return modelLoader.backend(url); }
	
	public FrontEnd frontend( String url ){
		JSFrontEnd front = null;
		try {
			Page page = new Page(url);
			String mode=page.mode();
			KeyMap<String, Component> comps = modelLoader.frontend(url); 
			if( mode.equals("fx") ) front = new FXManager(url); 
			if( mode.equals("servlet") ) front = new VCManager(); 
			if( front != null )	front.init(comps); 
		} catch (Exception e) { e.printStackTrace(); }
		return front;
	}	
}