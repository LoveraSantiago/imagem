package lovera.img.modelos.floodfill;

import java.awt.Point;
import java.awt.Rectangle;

class PontosCardeais {
	
	private int norte;
	private int sul;
	private int leste;
	private int oeste;
	
	public PontosCardeais() {
		reset();
	}
	
	public void inspecionarPonto(Point ponto){
		this.norte = Math.min(this.norte, ponto.y);
		this.sul = Math.max(this.sul, ponto.y);
		this.oeste = Math.min(this.oeste, ponto.x);
		this.leste = Math.max(this.leste, ponto.x);		
	}
	
	public boolean retanguloInfoValido(){
		if(this.norte == this.sul && this.oeste == this.leste) return false;
		
		if((this.leste - this.oeste) == 0) return false;
		if((this.sul   - this.norte) == 0) return false;
		
		if(this.norte == Integer.MAX_VALUE && this.oeste == Integer.MAX_VALUE) return false;
		if(this.sul   == Integer.MIN_VALUE && this.leste == Integer.MIN_VALUE) return false;
		
		return true;
	}
	
	public RetanguloInfo getAreaInfo(){
		Point p1 = new Point(this.oeste, this.norte);
		Point p2 = new Point(this.leste, this.sul);
		Rectangle area = new Rectangle(this.oeste, this.norte, this.leste - this.oeste, this.sul - this.norte);
		return new RetanguloInfo(p1, p2, area);
	}
	
	public void reset(){
		this.norte = this.oeste = Integer.MAX_VALUE;
		this.sul   = this.leste = Integer.MIN_VALUE;
	}
}
