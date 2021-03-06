package lovera.img.modelos.blocos;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.estatistica.grao.Estatistica;
import lovera.img.contratos.CoordenadasArea;
import lovera.img.contratos.Executador;

class GeradorDeBlocos implements CoordenadasArea, Executador{
	
	private final int altMedia;
	
	private List<Rectangle> listaAreas;	
	private List<Rectangle> listaTemp;
	
	public GeradorDeBlocos(List<Rectangle> listaAreas, Estatistica estats) {
		this.altMedia = (int) (Math.round(estats.getMedia()));
		this.listaAreas = new ArrayList<>();
		this.listaTemp = listaAreas;
	}
	
	@Override
	public GeradorDeBlocos executar() {
		gerarBlocos();
		return this;
	}

	private void gerarBlocos(){
		this.listaTemp.forEach((area) -> {
			
			int xI = area.x;
			int divisao = (int) (area.width / this.altMedia);
			
			for(int i = divisao; i > 0; i--){
				Rectangle bloco = new Rectangle(xI, area.y, this.altMedia, area.height);
				this.listaAreas.add(bloco);			
				xI += this.altMedia;
			}
			
			if(xI < (area.width + area.x)){
				Rectangle blocoMenordoFinal = new Rectangle(xI, area.y, (area.width + area.x) - xI, area.height);
				this.listaAreas.add(blocoMenordoFinal);
			}
		});
	}
	
	@Override
	public List<Rectangle> getAreas() {
		Regras.validarListaDeAreas(this.listaAreas, this.getClass());
		return this.listaAreas;
	}
}
