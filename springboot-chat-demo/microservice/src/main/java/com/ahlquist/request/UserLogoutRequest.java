package com.ahlquist.request;

import java.util.Objects;
import java.util.UUID;

public class UserLogoutRequest {

	private String uuid;
	
	public UserLogoutRequest() {
		super();
	}
	
	public UserLogoutRequest(UUID uuid) {
		super();
		this.uuid = uuid.toString();
	}

	public UserLogoutRequest(String uuid) {
		super();
		this.uuid = uuid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogoutRequest other = (UserLogoutRequest) obj;
		return Objects.equals(uuid, other.uuid);
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "UserLogoutRequest [uuid=" + uuid + "]";
	}

}
