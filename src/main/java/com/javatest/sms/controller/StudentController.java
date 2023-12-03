package com.javatest.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.javatest.sms.entity.Student;
import com.javatest.sms.service.StudentService;

@Controller
public class StudentController 
{
  private StudentService studentService;

public StudentController(StudentService studentService) {
	super();
	this.studentService = studentService;
}

//handler method to handle list students and return model and view
@GetMapping("/students")
public String listStudents(Model model)
{
  model.addAttribute("students", studentService.getAllStudent());
  return "students";
}

@GetMapping("/students/new")
public String createStudentForm(Model model)
{
	//create student object to hold student form data
	Student student=new Student();
	model.addAttribute("student",student);
	return "create_student";
}

@PostMapping("/students")
public String saveStudent(@ModelAttribute("student") Student student)
{
	studentService.saveStudent(student);
	return "redirect:/students";
}

@GetMapping("/students/edit/{id}")
public String editStudentForm(@PathVariable Long id, Model model)
{
  model.addAttribute("student", studentService.getStudentById(id));
  return "edit_student";
}

@PostMapping("/students/{id}")
public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model)
{
  //get student form database by id
	Student existeingStudent=studentService.getStudentById(id);
	existeingStudent.setId(id);
	existeingStudent.setFirstName(student.getFirstName());
	existeingStudent.setLastName(student.getLastName());
	existeingStudent.setEmail(student.getEmail());
	
  //save updated student object
	studentService.updateStudent(existeingStudent);
	return "redirect:/students";
}

//handler method to handle delete student request
@GetMapping("/students/{id}")
public String deleteStudent(@PathVariable Long id)
{
	studentService.deleteStudentById(id);
	return "redirect:/students";
}

}
