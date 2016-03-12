package lovera.img.modelos;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import lovera.img.comum.Pixel;
import lovera.img.comum.Regras;
import lovera.img.contratos.Coordenadas;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos_img.BinarizacaoImg;

public final class FloodFillImg implements Coordenadas{

	private List<Rectangle> listaAreas;

	private BufferedImage img;	
	
	public FloodFillImg(BinarizacaoImg binarizacao) {
		Regras.validarBufferedImgCinza(binarizacao);		
		this.img = ManipulacaoImg.copiarImg(binarizacao.getImgTransformada());	
		this.listaAreas = new ArrayList<>(); 
		
		floodfill();
	}
	
	private void floodfill(){
		PontosCardeais pCardeais = new PontosCardeais();		
		WritableRaster wRaster = this.img.getRaster();
		
		for(int i = 0; i < this.img.getHeight(); i++)
			for(int j = 0; j < this.img.getWidth(); j++){				
				
				floodfillNoPonto(new Point(j, i) , wRaster, pCardeais);
				this.listaAreas.add(pCardeais.getArea());
				pCardeais.reset();
			}
		
		this.img = null;
	}
	
	public void floodfillNoPonto(Point ponto, WritableRaster wRaster, PontosCardeais pCardeais){
		int x = ponto.x;
		int y = ponto.y;
		int pixel = wRaster.getSample(x, y, 0);
		if(pixel == Pixel.VAZIO) return;
		
		wRaster.setSample(x, y, 1, 255);
		
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
	
	@Override
	public List<Rectangle> getAreas() {
		Regras.validarListaDeAreas(this.listaAreas);
		return this.listaAreas;
	}
}
