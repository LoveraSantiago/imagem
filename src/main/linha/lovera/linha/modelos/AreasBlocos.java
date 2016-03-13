package lovera.linha.modelos;

import java.awt.Rectangle;
import java.util.List;

import lovera.comuns.contratos.Coordenadas;
import lovera.comuns.recursos.Regras;
import lovera.img.modelos.floodfill.FloodFillCCs;

public class AreasBlocos implements Coordenadas{
	
	private List<Rectangle> listaAreas;
	
	public AreasBlocos(FloodFillCCs ffillCcs) {
		Regras.validarListaDeAreas(ffillCcs.getAreas(), ffillCcs.getClass());
		this.listaAreas = ffillCcs.getAreas();
	}

	public AreasBlocos gerarBlocos(){
		return this;
	}
}
