/**
 * 
 */
package com.mybatis3.services;

import com.mybatis3.domain.Tutor;
import com.mybatis3.mappers.TutorMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Siva
 *
 */
@Service
@Transactional
public class TutorService 
{
	@Autowired
	private SqlSession sqlSession;
	
	private TutorMapper getTutorMapper(){
		return sqlSession.getMapper(TutorMapper.class);
	}
	public List<Tutor> findAllTutors() {
		return getTutorMapper().findAllTutors();
	}
	
	public Tutor findTutorById(int tutorId) {
		return getTutorMapper().findTutorById(tutorId);
	}
	
	public Tutor findTutorByNameAndEmail(String name, String email) {
		return getTutorMapper().findTutorByNameAndEmail(name, email);
	}
	
	public Tutor createTutor(Tutor tutor) {
		getTutorMapper().insertTutor(tutor);
		return tutor;
	}
	
	public Tutor updateTutor(Tutor tutor) {
		getTutorMapper().updateTutor(tutor);
		return tutor;
	}
	
	public boolean deleteTutor(int tutorId) {
		boolean deleted = false;
		int nor = getTutorMapper().deleteTutor(tutorId);
		deleted = (nor == 1);
		return deleted;
	}
	
	public Tutor selectTutorById(int tutorId) {
		return getTutorMapper().selectTutorById(tutorId);
	}
	
	public Tutor selectTutorWithCoursesById(int tutorId) {
		return getTutorMapper().selectTutorWithCoursesById(tutorId);
	}
}
