package lovera.imagem.contratos;

import java.awt.image.BufferedImage;

public interface ImgTransformavel extends Gravavel{
	
	void executarTransformacao();
	BufferedImage getImgTransformada();

}
