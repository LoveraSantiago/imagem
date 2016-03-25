package lovera.img.hough;

import java.awt.Point;
import java.awt.Rectangle;

class PreProcessamento {
	
	public Point moverPontoEmRelacaoOrigem(Rectangle area, Point pontoOriginal){
		int origemX = pontoOriginal.x - area.x;
		int origemY = pontoOriginal.y - area.y;
		
		return new Point(origemX, origemY);
	}

}
