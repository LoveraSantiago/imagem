package lovera.img.graos;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Objeto que armazena informações sobre os blocos que estao em volta dos CCs 'componentes conectados'. </br>
 * Armazena para cada bloco um ponto, (geralmente o centro de gravidade)
 * @author Lovera
 * @since 01/04/2016
 */
public class BlocoComPonto extends Area{
	
	private final Point ponto;	
	
	public BlocoComPonto(Rectangle area, Point ponto) {
		super(area);
		this.ponto = ponto;
	}
	
	public Point getPonto() {
		return ponto;
	}
}
