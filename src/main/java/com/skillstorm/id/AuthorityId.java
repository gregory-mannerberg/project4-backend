package com.skillstorm.id;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityId implements Serializable {
	
	private static final long serialVersionUID = -4673017661278092037L;
	private String username;
	private String authority;
	
	public AuthorityId() {
		super();
	}

	public AuthorityId(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authority, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityId other = (AuthorityId) obj;
		return Objects.equals(authority, other.authority) && Objects.equals(username, other.username);
	}
	
	

}
