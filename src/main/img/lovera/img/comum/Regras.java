package lovera.img.comum;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.img.contratos.ImgTransformavel;

public final class Regras {
	
	public static void validarBufferedImgCinza(BufferedImage img){
		if(img.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new IllegalArgumentException("BufferedImage deve ser do tipo cinza.");
	}
	
	public static void validarBufferedImgCinza(ImgTransformavel imgTranformavel){
		validarBufferedImgCinza(imgTranformavel.getImgTransformada());
	}
	
	public static void validarListaCoordenadas(List<Point> lista){
		if(lista.size() <= 0)
			throw new IllegalArgumentException("Lista de coordenadas vazia.");
	}

	public static void validarNivelCinza(int nivelCinza){
		if(nivelCinza < 0)   
			throw new IllegalArgumentException("O pixel passado não pode ser menor do que 0.");
		if(nivelCinza > 255) 
			throw new IllegalArgumentException("O pixel passado não pode ser maior do que 255.");
	}
	
	public static void validarOperacaoExecutada(BufferedImage img, ImgTransformavel classe){
		if(img == null)
			throw new IllegalArgumentException("Metodo executarOperação não foi chamada na classe " + classe.getClass().getSimpleName());
	}
}
