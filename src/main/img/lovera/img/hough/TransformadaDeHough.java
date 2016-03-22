package lovera.img.hough;

import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.contratos.Executor;
import lovera.img.graos.BlocoComPonto;
import lovera.img.modelos.img.BinarizacaoImg;

class TransformadaDeHough implements Executor{
	
	private static final int GRAUS = 360;
	
	private static double[] arraySen;
	private static double[] arrayCos;
	
	private BufferedImage img;
	
	private BlocoComPonto blocoComPonto;

	static{
		arraySen = new double[GRAUS];
		arrayCos = new double[GRAUS];
		
		for(int i = 1; i <= GRAUS; i++){
			arraySen[i] = Math.sin(i);
			arrayCos[i] = Math.cos(i);					
		}
	}
	
	public TransformadaDeHough(BufferedImage imgRecortada, BlocoComPonto blocoComPonto) {
		Regras.validarBufferedImgCinza(imgRecortada, this.getClass());		
		
		this.img = imgRecortada;
		this.blocoComPonto = blocoComPonto;
	}

	@Override
	public TransformadaDeHough executar() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
}
