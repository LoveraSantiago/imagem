package lovera.linha.modelos;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.CoordenadasArea;
import lovera.comuns.recursos.Regras;
import lovera.estatistica.grao.Estatistica;

class GeradorDeBlocos implements CoordenadasArea{
	
	private final int altMedia;
	
	private List<Rectangle> listaAreas;	
	private List<Rectangle> listaTemp;
	
	public GeradorDeBlocos(List<Rectangle> listaAreas, Estatistica estats) {
		this.altMedia = (int) (Math.round(estats.getMedia()));
		this.listaAreas = new ArrayList<>();
		this.listaTemp = listaAreas;
	}
	
	public GeradorDeBlocos gerarBlocos(){
		this.listaTemp.forEach((area) -> {
			int xI = area.x;
			int divisao = (int) (area.width / this.altMedia);
			
			for(int i = divisao; i > 0; i--, xI += this.altMedia)
				this.listaAreas.add(new Rectangle(xI, area.y, this.altMedia, area.height));			
			
			if(xI < area.width)
				this.listaAreas.add(new Rectangle(xI, area.y, area.width- xI, area.height));				
		});
		
//		for(Rectangle area : this.listaTemp){
//			int xI = area.x;
//			int divisao = (int) (area.width / this.altMedia);
//			
//			for(int i = divisao; i > 0; i--, xI += this.altMedia)
//				this.listaAreas.add(new Rectangle(xI, area.y, this.altMedia, area.height));			
//			
//			if(xI < area.width)
//				this.listaAreas.add(new Rectangle(xI, area.y, area.width- xI, area.height));	
//		}
		
		return this;
	}
	
	@Override
	public List<Rectangle> getAreas() {
		Regras.validarListaDeAreas(this.listaAreas, this.getClass());
		return this.listaAreas;
	}
	

}
