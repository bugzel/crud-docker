package pl.crud.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.crud.entity.Student;
import pl.crud.model.StudentModel;
import pl.crud.repository.StudentRepository;

@RestController
@RequestMapping(value = "/api/crud")
public class CrudController {

	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping(value = "/{id}")
	public Student oneStudent(@PathVariable("id") Long id) {
		Student student = studentRepository.findOneById(id);
		return student;
	}
	
	@GetMapping(value = "/")
	public List<Student> studentList() {
		List<Student> studentList = studentRepository.findAll();
		return studentList;
	}
	
	@PostMapping(value = "/add_student/")
	public HttpStatus addStudent(@RequestBody StudentModel studentModel) {
		Student student = new Student();
		student = fillStudentData(student, studentModel);
		studentRepository.save(student);
		return HttpStatus.CREATED;
	}
	
	@PutMapping(value = "/edit_student/{id}")
	public HttpStatus editStudent(@PathVariable("id") Long id, @RequestBody StudentModel studentModel) {
		Student student = studentRepository.findOneById(id);
		student = fillStudentData(student, studentModel);
		student.setId(id);
		studentRepository.save(student);
		return HttpStatus.ACCEPTED;	
	}
	
	@DeleteMapping(value = "/delete_student/{id}")
	public HttpStatus deleteStudent(@PathVariable("id") Long id) {
		Student student = studentRepository.findOneById(id);
		studentRepository.delete(student);
		return HttpStatus.ACCEPTED;	
	}
	
	public Student fillStudentData(Student student, StudentModel studentModel) {
		ModelMapper modelMapper = new ModelMapper();
		student = modelMapper.map(studentModel, Student.class);
		return student;
	}
}
