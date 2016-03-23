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
	
	private static final int GRAUS = 360;
	
	private static double[] arraySen;
	private static double[] arrayCos;
	
	private BufferedImage img;
	
	private Rectangle area;
	private Point ponto;
	
	private int[][] matrizVotos;
	
	private Line2D linhaHough;
	
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
		transformada();
		
		this.img         = null;
		this.area        = null;
		this.ponto       = null;
		this.matrizVotos = null;
		return this;
	}
	
	private void recalcularCentro(){
		int origemX = this.area.x - this.ponto.x;
		int origemY = this.area.y - this.ponto.y;
		this.ponto = new Point(origemX, origemY);
	}

	private void transformada(){
		recalcularCentro();
		
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
		Line2D linha = polarParaLinha(polar);
		linha = recalcularLinha(linha);
		setLinhaHough(linha);
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
