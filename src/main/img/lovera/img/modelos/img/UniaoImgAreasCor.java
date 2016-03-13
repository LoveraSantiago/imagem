package lovera.img.modelos.img;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.contratos.Coordenadas;
import lovera.img.contratos.ImgTransformavel;
import lovera.linha.grao.AlturaSubset;
import lovera.linha.modelos.ClassifAltura;

public class UniaoImgAreasCor extends UniaoImgAreas{
	
	List<AlturaSubset> listaClassificacao;

	public UniaoImgAreasCor(String nomeArquivo, Coordenadas coordenadas, BufferedImage img) {
		super(nomeArquivo, coordenadas, img);
		this.listaClassificacao = ((ClassifAltura) coordenadas).getAlturasClassificadas();
	}
	
	@Override
	public ImgTransformavel executarTransformacao() {
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
