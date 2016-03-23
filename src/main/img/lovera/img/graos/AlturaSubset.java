package lovera.img.graos;

import java.awt.Rectangle;

import lovera.img.comum.Subset;

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
