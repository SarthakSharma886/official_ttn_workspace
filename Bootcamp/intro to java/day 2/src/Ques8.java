
import java.util.Scanner;

public class Ques8 {
    public static void main(String[] args) {

        System.out.println("Using 'while' statement:- ");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
            while (!s.equals("done")) {
                if (s.charAt(0) == s.charAt(s.length() - 1)) {
                    System.out.println("1st and last character matched");
                } else {
                    System.out.println("1st and last character not matched");
                }
                s = sc.next();
            }

            System.out.println("Using 'do-while' statement:- ");
            do {
                s = sc.next();
                if (s.charAt(0) == s.charAt(s.length() - 1)) {
                    System.out.println("1st and last character matched");
                } else {
                    System.out.println("1st and last character not matched");
                }
            }
            while (!s.equals("done"));
    }
}
