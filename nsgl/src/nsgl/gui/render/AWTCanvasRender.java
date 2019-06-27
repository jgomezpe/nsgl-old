package nsgl.gui.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import nsgl.gui.paint.AWTCanvas;
import nsgl.gui.paint.Canvas;
import nsgl.gui.paint.CanvasRender;
import nsgl.gui.paint.Drawable;
import nsgl.type.array.Vector;
import nsgl.type.collection.Mutable;
import nsgl.type.keymap.HashMap;
import nsgl.vc.Side;

public class AWTCanvasRender extends JPanel implements CanvasRender{
	protected Vector<Drawable> objects = new Vector<Drawable>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1691456845015325692L;

	protected AWTCanvas canvas = new AWTCanvas();
	protected String id;
	protected Side side;
	
	protected HashMap<String, Image> images = new HashMap<String,Image>();
	
	public AWTCanvasRender(){ this("",new Color(255,255,255)); }
	
	public AWTCanvasRender( String id ){ this(id,new Color(255,255,255)); }

	public AWTCanvasRender( String id, Color background_color ){
		this.id=id; 
		setBackground(background_color);
	}

	@Override
	public void render( Object obj ){
		add(obj);
		updateUI();
	}
	
	/**
	 * Paints the graphic component
	 * @param g Graphic component
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		canvas.setGraphics( g );
		render();
	}		

	@Override
	public void setSide(Side side){ this.side = side; }

	@Override
	public Side side(){ return side; }

	@Override
	public void setId(String id) { this.id = id; }
	
	@Override
	public String id(){ return id; }

	@Override
	public Canvas getCanvas(){ return canvas; }

	@Override
	public Mutable<Drawable> objects(){ return objects; }

	@Override
	public void setCanvas(Canvas canvas){}	
}