package lovera.img.debug;

import static lovera.img.manipulacao.ImgIO.abrirImg;
import static lovera.img.manipulacao.ImgIO.gravarImg;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;;

/**
 * Classe para ajudar na debugagem salvando e abrindo a img no desktop.</br> 
 * Para fins de vizualizar mudancas na imagem.
 * @author Lovera
 * @since 12/03/2016
 */
public final class DebugImgModelo {
	
	public static final void debugarImg(BufferedImage img, String nome){
		nome = nome == null ? "" : nome;		
		String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy-hhmmss"));
		String end = gravarImg(img, Endereco.TESTES, "debug_" + nome + "_" + data, TipoImagem.PNG);
		abrirImg(end);
	}

}
