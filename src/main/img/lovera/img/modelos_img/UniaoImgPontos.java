package lovera.img.modelos_img;

import static lovera.img.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.ImgIO.gravarImg;
import static lovera.img.manipulacao.ManipulacaoImg.copiarImg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.contratos.UnidorImagens;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;

public final class UniaoImgPontos implements UnidorImagens{
	
	private final String nomeArquivo;
	
	private final List<Point> coordenadas;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	private String endImgSalva;
	
	public UniaoImgPontos(String nomeArquivo, Coordenadas coordenadas, BufferedImage img) {
		this.nomeArquivo = nomeArquivo;
		this.coordenadas = coordenadas.getCoordenadas();
		this.imgTemp = img;
	}
	
	/**
	 * Construtor para debug 
	 */
	public UniaoImgPontos(String nomeArquivo, List<Point> coordenadas, BufferedImage img) {
		this.nomeArquivo = nomeArquivo;
		this.coordenadas = coordenadas;
		this.imgTemp = img;
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		this.imgUniao = copiarImg(imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.red);
		
		for(Point ponto : this.coordenadas)	
			graphics.fillOval((int) ponto.getX(), (int) ponto.getY(), 2, 2);

		graphics.dispose();
		
		this.imgTemp = null;		
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgUniao, this);
		return this.imgUniao;
	}
	
	@Override
	public void abrir() {
		ImgIO.abrirImg(this.endImgSalva);
	}

	@Override
	public void gravar() {
		validarOperacaoExecutada(this.imgUniao, this);
		this.endImgSalva = gravarImg(this.imgUniao, Endereco.TESTES, this.nomeArquivo, TipoImagem.PNG);		
	}
}
