package com.pos.dao;

import java.util.List;

import com.pos.daoimpl.MemberDaoImpl;
import com.pos.entity.Member;
import com.pos.entity.Role;

public interface MemberDao {
	
	public static MemberDao getInstance() {
		return new MemberDaoImpl();
	}

	Member login(String loginID, String password);
	void save(Member member);
	void delete(Member member);
	List<Member> search(String username, Role role);
	long getCount();
}
