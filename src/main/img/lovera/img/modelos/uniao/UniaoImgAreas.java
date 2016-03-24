package lovera.img.modelos.uniao;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.img.contratos.CoordenadasArea;
import lovera.img.contratos.UnidorImagens;

/**
 * Classe que desenha retangulos de uma lista para uma imagem.
 * @author Lovera
 * @since 12/03/2016
 */
public final class UniaoImgAreas extends UniaoImg{
	
	private List<Rectangle> areas;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;

	public UniaoImgAreas(String nomeArquivo, CoordenadasArea coordenadas, BufferedImage img) {
		super(nomeArquivo);
		this.areas = coordenadas.getAreas();
		this.imgTemp = img;
	}

	@Override
	public UniaoImgAreas executarTransformacao() {
		this.imgUniao = copiarImg(this.imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.blue);
		
		this.areas.forEach((area) -> graphics.draw(area));

		graphics.dispose();
		
		this.imgTemp = null;
		this.areas   = null;
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
