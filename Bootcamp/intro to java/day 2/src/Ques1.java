public class Ques1 implements Library{
    String book_id;
    String student_id;
    public static void main(String[] args) {
        Ques1 ques1=new Ques1();
        ques1.book_id="B1001";
        ques1.student_id="S1001";
        ques1.issue_book();
        ques1.book_returned();
    }

    @Override
    public void issue_book() {
        System.out.println("The book: "+book_id+" is issued to "+student_id);
    }

    @Override
    public void book_returned() {
        System.out.println("The book: "+book_id+" is issued to "+student_id);
    }
}
interface Library {
    void issue_book();

    void book_returned();
}