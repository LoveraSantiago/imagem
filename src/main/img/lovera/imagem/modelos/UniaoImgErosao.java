package lovera.imagem.modelos;

import static lovera.imagem.comum.Regras.validarOperacaoExecutada;
import static lovera.imagem.manipulacao.ImgIO.gravarImg;
import static lovera.imagem.manipulacao.ManipulacaoImg.copiarImg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.imagem.contratos.ImgTransformavel;
import lovera.imagem.recursos.Endereco;
import lovera.imagem.recursos.TipoImagem;

public final class UniaoImgErosao implements ImgTransformavel{
	
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
