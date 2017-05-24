package com.mybatis3.mappers;

import com.mybatis3.domain.Student;

import java.util.List;


/**
 * @author Siva
 *
 */
public interface StudentMapper
{

	List<Student> findAllStudents();

	Student findStudentById(Integer id);

	void insertStudent(Student student);

	void updateStudent(Student student);

}
