/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.db.repository;

import com.epam.networker.db.entities.Task;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author harker777
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
