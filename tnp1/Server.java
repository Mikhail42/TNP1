package tnp1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import myMath.MyMatrix;
public class Server {
    public static final int SOCKETID = 8070;
    public static void run(String[] args) throws Throwable{
        ServerSocket ss = new ServerSocket(SOCKETID);
        while (true) {
            Socket s = ss.accept();
            System.out.println("Клиент подключился");
            (new Thread(new SocketProcessor(s))).start();
        }
    }
    private static class SocketProcessor implements Runnable {
        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.inStream = s.getInputStream();
            this.outStream = s.getOutputStream();
        }
        public void run(){
            try {
                try{
                    writeResponse(readAndCalculate());
                }  catch (java.lang.IllegalArgumentException nfe){
                    String out1 = "Error ";
                    String out2 = "Невозможно выполнение данной операции над полученными матрицами. Проверьте размерность матрицы";
                    outStream.write(out1.getBytes()); 
                    outStream.write(out2.getBytes());            
                    outStream.flush(); 
                } 
            } catch (IOException ioe) {
                System.err.println("При чтении из потока или при записи из него возникла ошибка: "+ioe.toString());
            } finally {
                try {
                    System.out.println("Закрытие сокета");
                    s.close();
                } catch (IOException t) {
                    System.err.println("Закрытие сокета произошло с ошибкой");
                }
            }
            System.out.println("Клиент отключился");
        }
        private void writeResponse(String res) throws IOException {
            outStream.write(res.getBytes());            
            outStream.flush();            
        }
        private String readAndCalculate() throws NumberFormatException,IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
            int operationID = br.read() - 48;    
            String curAdd = null; 
            java.util.LinkedList<String> strs1 = new java.util.LinkedList<>();
            while (!"$".equals(curAdd = br.readLine())) strs1.addLast(curAdd);
            if (strs1.size()==0) throw new NumberFormatException();
            java.util.LinkedList<String> strs2 = new java.util.LinkedList<>();
            while (!"$".equals(curAdd = br.readLine())) strs2.addLast(curAdd);
            if (strs2.size()==0) throw new NumberFormatException();
            double[][] A = MyMatrix.input(strs1.toArray());
            double[][] B = MyMatrix.input(strs2.toArray());         
            return getResultMatrix(operationID,A,B);
        }
        private String getResultMatrix(int operationID, double[][] A, double[][] B) throws java.lang.IllegalArgumentException {
            String res; 
            switch(operationID){
                case 1: {res = MyMatrix.toString(MyMatrix.minus(A, B)); break;}
                case 2: {res = MyMatrix.toString(MyMatrix.plus(A, B));  break;}
                case 3: {res = MyMatrix.toString(MyMatrix.multi(A, B)); break;}
                default: res = null;
            }
            return res;
        }
        
        private final Socket s;
        private final InputStream inStream;
        private final OutputStream outStream;
    }
}
