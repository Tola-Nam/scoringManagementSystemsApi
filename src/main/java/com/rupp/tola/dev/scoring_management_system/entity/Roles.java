package com.rupp.tola.dev.scoring_management_system.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "role_id")
	private UUID id;

	@Column(name = "role_name", unique = true, nullable = false)
	private String name;

	@Column(name = "description" , nullable = false , length = 255)
	private String description;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "roles" ,fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private Set<Users> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "role_permission",
			joinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id" , referencedColumnName = "permission_id")
	)
	private Set<Permissions> permissions = new HashSet<>();

}
