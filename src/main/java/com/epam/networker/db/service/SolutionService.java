package com.epam.networker.db.service;

import com.epam.networker.db.entities.Solution;
import java.util.List;

/**
 *
 * @author harker777
 */
public interface SolutionService {

	public List<Solution> findAll();

	public Solution findById(Integer id);

	public Solution save(Solution solution);
}
