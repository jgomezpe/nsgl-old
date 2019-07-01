package nsgl.gui.paint;

public interface Drawable {
	PaintCommand draw();
	default void draw(Canvas canvas){
		canvas.command(draw());
	}
}