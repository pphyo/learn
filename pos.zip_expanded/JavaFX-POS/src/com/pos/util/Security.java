package com.pos.util;

import com.pos.entity.Member;

public final class Security {

	private static Member loginMember;
	
	public static void setLoginMember(Member member) {
		loginMember = member;
	}
	
	public static Member getLoginMember() {
		return loginMember;
	}
}
