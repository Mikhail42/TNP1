package myMath;
/**
 * @author misha
 */
public class MyMatrix implements java.io.Serializable{
    private static void tryCorrect(double[][] A) throws java.lang.IllegalArgumentException{
        if (A==null || A.length==0) throw new java.lang.IllegalArgumentException("Некорректный размер обрабатываемых объектов");
    }
    public static String toString(double[][] A, int count) throws java.lang.IllegalArgumentException{
        tryCorrect(A);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<A.length-1;i++) sb.append(MyVector.toString(A[i])).append('\n');
        sb.append(MyVector.toString(A[A.length-1]));
        return sb.toString();
    }
    public static String toString(double[][] A) throws java.lang.IllegalArgumentException{
        return toString(A,2);
    }
    public static double[][] input(Object[] obs) throws java.lang.NumberFormatException{
        String[] ins = new String[obs.length];
        for (int i=0; i<obs.length;i++) ins[i] = (String)obs[i];
        return input(ins);
    }
    public static double[][] input(String[] ins) throws java.lang.NumberFormatException{
        double[][] A = new double[ins.length][];
        for (int i=0; i<A.length;i++) A[i] = MyVector.input(ins[i]);
        return A;
    }
    private static void tryCorrect(double[][] A, double[][] B) throws java.lang.IllegalArgumentException{
        boolean flag = !(A==null || B==null || A.length==0 || B.length==0);
        int m = A[0].length; for (int i = 1; i<A.length;i++) flag&=(A[i].length==m);
        int k = B[0].length; for (int i = 1; i<B.length;i++) flag&=(B[i].length==k);
        if (!flag) throw new java.lang.IllegalArgumentException("Некорректный размер одной из матриц");
    }     
    public static double[][] multi(double[][] A, double[][] B) throws java.lang.IllegalArgumentException{
        tryCorrect(A,B);
        int n = A.length; int m = B[0].length;
        double[][] C = new double[n][m];
        for(int i=0;i<n;i++) for (int j=0;j<m;j++) C[i][j] = MyVector.multi(A[i], B, j);
        return C;
    }
    public static double[][] plus(double[][] A, double[][] B) throws java.lang.IllegalArgumentException{
        tryCorrect(A,B);
        if (A.length!=B.length) throw new java.lang.IllegalArgumentException("Некорректный размер одной из матриц");        
        double[][] C = new double[A.length][];
        for(int i=0;i<A.length;i++) C[i] = MyVector.plus(A[i], B[i]);
        return C;
    }
    public static double[][] minus(double[][] A, double[][] B) throws java.lang.IllegalArgumentException{
        tryCorrect(A,B);
        if (A.length!=B.length) throw new java.lang.IllegalArgumentException("Некорректный размер одной из матриц");        
        double[][] C = new double[A.length][];
        for(int i=0;i<A.length;i++) C[i] = MyVector.minus(A[i], B[i]);
        return C;
    }
}
