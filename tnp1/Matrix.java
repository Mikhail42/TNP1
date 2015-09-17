package tnp1;
import myMath.MyMatrix;
import myMath.MyVector;
/**
 * @author misha
 */
public class Matrix {
    public int getCountStrings(){return n;}
    public int getCountColumns(){return m;}
    public double getElement(int i, int j) throws java.lang.IllegalArgumentException{
        if (i<0 || i>=matrix.length) throw new java.lang.IllegalArgumentException("Неправильный номер строки");
        return MyVector.getElement(matrix[i],j);
    }
    public void setElement(double element, int i, int j) throws java.lang.IllegalArgumentException{
        if (i<0 || i>=matrix.length) throw new java.lang.IllegalArgumentException("Неправильный номер строки");
        MyVector.setElement(element, matrix[i], i);
    }
    public Matrix(int n,int m) throws java.lang.IllegalArgumentException{
        if (n<=0 || m<=0) throw new java.lang.IllegalArgumentException("Некорректный размер матрицы");
        this.n = n; this.m = m; matrix = new double[n][m];
    }
    public static double[][] multi(double[][] matrix1,double[][] matrix2) throws java.lang.IllegalArgumentException {
        return MyMatrix.multi(matrix1, matrix2);
    }
    public static double[][] plus(double[][] matrix1,double[][] matrix2) throws java.lang.IllegalArgumentException {
        return MyMatrix.plus(matrix1, matrix2);
    }
    public static double[][] minus(double[][] matrix1,double[][] matrix2) throws java.lang.IllegalArgumentException {
        return MyMatrix.minus(matrix1, matrix2);
    }
    
    private final double[][] matrix;
    private final int n;
    private final int m;
}
