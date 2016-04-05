package lovera.img.modelos.uniao;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.img.contratos.CoordenadasPonto;
import lovera.img.contratos.UnidorImagens;
import lovera.img.modelos.blocos.AreasParaBlocos;

public class UniaoImgCirculosTEMP extends UniaoImg{
	
	private final int raio;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	private final List<Point> coordenadas;

	public UniaoImgCirculosTEMP(String nomeArquivo, CoordenadasPonto coordenadas, BufferedImage img) {
		super(nomeArquivo);
		this.coordenadas = coordenadas.getCoordenadas();
		this.imgTemp = img;
		this.raio = (int) Math.round(((AreasParaBlocos)coordenadas).getAlturaMediaBloco());
	}

	@Override
	public UniaoImgCirculosTEMP executarTransformacao() {
		this.imgUniao = copiarImg(imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics graphics = this.imgUniao.getGraphics();
		graphics.setColor(Color.cyan);
		
		this.coordenadas.forEach((ponto) -> 
			graphics.drawOval(ponto.x, ponto.y, raio, raio));
		 
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
