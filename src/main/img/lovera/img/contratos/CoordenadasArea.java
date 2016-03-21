package lovera.img.contratos;

import java.awt.Rectangle;
import java.util.List;

/**
 * Interface que extende de Coordenadas.</br>
 * Utilizada pelas classes que lidam com listas de Retangulos "areas" de uma imagem.
 * @author Lovera
 * @since 21/03/2016
 */
public interface CoordenadasArea extends Coordenadas{
	
	List<Rectangle> getAreas();
	
}
