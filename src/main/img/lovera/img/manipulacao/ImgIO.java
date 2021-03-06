package lovera.img.manipulacao;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.Imagens;
import lovera.comuns.recursos.TipoImagem;

/**
 * Classe com metodos IO (entrada - saida) para imagens. 
 * @author Lovera
 * @since 13/03/2016
 */
public final class ImgIO {
	
	private static final Logger log = Logger.getLogger(ImgIO.class);
	
	public static final void abrirImg(String endImg){
		if(!Desktop.isDesktopSupported())
			throw new RuntimeException("Desktop não é possivel abrir a imagem.");
		
		File arquivo = new File(endImg);
		if(!arquivo.exists())
			throw new IllegalArgumentException("Arquivo para abrir não existe.");
		
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(arquivo);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public static final BufferedImage carregarImg_modoIO(Imagens imagem){
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imagem.getEndereco()));
		} catch (IOException e) {			
			e.printStackTrace();
			log.error("Img não carregada! End: " + imagem.getEndereco() + " Modo: IO");
		}
		return img;
	}
	
	public static final BufferedImage carregarImg_modoMediaTracker(Imagens imagem) {		
		return carregarImg_modoMediaTracker(imagem, new UtilComponent());
	}

	public static final BufferedImage carregarImg_modoMediaTracker(Imagens imagem, Component cpt) {
		return carregarImg_modoMediaTracker(imagem.getEndereco(), cpt);
	}

	public static final BufferedImage carregarImg_modoMediaTracker(String endereco) {
		return carregarImg_modoMediaTracker(endereco, new UtilComponent());
	}
	
	public static final BufferedImage carregarImg_modoMediaTracker(String endereco, Component cpt) {
		Image image = Toolkit.getDefaultToolkit().getImage(endereco);
		MediaTracker mt = new MediaTracker(cpt);
		mt.addImage(image, 0);
		
		try {
			mt.waitForID(0);
		} catch (InterruptedException e) {			
			e.printStackTrace();
			log.error("Img não carregada! End: " + endereco + " Modo: MediaTracker");
		}
		
		BufferedImage img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
		Graphics graphics = img.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();		
		return img;
	}
	
	public static final String gravarImg(BufferedImage img, Endereco endereco, String nomeArquivo, TipoImagem extensao){			
		String enderecoCpto = validarNomeArquivo(endereco.getEndereco() + "/" + nomeArquivo + "." + extensao.getTipo());
		 try {
			 File arquivo = new File(enderecoCpto);
			 ImageIO.write(img, extensao.getTipo(), arquivo);
			 
			 log.info("Arquivo salvo como: " + arquivo.getPath());
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return enderecoCpto;
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
