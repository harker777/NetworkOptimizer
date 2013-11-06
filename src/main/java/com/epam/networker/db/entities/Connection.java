/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.db.entities;

import com.epam.networker.entities.NetworkConnection;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author harker777
 */
@Entity
@Table(name = "connection")
public class Connection implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "StartNodeName")
	private char startNodeName;
	@Basic(optional = false)
	@NotNull
	@Column(name = "EndNodeName")
	private char endNodeName;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Delay")
	private double delay;
	@JoinColumn(name = "Solution_ID", referencedColumnName = "ID")
	@ManyToOne
	private Solution solutionID;
	@JoinColumn(name = "Task_ID", referencedColumnName = "ID")
	@ManyToOne
	private Task taskID;

	public Connection() {
	}

	public Connection(NetworkConnection networkConnection) {
		this.startNodeName = networkConnection.getStartNodeName();
		this.endNodeName = networkConnection.getEndNodeName();
		this.delay = networkConnection.getDelay();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public char getStartNodeName() {
		return startNodeName;
	}

	public void setStartNodeName(char startNodeName) {
		this.startNodeName = startNodeName;
	}

	public char getEndNodeName() {
		return endNodeName;
	}

	public void setEndNodeName(char endNodeName) {
		this.endNodeName = endNodeName;
	}

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}

	public Solution getSolutionID() {
		return solutionID;
	}

	public void setSolutionID(Solution solutionID) {
		this.solutionID = solutionID;
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
		if (!(object instanceof Connection)) {
			return false;
		}
		Connection other = (Connection) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.epam.networker.db.entities.Connection[ id=" + id + " ]";
	}
}
