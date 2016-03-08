package lovera.img.modelos;

import java.awt.Point;
import java.awt.Rectangle;

public class PontosCardeais {
	
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
	
	public Rectangle getArea(){
		return new Rectangle(this.norte, this.oeste, this.leste - this.oeste, this.sul - this.norte);
	}
	
	public void reset(){
		this.norte = this.sul = this.leste = this.oeste = 0;
	}

}
