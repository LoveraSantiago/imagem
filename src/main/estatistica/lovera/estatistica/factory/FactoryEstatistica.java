package lovera.estatistica.factory;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import lovera.estatistica.descritiva.Estatistica;

public final class FactoryEstatistica {
	
	public static final Estatistica factory_Estatistica(BufferedImage img){
		DescriptiveStatistics stats = new DescriptiveStatistics();
		Raster raster = img.getRaster();
		
		for(int i = 0; i < img.getHeight(); i++)
			for(int j = 0; j < img.getWidth(); j++){
				stats.addValue(raster.getSample(j, i, 0));
			}
		
		Estatistica estatistica = new Estatistica();
		estatistica.setKurtosis(stats.getKurtosis());
		estatistica.setMaximo(stats.getMax());
		estatistica.setMedia(stats.getMean());
		estatistica.setMinimo(stats.getMin());
		estatistica.setSkewnees(stats.getSkewness());
		estatistica.setVariancia(stats.getVariance());
		estatistica.setDesvioMedio(stats.getStandardDeviation());
		estatistica.setDesvio(Math.sqrt(stats.getVariance()));
				
		double coefVariacao = (stats.getStandardDeviation() / stats.getMean()) * 100.0;
		estatistica.setCoefVariacao(coefVariacao);
		return estatistica;
	}

}
