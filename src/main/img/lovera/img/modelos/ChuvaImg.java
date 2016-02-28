package lovera.img.modelos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.comum.Regras;
import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.manipulacao.ImgIO;

public class ChuvaImg implements ImgTransformavel, Gravavel, Coordenadas{
	
	private static final int VAZIO      = 0;
	private static final int PREENCHIDO = 255;
	
	private BufferedImage imgTemp;
	private BufferedImage imgChuva;
	
	private List<Point> coordenadas;
	
	public ChuvaImg(LaplaceImg laplace) {
		Regras.validarBufferedImgCinza(laplace.getImgTransformada());
		
		this.imgTemp = laplace.getImgTransformada();
	}

	@Override
	public void executarTransformacao() {
		carregarCoordenadas();
		desenharImg();
		
		this.imgTemp = null;
	}
	
	private void carregarCoordenadas(){
		this.coordenadas = new ArrayList<>();
		
		Raster raster = this.imgTemp.getRaster();
		
		for(int j = 0; j < this.imgTemp.getWidth(); j++)
			for(int i = 0; i < this.imgTemp.getHeight(); i++)
				if(raster.getSample(j, i, 0) != VAZIO){
					this.coordenadas.add(new Point(j, i));
					break;
				}		
	}
	
	private void desenharImg(){
		this.imgChuva = new BufferedImage(this.imgTemp.getWidth(), this.imgTemp.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgChuva.createGraphics();
		graphics.setColor(Color.red);
		
		for(Point ponto : this.coordenadas)
			graphics.fillOval((int) ponto.getX(), (int) ponto.getY(), 2, 2);
		
		graphics.dispose();
	}

	@Override
	public BufferedImage getImgTransformada() {
		Regras.validarOperacaoExecutada(this.imgChuva, this);
		return this.imgChuva;
	}

	@Override
	public List<Point> getCoordenadas() {
		Regras.validarListaCoordenadas(this.coordenadas);
		return this.coordenadas;
	}

	@Override
	public void gravar() {
		Regras.validarOperacaoExecutada(this.imgChuva, this);
		ImgIO.gravarImg(this.imgChuva, Endereco.TESTES, "redacaoChuva", TipoImagem.PNG);
	}	
}
