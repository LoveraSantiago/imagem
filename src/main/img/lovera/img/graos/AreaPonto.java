package lovera.img.graos;

import java.awt.Point;
import java.awt.Rectangle;

public class AreaPonto {
	
	private final Rectangle area;
	private final Point ponto;	
	
	public AreaPonto(Rectangle area, Point ponto) {
		super();
		this.area = area;
		this.ponto = ponto;
	}
	
	public Rectangle getArea() {
		return area;
	}
	public Point getPonto() {
		return ponto;
	}
	
	

}
