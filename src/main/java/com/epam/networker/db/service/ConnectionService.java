package com.epam.networker.db.service;

import com.epam.networker.db.entities.Connection;
import java.util.List;

/**
 *
 * @author harker777
 */
public interface ConnectionService {

	public List<Connection> findAll();

	public Connection findById(Integer id);

	public Connection save(Connection connection);
}
