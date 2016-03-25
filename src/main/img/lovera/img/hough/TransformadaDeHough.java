package lovera.img.hough;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import lovera.comuns.recursos.Regras;
import lovera.img.comum.Pixel;
import lovera.img.contratos.Executor;
import lovera.img.graos.BlocoComPonto;

class TransformadaDeHough implements Executor{
	
	private static final int GRAUS;;
		
	private static double[] arraySen;
	private static double[] arrayCos;
	
	private int[][] matrizVotos;
	private int eixoX;
	
	private BufferedImage img;
	
	private Rectangle area;
	private Point ponto;	
	
	private Line2D linhaHough;
	
	static{
		GRAUS = 360;
		
		arraySen = new double[GRAUS + 1];
		arrayCos = new double[GRAUS + 1];
		
		for(int i = 1; i <= GRAUS; i++){
			arraySen[i] = Math.sin(i);
			arrayCos[i] = Math.cos(i);					
		}
	}
	
	public TransformadaDeHough() {		
	}
	
	public TransformadaDeHough(BufferedImage imgRecortada, BlocoComPonto blocoComPonto) {
		Regras.validarBufferedImgCinza(imgRecortada, this.getClass());		
		
		this.img = imgRecortada;
		this.area = blocoComPonto.getArea();
		this.ponto = blocoComPonto.getPonto();
		
		this.eixoX = Math.max(this.img.getWidth(), this.img.getHeight());
		int alturaMatriz = 2 * eixoX;
		this.matrizVotos = new int[GRAUS + 1][alturaMatriz];
	}

	@Override
	public TransformadaDeHough executar() {
		transformada();
		
		this.img         = null;
		this.area        = null;
		this.ponto       = null;
		this.matrizVotos = null;
		return this;
	}
	
	private void recalcularCentro(){
		int origemX = this.ponto.x - this.area.x;
		int origemY = this.ponto.y - this.area.y;
		this.ponto = new Point(origemX, origemY);
	}

	int contadorA = 0;
	private void transformada(){
		recalcularCentro();
		
		Raster raster = this.img.getRaster();
		
		for(int i = 0; i < this.img.getHeight(); i++)
			for(int j = 0; j < this.img.getWidth(); j++){
				
				int sample = raster.getSample(j, i, 0);
				if(sample == Pixel.PREENCHIDO.getValor()){
					
//					DebugImgModelo.debugarImg(img, "debug", true);
//					System.out.println("Chamado dentro do if " + contadorA++ + " vezes.");
					
					Point ponto = recalcularPontoParaOrigem(j, i);
					int[] polaresDoPonto = calcularPolarDoPonto(ponto);
					inputarVotos(polaresDoPonto);
				}
			}
		
		Point polar = coordenadaPolarMaisVotada();
		Line2D linha = polarParaLinha(polar);
		linha = recalcularLinha(linha);
		setLinhaHough(linha);
	}
	
	private Point recalcularPontoParaOrigem(int x, int y){
		int novoX = x - this.ponto.x;
		int novoY = y - this.ponto.y;
		
		return new Point(novoX, novoY);
	}
	
	private int[] calcularPolarDoPonto(Point ponto){
		int[] polar = new int[GRAUS + 1];
		
		for(int i = 1; i <= GRAUS; i++)
			polar[i] =(int)(Math.round((ponto.x * arrayCos[i]) + (ponto.y * arraySen[i])));
		
		return polar;
	}
	int contador = 0;
	private void inputarVotos(int[] polaresDoPonto){
//			System.out.println("Chamado inputar votos " + contador++ + " vezes.");
			for(int i = 1; i <= GRAUS; i++){
				int ajustadoPEixoX = ajustarParaEixoX(polaresDoPonto[i]);
				this.matrizVotos[i][ajustadoPEixoX]++;
			}		
	}
	
	private int ajustarParaEixoX(int valorY){
		return valorY + this.eixoX;
	}
	
	int contadorB = 0;
	int contadorC = 0;
	private Point coordenadaPolarMaisVotada(){
		int vetorMVot = 0;
		int anguloMVot = 0;
		
		int maisVotos = Integer.MIN_VALUE;
		
		for(int i = 1; i <= GRAUS; i++){
//			System.err.println("matrizVotos graus " + contadorB++);
			contadorC = 0;
			for(int j = i; j < this.matrizVotos[i].length; j++){
				
//				System.out.println("matrizVotos vetores " + contadorC++);
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
		
		double pt2x = ponto.x / arrayCos[ponto.y];
		double pt2Y = 0;
		
		double coefAngular = (pt2Y - pt1X) / (pt1Y - pt1X);
		double b = -(coefAngular * pt2x);
		
		int x1 = 0;
		int y1 = (int) (Math.round(b));
		int x2 = (int) (Math.round(pt2x));
		int y2 = (int) (Math.round(pt2Y));
		
		return new Line2D.Double(x1, y1, x2, y2);
	}
	
	private Line2D recalcularLinha(Line2D linha){
		int x1 = (int)(linha.getX1() + area.x);
		int y1 = (int)(linha.getY1() + area.y);
		int x2 = (int)(linha.getX2() + area.x);
		int y2 = (int)(linha.getY2() + area.y);
		
		x1 = x1 < area.x ? area.x : x1;
		y1 = y1 < area.y ? area.y : y1;
		x2 = x2 > (area.x + area.width ) ? area.x + area.width : x2;
		y2 = y2 > (area.y + area.height) ? area.x + area.width : y2;
		
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
