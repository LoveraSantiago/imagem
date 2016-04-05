package lovera.img.modelos.blocos;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.comum.Subset;
import lovera.img.contratos.CoordenadasArea;
import lovera.img.contratos.Executor;
import lovera.img.graos.AreaSubset;

class FiltroSubset1 implements CoordenadasArea, Executor{
	
	List<AreaSubset> listaFiltrada;
	List<Rectangle> listaAreas;
	
	public FiltroSubset1(List<AreaSubset> listaParaClassificar) {
		this.listaFiltrada = listaParaClassificar;
	}
	
	@Override
	public FiltroSubset1 executar() {
		filtrarListaDeAreas();
		return this;
	}

	private void filtrarListaDeAreas(){
		List<Rectangle> listaFiltrada = new ArrayList<>();
		
		this.listaFiltrada.forEach((classif) -> {
			if(classif.getSubset() == Subset.S1)
				listaFiltrada.add(classif.getArea());
		});
		
		this.listaAreas = listaFiltrada;
		this.listaFiltrada = null;
	}	
	
	@Override	
	public List<Rectangle> getAreas() {
		Regras.validarListaDeAreas(this.listaAreas, this.getClass());
		return this.listaAreas;
	}

}
