package lovera.img.modelos.uniao;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.List;

import lovera.img.comum.Pixel;
import lovera.img.contratos.CoordenadasPonto;
import lovera.img.contratos.UnidorImagens;

/**
 * Classe que desenha pontos de uma lista de pontos em uma imagem.
 * @author Lovera
 * @since 12/03/2016
 */
public final class UniaoImgPontos extends UniaoImg{
	
	private final List<Point2D> coordenadas;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	public UniaoImgPontos(String nomeArquivo, CoordenadasPonto coordenadas, BufferedImage img) {
		super(nomeArquivo);
		this.coordenadas = coordenadas.getCoordenadas();
		this.imgTemp = img;
	}
	
	/**
	 * Construtor para debug 
	 */
	public UniaoImgPontos(String nomeArquivo, List<Point2D> coordenadas, BufferedImage img) {
		super(nomeArquivo);
		this.coordenadas = coordenadas;
		this.imgTemp = img;
	}

	@Override
	public UniaoImgPontos executarTransformacao() {
		this.imgUniao = copiarImg(imgTemp, BufferedImage.TYPE_INT_RGB);
		
		WritableRaster wRaster = this.imgUniao.getRaster(); 
		
		this.coordenadas.forEach((ponto) -> {
			
			int x = (int) Math.round(ponto.getX());
			int y = (int) Math.round(ponto.getY());
			
			wRaster.setSample(x, y, 1, Pixel.VAZIO.getValor());			
		});
		
		this.imgTemp = null;		
		return this;
	}
	
	@Override
	public UnidorImagens getInstancia() {		
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgUniao, this.getClass());
		return this.imgUniao;
	}
}
