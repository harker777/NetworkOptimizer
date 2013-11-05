/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.db.repository;

import com.epam.networker.db.entities.Connection;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author harker777
 */
public interface ConnectionRepository extends CrudRepository<Connection, Integer> {
}
