package lovera.img.contratos;

import java.util.List;

import lovera.img.graos.AreaSubset;

/**
 * Interface para modelos que produzem areas retangulares separadas por grpos.</br>
 * Os agrupamentos são os Subset's
 * @author Lovera
 * @since 23/03/2016
 */
public interface CoordAClassificadas extends Coordenadas{
	
	List<AreaSubset> getListaAreaClassificadas();

}
