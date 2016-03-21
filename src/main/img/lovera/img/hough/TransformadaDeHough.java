package lovera.img.hough;

public class TransformadaDeHough {
	
	private static final int GRAUS = 360;
	
	private static double[] arraySen;
	private static double[] arrayCos;

	static{
		arraySen = new double[GRAUS];
		arrayCos = new double[GRAUS];
		
		for(int i = 1; i <= GRAUS; i++){
			arraySen[i] = Math.sin(i);
			arrayCos[i] = Math.cos(i);					
		}
	}
	
	public TransformadaDeHough() {
		
	}
}
