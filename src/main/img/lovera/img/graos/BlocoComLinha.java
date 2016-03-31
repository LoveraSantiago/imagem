package lovera.img.graos;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

/**
 * Objeto que armazena informações sobre os blocos que estao em volta dos CCs 'componentes conectados'. </br>
 * Armazena para cada bloco um objeto line. 
 * @author Lovera
 * @since 31/03/2016
 */
public class BlocoComLinha extends Area{
	
	private final Line2D linha;
	
	public BlocoComLinha(Rectangle area, Line2D linha) {
		super(area);
		this.linha = linha;
	}

	public Line2D getLinha() {
		return linha;
	}
}
