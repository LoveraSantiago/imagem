package lovera.linha.modelos;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.estatistica.descritiva.Estatistica;
import lovera.img.contratos.Coordenadas;
import lovera.linha.comum.Subset;

public class ClassificadorAltura {
	
	private final double altMedia;
	
	private List<Rectangle> listaAreas;
	
	public ClassificadorAltura(Coordenadas coordenadas, Estatistica estats) {
		this.listaAreas = coordenadas.getAreas();
		this.altMedia   = estats.getMedia();
	}
	
	public List<AlturaClasse> classificarAreas(){
		List<AlturaClasse> lClassificada = new ArrayList<>(this.listaAreas.size());
		this.listaAreas.forEach((area) -> lClassificada.add(new AlturaClasse(classificarSubset(area), area)));
		return null;
	}
	
	private int classificarSubset(Rectangle area){
		if(casoSubset1(area)) return Subset.S1;
		return 0;
	}
	
	private boolean casoSubset1(Rectangle area){
		
		boolean sentenca1 = ((0.5 * this.altMedia) <= area.height) && (area.height < (3 * this.altMedia));
		boolean sentenca2 = ((0.5 * this.altMedia) <= area.width);
		return sentenca1 && sentenca2;
	}

}
