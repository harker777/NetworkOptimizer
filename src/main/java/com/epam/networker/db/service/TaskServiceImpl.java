package com.epam.networker.db.service;

import com.epam.networker.db.entities.Connection;
import com.epam.networker.db.entities.Task;
import com.epam.networker.db.repository.ConnectionRepository;
import com.epam.networker.db.repository.TaskRepository;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author harker777
 */
@Service("taskService")
@Repository
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ConnectionRepository connectionRepository;

	@Override
	public List<Task> findAll() {
		return Lists.newArrayList(taskRepository.findAll());
	}

	@Override
	public Task findById(Integer id) {
		return taskRepository.findOne(id);
	}

	@Override
	public Task save(Task task) {
		Task savedTask = taskRepository.save(task);
		for (Connection connection : savedTask.getConnectionCollection()) {
			connection.setTaskID(savedTask);
			connectionRepository.save(connection);
		}
		return savedTask;
	}
}
