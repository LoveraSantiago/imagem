package lovera.comuns.recursos;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;

import lovera.estatistica.contratos.EstatsDesc;
import lovera.estatistica.grao.Estatistica;
import lovera.img.contratos.Coordenadas;
import lovera.img.contratos.ImgTransformavel;
import lovera.img.graos.AreaSubset;
import lovera.img.graos.BlocoComPonto;
import lovera.img.hough.CoordenadaPolarTemp;
import lovera.img.modelos.blocos.AreasParaBlocos;

/**
 * Regras usadas de validacoes utilizadas no source src/main/img.
 * -REDISTRIBUIR REGRAS CONFORME USO PARA OS DEVIDOS FOLDERS SOURCES 13/03/2016
 * -ARRUMAR EXCESSO DE CHAMADAS DE REGRAS (EX: NA CLASSE VALIDA ANTES DO GET E DEPOIS DELE)
 * @author Lovera
 * @since 06/03/2016
 */
public final class Regras {
	
	public static void validarBufferedImgCinza(BufferedImage img, Class<?> classe){
		if(img.getType() != BufferedImage.TYPE_BYTE_GRAY)
			throw new IllegalArgumentException("BufferedImage deve ser do tipo cinza. Na classe " + classe.getName() + ".");
	}
	
	public static void validarBufferedImgCinza(ImgTransformavel imgTranformavel, Class<?> classe){
		validarBufferedImgCinza(imgTranformavel.getImgTransformada(), imgTranformavel.getClass());
	}
	
	public static void validarClassificacaoAlturas(List<AreaSubset> lista, Coordenadas coordenadas){
		if(lista == null)
			throw new IllegalStateException("Metodo classificarAreas não foi chamado na classe " + coordenadas.getClass().getName() + ".");
	}

	public static void validarEstatistica(Estatistica estats, EstatsDesc classe){
		if(estats == null)
			throw new IllegalStateException("Metodo gerarEstatistica não foi chamado na classe " + classe.getClass().getName() + ".");
	}
	
	public static void validarLinha(Line2D linha, Class<?> classe){
		if(linha == null)
			throw new IllegalStateException("Objeto linha nulo. Na classe " + classe.getName() + ".");
	}
	
	public static void validarListaDeAreas(List<Rectangle> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de Areas nula. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalStateException("Lista de Areas vazia. Na classe " + classe.getName() + ".");
	}
	
	public static void validarListaDeBlocosComPonto(List<BlocoComPonto> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de Areas com Ponto nulo. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalStateException("Lista de Areas com Ponto vazia. Na classe " + classe.getName() + ".");
	}
	
	public static void validarListaDeBlocosComPonto(AreasParaBlocos areas, Class<?> classe){
		validarListaDeBlocosComPonto(areas.getListaBlocosComPontos(), classe);
	}
	
	public static void validarListaCoordenadas(List<Point2D> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de coordenadas nula. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalArgumentException("Lista de coordenadas vazia. Na classe " + classe.getName() + ".");
	}
	
	public static void validarListaCoordenadasPolar(List<CoordenadaPolarTemp> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de coordenadas polares nula. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalArgumentException("Lista de coordenadas polares vazia. Na classe " + classe.getName() + ".");
	}
	
	public static void validarListaDeLinhas(List<Line2D> lista, Class<?> classe){
		if(lista == null)
			throw new IllegalStateException("Lista de linhas nula. Na classe " + classe.getName() + ".");
		if(lista.size() <= 0)
			throw new IllegalArgumentException("Lista de coordenadas vazia. Na classe " + classe.getName() + ".");
	}

	public static void validarNivelCinza(int nivelCinza, Class<?> classe){
		if(nivelCinza < 0)   
			throw new IllegalArgumentException("O pixel passado não pode ser menor do que 0. Na classe " + classe.getName() + ".");
		if(nivelCinza > 255) 
			throw new IllegalArgumentException("O pixel passado não pode ser maior do que 255. Na classe " + classe.getName() + ".");
	}
	
	public static void validarOperacaoExecutada(BufferedImage img, Class<?> classe){
		if(img == null)
			throw new IllegalStateException("Metodo executarOperação não foi chamada na classe " + classe.getName() + ".");
	}
	
	public static void validarPonto(Point ponto, Class<?> classe){
		if(ponto == null)
			throw new IllegalStateException("Objeto ponto nulo na classe " + classe.getName() + ".");
	}
	
	public static void validarPontoDentroDaImg(BufferedImage img, Point ponto, Class<?> classe){
		validarPonto(ponto, classe);
		if(ponto.x < 0) 
			throw new IllegalArgumentException("Coordenada do x menor do que 0. Na classe " + classe.getName() + ".");
		if(ponto.x > img.getWidth())
			throw new IllegalArgumentException("Coordenada do x maior do que a largura da imagem. Na classe " + classe.getName() + ".");
		if(ponto.y < 0)
			throw new IllegalArgumentException("Coordenada do y menor do que 0. Na classe " + classe.getName() + ".");
		if(ponto.y > img.getHeight())
			throw new IllegalArgumentException("Coordenada do y maior do que a altura da imagem. Na classe " + classe.getName() + ".");
	}
	
}
