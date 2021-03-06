package lovera.img.modelos.blocos;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.estatistica.contratos.EstatsDesc;
import lovera.estatistica.grao.Estatistica;
import lovera.img.contratos.CoordAClassificadas;
import lovera.img.contratos.CoordenadasArea;
import lovera.img.contratos.CoordenadasPonto;
import lovera.img.contratos.Executador;
import lovera.img.graos.AreaSubset;
import lovera.img.graos.BlocoComPonto;
import lovera.img.modelos.floodfill.FloodFillCCs;
import lovera.img.modelos.img.BinarizacaoImg;

/**
 * Classe que executa a transformação de areas "Rectangles" da imagem para blocos.</br>
 * - Recebe as areas dos componentes conectados.</br> 
 * - Utiliza de estatistica para pegar a altura media desses retangulos.</br>
 * - Classifica em um 3 tipos de subsets.</br>
 * - Realiza a filtragem para os subsets1.</br>
 * - Divide as areas dos componentes conectados em blocos.</br>
 * - Marca o ponto de centro de gravidade para cada bloco. 
 * @author Lovera
 * @since 05/04/2016
 */
public class AreasParaBlocos implements CoordenadasArea, CoordenadasPonto, Executador{
	
	private double alturaMediaBloco;
	
	private List<BlocoComPonto> listaBlocosCPontos;
	private List<Rectangle> listaTemp;
	
	private BinarizacaoImg binarizacao;
	
	public AreasParaBlocos(FloodFillCCs ffillCcs, BinarizacaoImg binarizacao) {
		Regras.validarListaDeAreas(ffillCcs.getAreas(), ffillCcs.getClass());
		Regras.validarBufferedImgCinza(binarizacao, this.getClass());
		
		this.listaTemp = ffillCcs.getAreas();
		this.binarizacao = binarizacao;
	}
	
	@Override
	public AreasParaBlocos executar() {
		gerarBlocos();
		return this;
	}

	private void gerarBlocos(){
		Estatistica estats = getEstatisticaDaAltura();		
		setAlturaMediaBloco(estats);
		
		List<AreaSubset> altClassificadas = classificarAlturas(estats);
		List<Rectangle> areas = filtrarAlturasClassificadas(altClassificadas);		
						areas = gerarBlocos(areas, estats);		
		this.listaBlocosCPontos = localizarCentroDosBlocos(areas);
		
		this.binarizacao = null;
		this.listaTemp   = null;
	}
	
	private Estatistica getEstatisticaDaAltura(){
		EstatsDesc estatsAltura = new EstatisticaAltura(this.listaTemp);
		return estatsAltura.gerarEstatistica()
						   .getEstatistica();
	}
	
	private List<AreaSubset> classificarAlturas(Estatistica estats){
		Executador classificador = new ClassifAltura(this.listaTemp, estats);
		classificador.executar();
		return ((CoordAClassificadas) classificador).getListaAreaClassificadas();
	}
	
	private List<Rectangle> filtrarAlturasClassificadas(List<AreaSubset> altClassificadas){
		Executador filtro = new FiltroSubset1(altClassificadas);
		filtro.executar();
		return ((CoordenadasArea) filtro).getAreas();
	}
	
	private List<Rectangle> gerarBlocos(List<Rectangle> listaAreas, Estatistica estats){
		Executador gerador = new GeradorDeBlocos(listaAreas, estats);
		gerador.executar();
		return ((CoordenadasArea) gerador).getAreas();
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
	public List<Point2D> getCoordenadas() {
		Regras.validarListaDeBlocosComPonto(this.listaBlocosCPontos, this.getClass());
		List<Point2D> listaPontos = new ArrayList<>(this.listaBlocosCPontos.size());
		this.listaBlocosCPontos.forEach((areaPonto) -> listaPontos.add(areaPonto.getPonto()));
		return listaPontos;
	}
	
	public List<BlocoComPonto> getListaBlocosComPontos(){
		Regras.validarListaDeBlocosComPonto(this.listaBlocosCPontos, this.getClass());
		return this.listaBlocosCPontos;		
	}

	public double getAlturaMediaBloco() {
		return alturaMediaBloco;
	}

	private void setAlturaMediaBloco(Estatistica estats) {
		this.alturaMediaBloco = estats.getMedia();
	}
}
