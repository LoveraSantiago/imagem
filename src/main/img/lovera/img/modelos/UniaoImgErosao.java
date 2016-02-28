package lovera.img.modelos;

import static lovera.comuns.comum.Regras.validarOperacaoExecutada;
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
import lovera.img.contratos.ImgTransformavel;

public final class UniaoImgErosao implements ImgTransformavel, Gravavel{
	
	private final List<Point> coordenadas;
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	public UniaoImgErosao(ErosaoImg erosao, BufferedImage img) {
		this.coordenadas = erosao.getCoordenadas();
		this.imgTemp = img;
	}

	@Override
	public void executarTransformacao() {
		this.imgUniao = copiarImg(imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.red);
		
		for(Point ponto : coordenadas)			
			graphics.fillOval((int) ponto.getX(), (int) ponto.getY(), 1, 1);
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
		gravarImg(this.imgUniao, Endereco.TESTES, "redacaoUniaoErosao", TipoImagem.PNG);		
	}

}
