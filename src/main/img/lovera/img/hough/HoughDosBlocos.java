package lovera.img.hough;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.contratos.CoordenadasLinhas;
import lovera.img.contratos.Executor;
import lovera.img.debug.DebugImgModelo;
import lovera.img.graos.BlocoComPonto;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos.blocos.AreasParaBlocos;
import lovera.img.modelos.img.BinarizacaoImg;

public class HoughDosBlocos implements CoordenadasLinhas, Executor{
	
	private List<BlocoComPonto> blocos;
	private BufferedImage img;
	private List<Line2D> listaLinhas;
	
	public HoughDosBlocos(AreasParaBlocos areas, BinarizacaoImg binarizacao) {
		Regras.validarBufferedImgCinza(binarizacao, this.getClass());
		Regras.validarListaDeBlocosComPonto(areas, this.getClass());
		
		this.blocos = areas.getListaBlocosComPontos();
		this.img = binarizacao.getImgTransformada();
		this.listaLinhas = new ArrayList<>(this.blocos.size());
	}
	
	@Override
	public HoughDosBlocos executar() {
		gerarHoughNosBlocos();
		return this;
	}
	
	private void gerarHoughNosBlocos(){
		
		Executor transformadaH = new TransformadaDeHough();//Para carregar blocos estaticos
		Processamento processamento = new Processamento();
		
		for(BlocoComPonto blocoPt : this.blocos){
			
			BufferedImage imgRecortada = recortarImgParaArea(blocoPt.getArea());	
//			DebugImgModelo.debugarImg(imgRecortada, "imgRecorte", true);
			Point pROrigem = processamento.pre.moverPontoEmRelacaoOrigem(blocoPt.getArea(), blocoPt.getPonto());
			
			transformadaH = new TransformadaDeHough(imgRecortada, pROrigem);
			transformadaH.executar();
			Line2D linha = ((TransformadaDeHough) transformadaH).getLinhaHough();
			System.out.println("Hough linha " + linha.getX1() + ", " + linha.getY1() + ", " + linha.getX2() + ", " + linha.getY2());
			
			linha = processamento.pos.moverRetaPCentralDoBloco(linha, blocoPt.getArea(), blocoPt.getPonto());
			
			this.listaLinhas.add(linha);
		}
			
		this.blocos = null;
		this.img    = null;
	}
	
	private BufferedImage recortarImgParaArea(Rectangle area){
		return ManipulacaoImg.recortar(this.img, area);
	}

	@Override
	public List<Line2D> getLinhas() {
		Regras.validarListaDeLinhas(this.listaLinhas, this.getClass());
		return this.listaLinhas;
	}
}
