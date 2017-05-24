package com.mybatis3.mappers;

import com.mybatis3.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * @author Siva
 *
 */
public interface CourseMapper
{

	List<Course> selectCoursesByTutor(int tutorId);

	List<Course> searchCourses(Map<String, Object> map);

	List<Course> searchCoursesByTutors(Map<String, Object> map);

}
