package lovera.img.modelos.blocos;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.estatistica.grao.Estatistica;
import lovera.img.contratos.CoordenadasArea;
import lovera.img.contratos.CoordenadasPonto;
import lovera.img.graos.AlturaSubset;
import lovera.img.graos.BlocoComPonto;
import lovera.img.modelos.floodfill.FloodFillCCs;
import lovera.img.modelos.img.BinarizacaoImg;

public class AreasParaBlocos implements CoordenadasArea, CoordenadasPonto{
	
	private List<BlocoComPonto> listaBlocosCPontos;
	private List<Rectangle> listaTemp;
	
	private BinarizacaoImg binarizacao;
	
	public AreasParaBlocos(FloodFillCCs ffillCcs, BinarizacaoImg binarizacao) {
		Regras.validarListaDeAreas(ffillCcs.getAreas(), ffillCcs.getClass());
		Regras.validarBufferedImgCinza(binarizacao, this.getClass());
		
		this.listaTemp = ffillCcs.getAreas();
		this.binarizacao = binarizacao;
	}

	public AreasParaBlocos gerarBlocos(){
		Estatistica estats = getEstatisticaDaAltura();
		List<AlturaSubset> altClassificadas = classificarAlturas(estats);
		List<Rectangle> areas = filtrarAlturasClassificadas(altClassificadas);		
						areas = gerarBlocos(areas, estats);		
		this.listaBlocosCPontos = localizarCentroDosBlocos(areas);
		
		this.binarizacao = null;
		this.listaTemp   = null;
		return this;
	}
	
	private Estatistica getEstatisticaDaAltura(){
		EstatisticaAltura estatsAltura = new EstatisticaAltura(this.listaTemp);
		return estatsAltura.gerarEstatistica()
						   .getEstatistica();
	}
	
	private List<AlturaSubset> classificarAlturas(Estatistica estats){
		ClassifAltura classificador = new ClassifAltura(this.listaTemp, estats);
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
	
	private List<BlocoComPonto> localizarCentroDosBlocos(List<Rectangle> listaAreas){
		CentroDosBlocos centro = new CentroDosBlocos(binarizacao, listaAreas);
		centro.localizarCentros();
		return centro.getListaAreasComPonto();
	}

	@Override
	public List<Rectangle> getAreas() {
		Regras.validarListaDeBlocosComPonto(this.listaBlocosCPontos, this.getClass());
		List<Rectangle> listaAreas = new ArrayList<>(this.listaBlocosCPontos.size());
		this.listaBlocosCPontos.forEach((areaPonto) -> listaAreas.add(areaPonto.getArea()));
		return listaAreas;
	}

	@Override
	public List<Point> getCoordenadas() {
		Regras.validarListaDeBlocosComPonto(this.listaBlocosCPontos, this.getClass());
		List<Point> listaPontos = new ArrayList<>(this.listaBlocosCPontos.size());
		this.listaBlocosCPontos.forEach((areaPonto) -> listaPontos.add(areaPonto.getPonto()));
		return listaPontos;
	}
	
	public List<BlocoComPonto> getListaBlocosComPontos(){
		Regras.validarListaDeBlocosComPonto(this.listaBlocosCPontos, this.getClass());
		return this.listaBlocosCPontos;		
	}
	
}
