/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.db.service;

import com.epam.networker.db.entities.Connection;
import com.epam.networker.db.entities.Solution;
import com.epam.networker.db.repository.ConnectionRepository;
import com.epam.networker.db.repository.SolutionRepository;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author harker777
 */
@Service("solutionService")
@Repository
public class SolutionServiceImpl implements SolutionService {

	@Autowired
	SolutionRepository solutionRepository;
	@Autowired
	ConnectionRepository connectionRepository;

	@Override
	public List<Solution> findAll() {
		return Lists.newArrayList(solutionRepository.findAll());
	}

	@Override
	public Solution findById(Integer id) {
		return solutionRepository.findOne(id);
	}

	@Override
	public Solution save(Solution solution) {
		Solution savedSolution = solutionRepository.save(solution);
		for (Connection connection : savedSolution.getConnectionCollection()) {
			connection.setSolutionID(solution);
			connectionRepository.save(connection);
		}
		return savedSolution;
	}
}
