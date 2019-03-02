

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ques1 {

    public static void main(String[] args) {

        List<Float> floatList = new ArrayList<>();
        floatList.add(7.45f);
        floatList.add(8.756f);
        floatList.add(0.22f);
        floatList.add(9.888f);
        floatList.add(771.895f);

        System.out.print("ELements in List are:- ");
        float sum = 0;
        Iterator<Float> iterator = floatList.iterator();
        while(iterator.hasNext()){
            float y = iterator.next();

            if(!iterator.hasNext()) System.out.print(y);
            else System.out.print(y + ", ");

            sum += y;
        }

        System.out.println("\nThe sum of all elements of List are:- " + sum);
    }
}
