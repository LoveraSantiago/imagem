package lovera.comuns.recursos;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.contratos.Coordenadas;
import lovera.estatistica.contratos.EstatsDesc;
import lovera.estatistica.descritiva.Estatistica;
import lovera.img.contratos.ImgTransformavel;
import lovera.linha.modelos.AlturaClasse;

/**
 * Regras usadas de validacoes utilizadas no source src/main/img.
 * REDISTRIBUIR REGRAS CONFORME USO PARA OS DEVIDOS FOLDERS SOURCES 13/03/2016
 * @author Lovera
 * @since 06/03/2016
 */
public final class Regras {
	
	public static void validarBufferedImgCinza(BufferedImage img){
		if(img.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new IllegalArgumentException("BufferedImage deve ser do tipo cinza.");
	}
	
	public static void validarBufferedImgCinza(ImgTransformavel imgTranformavel){
		validarBufferedImgCinza(imgTranformavel.getImgTransformada());
	}
	
	public static void validarClassificacaoAlturas(List<AlturaClasse> lista, Coordenadas coordenadas){
		if(lista == null)
			throw new IllegalStateException("Metodo classificarAreas não foi chamada na classe " + coordenadas.getClass().getSimpleName());
	}

	public static void validarEstatistica(Estatistica estats, EstatsDesc classe){
		if(estats == null)
			throw new IllegalStateException("Metodo gerarEstatistica não foi chamado na classe " + classe.getClass().getSimpleName());
	}
	
	public static void validarListaDeAreas(List<Rectangle> lista){
		if(lista.size() <= 0)
			throw new IllegalArgumentException("Lista de Areas vazia.");
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
			throw new IllegalStateException("Metodo executarOperação não foi chamada na classe " + classe.getClass().getSimpleName());
	}
}
