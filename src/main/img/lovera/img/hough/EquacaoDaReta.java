package lovera.img.hough;

class EquacaoDaReta {	
	
	private final double coefAngular;
	private final double intercepto;
	
	public EquacaoDaReta(double coefAngular, double intercepto) {
		super();
		this.coefAngular = coefAngular;
		this.intercepto = intercepto;
	}
	
	@Override
	public String toString() {		
		return "Eq. da Reta y = " + coefAngular + "x + " + intercepto + ".";
	}

	public double getCoefAngular() {
		return coefAngular;
	}

	public double getIntercepto() {
		return intercepto;
	}		
}
