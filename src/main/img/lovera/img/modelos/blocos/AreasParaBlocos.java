package lovera.img.modelos.blocos;

import java.awt.Rectangle;
import java.util.List;

import lovera.comuns.contratos.CoordenadasArea;
import lovera.comuns.recursos.Regras;
import lovera.estatistica.grao.Estatistica;
import lovera.img.graos.AlturaSubset;
import lovera.img.modelos.floodfill.FloodFillCCs;

public class AreasParaBlocos implements CoordenadasArea{
	
	private List<Rectangle> listaAreas;
	
	public AreasParaBlocos(FloodFillCCs ffillCcs) {
		Regras.validarListaDeAreas(ffillCcs.getAreas(), ffillCcs.getClass());
		this.listaAreas = ffillCcs.getAreas();
	}

	public AreasParaBlocos gerarBlocos(){
		Estatistica estats = getEstatisticaDaAltura();
		List<AlturaSubset> altClassificadas = classificarAlturas(estats);
		List<Rectangle> areasFiltradas = filtrarAlturasClassificadas(altClassificadas);		
		this.listaAreas = gerarBlocos(areasFiltradas, estats);
		
		return this;
	}
	
	private Estatistica getEstatisticaDaAltura(){
		EstatisticaAltura estatsAltura = new EstatisticaAltura(this.listaAreas);
		return estatsAltura.gerarEstatistica()
						   .getEstatistica();
	}
	
	private List<AlturaSubset> classificarAlturas(Estatistica estats){
		ClassifAltura classificador = new ClassifAltura(this.listaAreas, estats);
		return classificador.classificarAreas()
							.getAlturasClassificadas();
	}
	
	private List<Rectangle> filtrarAlturasClassificadas(List<AlturaSubset> altClassificadas){
		FiltroSubset1 filtro = new FiltroSubset1(altClassificadas);
		return filtro.filtrarListaDeAreas()
					 .getAreas();
	}
	
	private List<Rectangle> gerarBlocos(List<Rectangle> listaAreas, Estatistica estats){
		GeradorDeBlocos gerador = new GeradorDeBlocos(listaAreas, estats);
		gerador.gerarBlocos();
		return gerador.getAreas();
	}

	@Override
	public List<Rectangle> getAreas() {
		Regras.validarListaDeAreas(this.listaAreas, this.getClass());
		return this.listaAreas;
	}
	
}
