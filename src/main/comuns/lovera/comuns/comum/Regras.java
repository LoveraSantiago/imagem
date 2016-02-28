package lovera.comuns.comum;

import java.awt.image.BufferedImage;

import lovera.comuns.contratos.ImgTransformavel;

public final class Regras {
	
	public static void validarBufferedImgCinza(BufferedImage img){
		if(img.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new IllegalArgumentException("BufferedImage deve ser do tipo cinza.");
	}

	public static void validarNivelCinza(int nivelCinza){
		if(nivelCinza < 0)   
			throw new IllegalArgumentException("O pixel passado n�o pode ser menor do que 0.");
		if(nivelCinza > 255) 
			throw new IllegalArgumentException("O pixel passado n�o pode ser maior do que 255.");
	}
	
	public static void validarOperacaoExecutada(BufferedImage img, ImgTransformavel classe){
		if(img == null)
			throw new IllegalArgumentException("Metodo executarOperação não foi chamada na classe " + classe.getClass().getSimpleName());
	}

}
