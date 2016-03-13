package lovera.estatistica.modelos;

import java.awt.Rectangle;
import java.util.List;

import lovera.estatistica.contratos.EstatsDesc;
import lovera.estatistica.descritiva.Estatistica;
import lovera.img.comum.Regras;

public class AlturaDeAreas implements EstatsDesc{
	
	private List<Rectangle> listaAreas;
	
	private Estatistica estatistica;	 
	
	public AlturaDeAreas(List<Rectangle> listaAreas) {
		
	}

	@Override
	public EstatsDesc gerarEstatistica() {
		Regras.validarListaDeAreas(this.listaAreas);
		
		this.listaAreas = null;
		return this;
	}

	@Override
	public Estatistica getEstatistica() {
		Regras.validarEstatistica(this.estatistica, this);
		return this.estatistica;
	}

}
