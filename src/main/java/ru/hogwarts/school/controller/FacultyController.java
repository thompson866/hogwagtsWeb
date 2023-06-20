package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {


    private final FacultyService facultyService;


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

    @GetMapping("/color/{facultyColor}")
    public ResponseEntity<Collection<Faculty>> getFacultyColor(@PathVariable String facultyColor) {
        Collection<Faculty> facultyByColor = facultyService.getFacultyColors(facultyColor);
        if (facultyByColor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyByColor);
    }

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
    public ResponseEntity<Faculty>  deleteFaculty(@PathVariable Long facultyId) {

        Faculty removeFaculty = facultyService.deleteFaculty(facultyId);
        if (removeFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(removeFaculty);


    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

}

