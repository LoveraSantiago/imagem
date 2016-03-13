package lovera.img.modelos.img;

import static lovera.comuns.recursos.Regras.validarBufferedImgCinza;
import static lovera.comuns.recursos.Regras.validarListaCoordenadas;
import static lovera.comuns.recursos.Regras.validarOperacaoExecutada;
import static lovera.img.comum.Binario.HUM_BINARIO;
import static lovera.img.comum.Binario.ZERO_BINARIO;
import static lovera.img.manipulacao.ImgIO.gravarImg;
import static lovera.img.manipulacao.ManipulacaoImg.copiarImg;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import lovera.comuns.contratos.Coordenadas;
import lovera.comuns.contratos.Gravavel;
import lovera.comuns.recursos.Endereco;
import lovera.comuns.recursos.TipoImagem;
import lovera.img.comum.Pixel;
import lovera.img.contratos.ImgTransformavel;;

/**
 * @author Lovera
 */
@Deprecated //10/03/2016 Possivelmente nao sera utilizada.
public final class CorrosaoImg implements ImgTransformavel, Gravavel, Coordenadas{
	
	private BufferedImage imgCorrosao;
	
	private List<Point> coordenadas;
	
	public CorrosaoImg(BinarizacaoImg binarizacao) {
		validarBufferedImgCinza(binarizacao);
		
		this.imgCorrosao = copiarImg(binarizacao.getImgTransformada());		 
	}
	
	@Override
	public ImgTransformavel executarTransformacao() {		
		executarErosao();
		carregarCoordenadas();
		return this;
	}

	private void executarErosao(){
		WritableRaster wRaster = this.imgCorrosao.getRaster();
		
		for(int i = 1; i < this.imgCorrosao.getHeight() - 1; i++)
			for(int j = 1; j < this.imgCorrosao.getWidth() - 1; j++){
				int[] vizinhanca = getVizinhanca(j, i, wRaster);
				int[] vErodida = analiseVizinhanca(vizinhanca, j, i);
				setVizinhanca(j, i, wRaster, vErodida);
			}
		
	}
	
	private int[] getVizinhanca(int x, int y, Raster raster){
		int[] array = new int[6];	
		
		array[0] = (raster.getSample(x - 1, y    , 0) == 0) ? ZERO_BINARIO.getValor() : HUM_BINARIO.getValor();
		array[1] = (raster.getSample(x    , y    , 0) == 0) ? ZERO_BINARIO.getValor() : HUM_BINARIO.getValor();
		array[2] = (raster.getSample(x + 1, y    , 0) == 0) ? ZERO_BINARIO.getValor() : HUM_BINARIO.getValor();
		array[3] = (raster.getSample(x - 1, y + 1, 0) == 0) ? ZERO_BINARIO.getValor() : HUM_BINARIO.getValor();
		array[4] = (raster.getSample(x    , y + 1, 0) == 0) ? ZERO_BINARIO.getValor() : HUM_BINARIO.getValor();
		array[5] = (raster.getSample(x + 1, y + 1, 0) == 0) ? ZERO_BINARIO.getValor() : HUM_BINARIO.getValor();
		
		return array;
	}
	
	private void setVizinhanca(int x, int y, WritableRaster wRaster, int[] vizinhancaErosao){
		wRaster.setSample(x - 1, y    , 0, vizinhancaErosao[0]);
		wRaster.setSample(x   ,  y    , 0, vizinhancaErosao[1]);
		wRaster.setSample(x + 1, y    , 0, vizinhancaErosao[2]);
		wRaster.setSample(x - 1, y + 1, 0, vizinhancaErosao[3]);
		wRaster.setSample(x    , y + 1, 0, vizinhancaErosao[4]);
		wRaster.setSample(x + 1, y + 1, 0, vizinhancaErosao[5]);
	}
	
	private int[] analiseVizinhanca(int[] vizinhanca, int x, int y){
		int caso = getCaso(vizinhanca);
		int casoErosao = getErosao(caso);
		return casoParaVizinhanca(casoErosao, new int[vizinhanca.length]);
	}
	
