package com.senior.project.service;
import java.util.List;
import com.senior.project.model.Student;
public interface StudentService {
	public Student addStudent(Student thestudent);
	public Student getStudentById(int thestudentId);
	public Student updateStudent(int thestudentId,Student newStudent);
	public void deleteStudent (int thestudentId);
	public List<Student> getAllStudents();

}
