package lovera.imagem.modelos;

import static lovera.imagem.comum.Regras.validarBufferedImgCinza;
import static lovera.imagem.comum.Regras.validarOperacaoExecutada;
import static lovera.imagem.manipulacao.ManipulacaoImg.copiarImg;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lovera.imagem.contratos.ImgTransformavel;
import lovera.imagem.manipulacao.ImgIO;
import lovera.imagem.recursos.Endereco;
import lovera.imagem.recursos.TipoImagem;

public final class ErosaoImg implements ImgTransformavel{
	
	private static final int VAZIO      = 0;
	private static final int PREENCHIDO = 255;
	
	private static final int ZERO_BINARIO = 0;
	private static final int HUM_BINARIO  = 1;
	
	private BufferedImage imgErosao;
	private List<Point> coordenadas;
	
	public ErosaoImg(LaplaceImg laplace) {
		validarBufferedImgCinza(laplace.getImgTransformada());
		
		this.imgErosao = copiarImg(laplace.getImgTransformada());		
		this.coordenadas = new ArrayList<>(); 
	}
	
	@Override
	public void executarTransformacao() {		
		executarErosao();
		filtrarListaDeCoordenadas();
	}

	private void executarErosao(){
		WritableRaster wRaster = this.imgErosao.getRaster();
		
		for(int i = 1; i < this.imgErosao.getHeight() - 1; i++)
			for(int j = 1; j < this.imgErosao.getWidth() - 1; j++){
				int[] vizinhanca = getVizinhanca(j, i, wRaster);
				int[] vErodida = analiseVizinhanca(vizinhanca, j, i);
				setVizinhanca(j, i, wRaster, vErodida);
			}
		
	}
	
	private int[] getVizinhanca(int x, int y, Raster raster){
		int[] array = new int[6];
		
		array[0] = (raster.getSample(x - 1, y    , 0) == 0) ? ZERO_BINARIO : HUM_BINARIO;
		array[1] = (raster.getSample(x    , y    , 0) == 0) ? ZERO_BINARIO : HUM_BINARIO;
		array[2] = (raster.getSample(x + 1, y    , 0) == 0) ? ZERO_BINARIO : HUM_BINARIO;
		array[3] = (raster.getSample(x - 1, y + 1, 0) == 0) ? ZERO_BINARIO : HUM_BINARIO;
		array[4] = (raster.getSample(x    , y + 1, 0) == 0) ? ZERO_BINARIO : HUM_BINARIO;
		array[5] = (raster.getSample(x + 1, y + 1, 0) == 0) ? ZERO_BINARIO : HUM_BINARIO;
		
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
		
		if(casoErosao == 1)this.coordenadas.add(new Point(x, y));
		
		return casoParaVizinhanca(casoErosao, new int[vizinhanca.length]);
	}
	
	private int[] casoParaVizinhanca(int caso, int[] array){
		for(int i = array.length - 1, contador = 0; i >= 0; i--, contador++){
			array[contador] = ((caso & (HUM_BINARIO << i)) != ZERO_BINARIO) ? PREENCHIDO : VAZIO; 
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
	
	private void filtrarListaDeCoordenadas(){
		Set<Point> filtro = new HashSet<>(this.coordenadas);
		this.coordenadas = new ArrayList<>(filtro);
	}

	public List<Point> getCoordenadas() {
		validarOperacaoExecutada(this.imgErosao, this);
		return coordenadas;
	}

	/**
	 * Transformação ocorrida por manipulação da imagem.
	 */
	@Override
	public BufferedImage getImgTransformada() {
		validarOperacaoExecutada(this.imgErosao, this);
		return this.imgErosao;
	}

	@Override
	public void gravar() {
		validarOperacaoExecutada(this.imgErosao, this);
		ImgIO.gravarImg(this.imgErosao, Endereco.TESTES, "RedacaoErosao", TipoImagem.PNG);
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