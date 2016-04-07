package lovera.img.hough;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.contratos.Executador;

public class TransformadaDeHoughTemp implements Executador{

	private static final int GRAUS;
	
	private static double[] arraySen;
	private static double[] arrayCos;
	
	private int[][] matrizVotos;
	private int eixoX;
	
	private List<Point2D> listaCentrosGravidades;
	
	static{
		GRAUS = 360;
		
		arraySen = new double[GRAUS + 1];
		arrayCos = new double[GRAUS + 1];
		
		for(int i = 1; i <= GRAUS; i++){
			arraySen[i] = Math.sin(Math.toRadians(i));
			arrayCos[i] = Math.cos(Math.toRadians(i));					
		}
	}
	
	public TransformadaDeHoughTemp(List<Point2D> listaCentrosDeGravidade, int imgLargura, int imgAltura) {
		Regras.validarListaCoordenadas(listaCentrosDeGravidade, this.getClass());
		
		this.listaCentrosGravidades = listaCentrosDeGravidade;
		
		int alturaMatriz = 4 * Math.max(imgLargura, imgAltura);//o dobro para positivo e o dobro para negativo		
		this.eixoX = alturaMatriz / 2;
		this.matrizVotos = new int[GRAUS + 1][alturaMatriz];
	}

	@Override
	public TransformadaDeHoughTemp executar() {
		transformada();
		return this;
	}

	private void transformada() {
		// TODO Auto-generated method stub
		
	}
}
