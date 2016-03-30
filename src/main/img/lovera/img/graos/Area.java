package lovera.img.graos;

import java.awt.Rectangle;

/**
 * Objeto abstrato que serve como base para diversos momentos pela qual o 
 * objeto Rectangle passa.  
 * @author Lovera
 * @since 30/03/2016
 */
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
