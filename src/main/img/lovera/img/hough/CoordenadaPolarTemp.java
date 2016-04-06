package lovera.img.hough;

public class CoordenadaPolarTemp {
	
	private final double raio;
	private final int  angulo;
	
	public CoordenadaPolarTemp(double raio, int angulo) {
		super();
		this.raio = raio;
		this.angulo = angulo;
	}

	public double getRaio() {
		return raio;
	}

	public int getAngulo() {
		return angulo;
	}
	
	@Override
	public String toString() {		
		return "P(" + raio + ", " + angulo + ")";
	}
}
