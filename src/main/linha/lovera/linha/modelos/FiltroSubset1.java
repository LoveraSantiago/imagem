package lovera.linha.modelos;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.Coordenadas;
import lovera.comuns.contratos.CoordenadasArea;
import lovera.comuns.recursos.Regras;
import lovera.linha.comum.Subset;
import lovera.linha.grao.AlturaSubset;

class FiltroSubset1 implements CoordenadasArea{
	
	List<AlturaSubset> listaFiltrada;
	List<Rectangle> listaAreas;
	
	public FiltroSubset1(List<AlturaSubset> listaParaClassificar) {
		this.listaFiltrada = listaParaClassificar;
	}
	
	public FiltroSubset1 filtrarListaDeAreas(){
		List<Rectangle> listaFiltrada = new ArrayList<>();
		
		this.listaFiltrada.forEach((classif) -> {
			if(classif.getSubset() == Subset.S1)
				listaFiltrada.add(classif.getArea());
		});
		
		this.listaAreas = listaFiltrada;
		return this;
	}	
	
	@Override	
	public List<Rectangle> getAreas() {
		Regras.validarListaDeAreas(this.listaAreas, this.getClass());
		return this.listaAreas;
	}

}
