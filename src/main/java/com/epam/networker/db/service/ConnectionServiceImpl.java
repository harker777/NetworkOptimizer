/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.db.service;

import com.epam.networker.db.entities.Connection;
import com.epam.networker.db.repository.ConnectionRepository;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author harker777
 */
@Service("connectionService")
@Repository
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private ConnectionRepository connectionRepository;

	@Override
	public List<Connection> findAll() {
		return Lists.newArrayList(connectionRepository.findAll());
	}

	@Override
	public Connection findById(Integer id) {
		return connectionRepository.findOne(id);
	}

	@Override
	public Connection save(Connection connection) {
		return connectionRepository.save(connection);
	}
}
