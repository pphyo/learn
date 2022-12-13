package com.jdc.app.repo;

import java.util.ArrayList;
import java.util.List;

import com.jdc.app.entity.Student;
import static com.jdc.app.util.CommonUtil.isNull;

public class StudentRepo implements BaseRepo<Student> {
	
	private List<Student> repo;
	private static StudentRepo INSTANCE;
	
	public static StudentRepo getInstance() {
		return null == INSTANCE ? INSTANCE = new StudentRepo() : INSTANCE;
	}
	
	private StudentRepo() {
		repo = new ArrayList<>();
	}

	@Override
	public void add(Student data) {
		if(!isNull(data))
			repo.add(data);
	}

	@Override
	public void update(int id, Student newData) {
		if(!isNull(newData))
			repo.set(findIndex(id), newData);
	}

	@Override
	public void delete(int id) {
		if(id > 0)
			repo.remove(findIndex(id));
	}

	@Override
	public Student findById(int id) {
	
		if(id > 0)
			return repo.get(findIndex(id));
		
		return null;
	}

	@Override
	public List<Student> findAll() {
		return new ArrayList<>(repo);
	}
	
	private int findIndex(int id) {
		
		int index = 0;
		
		for(Student stu : repo) {
			if(stu.getId() == id)
				index = repo.indexOf(stu);
		}
		
		return index;
	}

}
