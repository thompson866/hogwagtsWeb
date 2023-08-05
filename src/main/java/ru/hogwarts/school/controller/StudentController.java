package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo (@PathVariable long id){
        Student student = studentService.findStudent(id);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity <Collection<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent (@RequestBody Student student){
        Student findStudent = studentService.editStudent(student);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent (@PathVariable long id){
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity <Collection<Student>> findByAgeBetween(@RequestParam int from, @RequestParam int to){
        return ResponseEntity.ok(studentService.findByAgeBetween(from, to));
    }

    @GetMapping("/faculty")
    public ResponseEntity<Faculty> getFacultyStudent (@RequestParam String name) {
        return ResponseEntity.ok(studentService.getFacultyByName(name));

    }


}
