package com.senior.project.restapi;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senior.project.exception.StudentNotFound;
import com.senior.project.model.Student;
import com.senior.project.service.StudentService;
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
		
	}
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student thestudent){
		return studentService.addStudent(thestudent);
	}
	
	@GetMapping("/student/{studentId}")
	public Student getStudentById(@PathVariable("studentId") int studentId){
		Student student = studentService.getStudentById(studentId);
		if(student == null){
			throw new StudentNotFound("Student not found with "+studentId);
		}
		return student;
		
	}
	
	@PutMapping("/student/{studentId}")
	public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student newStudent){
		Student thest = studentService.getStudentById(studentId);
		if( thest == null){
			throw new StudentNotFound("Student not found with "+studentId);
		}
		
		Student theupdate =studentService.updateStudent(studentId, newStudent);
		return theupdate;
		
	}
	
	@DeleteMapping("/student/{studentId}")
	public String deleteStudent(@PathVariable("studentId") int studentId){
		Student found = studentService.getStudentById(studentId);
		if( found == null){
			throw new StudentNotFound("Student not found with id "+studentId);
		}
		studentService.deleteStudent(studentId);
		
		return " Deleted Student with id "+studentId;
	}

}
