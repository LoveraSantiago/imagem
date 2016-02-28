package lovera.img.uniaomodelos;

import static lovera.img.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.ImgIO.gravarImg;
import static lovera.img.manipulacao.ManipulacaoImg.copiarImg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.UnidorImagens;

public final class UniaoImg implements UnidorImagens, Gravavel{
	
	private final String nomeArquivo;
	
	private final List<Point> coordenadas;
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	public UniaoImg(String nomeArquivo, Coordenadas imgCoordenadas, BufferedImage img) {
		this.nomeArquivo = nomeArquivo;
		this.coordenadas = imgCoordenadas.getCoordenadas();
		this.imgTemp = img;
	}

	@Override
	public void executarTransformacao() {
		this.imgUniao = copiarImg(imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.red);
		
		for(Point ponto : this.coordenadas)	
			graphics.fillOval((int) ponto.getX(), (int) ponto.getY(), 2, 2);

		graphics.dispose();
		this.imgTemp = null;
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgUniao, this);
		return this.imgUniao;
	}

	@Override
	public void gravar() {
		validarOperacaoExecutada(this.imgUniao, this);
		gravarImg(this.imgUniao, Endereco.TESTES, this.nomeArquivo, TipoImagem.PNG);		
	}
}
