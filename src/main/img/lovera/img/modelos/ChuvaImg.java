package lovera.img.modelos;

import static lovera.img.comum.Regras.validarBufferedImgCinza;
import static lovera.img.comum.Regras.validarListaCoordenadas;
import static lovera.img.comum.Regras.validarOperacaoExecutada;
import static lovera.img.manipulacao.ImgIO.gravarImg;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.comum.Pixel;
import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.uniaomodelos.UniaoImg;

public class ChuvaImg implements ImgTransformavel, Gravavel, Coordenadas{
	
	private BufferedImage imgTemp;
	private BufferedImage imgChuva;
	
	private List<Point> coordenadas;
	
	public ChuvaImg(LaplaceImg laplace) {
		validarBufferedImgCinza(laplace.getImgTransformada());
		
		this.imgTemp = laplace.getImgTransformada();
	}

	@Override
	public void executarTransformacao() {
		carregarCoordenadas();
		desenharImg();
		
		this.imgTemp = null;
	}
	
	private void carregarCoordenadas(){
		this.coordenadas = new ArrayList<>();
		
		Raster raster = this.imgTemp.getRaster();
		
		for(int j = 0; j < this.imgTemp.getWidth(); j++)
			for(int i = 0; i < this.imgTemp.getHeight(); i++)
				if(raster.getSample(j, i, 0) != Pixel.VAZIO){
					this.coordenadas.add(new Point(j, i));
					break;
				}		
	}
	
	private void desenharImg(){
		this.imgChuva = new BufferedImage(this.imgTemp.getWidth(), this.imgTemp.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		UniaoImg uniao = new UniaoImg(null, this, this.imgChuva);
		uniao.executarTransformacao();
		this.imgChuva = uniao.getImgTransformada();		
	}

	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgChuva, this);
		return this.imgChuva;
	}

	@Override
	public List<Point> getCoordenadas() {
		validarListaCoordenadas(this.coordenadas);
		return this.coordenadas;
	}

	@Override
	public void gravar() {
		validarOperacaoExecutada(this.imgChuva, this);
		gravarImg(this.imgChuva, Endereco.TESTES, "redacaoChuva", TipoImagem.PNG);
	}	
}
