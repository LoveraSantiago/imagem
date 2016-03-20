package lovera.comuns.recursos;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.estatistica.contratos.EstatsDesc;
import lovera.estatistica.grao.Estatistica;
import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.graos.AlturaSubset;
import lovera.img.graos.AreaPonto;

/**
 * Regras usadas de validacoes utilizadas no source src/main/img.
 * REDISTRIBUIR REGRAS CONFORME USO PARA OS DEVIDOS FOLDERS SOURCES 13/03/2016
 * @author Lovera
 * @since 06/03/2016
 */
public final class Regras {
	
	public static void validarBufferedImgCinza(BufferedImage img, Class<?> classe){
		if(img.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new IllegalArgumentException("BufferedImage deve ser do tipo cinza. Na classe " + classe.getName());
	}
	
	public static void validarBufferedImgCinza(ImgTransformavel imgTranformavel){
		validarBufferedImgCinza(imgTranformavel.getImgTransformada(), imgTranformavel.getClass());
	}
	
	public static void validarClassificacaoAlturas(List<AlturaSubset> lista, Coordenadas coordenadas){
		if(lista == null)
			throw new IllegalStateException("Metodo classificarAreas não foi chamada na classe " + coordenadas.getClass().getName() + ".");
	}

	public static void validarEstatistica(Estatistica estats, EstatsDesc classe){
		if(estats == null)
			throw new IllegalStateException("Metodo gerarEstatistica não foi chamado na classe " + classe.getClass().getName() + ".");
	}
	
	public static void validarListaDeAreas(List<Rectangle> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de Areas nula. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalStateException("Lista de Areas vazia. Na classe " + classe.getName() + ".");
	}
	
	public static void validarListaDeAreasComPonto(List<AreaPonto> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de Areas com Ponto nula. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalStateException("Lista de Areas com Ponto vazia. Na classe " + classe.getName() + ".");
	}
	
	public static void validarListaCoordenadas(List<Point> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de coordenadas. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalArgumentException("Lista de coordenadas vazia. Na classe " + classe.getName() + ".");
	}

	public static void validarNivelCinza(int nivelCinza, Class<?> classe){
		if(nivelCinza < 0)   
			throw new IllegalArgumentException("O pixel passado não pode ser menor do que 0. Na classe " + classe.getName());
		if(nivelCinza > 255) 
			throw new IllegalArgumentException("O pixel passado não pode ser maior do que 255. Na classe " + classe.getName());
	}
	
	public static void validarOperacaoExecutada(BufferedImage img, Class<?> classe){
		if(img == null)
			throw new IllegalStateException("Metodo executarOperação não foi chamada na classe " + classe.getName());
	}
}
