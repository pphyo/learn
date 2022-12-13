package com.pos.util;

import java.util.List;

import com.pos.dao.MemberDao;
import com.pos.entity.Member;
import com.pos.entity.Role;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class MemberSearchService extends Service<List<Member>> {
	
	private String username;
	private Role role;

	public MemberSearchService(String username, Role role) {
		this.username = username;
		this.role = role;
	}

	@Override
	protected Task<List<Member>> createTask() {
		return new Task<List<Member>>() {
			@Override
			protected List<Member> call() throws Exception {
				return MemberDao.getInstance().search(username, role);
			}
		};
	}

}
