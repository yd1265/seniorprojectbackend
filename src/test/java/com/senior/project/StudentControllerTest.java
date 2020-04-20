package com.senior.project;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senior.project.exception.StudentNotFound;
import com.senior.project.model.Student;
import com.senior.project.restapi.StudentController;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
     private  ObjectMapper objectMapper;
	
	
    @MockBean
	private StudentController studentController;
    
    @Test
    public void getAllStudents() throws Exception{
    	List<Student> students = new ArrayList<Student>();
    	Student thes = new Student("Youssouf", "Diallo");
    	thes.setId(1);
    	students.add(thes);
    	given(studentController.getAllStudents()).willReturn(students);
    	
    	mvc.perform(get("http://localhost:8081/api/students")
    			.contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().isOk())
         .andExpect(jsonPath("$", hasSize(1)));
    }
    
   @Test
    public void addStudent() throws Exception{
      Student thestudent = new Student("Youssouf", "Diallo");
      thestudent.setId(1);
       given(studentController.addStudent(thestudent)).willReturn(thestudent);
      
       mvc.perform(post("http://localhost:8081/api/student")
        	.contentType(MediaType.APPLICATION_JSON)
        	.content(this.objectMapper.writeValueAsString(thestudent))
        	.accept(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk());
        	  	
        }
    @Test
   public void getStudentByIdwhenValidRequest() throws Exception{
	   Student thestudent = new Student("Test2", "test");
	   thestudent.setId(1);
	   when(studentController.getStudentById(1)).thenReturn(thestudent);
       mvc.perform(get("http://localhost:8081/api/student/1")
    		   .accept(MediaType.APPLICATION_JSON))
    		   .andExpect(status().is(200))
    		.andExpect(jsonPath("$.firstName").value("Test2"))
          .andExpect(jsonPath("$.lastName").value("test"));;
       
    		
   }
	@Test
   public void delteStudentId() throws Exception{
		 Student thestudent = new Student("Test2", "test");
		   thestudent.setId(1);
		   when(studentController.deleteStudent(1)).thenReturn("Deleted"); 
		mvc.perform(delete("http://localhost:8081/api/student/1")
        		.contentType(MediaType.APPLICATION_JSON))
        	   .andExpect(status().isOk());
	   
   }
  @Test	
  public void updateStudent() throws Exception{
		   Student thestudent = new Student("Diallo", "Test");
		   Student newStudent = new Student("Diallo", "diallo");
		   thestudent.setId(1);
	       given(studentController.updateStudent(1, newStudent)).willReturn(thestudent);
	        	mvc.perform(put("http://localhost:8081/api/student/1")
	        	   .contentType(MediaType.APPLICATION_JSON)
	        	    .content(this.objectMapper.writeValueAsString(thestudent))
	        		 .accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().is(200));
		   
	   }

  
  @Test
  public void postInvalidshould_Return404() throws Exception {
    Student wrongdata= new Student("Youssouf", "Diallo");
      wrongdata.setId(1);
    when(studentController.addStudent(wrongdata)).thenReturn(wrongdata);
    mvc.perform(post("http://localhost:8081/api/test/student/") 
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(this.objectMapper.writeValueAsString(wrongdata)))
            .andExpect(status().is(404));
  }
  
  @Test()
  public void notFoundUsershould_Return404() throws Exception {
	  when(studentController.deleteStudent(2)).thenThrow(StudentNotFound.class);
		mvc.perform(delete("http://localhost:8081/api/student/2")
      		.contentType(MediaType.APPLICATION_JSON))
      	   .andExpect(status().isNotFound());
  } 
  
}
