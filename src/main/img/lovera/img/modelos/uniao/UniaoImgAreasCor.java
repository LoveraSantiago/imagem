package lovera.img.modelos.uniao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.img.contratos.CoordAClassificadas;
import lovera.img.graos.AlturaSubset;

public class UniaoImgAreasCor extends UniaoImgAreas{
	
	List<AlturaSubset> listaClassificacao;

	public UniaoImgAreasCor(String nomeArquivo, CoordAClassificadas coordenadas, BufferedImage img) {
		super(nomeArquivo, coordenadas, img);
		this.listaClassificacao = coordenadas.getListaAreaClassificadas();
	}
	
	@Override
	public UniaoImgAreasCor executarTransformacao() {
		this.imgUniao = copiarImg();
		
		Graphics2D graphics = this.imgUniao.createGraphics();
		
		this.listaClassificacao.forEach((classe) ->{			
			graphics.setColor(classe.getSubset().getCor());
			graphics.draw(classe.getArea());			
		});
		
		graphics.dispose();		
		
		this.listaClassificacao = null;		
		return this;
	}
}
