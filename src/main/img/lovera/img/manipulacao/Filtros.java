package lovera.img.manipulacao;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Classe com metodos estaticos para filtragem de imagens.</br>
 * Usados pelas classes "modelos-imagens".
 * @author Lovera
 * @since 13/03/2016
 */
public final class Filtros {
	
	/**
	 * No Gaussiano é feito uma filtragem por media, porem se uma uma 
	 * uma função Gaussina bidimensional para o Kernel resultando em um
	 * peso mais alto para o pixel central. Usado para tirar ruidos e 
	 * é um filtro passa baixo.
	 */
	public static final BufferedImage gauss(BufferedImage img){

		float matrizGauss[] = new float[]{1/16f, 2/16f, 1/16f,
										  2/16f, 4/16f, 2/16f,
										  1/16f, 2/16f, 1/16f };
		
		return filtrar(img, matrizGauss);
	}
	
	/**
	 * O filtro laplaciano utiliza geralmente o caso 1. Que representa a derivação de
	 * 2º ordem das mudanças de nivel de cinza. Segundo Miranda, raramente usado para 
	 * detecção de bordas dado sua sensibilidade a ruidos. Por isso é feito antes o 
	 * Gaussiano. (JÁ TESTEI TAL AFIRMAÇÂO VERDADEIRA)
	 */
	public static final BufferedImage laplace(BufferedImage img){
		float[] matrizLaplace = new float[]{ 0f,  1f,  0f,
  			     							 1f, -4f,  1f,
  			     							 0f,  1f,  0f};
		
		return filtrar(img, matrizLaplace);
	}
	
	private static final BufferedImage filtrar(BufferedImage img, float[] matriz){		
		Raster raster = img.getRaster();
		Kernel filtroMedia = new Kernel(3, 3, matriz);
		ConvolveOp blur = new ConvolveOp(filtroMedia);
		WritableRaster wRaster = blur.filter(raster, null);			
		return new BufferedImage(img.getColorModel(), wRaster, false, null);
	}

}
