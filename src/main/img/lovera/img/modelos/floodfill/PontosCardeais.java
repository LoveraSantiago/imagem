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
	
	/**
	 * 
	 * @return
	 */
	public Rectangle getArea(){
		return new Rectangle(this.oeste, this.norte, this.leste - this.oeste, this.sul - this.norte);
	}
	
	public void reset(){
		this.norte = this.oeste = Integer.MAX_VALUE;
		this.sul   = this.leste = Integer.MIN_VALUE;
	}

}
