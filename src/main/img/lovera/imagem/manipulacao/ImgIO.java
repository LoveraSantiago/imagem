package lovera.imagem.manipulacao;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lovera.imagem.recursos.Endereco;
import lovera.imagem.recursos.Imagens;
import lovera.imagem.recursos.TipoImagem;

public final class ImgIO {
	
	public static final BufferedImage carregarImg_modoIO(Imagens imagem){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imagem.getEndereco()));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return img;
	}
	
	public static final BufferedImage carregarImg_modoMediaTracker(Imagens imagem, Component cpt) {
		Image image = Toolkit.getDefaultToolkit().getImage(imagem.getEndereco());
		MediaTracker mt = new MediaTracker(cpt);
		mt.addImage(image, 0);
		
		try {
			mt.waitForID(0);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		BufferedImage img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
		Graphics graphics = img.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		return img;
	}
	
	public static final BufferedImage carregarImg_modoMediaTracker(Imagens imagem) {		
		return carregarImg_modoMediaTracker(imagem, new UtilComponent());
	}
	
	public static final void gravarImg(BufferedImage img, Endereco endereco, String nomeArquivo, TipoImagem extensao){			
		String enderecoCpto = validarNomeArquivo(endereco.getEndereco() + "/" + nomeArquivo + "." + extensao.getTipo());
		 try {
			ImageIO.write(img, extensao.getTipo(), new File(enderecoCpto));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	private static String validarNomeArquivo(String endereco){
		String endTemp  = endereco;
		int contador = 0;
		while(new File(endTemp).exists()){
			int ponto = endereco.lastIndexOf(".");
			if(ponto < 0) throw new IllegalArgumentException("Erro no nome do arquivo.");
			contador++;
			endTemp = endereco.substring(0, ponto) + "Rep_" + String.format("%02d", contador) + endereco.substring(ponto);
		}			return endTemp;			
	}

}
