package de.hfu.residents.repository;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import de.hfu.residents.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository{

	List<Resident> Residents;
	
	public ResidentRepositoryStub() {
		this.Residents = new ArrayList<Resident>();
		Residents.add(new Resident("Vincent", "Mattes", "Geißbühlstr. 15", "Balingen", new Date(1998, 2, 10)));
		Residents.add(new Resident("Torben", "Schulz", "Imaginärstr. 1", "Stuttgart", new Date(2000, 1, 1)));
		Residents.add(new Resident("Hans", "Schopp", "Neue Straße 2", "Neustadt", new Date(1955, 1, 1)));
		Residents.add(new Resident("Hans", "Schopp", "Neue Straße 2", "Neustadt", new Date(1955, 1, 1)));
	}
	@Override
	public List<Resident> getResidents() {
		
		return Residents;
	}

}
