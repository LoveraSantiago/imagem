package lovera.img.hough;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
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
	
	private int[][] matrizVotos;
	
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
		this.matrizVotos = new int[GRAUS][Math.max(this.img.getWidth(), this.img.getHeight())];
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
					
					Point ponto = recalcularPontoParaOrigem(j, i);
					int[] polaresDoPonto = calcularPolarDoPonto(ponto);
					inputarVotos(polaresDoPonto);
				}
			}
		
		Point polar = coordenadaPolarMaisVotada();
	}
	
	private Point recalcularPontoParaOrigem(int x, int y){
		int novoX = this.ponto.x - x;
		int novoY = this.ponto.y - y;
		
		return new Point(novoX, novoY);
	}
	
	private int[] calcularPolarDoPonto(Point ponto){
		int[] polar = new int[GRAUS];
		
		for(int i = 1; i <= GRAUS; i++)
			polar[i] =(int)(Math.round((ponto.x * arrayCos[i]) + (ponto.y * arraySen[i])));
		
		return polar;
	}
	
	private void inputarVotos(int[] polaresDoPonto){
		
			for(int i = 1; i <= GRAUS; i++){
				this.matrizVotos[i][(polaresDoPonto[i])]++;
			}		
	}
	
	private Point coordenadaPolarMaisVotada(){
		int vetorMVot = 0;
		int anguloMVot = 0;
		
		int maisVotos = Integer.MIN_VALUE;
		
		for(int i = 1; i <= GRAUS; i++)
			for(int j = i; j <= this.matrizVotos[i].length; j++){
				
				int voto = this.matrizVotos[i][j];
				if(voto > maisVotos){
					maisVotos = voto;
					anguloMVot = i; 
					vetorMVot = j;
				}
			}
		
		return new Point(vetorMVot, anguloMVot);
	}
	
	private Line2D polarParaLinha(Point ponto){
		double pt1X = ponto.x * arraySen[ponto.y];
		double pt1Y = ponto.x * arrayCos[ponto.y];
		
		double pt2x = Math.round(ponto.x / arrayCos[ponto.y]);
		double pt2Y = 0;
		
	}
}
