package lovera.imagem.manipulacao;

import static lovera.imagem.comum.Regras.validarNivelCinza;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public final class ManipulacaoImg {

	private static final int TAMANHO = 256;	
	
	public static final BufferedImage binarizar(BufferedImage img, int nivelCinza){
		validarNivelCinza(nivelCinza);
		
		int branco = 255;
		int preto = 0;
		
		int arrayBinario[] = new int[TAMANHO];
		for(int i = 0; i < TAMANHO; i++)
			if(i <= nivelCinza) arrayBinario[i] = preto;
			else arrayBinario[i] = branco;
		
		return transformarImgPeloArray(img, arrayBinario);
	}

	public static final BufferedImage copiarImg(BufferedImage img){
		return copiarImg(img, img.getType());
	}
	
	public static final BufferedImage copiarImg(BufferedImage img, int tipoBufferedImg){
		BufferedImage imgCopia = new BufferedImage(img.getWidth(), img.getHeight(), tipoBufferedImg);
		
		Graphics2D graphic = imgCopia.createGraphics();
		graphic.drawImage(img, 0, 0, null);
		graphic.dispose();
		
		return imgCopia;
	}
	
	public static final BufferedImage converterToCinza(BufferedImage img){
		int width = img.getWidth();
		int height = img.getHeight();
		
		BufferedImage imgCinza = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
		Graphics2D graphics = imgCinza.createGraphics();
		graphics.drawImage(img, 0, 0, width, height, null);
		
		ColorSpace csCinza = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorSpace csImgCinza = imgCinza.getColorModel().getColorSpace();
		ColorConvertOp op = new ColorConvertOp(csCinza, csImgCinza, null);
		op.filter(imgCinza, imgCinza);
		
		return imgCinza;
	}
	
	public static final BufferedImage inverterCores(BufferedImage img){
		int[] arrayInvertido = new int[TAMANHO];
		for(int i = 0; i < TAMANHO; i++) arrayInvertido[i] = 255 - i;
		
		return transformarImgPeloArray(img, arrayInvertido);
	}
	
	private static final BufferedImage transformarImgPeloArray(BufferedImage img, int[] array){
		BufferedImage imgRetorno = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		
		WritableRaster wRaster = imgRetorno.getRaster();
		Raster raster = img.getRaster();
		
		for(int i = 0; i < img.getHeight(); i++)
			for(int j = 0; j < img.getWidth(); j++)
				wRaster.setSample(j, i, 0, array[raster.getSample(j, i, 0)]);
			
		return imgRetorno;
	}
}