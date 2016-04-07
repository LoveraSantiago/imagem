package lovera.img.hough;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.contratos.Executador;
import lovera.img.graos.BlocoComPonto;
import lovera.img.modelos.blocos.AreasParaBlocos;
import lovera.img.modelos.img.BinarizacaoImg;

public class HoughDosBlocosTemp implements Executador{
	
	private List<BlocoComPonto> blocos;
	private List<CoordenadaPolarTemp> listaPolar;
	
	private List<Point2D> listaDePontos;
	
	public HoughDosBlocosTemp(AreasParaBlocos areas, BinarizacaoImg binarizacao) {
		Regras.validarBufferedImgCinza(binarizacao, this.getClass());
		Regras.validarListaDeBlocosComPonto(areas, this.getClass());		
		
		this.blocos = areas.getListaBlocosComPontos();
		this.listaDePontos = areas.getCoordenadas();
		this.listaPolar = new ArrayList<>(this.blocos.size());
	}
	
		
	@Override
	public HoughDosBlocosTemp executar(){
		TransformadaDeHoughTemp transformada = new TransformadaDeHoughTemp(this.listaDePontos, );
		transformada.executar();
		
		
		this.blocos.forEach((blocoPonto) -> {
			
			Point2D pCartesiano = blocoPonto.getPonto();
			CoordenadaPolarTemp pPolar = TransformacaoCoordenadas.cartesianoParaPolar(pCartesiano);
			this.listaPolar.add(pPolar);
		});
		
		this.blocos = null;
		return this;
	}

	public List<CoordenadaPolarTemp> getListaPolar() {
		Regras.validarListaCoordenadasPolar(this.listaPolar, this.getClass());		
		return listaPolar;
	}
}
