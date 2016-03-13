package lovera.img.modelos.floodfill;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

class RetangInfoUtils {
	
	public final static List<Rectangle> listaDeRetangulosInfoParaArea(List<RetanguloInfo> listaPCardeais){
		List<Rectangle> listaArea = new ArrayList<>(listaPCardeais.size());		
		listaPCardeais.forEach((area)-> listaArea.add(area.area));		
		return listaArea;
	}
	
	public final static List<RetanguloInfo> filtrarRetangulosInfoContidos(List<RetanguloInfo> lista){
		List<RetanguloInfo> listaOrdenada = ordenarLista(lista);
		
		varrendoArray:
		for(int i = listaOrdenada.size() - 1; i >=0; i--){
			
			try{
				
				RetanguloInfo rInfo = listaOrdenada.get(i);
				
				for(int j = 0; j < listaOrdenada.size(); j++){
					
					RetanguloInfo rComp = listaOrdenada.get(j);
					if(rInfo == rComp) continue;
					
					boolean pInicio = rInfo.p1.x >= rComp.p1.x && rInfo.p1.y >= rComp.p1.y;
					boolean pFinal  = rInfo.p2.x <= rComp.p2.x && rInfo.p2.y <= rComp.p2.y;
					
					if(pInicio && pFinal){
						listaOrdenada.remove(i);
						continue varrendoArray;
					}					
				}
			}
			catch(IndexOutOfBoundsException e){
				break varrendoArray;
			}
		}
		return listaOrdenada;
	}
	
	private final static List<RetanguloInfo> ordenarLista(List<RetanguloInfo> lista){
		lista.sort((rInf1, rInf2) -> (int)((rInf2.area.height + rInf2.area.width) - (rInf1.area.height + rInf1.area.width)));
		return lista;		
	}
}
