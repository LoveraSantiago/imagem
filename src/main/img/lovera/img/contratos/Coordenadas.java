package lovera.img.contratos;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 * Interface para modelos que produzem Coordenadas para uma imagem.
 * @author Lovera
 * @since 10/03/2016
 */
public interface Coordenadas {
	
	default List<Rectangle> getAreas(){ 
		throw new IllegalStateException("Metodo getAreas da Interface Coordenadas não implementado."); 
	}
	
	default List<Point> getCoordenadas(){ 
		throw new IllegalStateException("Metodo getCoordenadas da Interface Coordenadas não implementado."); 
	}
}
