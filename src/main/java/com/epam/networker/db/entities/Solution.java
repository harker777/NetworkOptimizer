/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.db.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author harker777
 */
@Entity
@Table(name = "solution")
public class Solution implements Serializable {

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ID")
	private Integer id;
	@OneToMany(mappedBy = "solutionID")
	private Collection<Connection> connectionCollection;
	@JoinColumn(name = "Task_ID", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Task taskID;

	public Solution() {
	}

	public Solution(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<Connection> getConnectionCollection() {
		return connectionCollection;
	}

	public void setConnectionCollection(
			Collection<Connection> connectionCollection) {
		this.connectionCollection = connectionCollection;
	}

	public Task getTaskID() {
		return taskID;
	}

	public void setTaskID(Task taskID) {
		this.taskID = taskID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Solution)) {
			return false;
		}
		Solution other = (Solution) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.epam.networker.db.entities.Solution[ id=" + id + " ]";
	}
}
