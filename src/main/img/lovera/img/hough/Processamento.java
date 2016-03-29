package lovera.img.hough;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

class Processamento {
	
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
		
		/**
		 * Ajusta a linha encontrada pela transformada de Hough para o bloco da imagem.  
		 * @param linha : Line2d - linha encontrada pela transformada de Hough
		 * @param area : Rectangle - area que representa o bloco da imagem. 
		 * @param pontoCentral : Point - centro de gravidade da imagem.
		 * @return : Line2D - linha com coordenadas ajustadas ao bloco.
		 */
		public Line2D moverRetaPCentralDoBloco(Line2D linha, Rectangle area, Point pontoCentral){
			
			Line2D linhaMovida = recalcularLinha(linha, pontoCentral);
			EquacaoDaReta reta = linhaParaEquacaoDaReta(linhaMovida);
			Line2D linhaAjustada = ajustarRetaNaArea(reta, area, pontoCentral);
			return linhaAjustada;
		}
		
		/**
		 * A linha retornada pela transformada de Hough 'que possue coordenadas em relação a um ponto de origem (0,0)' é deslocada 
		 * levada para o ponto central do bloco.</br>
		 * O ponto central do bloco para ser a nova origem.
		 * @param linha : Line2d - linha encontrada pela transformada de Hough
		 * @param ponto : Point - centro de gravidade da imagem.
		 * @return linha : Line2D - linha com coordenadas de um novo ponto de origem.
		 */
		private Line2D recalcularLinha(Line2D linha, Point ponto){
			int x1 = (int)(linha.getX1() + ponto.x);
			int y1 = (int)(linha.getY1() + ponto.y);
			int x2 = (int)(linha.getX2() + ponto.x);
			int y2 = (int)(linha.getY2() + ponto.y);
			
			return new Line2D.Double(x1, y1, x2, y2);
		}
		
		private EquacaoDaReta linhaParaEquacaoDaReta(Line2D linha){
			return FactoryEquacaoDaReta.factory_EqDaReta(linha);
		}
		
		private Line2D ajustarRetaNaArea(EquacaoDaReta reta, Rectangle area, Point pontoCentral){
			
			int pHMin = area.x;
			int pHMax = area.x + area.width;
			
			int x1 = Integer.MAX_VALUE;
			int y1 = 0;
			int x2 = Integer.MIN_VALUE;
			int y2 = 0;
			
			for(int i = pHMin; i <= pHMax; i++){
				
				double resultado = (i * reta.getCoefAngular()) + reta.getIntercepto();//reta.x coef. angular reta.y intercepto
				boolean pertenceArea = resultadoDentroDaArea(resultado, area); 
				if(pertenceArea){
					
					if(i <= x1){
						x1 = i;
						y1 = (int) resultado;
					}
					
					if(i >= x2){
						x2 = i;
						y2 = (int) resultado;
					}
				}
			}			
			return new Line2D.Double(x1, y1, x2, y2);
		}
		
		private boolean resultadoDentroDaArea(double resultado, Rectangle area){
			if(resultado < area.y) return false;
			if(resultado > (area.y + area.height)) return false;
			return true;
		}
		
		private Line2D polarParaLinha(Point ponto){
			double pt1X = ponto.x * arrayCos[ponto.y];
			double pt1Y = ponto.x * arraySen[ponto.y];
			
			double pt2X = ponto.x / arrayCos[ponto.y];
			double pt2Y = 0;
			
			EquacaoDaReta reta = FactoryEquacaoDaReta.factory_EqDaReta(pt1X, pt1Y, pt2X, pt2Y);
			
			int x1 = 0;
			int y1 = (int) (Math.round((x1 * reta.getCoefAngular()) + (reta.getIntercepto())));
			int x2 = 50;
			int y2 = (int) (Math.round((x2 * reta.getCoefAngular()) + (reta.getIntercepto())));
			
			return new Line2D.Double(x1, y1, x2, y2);
		}
	}
}
