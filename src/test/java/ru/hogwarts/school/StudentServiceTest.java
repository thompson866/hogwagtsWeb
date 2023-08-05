package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class StudentServiceTest {


    @Mock
    private StudentRepository repositoryMock;

    @InjectMocks
    StudentService out;

    @Test
    void addStudentTest() {
        Student s = new Student(3l, "22222", 11);
        when(repositoryMock.save(s)).thenReturn(s);
        assertEquals(s, out.createStudent(s));
    }

    @Test
    void getStudentPositiveTest() {
        Student s = new Student(3l, "22222", 11);
        when(repositoryMock.findById(3L)).thenReturn(Optional.of(s));
        assertEquals(s, out.findStudent(3));
    }

    @Test
    void getStudentNegativeTest() {
        when(repositoryMock.findById(3l)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> out.findStudent(3l));
    }

    @Test
    void editStudentTest() {
        Student s = new Student(3l, "22222", 11);
        when(repositoryMock.save(s)).thenReturn(s);
        assertEquals(s, out.editStudent(s));
    }



    @Test
    void getStudentsByAgePositiveTest() {
        Student s = new Student(3l,"22222", 11);
        when(repositoryMock.findByAge(11)).thenReturn(List.of(s));
        assertIterableEquals(List.of(s), out.findStudentByAge(11));
    }

    @Test
    void getStudentsByAgeNegativeTest() {
        List<Student> test = Collections.emptyList();
        when(repositoryMock.findByAge(11)).thenReturn(test);
        assertIterableEquals(test, out.findStudentByAge(11));
    }

}
