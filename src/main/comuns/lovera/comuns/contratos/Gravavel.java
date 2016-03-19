package lovera.comuns.contratos;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.Regras;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.manipulacao.ImgIO;

/**
 * Interface para objetos que são gravaveis.
 * @author Lovera
 * @since 19/03/2016
 */
public interface Gravavel {
	
	void gravar();
	
	/**
	 * Metodo pai para gravar imagens. Usado como herança.
	 * @param img - imagem a ser gravada.
	 * @param nomeArquivo - nome do arquivo a ser gravado na pasta de testes na extensão .png
	 * @param gravavel - classe que está utilizando o metodo para casos de exceção para fins de printagem. 
	 */
	default String gravarImg(BufferedImage img, String nomeArquivo, Gravavel gravavel){
		Regras.validarOperacaoExecutada(img, gravavel.getClass());
		return ImgIO.gravarImg(img, Endereco.TESTES, nomeArquivo, TipoImagem.PNG);
	}
	
	default void abrir(){};
	
	default void abrir(String endImgSalva){
		ImgIO.abrirImg(endImgSalva);
	}

}
