package lovera.img.graos;

import java.awt.Point;
import java.awt.Rectangle;

public class BlocoComPonto extends Area{
	
	private final Point ponto;	
	
	public BlocoComPonto(Rectangle area, Point ponto) {
		super(area);
		this.ponto = ponto;
	}
	
	public Point getPonto() {
		return ponto;
	}
}
