package lovera.img.modelos.uniao;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.contratos.CoordenadasLinhas;
import lovera.img.contratos.UnidorImagens;

public final class UniaoImgLinhas extends UniaoImg{
	
	private List<Line2D> linhas;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	public UniaoImgLinhas(String nomeArquivo, CoordenadasLinhas coordenadas, BufferedImage img) {
		super(nomeArquivo);
		this.linhas = coordenadas.getLinhas();
		this.imgTemp = img;
	}
	
	//Para fins de debugagem
	public UniaoImgLinhas(String nomeArquivo, List<Line2D> linhas, BufferedImage img) {
		super(nomeArquivo);
		this.linhas = linhas;
		this.imgTemp = img;
	}

	@Override
	public UniaoImgLinhas executarTransformacao() {
		this.imgUniao = copiarImg(this.imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.orange);
		
		this.linhas.forEach((linha) -> graphics.draw(linha));
		
		graphics.dispose();
		
		this.imgTemp = null;
		this.linhas = null;
		return this;
	}

	@Override
	public BufferedImage getImgTransformada() {
		Regras.validarOperacaoExecutada(this.imgUniao, this.getClass());
		return this.imgUniao;
	}

	@Override
	public UnidorImagens getInstancia() {		
		return this;
	}
}
