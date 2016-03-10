package lovera.img.contratos;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.Gravavel;

/**
 * @author Lovera
 * @since 10/03/2016
 * Interface para modelos que realizam alguma transformacao na imagem. 
 * Tambem sempre sao gravaveis.
 */
public interface ImgTransformavel extends Gravavel{
	
	ImgTransformavel executarTransformacao();
	BufferedImage getImgTransformada();
}
