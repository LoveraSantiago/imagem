package lovera.img.graos;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class BlocoComLinha extends Area{
	
	private final Line2D linha;
	
	public BlocoComLinha(Rectangle area, Line2D linha) {
		super(area);
		this.linha = linha;
	}

	public Line2D getLinha() {
		return linha;
	}
}
