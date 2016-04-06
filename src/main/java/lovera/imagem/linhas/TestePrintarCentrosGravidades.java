package lovera.imagem.linhas;

import java.awt.image.BufferedImage;
import java.util.List;

import lovera.comuns.recursos.Imagens;
import lovera.img.contratos.UnidorImagens;
import lovera.img.factory.FactoryModelo;
import lovera.img.graos.BlocoComPonto;
import lovera.img.hough.CoordenadaPolarTemp;
import lovera.img.hough.HoughDosBlocos;
import lovera.img.hough.HoughDosBlocosTemp;
import lovera.img.manipulacao.ImgIO;
import lovera.img.modelos.blocos.AreasParaBlocos;
import lovera.img.modelos.floodfill.FloodFillCCs;
import lovera.img.modelos.img.BinarizacaoImg;
import lovera.img.modelos.uniao.UniaoImgAreas;
import lovera.img.modelos.uniao.UniaoImgLinhas;
import lovera.img.modelos.uniao.UniaoImgPontos;

public class TestePrintarCentrosGravidades {

	public static void main(String[] args) {
		BufferedImage img = ImgIO.carregarImg_modoMediaTracker(Imagens.REDACAO_PNG);
		BinarizacaoImg binarizacao = FactoryModelo.factoryBinarizacao(img);
		
		FloodFillCCs flood = new FloodFillCCs(binarizacao);	
		flood.executar();
		
		AreasParaBlocos blocos = new AreasParaBlocos(flood, binarizacao);
		blocos.executar();
		List<BlocoComPonto> cGravidades = blocos.getListaBlocosComPontos();
		
		HoughDosBlocosTemp hTemp = new HoughDosBlocosTemp(blocos, binarizacao);
		hTemp.executar();
		List<CoordenadaPolarTemp> listaPolar = hTemp.getListaPolar();
		
		int contador = 0;
		for(int i = 0; i < cGravidades.size(); i++){
			BlocoComPonto bp = cGravidades.get(i);
			CoordenadaPolarTemp pPolar = listaPolar.get(i);
			
			System.out.println("tt = " + ++contador + " " + bp.getPonto() + " - polar " + pPolar);
		}
		
	}
}
