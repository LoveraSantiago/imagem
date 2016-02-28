package lovera.coisas.factorycoisas;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import lovera.coisas.coisas.FrequenciaDeCinza;

public final class FactoryFreqCinza {
	
	public static final FrequenciaDeCinza factory_FrequenciaDeCinza(BufferedImage img){
		int[] escalaCinza = new int[256];
		for(int i = 0; i < 256; i++) escalaCinza[i] = 0;
		
		Raster raster = img.getRaster();			
		for(int i = 0; i < img.getHeight(); i++)
			for(int j = 0; j < img.getWidth(); j++){
				int pixel = raster.getSample(j, i, 0);
				escalaCinza[pixel] = escalaCinza[pixel] + 1;
			}
		
		FrequenciaDeCinza frequencia = new FrequenciaDeCinza();
		frequencia.setEscalaCinza(escalaCinza);
		
		int cinzaMax = 0;		
		int maior = escalaCinza[0];
		int cinzaMin = 0;
		int menor = escalaCinza[0];
		for(int i = 0; i < escalaCinza.length; i++){
			try{
				if(maior < escalaCinza[i + 1]){
					cinzaMax = i + 1;
					maior = escalaCinza[i + 1];
				}
			}catch(IndexOutOfBoundsException e){}
			try{
				if(menor > escalaCinza[i + 1]){
					cinzaMin = i + 1;
					menor = escalaCinza[i + 1];
				}
			}catch(IndexOutOfBoundsException e){}
		}
		
		frequencia.setMaiorEscalaCinza(cinzaMax);
		frequencia.setMaiorFrequenciaCinza(maior);
		frequencia.setMenorEscalaCiza(cinzaMin);
		frequencia.setMenorFrequenciaCinza(menor);
		
		int amplitude = maior - menor;
		frequencia.setAmplitude(amplitude);
		
		return factory_FrequenciaDeCinzaDerivadas(frequencia);
	}
	
	private static FrequenciaDeCinza factory_FrequenciaDeCinzaDerivadas(FrequenciaDeCinza fCinza){
		int[] escalaCinza = fCinza.getEscalaCinza();
		int[] derivadas = new int[escalaCinza.length - 1];
		
		int cinzaMax = 0;		
		int maior = escalaCinza[0];
		int cinzaMin = 0;
		int menor = escalaCinza[0];
		for(int i = 0; i < derivadas.length; i++){
			derivadas[i] = escalaCinza[i + 1] - escalaCinza[i];
			
			if(maior < derivadas[i]){
				cinzaMax = i;
				maior = derivadas[i];
			}
			if(menor > derivadas[i]){
				cinzaMin = i;
				menor = escalaCinza[i + 1];
			}
		}
		
		fCinza.setEscalaDerivadas(derivadas);
		fCinza.setMaiorDerivadaEscalaCinza(cinzaMax);
		fCinza.setMaiorDerivadaCinza(maior);
		fCinza.setMenorDerivadaEscalaCinza(cinzaMin);
		fCinza.setMenorDerivadaCinza(menor);
		return fCinza;
	}

}
