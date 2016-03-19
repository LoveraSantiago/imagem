package lovera.img.modelos.uniao;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.ManipulacaoImg.copiarImg;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.List;

import lovera.comuns.contratos.CoordenadasPonto;
import lovera.img.comum.Pixel;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.contratos.UnidorImagens;

/**
 * Classe que desenha pontos de uma lista de pontos em uma imagem.
 * @author Lovera
 * @since 12/03/2016
 */
public final class UniaoImgPontos implements UnidorImagens{
	
	private final String nomeArquivo;
	
	private final List<Point> coordenadas;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	private String endImgSalva;
	
	public UniaoImgPontos(String nomeArquivo, CoordenadasPonto coordenadas, BufferedImage img) {
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
		
		WritableRaster wRaster = this.imgUniao.getRaster(); 
		
		this.coordenadas.forEach((ponto) -> 
			wRaster.setSample(ponto.x, ponto.y, 1, Pixel.VAZIO.getValor()));
		
		this.imgTemp = null;		
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgUniao, this.getClass());
		return this.imgUniao;
	}
	
	@Override
	public void abrir() {
		UnidorImagens.super.abrir(this.endImgSalva);
	}

	@Override
	public void gravar() {
		this.endImgSalva = UnidorImagens.super.gravarImg(this.imgUniao, this.nomeArquivo, this);  
	}
}
