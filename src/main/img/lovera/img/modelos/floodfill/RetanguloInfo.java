package lovera.img.modelos.floodfill;

import java.awt.Point;
import java.awt.Rectangle;

class RetanguloInfo {
	
	public final Point p1;
	public final Point p2;
	
	public final Rectangle area;

	public RetanguloInfo(Point p1, Point p2, Rectangle area) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.area = area;
	}
}
