package nsgl.js.fx;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;
import nsgl.app.BackEnd;
import nsgl.app.Component;
import nsgl.app.QueueFrontEnd;
import nsgl.java.reflect.Command;

public class FXManager extends QueueFrontEnd{
	protected FXPanel panel;
	
	protected WebEngine webEngine;

	protected boolean ready = false;

	public FXManager( String url ){		
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
		panel = new FXPanel(url, this);

		// Run this later:
        SwingUtilities.invokeLater(new Runnable() {  
            @Override
            public void run() {  
                final JFrame frame = new JFrame();  
                 
                frame.getContentPane().add(panel);  
        		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        		int width = (int)screenSize.getWidth();
        		int height = (int)screenSize.getHeight();
        		frame.setSize(new Dimension(width*4/5, height*4/5));                 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                frame.setVisible(true);  
            }  
        });     		
	}
	
	public void setEngine( WebEngine webEngine ){ this.webEngine = webEngine; }

	protected void execute_late(Command command ){
		FXDeamon deamon = new FXDeamon(this, command);
		Platform.runLater( deamon );
	}

	protected void synchronize_back(){
		BackEnd backend = backend();
		if( backend != null && backend.hasChanged() && webEngine!=null ){
			JSObject win = (JSObject)webEngine.executeScript("window");
			for( Component c : backend ){
				String[] ids = c.ids();
				for( String id:ids ) win.setMember(id, c);
			}
			backend.synchronize();
		}
	}
	
	@Override
	public Object execute( Command command ){
		synchronize_back();
		if(ready) try{ webEngine.executeScript(command.toString()); }catch( IllegalStateException e ){ execute_late(command); }
		else execute_late(command); 
		return null;
	}
	
	public void ready(){
		this.ready = true; 
		synchronize_back();
	}
}