package lovera.img.hough;

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

}