	private int[] casoParaVizinhanca(int caso, int[] array){
		for(int i = array.length - 1, contador = 0; i >= 0; i--, contador++){
			array[contador] = 
					((caso & (HUM_BINARIO.getValor() << i)) != ZERO_BINARIO.getValor()) ? 
							Pixel.PREENCHIDO.getValor() : Pixel.VAZIO.getValor(); 
		}
		return array;
	}
	
	private int getCaso(int[] vizinhanca){
		StringBuilder stringBuilder = new StringBuilder(vizinhanca.length);
		for(int i : vizinhanca)
			stringBuilder.append(String.valueOf(i));
		
		return Integer.parseInt(stringBuilder.toString(), 2);
	}
	
	private int getErosao(int caso){
		switch(caso){
		case 0:  return 0;
		case 1:  return 1;
		case 2:  return 2;
		case 3:  return 1;
		case 4:  return 4;
		case 5:  return 5;
		case 6:  return 2;
		case 7:  return 1;
		case 8:  return 8;
		case 9:  return 1;
		case 10: return 2;
		case 11: return 1;
		case 12: return 12;
		case 13: return 5;
		case 14: return 2;
		case 15: return 1;
		case 16: return 16;
		case 17: return 1;
		case 18: return 2;
		case 19: return 1;
		case 20: return 4;
		case 21: return 1;
		case 22: return 2;
		case 23: return 1;
		case 24: return 8;
		case 25: return 1;
		case 26: return 2;
		case 27: return 1;
		case 28: return 4;
		case 29: return 1;
		case 30: return 2;
		case 31: return 1;
		case 32: return 32;
		case 33: return 33;
		case 34: return 2;
		case 35: return 1;
		case 36: return 4;
		case 37: return 5;
		case 38: return 2;
		case 39: return 1;
		case 40: return 40;
		case 41: return 33;
		case 42: return 2;
		case 43: return 1;
		case 44: return 12;
		case 45: return 5;
		case 46: return 2;
		case 47: return 1;
		case 48: return 16;
		case 49: return 1;
		case 50: return 2;
		case 51: return 1;
		case 52: return 4;
		case 53: return 1;
		case 54: return 2;
		case 55: return 1;
		case 56: return 8;
		case 57: return 1;
		case 58: return 2;
		case 59: return 1;
		case 60: return 4;
		case 61: return 1;
		case 62: return 2;
		case 63: return 1;		
		}
		throw new RuntimeException("Caso nº" + caso + " de erosão inexistente.");
	}
	
	private void carregarCoordenadas(){
		this.coordenadas = new ArrayList<>();
		
		WritableRaster raster = this.imgCorrosao.getRaster();
		for(int i = 0; i < this.imgCorrosao.getHeight(); i++)
			for(int j = 0; j < this.imgCorrosao.getWidth(); j++)
				if(raster.getSample(j, i, 0) == Pixel.PREENCHIDO.getValor())
					this.coordenadas.add(new Point(j, i));		
	}

	/**
	 * Transformação ocorrida por manipulação da imagem.
	 */
	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgCorrosao, this);
		return this.imgCorrosao;
	}

	@Override
	public void gravar() {
		validarOperacaoExecutada(this.imgCorrosao, this);
		gravarImg(this.imgCorrosao, Endereco.TESTES, "RedacaoCorrosao", TipoImagem.PNG);
	}

	@Override
	public List<Point> getCoordenadas() {
		validarListaCoordenadas(this.coordenadas);
		return this.coordenadas;
	}
	
//	Testando conversoes binarias
//	public static void main(String[] args) {	
//		
//		for(int i = 0; i < 64; i++){
//			int[] array = casoParaVizinhanca(i, new int[6]);
//			int[] resposta = casoParaVizinhanca(getErosao(i), new int[6]);
//			
//			System.out.println("Caso " + String.format("%02d", i) + 
//					" = " + Arrays.toString(array) + " retornando " + 
//					" = " + Arrays.toString(resposta));
//		}
//		System.out.println("Fim");		
//	}
}
