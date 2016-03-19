package lovera.img.modelos.blocos;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.comum.Pixel;
import lovera.img.graos.AreaPonto;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos.img.BinarizacaoImg;

class CentroDosBlocos {
	
	private BufferedImage img;
	
	private List<AreaPonto> listaAreaPontos;
	private List<Rectangle> listaAreas;
	
	public CentroDosBlocos(BinarizacaoImg binarizado, List<Rectangle> listaAreas) {
		this.img = binarizado.getImgTransformada();
		this.listaAreas = listaAreas;
		this.listaAreaPontos = new ArrayList<>(listaAreas.size());
	}
	
	public CentroDosBlocos localizarCentros(){
		this.listaAreas.forEach((area) -> {
			
			BufferedImage imgRecortada = recortarImg(area);
			Point ponto = getCentroDaImagem(imgRecortada, area);
			this.listaAreaPontos.add(new AreaPonto(area, ponto));
		});
		
		this.listaAreas = null;
		return this;
	}
	
	private Point getCentroDaImagem(BufferedImage imgRecortada, Rectangle area){
		int totalx = 0; 
		int totaly = 0;
		int contador = 0;
		
		WritableRaster raster = imgRecortada.getRaster();
		for(int i = 0; i < imgRecortada.getHeight(); i++)
			for(int j = 0; j < imgRecortada.getWidth(); j++){
				int sample = raster.getSample(j, i, 0);
				if(sample == Pixel.PREENCHIDO.getValor()){
					totalx += area.x + j;
					totaly += area.y + i;
					contador++;
				}
			}
		
		int mediax = (int) (Math.round(totalx / contador));
		int mediay = (int) (Math.round(totaly / contador));
		
		return new Point(mediax, mediay);
	}
	
	private BufferedImage recortarImg(Rectangle area){
		return ManipulacaoImg.recortar(this.img, area);
	}
}
