public class Ques9{
    public static void main(String[] args) {
        Wood_Chair w1=new Wood_Chair();
        w1.fire();
        w1.stress();
        Wood_Table w2=new Wood_Table();
        w2.fire();
        w2.stress();
        Metal_Chair m1=new Metal_Chair();
        m1.fire();
        m1.stress();
        Metal_Table m2=new Metal_Table();
        m2.fire();
        m2.stress();
    }

}

interface Furniture{
    void fire();
    void stress();
}



class Wood_Chair implements Furniture{
    Wood_Chair(){
        System.out.println("Wood chair properties:");
    }
    @Override
    public void fire() {
        System.out.println("Fire proof");
    }

    @Override
    public void stress() {
        System.out.println("Stress tested");
    }
}
class Wood_Table implements Furniture{
    Wood_Table(){
        System.out.println("Wood table properties:");
    }
    @Override
    public void fire() {
        System.out.println("Fire proof");
    }

    @Override
    public void stress() {
        System.out.println("Stress tested");
    }
}
class Metal_Table implements Furniture{
    Metal_Table(){
        System.out.println("Metal table properties:");
    }
    @Override
    public void fire() {
        System.out.println("Fire proof");
    }

    @Override
    public void stress() {
        System.out.println("Stress tested");
    }
}
class Metal_Chair implements Furniture{
    Metal_Chair(){
        System.out.println("Metal chair properties:");
    }
    @Override
    public void fire() {
        System.out.println("Fire proof");
    }

    @Override
    public void stress() {
        System.out.println("Stress tested");
    }
}