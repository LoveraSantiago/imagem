package lovera.img.hough;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import lovera.comuns.recursos.Regras;
import lovera.img.comum.Pixel;
import lovera.img.contratos.Executor;

class TransformadaDeHough implements Executor{
	
	private static final int GRAUS;;
		
	private static double[] arraySen;
	private static double[] arrayCos;
	
	private int[][] matrizVotos;
	private int eixoX;
	
	private BufferedImage img;

	private Line2D linhaHough;
	
	static{
		GRAUS = 360;
		
		arraySen = new double[GRAUS + 1];
		arrayCos = new double[GRAUS + 1];
		
		for(int i = 1; i <= GRAUS; i++){
			arraySen[i] = Math.sin(Math.toRadians(i));
			arrayCos[i] = Math.cos(Math.toRadians(i));					
		}
	}
	
	public TransformadaDeHough() {		
	}
	
	
	public TransformadaDeHough(BufferedImage imgRecortada, Point ponto) {		
		Regras.validarBufferedImgCinza(imgRecortada, this.getClass());	
		Regras.validarPontoDentroDaImg(imgRecortada, ponto, this.getClass());
		
		this.img = imgRecortada;
		
		int alturaMatriz = 4 * Math.max(this.img.getWidth(), this.img.getHeight());//o dobro para positivo e o dobro para negativo		
		this.eixoX = alturaMatriz / 2;
		this.matrizVotos = new int[GRAUS + 1][alturaMatriz];
	}

	@Override
	public TransformadaDeHough executar() {
		
		transformada();		
		return this;
	}
	
	private void transformada(){
		
		Raster raster = this.img.getRaster();
		
		for(int i = 0; i < this.img.getHeight(); i++)
			for(int j = 0; j < this.img.getWidth(); j++){
				
				int sample = raster.getSample(j, i, 0);
				if(sample == Pixel.PREENCHIDO.getValor()){
					
					int[] polaresDoPonto = calcularPolarDoPonto(j, i);
					inputarVotos(polaresDoPonto);
				}
			}
		
		Point polar = encontrarCoordenadaPolarMaisVotada();
		Line2D linha = polarParaLinha(polar);
		setLinhaHough(linha);
		
		this.img         = null;
		this.matrizVotos = null;
	}
	
	private int[] calcularPolarDoPonto(int x, int y){
		int[] polar = new int[GRAUS + 1];
		
		for(int i = 1; i <= GRAUS; i++)
			polar[i] =(int)(Math.round((x * arrayCos[i]) + (y * arraySen[i])));
		
		return polar;
	}
	
	private void inputarVotos(int[] polaresDoPonto){
			for(int i = 1; i <= GRAUS; i++){
				int ajustadoPEixoX = ajustarParaEixoX(polaresDoPonto[i]);
				this.matrizVotos[i][ajustadoPEixoX]++;
			}		
	}
	
	private int ajustarParaEixoX(int valorY){
		return valorY + this.eixoX;
	}
	
	private Point encontrarCoordenadaPolarMaisVotada(){
		int vetorMVot = 0;
		int anguloMVot = 0;
		
		int maisVotos = Integer.MIN_VALUE;
		
		for(int i = 1; i <= GRAUS; i++){
			for(int j = i; j < this.matrizVotos[i].length; j++){
				
				int voto = this.matrizVotos[i][j];
				if(voto > maisVotos){
					maisVotos = voto;
					anguloMVot = i; 
					vetorMVot = j;
				}
			}
		}
		vetorMVot = ajustarParaEixoOriginal(vetorMVot);
		return new Point(vetorMVot, anguloMVot);
	}
	
	private int ajustarParaEixoOriginal(int valorY){
		return valorY - this.eixoX;
	}
	
	private Line2D polarParaLinha(Point ponto){
		double pt1X = ponto.x * arraySen[ponto.y];
		double pt1Y = ponto.x * arrayCos[ponto.y];
		
		double pt2X = ponto.x / arrayCos[ponto.y];
		double pt2Y = 0;
		
		Point reta = EquacaoDaReta.calcularEquacaoDaReta(pt1X, pt1Y, pt2X, pt2Y);
		
		int x1 = 0;
		int y1 = (int) (Math.round(reta.y));//reta.y é o b 'intercepto de y'
		int x2 = (int) (Math.round(pt2X));
		int y2 = (int) (Math.round(pt2Y));
		
		return new Line2D.Double(x1, y1, x2, y2);
	}

	public Line2D getLinhaHough() {
		Regras.validarLinha(this.linhaHough, this.getClass());
		return linhaHough;
	}

	private void setLinhaHough(Line2D linhaHough) {
		this.linhaHough = linhaHough;
	}
}
