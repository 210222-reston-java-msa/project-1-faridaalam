package com.ers.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Expense {

	private int id;
	private int userId;
	private int mgrId;
	private BigDecimal amount;
	private LocalDateTime time;
	private String note;
	private int type;
	private int status;
	private byte[] image;
	private boolean imgAdded;
	
	public Expense() {}
	
	
	
	public Expense(BigDecimal amount, int type) {
		super();
		this.amount = amount;
		this.type = type;
	}



	public Expense(BigDecimal amount, String note, int type) {
		super();
		this.amount = amount;
		this.note = note;
		this.type = type;
	}



	public Expense(BigDecimal amount, String note, int type, byte[] image) {
		super();
		this.amount = amount;
		this.note = note;
		this.type = type;
		this.image = image;
	}



	public Expense(int id, BigDecimal amount, LocalDateTime time, String note, int type, int status,
			byte[] image) {
		super();
		this.id = id;
		this.amount = amount;
		this.time = time;
		this.note = note;

		this.type = type;
		this.status = status;
		this.image = image;
	}
	public Expense(int id, BigDecimal amount, LocalDateTime time, String note, int type, int status,
			byte[] image, boolean imgAdded) {
		super();
		this.id = id;
		this.amount = amount;
		this.time = time;
		this.note = note;
	
		this.type = type;
		this.status = status;
		this.image = image;
		this.imgAdded = imgAdded;
	}

	public Expense(int id, BigDecimal amount, LocalDateTime time, String note, int type, int status,
			boolean imgAdded) {
		super();
		this.id = id;
		this.amount = amount;
		this.time = time;
		this.note = note;

		this.type = type;
		this.status = status;
		this.imgAdded = imgAdded;
	}

	public Expense(int id, int userId, int mgrId, BigDecimal amount, LocalDateTime time, String note,  int type, int status,
			boolean imgAdded) {
		super();
		this.id = id;
		this.userId = userId;
		this.mgrId = mgrId;
		this.amount = amount;
		this.time = time;
		this.note = note;
		this.type = type;
		this.status = status;
		this.imgAdded = imgAdded;
	}

	
	public boolean isImgAdded() {
		return imgAdded;
	}



	public void setImgAdded(boolean imgAdded) {
		this.imgAdded = imgAdded;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public LocalDateTime getTime() {
		return time;
	}



	public void setTime(LocalDateTime time) {
		this.time = time;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}





	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public byte[] getImage() {
		return image;
	}



	public void setImage(byte[] image) {
		this.image = image;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getMgrId() {
		return mgrId;
	}



	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + id;
		result = prime * result + (imgAdded ? 1231 : 1237);
		result = prime * result + mgrId;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + status;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + type;
		result = prime * result + userId;
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
		Expense other = (Expense) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id != other.id)
			return false;
		if (imgAdded != other.imgAdded)
			return false;
		if (mgrId != other.mgrId)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (status != other.status)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (type != other.type)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Expense [id=" + id + ", userId=" + userId + ", mgrId=" + mgrId + ", amount=" + amount + ", time=" + time
				+ ", note=" + note + ", type=" + type + ", status=" + status + ", imgAdded=" + imgAdded + "]";
	}
	
	
	
}
