package lovera.linha.modelos;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.Coordenadas;
import lovera.comuns.recursos.Regras;
import lovera.estatistica.descritiva.Estatistica;
import lovera.linha.comum.Subset;

public class ClassificadorAltura implements Coordenadas{
	
	private final double altMedia;
	
	private List<Rectangle> listaAreas;
	private List<AlturaClasse> lClassificada;
	
	public ClassificadorAltura(Coordenadas coordenadas, Estatistica estats) {
		this.listaAreas = coordenadas.getAreas();
		this.altMedia   = estats.getMedia();
	}
	
	public List<AlturaClasse> classificarAreas(){
		this.lClassificada = new ArrayList<>(this.listaAreas.size());
		
		this.listaAreas.forEach((area) -> 
			this.lClassificada.add(new AlturaClasse(classificarSubset(area), area)));
		
		return lClassificada;
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
		Regras.validarListaDeAreas(listaAreas);
		return listaAreas;
	}
	
	public List<AlturaClasse> getAlturasClassificadas(){
		Regras.validarClassificacaoAlturas(this.lClassificada, this);
		return this.lClassificada;
	}
}
