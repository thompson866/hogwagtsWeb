package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;


@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long studentId) {
        Student studentGet = studentService.findStudent(studentId);
        if (studentGet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentGet);
    }
//
//    @GetMapping("/age/{studentAge}")
//    public ResponseEntity<Collection<Student>> getStudentAge(@PathVariable Integer studentAge) {
//        Collection<Student> findStudentAge = studentService.getStudentAge(studentAge);
//        if (findStudentAge.size() == 0) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(findStudentAge);
//    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);//??
        return ResponseEntity.ok(newStudent);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student studentId) {
        Student foundStudent = studentService.editStudent(studentId);
        if (foundStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }
}
