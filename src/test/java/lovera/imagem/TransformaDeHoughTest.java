package lovera.imagem;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import lovera.comuns.recursos.Imagens;
import lovera.img.hough.TransformadaDeHough;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.uniao.UniaoImgLinhas;

public class TransformaDeHoughTest {
	
	@Test
	public void deveDesenharLinhaHorizontal(){
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.LINHA_HORIZONTAL);
		executarTransformada(img, "tHorizontal");
	}
	
	@Test
	public void deveDesenharLinhaVertical(){
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.LINHA_VERTICAL);
		executarTransformada(img, "tVertical");
	}
	
	@Test
	public void deveDesenharLinhaDescendo(){
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.LINHA_DESCENDO);
		executarTransformada(img, "tDescendo");
	}
	
	@Test
	public void deveDesenharLinhaSubindo(){
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.LINHA_SUBINDO);
		executarTransformada(img, "tSubindo");
	}
	
	private void executarTransformada(BufferedImage img, String nomeArquivo){
		List<Line2D> linhas = new ArrayList<>();		
		
		TransformadaDeHough tr = new TransformadaDeHough(img, new Point(0, 0));
		tr.executar();
		linhas.add(tr.getLinhaHough());
		
		UniaoImgLinhas u = new UniaoImgLinhas(nomeArquivo, linhas, img);
		u.executarTransformacao();
		u.gravar();
		u.abrir();
	}
}
