package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Collection<Faculty> getAllFaculties(){

        return facultyRepository.findAll();
    }

    public Faculty createFaculty (Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty (long id) {

        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty (Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public void removeFaculty (long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColorOrName (String colorOrName){
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }

    public Collection<Student> getStudentsbyFaculty(String name) {
        return facultyRepository.findFacultyByName(name).getStudents();

    }

}

