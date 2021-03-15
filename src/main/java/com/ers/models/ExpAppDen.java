package com.ers.models;

public class ExpAppDen {

	private String expId;


public ExpAppDen() {}


public String getExpId() {
	return expId;
}


public void setExpId(String expId) {
	this.expId = expId;
}


public ExpAppDen(String expId) {
	super();
	this.expId = expId;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((expId == null) ? 0 : expId.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ExpAppDen other = (ExpAppDen) obj;
	if (expId == null) {
		if (other.expId != null)
			return false;
	} else if (!expId.equals(other.expId))
		return false;
	return true;
}


@Override
public String toString() {
	return "ExpAppDen [expId=" + expId + "]";
}



}