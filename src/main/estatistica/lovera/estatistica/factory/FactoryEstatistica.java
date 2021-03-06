package lovera.estatistica.factory;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import lovera.estatistica.grao.Estatistica;

public final class FactoryEstatistica {
	
	public static final Estatistica factory_Estatistica(BufferedImage img){
		DescriptiveStatistics stats = new DescriptiveStatistics();
		Raster raster = img.getRaster();
		
		for(int i = 0; i < img.getHeight(); i++)
			for(int j = 0; j < img.getWidth(); j++){
				stats.addValue(raster.getSample(j, i, 0));
			}
		
		return descriptiveStatisticsParaEstatistica(stats);
	}
	
	public static final Estatistica factory_EstatisticaAltura(List<Rectangle> lista){
		DescriptiveStatistics stats = new DescriptiveStatistics();
		lista.forEach((rect) -> stats.addValue(rect.height));
		
		return descriptiveStatisticsParaEstatistica(stats);
	}
	
	private static final Estatistica descriptiveStatisticsParaEstatistica(DescriptiveStatistics stats){
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
