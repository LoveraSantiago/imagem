package lovera.img.contratos;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;

/**
 * Interface para modelos que realizam alguma transformacao na imagem. 
 * Tambem sempre sao gravaveis.
 * @author Lovera
 * @since 10/03/2016
 */
public interface ImgTransformavel extends Gravavel{
	
	ImgTransformavel executarTransformacao();
	BufferedImage getImgTransformada();
}
