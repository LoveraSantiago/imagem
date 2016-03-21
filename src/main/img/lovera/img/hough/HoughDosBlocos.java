package lovera.img.hough;

import java.util.List;

import lovera.comuns.recursos.Regras;
import lovera.img.graos.BlocoComPonto;
import lovera.img.modelos.blocos.AreasParaBlocos;
import lovera.img.modelos.img.BinarizacaoImg;

public class HoughDosBlocos {
	
	private List<BlocoComPonto> blocos;
	private BinarizacaoImg binarizacao;
	
	public HoughDosBlocos(AreasParaBlocos areas, BinarizacaoImg binarizacao) {
		Regras.validarBufferedImgCinza(binarizacao, this.getClass());
		Regras.validarListaDeBlocosComPonto(areas, this.getClass());
		
		this.blocos = areas.getListaBlocosComPontos();
		this.binarizacao = binarizacao;
	}
	
	public HoughDosBlocos gerarHoughNosBlocos(){
		
		this.binarizacao = null;
		return this;
	}

}
