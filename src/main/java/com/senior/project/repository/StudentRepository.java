package com.senior.project.repository;

import org.springframework.data.repository.CrudRepository;
import com.senior.project.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
	

}
