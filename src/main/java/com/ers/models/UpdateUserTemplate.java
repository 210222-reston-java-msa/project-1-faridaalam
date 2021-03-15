package com.ers.models;

public class UpdateUserTemplate extends User {

	
	private String newPword1;
	private String newPword2;
	
	UpdateUserTemplate (){
		super();
	}

	public UpdateUserTemplate(String newPword1, String newPword2) {
		super();
		this.newPword1 = newPword1;
		this.newPword2 = newPword2;
	}


	public UpdateUserTemplate(String firstName, String lastName, String email, String password, String newPword1, String newPword2 ) {
		super(firstName, lastName, email, password);
		
		this.newPword1 = newPword1;
		this.newPword2 = newPword2;
	}

	public String getNewPword1() {
		return newPword1;
	}

	public void setNewPword1(String newPword1) {
		this.newPword1 = newPword1;
	}

	public String getNewPword2() {
		return newPword2;
	}

	public void setNewPword2(String newPword2) {
		this.newPword2 = newPword2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((newPword1 == null) ? 0 : newPword1.hashCode());
		result = prime * result + ((newPword2 == null) ? 0 : newPword2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateUserTemplate other = (UpdateUserTemplate) obj;
		if (newPword1 == null) {
			if (other.newPword1 != null)
				return false;
		} else if (!newPword1.equals(other.newPword1))
			return false;
		if (newPword2 == null) {
			if (other.newPword2 != null)
				return false;
		} else if (!newPword2.equals(other.newPword2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "updateUserTemplate [newPword1=" + newPword1 + ", newPword2=" + newPword2 + "]";
	}

	
	
	
	
	
	
}
