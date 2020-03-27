package com.senior.project.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senior.project.exception.StudentNotFound;
import com.senior.project.model.Student;
import com.senior.project.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	
	@Override
	public Student addStudent(Student thestudent) {
			studentRepository.save(thestudent);
			return thestudent;
	}
	
	@Override
	public Student getStudentById(int thestudentId) {
		return studentRepository.findById(thestudentId).orElseThrow(() ->new StudentNotFound("Student not found with id = " +thestudentId));
	}
	
	@Override
	public Student updateStudent(int thestudentId, Student newStudent) {
		Student sfound =getStudentById(thestudentId);
			sfound.setFirstName(newStudent.getFirstName());
			sfound.setLastName(newStudent.getLastName());
			Student updateStudent = studentRepository.save(sfound);
		return updateStudent;
	}
	
	@Override
	public List<Student> getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		return students;
	}

	
	@Override
	public void deleteStudent(int thestudentId) {
		Student sfound = getStudentById(thestudentId);
		if( sfound != null){
			studentRepository.delete(sfound);
		}
	}

	

	

}
