package lovera.imagem.linhas;

import java.awt.image.BufferedImage;

import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.Imagens;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.manipulacao.ImgIO;
import lovera.img.manipulacao.ManipulacaoImg;
import lovera.img.modelos.BinarizacaoImg;

public class TesteBinarizacao {
	
	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		BufferedImage converterToCinza = ManipulacaoImg.converterToCinza(img);
//		ImgIO.gravarImg(converterToCinza, Endereco.TESTES, "cinza", TipoImagem.PNG);
		
		BinarizacaoImg binarizacao = new BinarizacaoImg(converterToCinza);
		binarizacao.executarTransformacao();
		binarizacao.gravar();
	}

}
