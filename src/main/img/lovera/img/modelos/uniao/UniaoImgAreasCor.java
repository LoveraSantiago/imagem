package lovera.img.modelos.uniao;

import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.img.contratos.CoordAClassificadas;
import lovera.img.contratos.UnidorImagens;
import lovera.img.graos.AreaSubset;

public final class UniaoImgAreasCor extends UniaoImg{
	
	private BufferedImage imgTemp;
	private BufferedImage imgUniao;
	
	List<AreaSubset> listaClassificacao;

	public UniaoImgAreasCor(String nomeArquivo, CoordAClassificadas coordenadas, BufferedImage img) {
		super(nomeArquivo);
		this.listaClassificacao = coordenadas.getListaAreaClassificadas();
		this.imgTemp = img;
	}
	
	@Override
	public UniaoImgAreasCor executarTransformacao() {
		this.imgUniao = copiarImg(this.imgTemp, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		
		this.listaClassificacao.forEach((classe) ->{			
			graphics.setColor(classe.getSubset().getCor());
			graphics.draw(classe.getArea());			
		});
		
		graphics.dispose();		
		
		this.listaClassificacao = null;		
		return this;
	}
	
	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgUniao, this.getClass());
		return this.imgUniao;
	}

	@Override
	public UnidorImagens getInstancia() {		
		return this;
	}
}
