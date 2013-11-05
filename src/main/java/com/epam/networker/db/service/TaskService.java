package com.epam.networker.db.service;

import com.epam.networker.db.entities.Task;
import java.util.List;

/**
 *
 * @author harker777
 */
public interface TaskService {

	public List<Task> findAll();

	public Task findById(Integer id);

	public Task save(Task task);
}
