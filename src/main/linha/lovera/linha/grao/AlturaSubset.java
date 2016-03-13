package lovera.linha.grao;

import java.awt.Rectangle;

import lovera.linha.comum.Subset;

public class AlturaSubset {
	
	private final Subset subset;
	private final Rectangle area;
	
	public AlturaSubset(Subset subset, Rectangle area) {
		this.subset = subset;
		this.area = area;
	}

	public Subset getSubset() {
		return subset;
	}

	public Rectangle getArea() {
		return area;
	}
	
	@Override
	public String toString() {		
		return "Retang ( " + this.area.x + ", " + this.area.y + ", " + 
				this.area.width + ", " + this.area.height + ") - Subset : " + this.subset.getSet();
	}
}
