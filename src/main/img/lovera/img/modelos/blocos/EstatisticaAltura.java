package lovera.img.modelos.blocos;

import java.awt.Rectangle;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.estatistica.contratos.EstatsDesc;
import lovera.estatistica.factory.FactoryEstatistica;
import lovera.estatistica.grao.Estatistica;

class EstatisticaAltura implements EstatsDesc{
	
	private List<Rectangle> listaAreas;
	
	private Estatistica estatistica;	 
	
	public EstatisticaAltura(List<Rectangle> listaAreas) {
		this.listaAreas = listaAreas;
	}

	@Override
	public EstatsDesc gerarEstatistica() {		
		alturaDeAreasParaEstatistica();		
		this.listaAreas = null;
		return this;
	}
	
	private void alturaDeAreasParaEstatistica(){
		this.estatistica = FactoryEstatistica.factory_EstatisticaAltura(this.listaAreas);
	}

	@Override
	public Estatistica getEstatistica() {
		Regras.validarEstatistica(this.estatistica, this);
		return this.estatistica;
	}
}
