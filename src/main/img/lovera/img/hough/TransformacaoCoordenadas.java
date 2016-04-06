package lovera.img.hough;

import java.awt.geom.Point2D;

public class TransformacaoCoordenadas {
	
	public static Point2D polarParaCartesiano(CoordenadaPolarTemp polar){
		
		double x = (Math.cos(Math.toRadians(polar.getAngulo()))) * polar.getRaio();
		double y = (Math.sin(Math.toRadians(polar.getAngulo()))) * polar.getRaio();

		return new Point2D.Double(x, y);
	}
	
	public static CoordenadaPolarTemp cartesianoParaPolar(Point2D ponto){
		double raio = Math.sqrt((ponto.getX() * ponto.getX()) + (ponto.getY() * ponto.getY()));
		
		double anguloRadiano = Math.acos(ponto.getX() / raio);		
		int angulo = (int) Math.round(Math.toDegrees(anguloRadiano));
		
		return new CoordenadaPolarTemp(raio, angulo);
		
	}

}
