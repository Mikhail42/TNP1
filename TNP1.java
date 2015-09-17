package tnp1;
public class TNP1 {
    public static void main(String[] args){
        try{
            Server.run(null);
        } catch(Throwable e){
            System.err.println(e);
        }   
    }
}
