/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.db.entities;

import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author harker777
 */
@Entity
@Table(name = "task")
public class Task implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Size(max = 45)
	@Column(name = "Name")
	private String name;
	@Column(name = "StartNodeName")
	private Character startNodeName;
	@Column(name = "EndNodeName")
	private Character endNodeName;
	@Column(name = "Timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	@OneToMany(mappedBy = "taskID")
	private Collection<Connection> connectionCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskID")
	private Collection<Solution> solutionCollection;

	public Task() {
	}

	public Task(NetworkOptimizationTask optimizationTask) {
		this.name = "Name";
		this.startNodeName = optimizationTask.getStartNodeName();
		this.endNodeName = optimizationTask.getEndNodeName();
		this.timestamp = new Date();

		this.connectionCollection = new LinkedList<Connection>();
		for (NetworkConnection networkConnection : optimizationTask.getConnections()) {
			this.connectionCollection.add(new Connection(networkConnection));
		}
	}

	public Task(Integer id) {
		this.id = id;
	}

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

	public Character getStartNodeName() {
		return startNodeName;
	}

	public void setStartNodeName(Character startNodeName) {
		this.startNodeName = startNodeName;
	}

	public Character getEndNodeName() {
		return endNodeName;
	}

	public void setEndNodeName(Character endNodeName) {
		this.endNodeName = endNodeName;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Collection<Connection> getConnectionCollection() {
		return connectionCollection;
	}

	public void setConnectionCollection(
			Collection<Connection> connectionCollection) {
		this.connectionCollection = connectionCollection;
	}

	public Collection<Solution> getSolutionCollection() {
		return solutionCollection;
	}

	public void setSolutionCollection(
			Collection<Solution> solutionCollection) {
		this.solutionCollection = solutionCollection;
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
		if (!(object instanceof Task)) {
			return false;
		}
		Task other = (Task) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.epam.networker.db.entities.Task[ id=" + id + " ]";
	}
}
