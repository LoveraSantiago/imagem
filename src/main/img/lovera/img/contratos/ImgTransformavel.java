package lovera.img.contratos;

import java.awt.image.BufferedImage;

/**
 * @author Lovera
 * @since 10/03/2016
 * Interface para modelos que realizam alguma transformacao na imagem.
 */
public interface ImgTransformavel {
	
	ImgTransformavel executarTransformacao();
	BufferedImage getImgTransformada();
}
