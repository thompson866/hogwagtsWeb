package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent (Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent (long id){
        return studentRepository.findById(id).get();

    }


    public Student editStudent (Student student) {
        return studentRepository.save(student);
    }

    public void removeStudent (long id){
        studentRepository.deleteById(id);
    }

    public Collection <Student> findByAgeBetween (int min, int max){
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFacultyByName(String name) {
        return studentRepository.findStudentByName(name).getFaculty();

    }

}

