package lovera.img.contratos;

import java.awt.geom.Line2D;
import java.util.List;

/**
 * Interface para modelos que produzem Coordenadas em linhas para uma imagem. 
 * @author Lovera
 * @since 24/03/2016
 */
public interface CoordenadasLinhas {

	List<Line2D> getLinhas();
}
