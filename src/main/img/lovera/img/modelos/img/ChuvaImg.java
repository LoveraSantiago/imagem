package lovera.img.modelos.img;

import static lovera.comuns.recursos.Regras.validarBufferedImgCinza;
import static lovera.comuns.recursos.Regras.validarListaCoordenadas;
import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.CoordenadasPonto;
import lovera.comuns.contratos.Gravavel;
import lovera.img.comum.Pixel;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.modelos.uniao.UniaoImgPontos;
/**
 * @author Lovera
 */
@Deprecated //10/03/2016 Possivelmente nao sera utilizada.
public final class ChuvaImg implements ImgTransformavel, Gravavel, CoordenadasPonto{
	
	private BufferedImage imgTemp;
	private BufferedImage imgChuva;
	
	private List<Point> coordenadas;
	
	public ChuvaImg(BinarizacaoImg binarizacao) {
		validarBufferedImgCinza(binarizacao);
		
		this.imgTemp = binarizacao.getImgTransformada();
	}

	@Override
	public ImgTransformavel executarTransformacao() {
		carregarCoordenadas();
		desenharImg();
		
		this.imgTemp = null;
		return this;
	}
	
	private void carregarCoordenadas(){
		this.coordenadas = new ArrayList<>();
		
		Raster raster = this.imgTemp.getRaster();
		
		for(int j = 0; j < this.imgTemp.getWidth(); j++)
			for(int i = 0; i < this.imgTemp.getHeight(); i++)
				if(raster.getSample(j, i, 0) != Pixel.VAZIO.getValor()){
					this.coordenadas.add(new Point(j, i));
					break;
				}		
	}
	
	private void desenharImg(){
		this.imgChuva = new BufferedImage(this.imgTemp.getWidth(), this.imgTemp.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		UniaoImgPontos uniao = new UniaoImgPontos(null, this, this.imgChuva);
		uniao.executarTransformacao();
		this.imgChuva = uniao.getImgTransformada();		
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgChuva, this.getClass());
		return this.imgChuva;
	}

	@Override
	public List<Point> getCoordenadas() {
		validarListaCoordenadas(this.coordenadas);
		return this.coordenadas;
	}

	@Override
	public void gravar() {
		ImgTransformavel.super.gravarImg(this.imgChuva, "redacaoChuva", this);
	}
}
