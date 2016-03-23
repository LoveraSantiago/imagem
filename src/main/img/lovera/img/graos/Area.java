package lovera.img.graos;

import java.awt.Rectangle;

abstract class Area {
	
	protected final Rectangle area;	

	public Area(Rectangle area) {
		super();
		this.area = area;
	}

	public Rectangle getArea() {
		return area;
	}
}
