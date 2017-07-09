package cn.baisee.entity;

import java.util.Date;

public class AuthorRole {

	private int roleId;
	private String roleName;
	private Date createTs;
	private double orderBy;
	private String note;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	public double getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(double orderBy) {
		this.orderBy = orderBy;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
}
