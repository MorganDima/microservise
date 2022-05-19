package com.example.rentme_backend_morgan.business.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = {"name"})

@Entity
@Table(name = "typeOfRealtyObject")
public class TypeOfRealtyObject {

	@Id
	@Column(length=100)
	String name;
	
	@OneToMany(mappedBy = "typeOfRealtyObject",cascade = CascadeType.ALL)
	Set<RealtyObject> realtyObjects;

	public TypeOfRealtyObject(String name) {
		this.name = name;
	}
	
}
