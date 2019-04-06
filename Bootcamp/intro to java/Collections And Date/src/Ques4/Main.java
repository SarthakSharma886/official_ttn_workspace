package Ques4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(30, 320000, "Chunnu"));
        employees.add(new Employee(24, 430000, "Munnu"));
        employees.add(new Employee(53, 550000, "Dhanno"));
        employees.add(new Employee(43, 440000, "Majnu"));

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int)(o2.salary-o1.salary);
            }
        });

        Iterator<Employee> iterator = employees.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}
