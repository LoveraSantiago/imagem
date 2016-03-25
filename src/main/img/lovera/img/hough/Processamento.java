package lovera.img.hough;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Processamento {
	
	public Pre pre;
	public Pos pos;
	
	public Processamento() {
		this.pre = new Processamento.Pre();
		this.pos = new Processamento.Pos();
	}
	
	public class Pre {
		
		public Point moverPontoEmRelacaoOrigem(Rectangle area, Point pontoOriginal){
			int origemX = pontoOriginal.x - area.x;
			int origemY = pontoOriginal.y - area.y;
			
			return new Point(origemX, origemY);
		}
	}
	
	public class Pos {
		
		public Line2D moverRetaPCentralDoBloco(Line2D linha, Rectangle area, Point pontoCentral){
			
			Line2D linhaMovida = recalcularLinha(linha, pontoCentral);
			Point reta = linhaParaEquacaoDaReta(linhaMovida);
			Line2D linhaAjustada = ajustarRetaNaArea(reta);
			return linhaAjustada;
		}
		
		private Line2D recalcularLinha(Line2D linha, Point ponto){
			int x1 = (int)(linha.getX1() + ponto.x);
			int y1 = (int)(linha.getY1() + ponto.y);
			int x2 = (int)(linha.getX2() + ponto.x);
			int y2 = (int)(linha.getY2() + ponto.y);
			
			return new Line2D.Double(x1, y1, x2, y2);
		}
		
		private Point linhaParaEquacaoDaReta(Line2D linha){
			return EquacaoDaReta.calcularEquacaoDaReta(linha);
		}
		
		private Line2D ajustarRetaNaArea(Point reta){
			return null;
		}
	}
}
