package com.satish.monitoring.web.models;

import java.io.Serializable;

import com.satish.monitoring.db.entities.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Satish Sharma
 * 
 * <pre>
 *  Model class to represent a person;
 *  For the sake of simplicity all fields are kept same as {@link PersonEntity} object to be used as Entity class representing th table
 *  </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = 3570556679061270028L;

	private int personId;
	private String firstName;
	private String lastName;
	private String email;

}
