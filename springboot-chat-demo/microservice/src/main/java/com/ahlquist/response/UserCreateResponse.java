package com.ahlquist.response;

import java.util.Objects;
import java.util.UUID;

public class UserCreateResponse {

	private UUID uuid;

	@Override
	public String toString() {
		return "UserCreateResponse [uuid=" + uuid + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCreateResponse other = (UserCreateResponse) obj;
		return Objects.equals(uuid, other.uuid);
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UserCreateResponse(UUID uuid) {
		super();
		this.uuid = uuid;
	}

}
