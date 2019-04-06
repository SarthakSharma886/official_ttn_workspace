
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ques3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string:- ");
        char s[] = sc.nextLine().toLowerCase().toCharArray();

        Map<Character, Integer> freq = new HashMap<>();

        for (char c : s) {
            if (freq.containsKey(c)) {

                // If char is present in charCountMap,
                // incrementing it's count by 1
                freq.put(c, freq.get(c) + 1);
            }
            else {

                // If char is not present in charCountMap,
                // putting this char to charCountMap with 1 as it's value
                freq.put(c, 1);
            }
        }


        System.out.println("Frequency of each character in the string is:- ");
        for(char chars: freq.keySet()){
            System.out.println(chars + ": " + freq.get(chars));
        }
    }
}
