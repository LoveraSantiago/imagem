package lovera.img.hough;

import java.awt.Point;
import java.awt.geom.Line2D;

class FactoryEquacaoDaReta {
	
	public static EquacaoDaReta factory_EqDaReta(double p1x, double p1y, double p2x, double p2y){
		
		double coefAngular = (p2y - p1y) / (p2x - p1x);
		double b = p2y -(coefAngular * p2x);		
		return new EquacaoDaReta(coefAngular, b);
	}
	
	public static EquacaoDaReta factory_EqDaReta(Line2D linha){
		
		return factory_EqDaReta(linha.getX1(), linha.getY1(), linha.getX2(), linha.getY2());
	}
	
	public static EquacaoDaReta factory_EqDaRetaOrtogonal(EquacaoDaReta reta, Point pontoDaReta){
		double coefAngular = -(1 / reta.getCoefAngular());
		double b = pontoDaReta.y / coefAngular;
		
		return new EquacaoDaReta(coefAngular, b);
		
	}
	
	public static void main(String[] args) {
		System.out.println(FactoryEquacaoDaReta.factory_EqDaReta(0, 0, 40, 30));
	}

}
