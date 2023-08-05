package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {

    @Mock
    private FacultyRepository repositoryMock;

    @InjectMocks
    FacultyService out;

    @Test
    void createFacultyTest() {
        Faculty f = new Faculty(4L, "DDD", "green");
        when(repositoryMock.save(f)).thenReturn(f);
        assertEquals(f, out.createFaculty(f));
    }

    @Test
    void findFacultyPositiveTest() {
        Faculty f = new Faculty(4L, "DDD", "green");
        when(repositoryMock.findById(4L)).thenReturn(Optional.of(f));
        assertEquals(f, out.findFacultyColor("green"));
    }

    @Test
    void findFacultyNegativeTest() {
        when(repositoryMock.findById(4L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> out.findFacultyColor("4L"));
    }

    @Test
    void editFacultyTest() {
        Faculty f = new Faculty(3L, "CCC", "black");
        when(repositoryMock.save(f)).thenReturn(f);
        assertEquals(f, out.editFaculty(f));
    }


    @Test
    void findFacultyColorPositiveTest() {
        Faculty f = new Faculty(4L, "DDD", "green");
        when(repositoryMock.findByColor("green")).thenReturn(List.of(f));
        assertIterableEquals(List.of(f), out.findFacultyColor("green"));
    }

    @Test
    void findFacultyColorNegativeTest() {
        List<Faculty> test = Collections.emptyList();
        when(repositoryMock.findByColor("black")).thenReturn(test);
        assertIterableEquals(test, out.findFacultyColor("black"));
    }

}

