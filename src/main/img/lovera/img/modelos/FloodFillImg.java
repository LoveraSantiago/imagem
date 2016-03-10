package lovera.img.modelos;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.List;

import lovera.img.comum.Pixel;
import lovera.img.comum.Regras;
import lovera.img.contratos.Coordenadas;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos_img.BinarizacaoImg;

public final class FloodFillImg implements Coordenadas{
	//https://en.wikipedia.org/wiki/Flood_fill
	//https://www.google.com.br/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=java+flood+fill+stack+overflow
	BufferedImage img;
	
	PontosCardeais pCardeais;
	List<Rectangle> listaRetangulos;
	
	public FloodFillImg(BinarizacaoImg binarizacao) {
		Regras.validarBufferedImgCinza(binarizacao);
		
		this.img = ManipulacaoImg.copiarImg(binarizacao.getImgTransformada());
		this.pCardeais = new PontosCardeais();
		
		floodfill();
	}
	
	private void floodfill(){
		WritableRaster wRaster = this.img.getRaster();
		for(int i = 0; i < this.img.getHeight(); i++)
			for(int j = 0; j < this.img.getWidth(); j++){				
				floodfillNoPonto(new Point(j, i) , wRaster);
				listaRetangulos.add(pCardeais.getArea());
				pCardeais.reset();
			}
	}
	
	public void floodfillNoPonto(Point ponto, WritableRaster wRaster){
		int pixel = wRaster.getSample(ponto.x, ponto.y, 0);
		if(pixel == Pixel.VAZIO) return;
		
		wRaster.setSample(ponto.x, ponto.y, 0, Pixel.VAZIO);
		
		this.pCardeais.inspecionarPonto(ponto);
		
		try{
			floodfillNoPonto(new Point(ponto.x, ponto.y + 1) , wRaster);			
		}catch(IndexOutOfBoundsException e){}
		
		try{
			floodfillNoPonto(new Point(ponto.x, ponto.y - 1) , wRaster);
		}catch(IndexOutOfBoundsException e){}
		
		try{
			floodfillNoPonto(new Point(ponto.x + 1, ponto.y) , wRaster);			
		}catch(IndexOutOfBoundsException e){}
		
		try{
			floodfillNoPonto(new Point(ponto.x - 1, ponto.y) , wRaster);			
		}catch(IndexOutOfBoundsException e){}
	}
	
	@Override
	public List<Rectangle> getAreas() {
		Regras.validarListaDeAreas(this.listaRetangulos);
		return this.listaRetangulos;
	}
}
