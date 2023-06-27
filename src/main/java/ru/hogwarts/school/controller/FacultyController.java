package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {


    private final FacultyService facultyService;

@Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long facultyId) {
        Faculty facultyGet = facultyService.findFaculty(facultyId);
        if (facultyGet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyGet);
    }

//    @GetMapping("/color/{facultyColor}")
//    public ResponseEntity<Collection<Faculty>> getFacultyColor(@PathVariable String facultyColor) {
//        Collection<Faculty> facultyByColor = facultyService.getFacultyColors(facultyColor);
//        if (facultyByColor == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(facultyByColor);
//    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable Long facultyId) {

        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();


    }

    @GetMapping("{allFaculty}")
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> findByColor(@RequestParam(required = false) String color) {
        if (color != null &&!color.isBlank() ) {
            return ResponseEntity.ok(facultyService.findFacultyColor(color));

        }
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }
}

