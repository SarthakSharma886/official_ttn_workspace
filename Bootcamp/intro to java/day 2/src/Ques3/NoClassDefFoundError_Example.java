package Ques3;

class A{
    static int var =1/0;
//    int n;
//
//    public int getN() {
//        return n;
//    }
//
//    public void setN(int n) {
//        this.n = n;
//    }
}

public class NoClassDefFoundError_Example {

    public static void main(String[] args) {
        try {
            A a =new A();
        }
        catch (Throwable t){
            System.out.println(t);
        }
        A a = new A();

    }
}
