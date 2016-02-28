package lovera.linha.uniao;

import static lovera.comuns.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.ImgIO.gravarImg;
import static lovera.img.manipulacao.ManipulacaoImg.copiarImg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.List;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.contratos.UnidorImagens;
import lovera.linha.modelos.ErosaoImg;

public final class UniaoImgErosao implements UnidorImagens, Gravavel{
	
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
//		this.imgUniao = new BufferedImage(imgTemp.getWidth(), imgTemp.getHeight(), BufferedImage.TYPE_INT_RGB);
//		WritableRaster wRaster = imgUniao.getRaster();
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		graphics.setColor(Color.red);
		
		for(Point ponto : coordenadas)	
			graphics.fillOval((int) ponto.getX(), (int) ponto.getY(), 2, 2);
//			wRaster.setSample((int) ponto.getX(), (int) ponto.getY(), 0, 255);

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
