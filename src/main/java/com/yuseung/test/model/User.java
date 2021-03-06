/**
 * 
 */
package com.yuseung.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author bbohi
 *
 */
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	private Boolean enabled;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
	  name = "user_role", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	List<Role> roles = new ArrayList<Role> ();
	
	//orphanRemoval 부모가 없는 데이터는 다 지운다.
	// OneToOne, ManyToOne default:FetchType.EAGER
	// OneToMany, ManyToMany default:FetchType.LAZY
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
//	@JsonIgnore
	private List<Board> boards = new ArrayList<Board> ();
}
