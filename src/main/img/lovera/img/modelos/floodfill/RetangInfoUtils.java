package lovera.img.modelos.floodfill;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

class RetangInfoUtils {
	
	public static List<Rectangle> listaDeRetangulosInfoParaArea(List<RetanguloInfo> listaPCardeais){
		List<Rectangle> listaArea = new ArrayList<>();		
		listaPCardeais.forEach((area)-> listaArea.add(area.area));		
		return listaArea;
	}

}
