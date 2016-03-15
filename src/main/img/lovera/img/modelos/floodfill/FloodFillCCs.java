package lovera.img.modelos.floodfill;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.CoordenadasArea;
import lovera.comuns.recursos.Regras;
import lovera.img.comum.Pixel;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos.img.BinarizacaoImg;

//CCs Componentes Conectados
public final class FloodFillCCs implements CoordenadasArea{

	private List<RetanguloInfo> listaRI;

	private BufferedImage img;	
	
	public FloodFillCCs(BinarizacaoImg binarizacao) {
		Regras.validarBufferedImgCinza(binarizacao);		
		this.img = ManipulacaoImg.copiarImg(binarizacao.getImgTransformada());	
		this.listaRI = new ArrayList<>(); 
		
		floodfill();
		filtragemListaCoordenadas();
	}
	
	private void floodfill(){
		PontosCardeais pCardeais = new PontosCardeais();		
		WritableRaster wRaster = this.img.getRaster();
		
		for(int i = 0; i < this.img.getHeight(); i++)
			for(int j = 0; j < this.img.getWidth(); j++){				
				
				floodfillNoPonto(new Point(j, i) , wRaster, pCardeais);
				if(pCardeais.retanguloInfoValido())	this.listaRI.add(pCardeais.getAreaInfo());
				pCardeais.reset();
			}
		
		this.img = null;
	}
	
	public void floodfillNoPonto(Point ponto, WritableRaster wRaster, PontosCardeais pCardeais){
		int x = ponto.x;
		int y = ponto.y;
		int pixel = wRaster.getSample(x, y, 0);
		if(pixel == Pixel.VAZIO.getValor()) return;
		
		wRaster.setSample(x, y, 0, Pixel.VAZIO.getValor());
		pCardeais.inspecionarPonto(ponto);
		
		try{
			floodfillNoPonto(new Point(x, y + 1) , wRaster, pCardeais);			
		}catch(IndexOutOfBoundsException e){}
		
		try{
			floodfillNoPonto(new Point(x, y - 1) , wRaster, pCardeais);
		}catch(IndexOutOfBoundsException e){}
		
		try{
			floodfillNoPonto(new Point(x + 1, y) , wRaster, pCardeais);			
		}catch(IndexOutOfBoundsException e){}
		
		try{
			floodfillNoPonto(new Point(x - 1, y) , wRaster, pCardeais);			
		}catch(IndexOutOfBoundsException e){}
	}
	
	private void filtragemListaCoordenadas(){
		this.listaRI = RetangInfoUtils.filtrarRetangulosInfoContidos(this.listaRI);
	}
	
	@Override
	public List<Rectangle> getAreas() {
		List<Rectangle> listaAreas = RetangInfoUtils.listaDeRetangulosInfoParaArea(this.listaRI);
		Regras.validarListaDeAreas(listaAreas, this.getClass());
		return listaAreas;
	}
}
