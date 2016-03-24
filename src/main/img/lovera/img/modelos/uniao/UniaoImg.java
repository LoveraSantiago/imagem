package lovera.img.modelos.uniao;

import java.awt.image.BufferedImage;

import lovera.img.contratos.UnidorImagens;
import lovera.img.manipulacao.ManipulacaoImg;

abstract class UniaoImg implements UnidorImagens{
	
	private final String nomeArquivo;
	private String endImgSalva;

	public UniaoImg(String nomeArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
	}
	
	protected BufferedImage copiarImg(BufferedImage img, int tipo){
		return ManipulacaoImg.copiarImg(img, tipo);
	}
	
	public abstract UnidorImagens getInstancia();
	
	@Override
	public void abrir() {
		UnidorImagens.super.abrir(this.endImgSalva);
	}
	
	@Override
	public UnidorImagens gravar() {
		BufferedImage imgUniao = getImgTransformada();
		UnidorImagens instancia = getInstancia();
		
		this.endImgSalva = UnidorImagens.super.gravarImg(imgUniao, this.nomeArquivo, instancia);
		return instancia;
	}
}
