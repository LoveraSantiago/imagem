package lovera.img.modelos.blocos;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.estatistica.grao.Estatistica;
import lovera.img.comum.Subset;
import lovera.img.contratos.CoordAClassificadas;
import lovera.img.contratos.CoordenadasArea;
import lovera.img.contratos.Executador;
import lovera.img.graos.AreaSubset;

class ClassifAltura implements CoordenadasArea, CoordAClassificadas, Executador{
	
	private final double altMedia;
	
	private List<Rectangle> listaAreas;
	private List<AreaSubset> lClassificada;
	
	public ClassifAltura(List<Rectangle> listaAreas, Estatistica estats) {
		this.listaAreas = listaAreas;
		this.altMedia   = estats.getMedia();
		this.lClassificada = new ArrayList<>(this.listaAreas.size());
	}
	
	@Override
	public ClassifAltura executar() {
		classificarAreas();
		return this;
	}

	private void classificarAreas(){

		this.listaAreas.forEach((area) -> 
			this.lClassificada.add(new AreaSubset(classificarSubset(area), area)));
		
		this.listaAreas = null;
	}
	
	private Subset classificarSubset(Rectangle area){
		if(casoSubset1(area)) return Subset.S1;
		if(casoSubset2(area)) return Subset.S2;
		if(casoSubset3(area)) return Subset.S3;
		return null;
	}
	
	private boolean casoSubset1(Rectangle area){		
		boolean sentenca1 = ((0.5 * this.altMedia) <= area.height) && (area.height < (3 * this.altMedia));
		boolean sentenca2 = ((0.5 * this.altMedia) <= area.width);
		
		return sentenca1 && sentenca2;
	}
	
	private boolean casoSubset2(Rectangle area){
		
		return area.height >= 3 * this.altMedia;
	}
	
	private boolean casoSubset3(Rectangle area){		
		boolean sentenca1 = (area.height < (3 * this.altMedia)) && ((0.5 * this.altMedia) > area.width);
		boolean sentenca2 = (area.height < (0.5 * this.altMedia)) && ((0.5 * this.altMedia) < area.width);
		
		return sentenca1 || sentenca2;
	}
	
	@Override
	public List<Rectangle> getAreas() {		
		List<Rectangle> listaAreas = new ArrayList<>(this.listaAreas.size());
		this.listaAreas.forEach((area) -> listaAreas.add(area));
		Regras.validarListaDeAreas(listaAreas, this.getClass());
		return listaAreas;
	}
	
	@Override
	public List<AreaSubset> getListaAreaClassificadas() {
		Regras.validarClassificacaoAlturas(this.lClassificada, this);
		return this.lClassificada;
	}
}
