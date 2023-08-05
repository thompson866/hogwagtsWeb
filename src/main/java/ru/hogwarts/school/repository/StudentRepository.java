package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
//    Student findStudentByAge(int age);
   // Collection<Student> findByAge(int age); //как обозвать


    Collection<Student> findByAgeBetween(int min, int max);

    Student findStudentByName (String name);
}
