package lovera.img.modelos_img;

import static lovera.img.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.ImgIO.gravarImg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.contratos.UnidorImagens;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;

/**
 * Classe que desenha retangulos de uma lista para uma imagem.
 * @author Lovera
 * @since 12/03/2016
 */
public class UniaoImgAreas implements UnidorImagens{
	
	private final String nomeArquivo;
	
	private final List<Rectangle> areas;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;

	private String endImgSalva;
	
	public UniaoImgAreas(String nomeArquivo, Coordenadas coordenadas, BufferedImage img) {
		this.nomeArquivo = nomeArquivo;
		this.areas = coordenadas.getAreas();
		this.imgTemp = img;
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		this.imgUniao = ManipulacaoImg.copiarImg(imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.green);
		
		this.areas.forEach((area) -> graphics.draw(area));

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
