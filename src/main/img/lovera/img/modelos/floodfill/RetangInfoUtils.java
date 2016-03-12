package lovera.img.modelos.floodfill;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

class RetangInfoUtils {
	
	//VER  ONDE MAIS DA P USAR RECURSOS JAVA 8	
	public static List<Rectangle> listaDeRetangulosInfoParaArea(List<RetanguloInfo> listaPCardeais){
		List<Rectangle> listaArea = new ArrayList<>();		
		listaPCardeais.forEach((area)-> listaArea.add(area.area));		
		return listaArea;
	}

}
