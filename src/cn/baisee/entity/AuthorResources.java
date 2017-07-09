package cn.baisee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="author_resources")
@Entity
public class AuthorResources {

	@Id@Column@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String path;
	private Integer parentId;
	private Double rorder;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTs;
	private String note;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Double getRorder() {
		return rorder;
	}
	public void setRorder(Double rorder) {
		this.rorder = rorder;
	}
	public Date getCreateTs() {
		return createTs;
	}
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}