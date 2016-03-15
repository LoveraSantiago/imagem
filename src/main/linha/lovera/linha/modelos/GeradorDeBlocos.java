package lovera.linha.modelos;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.CoordenadasArea;
import lovera.estatistica.grao.Estatistica;

class GeradorDeBlocos implements CoordenadasArea{
	
	private final double altMedia;
	
	private List<Rectangle> listaAreas;
	
	
	public GeradorDeBlocos(List<Rectangle> listaAreas, Estatistica estats) {
		this.altMedia = estats.getMedia();
		this.listaAreas = new ArrayList<>();
	}
	
	public GeradorDeBlocos gerarBlocos(){
		return this;
	}
	
	@Override
	public List<Rectangle> getAreas() {
		// TODO Auto-generated method stub
		return null;
	}

}
