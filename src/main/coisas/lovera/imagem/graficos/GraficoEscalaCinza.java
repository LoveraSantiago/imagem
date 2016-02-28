package lovera.imagem.graficos;

import static java.awt.Color.red;
import static java.awt.Color.yellow;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import lovera.imagem.coisas.FrequenciaDeCinza;
import lovera.imagem.contratos.Gravavel;

//IMPLEMENTAR GRAVAVEL
public final class GraficoEscalaCinza implements Gravavel{
	
BufferedImage grafico;
	//REVER ESCALA DO GRAFICO
	public GraficoEscalaCinza(FrequenciaDeCinza freqCinza) {
		gerarGrafico(freqCinza);
	}
	
	private void gerarGrafico(FrequenciaDeCinza freqCinza){
		int[] escalaCinza = freqCinza.getEscalaCinza();
		
		int linha = 10;
		int espaco = 5;
		
		int alturaImg = escalaCinza.length * (espaco + linha + espaco);
		int larguraImg = ((espaco + linha + espaco) * 2) + (freqCinza.getMaiorFrquenciaCinza() / 1000) + ((espaco + linha + espaco) * 2);
		this.grafico = new BufferedImage(alturaImg, larguraImg, TYPE_INT_ARGB);
		
		Graphics2D graphic = this.grafico.createGraphics();
		
		int posY = linha + espaco;
		for(int i = 0; i < escalaCinza.length; i++){
			graphic.setColor(red);
			graphic.draw(new Rectangle(9, posY - (linha) - 1, (linha * 2) + 2, linha + 2));
			graphic.setColor(new Color(i, i, i));
			graphic.fill(new Rectangle(9 + 1, (posY - (linha) - 1) + 1, ((linha * 2) + 2) - 1, (linha + 2)- 1));
			
			graphic.setColor(red);
			graphic.drawString(String.format("%03d", i), 10, posY);			
			
			int valorCinza = escalaCinza[i] / 1000;
			graphic.draw(new Rectangle(35, posY - (linha), valorCinza - 1, linha));
			graphic.setColor(new Color(i, i, i));
			graphic.fill(new Rectangle(35 + 1, (posY - (linha)) + 1, valorCinza - 1, linha - 1));
			
			graphic.setColor(yellow);
			graphic.drawString(String.valueOf(escalaCinza[i]), 40, posY);
			
			posY = posY + linha + espaco; 
		}
	}
	
	@Override
	public void gravar() {
				
	}

	public BufferedImage getGrafico(){
		return this.grafico;
	}

}
