package lovera.img.graos;

import java.awt.Rectangle;

import lovera.img.comum.Subset;

/**
 * Objeto que armazena informações sobre os blocos que estao em volta dos CCs 'componentes conectados'. </br>
 * Há 3 subsets conforme enum Subset. Cada bloco ou area 'Rectangle' é classificado com um subset. 
 * @author Lovera
 * @since 29/03/2016
 */
public class AlturaSubset extends Area{
	
	private final Subset subset;
	
	public AlturaSubset(Subset subset, Rectangle area) {
		super(area);
		this.subset = subset;
	}

	public Subset getSubset() {
		return subset;
	}
	
	@Override
	public String toString() {		
		return "Retang ( " + super.area.x + ", " + super.area.y + ", " + 
				super.area.width + ", " + super.area.height + ") - Subset : " + this.subset.getSet();
	}
}
