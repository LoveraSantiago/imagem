package lovera.img.hough;

import java.awt.Point;
import java.awt.geom.Line2D;

class EquacaoDaReta {	
		
	public static Point calcularEquacaoDaReta(double p1x, double p1y, double p2x, double p2y){
		
		double coefAngular = (p2y - p1y) / (p2x - p1x);
		double b = p2y -(coefAngular * p2x);		
		return new Point((int) coefAngular, (int) b);
	}
	
	public static Point calcularEquacaoDaReta(Line2D linha){
		
		return calcularEquacaoDaReta(linha.getX1(), linha.getY1(), linha.getX2(), linha.getY2());
	}
}
