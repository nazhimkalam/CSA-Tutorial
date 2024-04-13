package Ik.iit.tutorial;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentDAO {
    private static Map<Integer, Student> students = new HashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger(0);

    static {
        // Adding sample students to the map
        students.put(1, new Student(1, "John", "Doe"));
        students.put(2, new Student(2, "Alice", "Smith"));
        students.put(3, new Student(3, "Bob", "Johnson"));
        // Add more sample students as needed
    }

    public static Map<Integer, Student> getStudents() {
        return students;
    }

    public static Student findStudentById(int id) {
        return students.get(id);
    }

    public static int generateId() {
        int maxId = 0;
        for (Student student : students.values()) {
            maxId = Math.max(maxId, student.getId());
        }
        return maxId + 1;
    }

    public static void addStudent(Student student) {
        int newId = generateId();
        student.setId(newId);
        students.put(newId, student);
    }

    public static void updateStudent(int id, Student updatedStudent) {
        if (students.containsKey(id)) {
            updatedStudent.setId(id);
            students.put(id, updatedStudent);
        }
    }

    public static void deleteStudent(int id) {
        students.remove(id);
    }
}
