package lovera.img.hough;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.comum.Pixel;
import lovera.img.contratos.Executor;
import lovera.img.graos.BlocoComPonto;

class TransformadaDeHough implements Executor{
	
	private static final int GRAUS = 360;
	
	private static double[] arraySen;
	private static double[] arrayCos;
	
	private BufferedImage img;
	
	private Rectangle area;
	private Point ponto;
	
	private List<double[]> listaPolarDosPontos;	
	
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
		this.area = blocoComPonto.getArea();
		this.ponto = blocoComPonto.getPonto();
		
		this.listaPolarDosPontos = new ArrayList<>();
	}

	@Override
	public TransformadaDeHough executar() {
		recalcularCentro();
		transformada();
		return this;
	}
	
	private void recalcularCentro(){
		int origemX = this.area.x - this.ponto.x;
		int origemY = this.area.y - this.ponto.y;
		this.ponto = new Point(origemX, origemY);
	}

	private void transformada(){		
		Raster raster = this.img.getRaster();
		
		for(int i = 0; i < this.img.getHeight(); i++)
			for(int j = 0; j < this.img.getWidth(); j++){
				
				int sample = raster.getSample(j, i, 0);
				if(sample == Pixel.PREENCHIDO.getValor()){
					
					Point ponto = recalcularPonto(j, i);
					double[] polarDoPonto = calcularPolarDoPonto(ponto);
					this.listaPolarDosPontos.add(polarDoPonto);
				}
			}
	}
	
	private Point recalcularPonto(int x, int y){
		int novoX = this.ponto.x - x;
		int novoY = this.ponto.y - y;
		
		return new Point(novoX, novoY);
	}
	
	private double[] calcularPolarDoPonto(Point ponto){
		double[] polar = new double[GRAUS];
		
		for(int i = 1; i <= GRAUS; i++)
			polar[i] = (ponto.x * arrayCos[i]) + (ponto.y * arraySen[i]);
		
		return polar;
	}
	
	private void realizarVotacao(){
		int[][] matrizVotos = new int[GRAUS][Math.max(this.img.getWidth(), this.img.getHeight())];
		this.listaPolarDosPontos.forEach((array) -> {
			
			for(int i = 1; i <= GRAUS; i++){
				matrizVotos[i][(int) Math.round(array[i])]++;
			}
		});
	}
	
	private int[][] inputarVotos(){
		int[][] matrizVotos = new int[GRAUS][Math.max(this.img.getWidth(), this.img.getHeight())];
		this.listaPolarDosPontos.forEach((array) -> {
			
			for(int i = 1; i <= GRAUS; i++){
				matrizVotos[i][(int) Math.round(array[i])]++;
			}
		});
		return matrizVotos;
	}
}
