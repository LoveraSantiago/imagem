package lovera.img.modelos.uniao;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.contratos.CoordenadasArea;
import lovera.img.contratos.ImgTransformavel;
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
	
	public UniaoImgAreas(String nomeArquivo, CoordenadasArea coordenadas, BufferedImage img) {
		this.nomeArquivo = nomeArquivo;
		this.areas = coordenadas.getAreas();
		this.imgTemp = img;
	}

	@Override
	public ImgTransformavel executarTransformacao() {
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
	public void gravar() {
		this.endImgSalva = UnidorImagens.super.gravarImg(this.imgUniao, this.nomeArquivo, this); 
	}
}
