package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(
                student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No Student Found with Id: " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("No Student Found with Id: " + studentId));

        if (name != null && name.length() > 0 && name != student.getName()){
            student.setName(name);
        }

        if (email != null && email.length() > 0 && email != student.getEmail()){

            Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException(email+" Email Id already in use");
            }
            student.setEmail(email);
        }
    }
}
