package com.skillstorm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.skillstorm.id.AuthorityId;

@Entity(name="Authority")
@Table(name="authorities")
@IdClass(AuthorityId.class)
public class Authority {
	
		@Id
		@Column(name="username")
		private String username;
		
		@Id
		@Column(name="authority")
		private String authority;

		public Authority() {
			super();
		}

		public Authority(String username, String authority) {
			super();
			this.username = username;
			this.authority = authority;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}
		
		

}
