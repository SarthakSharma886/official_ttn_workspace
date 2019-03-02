package Ques5;

public class Student {

    double score, age;
    String first_name;

    public Student(double score, double age, String first_name) {
        this.score = score;
        this.age = age;
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "[score=" + score + ", age=" + age + ", first_name=" + first_name + "]" ;
    }
}
