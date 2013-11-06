/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.controller;

import com.epam.networker.algorithms.NaiveNetworkOptimizationTaskSolver;
import com.epam.networker.algorithms.NetworkOptimizationTaskSolver;
import com.epam.networker.db.entities.Connection;
import com.epam.networker.db.entities.Solution;
import com.epam.networker.db.entities.Task;
import com.epam.networker.db.service.ConnectionService;
import com.epam.networker.db.service.SolutionService;
import com.epam.networker.db.service.TaskService;
import com.epam.networker.entities.NetworkConnection;
import com.epam.networker.entities.NetworkOptimizationTask;
import com.epam.networker.utils.NetworkOptimizationTaskReader;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Iaroslav_Mazai
 */
@Controller
public class TaskUploadController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private ConnectionService connectionService;
	@Autowired
	private SolutionService solutionService;

	@RequestMapping(value = "/uploadForm")
	public String getUploadForm(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		return "/upload/uploadForm";
	}

	@RequestMapping(value = "/uploadFile")
	public ModelAndView fileUploaded(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			BindingResult result) {
		MultipartFile file = uploadedFile.getFile();
		try {
			NetworkOptimizationTask task = NetworkOptimizationTaskReader.readOptimizationTask(file.getInputStream());
			Task taskEntity = new Task(task);
			taskService.save(taskEntity);
			for (Connection connection : taskEntity.getConnectionCollection()) {
				connection.setTaskID(taskEntity);
				connectionService.save(connection);
			}
			NetworkOptimizationTaskSolver solver = new NaiveNetworkOptimizationTaskSolver();
			List<NetworkConnection> solutionConnections = solver.solve(task);

			Solution solution = new Solution(solutionConnections);
			solutionService.save(solution);
			for (Connection connection : solution.getConnectionCollection()) {
				connectionService.save(connection);
			}

		} catch (IOException ex) {
		}

		return new ModelAndView("/uploadForm");
	}
}
