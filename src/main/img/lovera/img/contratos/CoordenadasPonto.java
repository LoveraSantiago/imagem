package lovera.img.contratos;

import java.awt.Point;
import java.util.List;

/**
 * Interface que extende Coordenadas.</br>
 * Utilizado pelas classes que lidam com listas de Pontos.
 * @author Lovera
 * @since 25/03/2016
 */
public interface CoordenadasPonto extends Coordenadas{

	List<Point> getCoordenadas(); 
		
}
