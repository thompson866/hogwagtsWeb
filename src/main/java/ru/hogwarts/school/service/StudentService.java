package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0L;


    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }


    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long id) {

        return students.remove(id);
    }

    public Collection<Student> getAllStudent() {
        return students.values();
    }


    public Collection<Student>getStudentAge(int age){
        return students.values().stream()
                .filter(student -> student.getAge()== age)
                .collect(Collectors.toList());
    }
}

