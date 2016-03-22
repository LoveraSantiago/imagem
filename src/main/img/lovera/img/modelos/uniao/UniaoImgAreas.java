package lovera.img.modelos.uniao;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.CoordenadasArea;
import lovera.img.contratos.UnidorImagens;
import lovera.img.manipulacao.ManipulacaoImg;

/**
 * Classe que desenha retangulos de uma lista para uma imagem.
 * @author Lovera
 * @since 12/03/2016
 */
public class UniaoImgAreas implements UnidorImagens{
	
	private final String nomeArquivo;
	
	private List<Rectangle> areas;
	
	private BufferedImage imgTemp;
	protected BufferedImage imgUniao;

	private String endImgSalva;
	
	public UniaoImgAreas(String nomeArquivo, Coordenadas coordenadas, BufferedImage img) {
		this.nomeArquivo = nomeArquivo;
		this.areas = ((CoordenadasArea) coordenadas).getAreas();
		this.imgTemp = img;
	}

	@Override
	public UniaoImgAreas executarTransformacao() {
		this.imgUniao = copiarImg();
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.blue);
		
		this.areas.forEach((area) -> graphics.draw(area));

		graphics.dispose();
		
		this.imgTemp = null;
		this.areas   = null;
		return this;
	}
	
	protected BufferedImage copiarImg(){
		return ManipulacaoImg.copiarImg(this.imgTemp, BufferedImage.TYPE_INT_RGB);
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
	public UniaoImgAreas gravar() {
		this.endImgSalva = UnidorImagens.super.gravarImg(this.imgUniao, this.nomeArquivo, this);
		return this;
	}
}
