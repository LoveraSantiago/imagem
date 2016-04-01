package lovera.img.hough;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;

@Deprecated//NAO VAI SER USADO
public class SistemaLinear {
	
	public static void main(String[] args) {
		RealMatrix coeficientes = new Array2DRowRealMatrix(new double[][]{ { 1,  2}, 
																		   { 3,  6} 
																		 }, false);

		RealVector constantes = new ArrayRealVector(new double[] { 8, 7}, false);
		
		DecompositionSolver solver = new LUDecomposition(coeficientes).getSolver();
		RealVector solution = solver.solve(constantes);
		System.out.println("X = " + solution.getEntry(0));
		System.out.println("Y = " + solution.getEntry(1));
	}
	
	public static void umaSolucao(){
		
		RealMatrix coeficientes = new Array2DRowRealMatrix(new double[][]{ { 2,  3}, 
																		   { 1, -1} 
																		 }, false);

		RealVector constantes = new ArrayRealVector(new double[] { 12, 11 }, false);

		DecompositionSolver solver = new LUDecomposition(coeficientes).getSolver();
		RealVector solution = solver.solve(constantes);
		System.out.println("X = " + solution.getEntry(0));
		System.out.println("Y = " + solution.getEntry(1));
		
	}
	
	public static void nenhumaSolucao(){
		RealMatrix coeficientes = new Array2DRowRealMatrix(new double[][]{ { 1,  3}, 
																		   { 2,  6} 
																		 }, false);

		RealVector constantes = new ArrayRealVector(new double[] { 7, 14}, false);
		
		DecompositionSolver solver = new LUDecomposition(coeficientes).getSolver();
		
		try{
			
			RealVector solution = solver.solve(constantes);
			System.out.println("X = " + solution.getEntry(0));
			System.out.println("Y = " + solution.getEntry(1));
		}
		catch(SingularMatrixException ex){
			System.err.println("Infinitas soluções as linhas são iguais");
		}
	}

}
