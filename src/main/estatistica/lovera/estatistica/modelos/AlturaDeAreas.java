package lovera.estatistica.modelos;

import java.awt.Rectangle;
import java.util.List;

import lovera.estatistica.contratos.EstatsDesc;
import lovera.estatistica.descritiva.Estatistica;
import lovera.estatistica.factory.FactoryEstatistica;
import lovera.img.comum.Regras;
import lovera.img.contratos.Coordenadas;

public class AlturaDeAreas implements EstatsDesc{
	
	private List<Rectangle> listaAreas;
	
	private Estatistica estatistica;	 
	
	public AlturaDeAreas(Coordenadas coordenadas) {
		this.listaAreas = coordenadas.getAreas();
	}

	@Override
	public EstatsDesc gerarEstatistica() {
		Regras.validarListaDeAreas(this.listaAreas);
		
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
