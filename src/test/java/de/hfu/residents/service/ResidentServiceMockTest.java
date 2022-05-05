package de.hfu.residents.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import de.hfu.residents.domain.*;
import de.hfu.residents.service.*;
import de.hfu.residents.repository.ResidentRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResidentServiceMockTest {
	
	BaseResidentService residentService = new BaseResidentService();
	//ResidentRepository residentRepoStub = new ResidentRepositoryStub();
	@Before
	public void setResidentRepository() {
		ResidentRepository residentRepoMock = EasyMock.createMock(ResidentRepository.class);
		EasyMock.expect(residentService.getFilteredResidentsList(new Resident())).andReturn(new ArrayList<Resident>());
	}
	//GetFilteredResidentList
	@Test
	public void testGetFilteredResidentListEmptyFilter() {
		Resident filter = new Resident();
		List<Resident> res = residentService.getFilteredResidentsList(filter);
		assertEquals(res.size(), 4);
	}
	@Test
	public void testGetFilteredResidentListAllSchFamilyName() {
		Resident filter = new Resident("*", "Sch*", "*", "*", null);
		List<Resident> res = residentService.getFilteredResidentsList(filter);
		assertEquals(res.size(), 3);
	}
	@Test
	public void testGetFilteredResidentListAllFilter() {
		Resident filter = new Resident("*", "*", "*", "*", null);
		List<Resident> res = residentService.getFilteredResidentsList(filter);
		assertEquals(res.size(), 4);
	}
	@Test
	public void testGetFilteredResidentListOnlyVincent() {
		Resident filter = new Resident("Vincent", "Mattes", "Geißbühlstr. 15", "Balingen", new Date(1998, 2, 10));
		List<Resident> res = residentService.getFilteredResidentsList(filter);
		assertEquals(res.size(),1);
		assertEquals(res.get(0).getGivenName(), "Vincent");
	}
	
	@Test
	public void testGetUniqueResidentWithWildcard()  {
		Resident result;
		Resident residentWithWildcard = new Resident("Vin*", "mattes", "geißbühlstr. 15", "balingen", new Date(1998, 2, 10));
		
		try {
			result = residentService.getUniqueResident(residentWithWildcard);
		} catch (ResidentServiceException e) {
			assertTrue(true);
		}
		
	}
	@Test
	public void testGetUniqueResidentSuccessVincent() {
		Resident result = null;
		Resident filter = new Resident("Vincent", "Mattes", "Geißbühlstr. 15", "Balingen", new Date(1998, 2, 10));
		
		try {
			result = residentService.getUniqueResident(filter);
		} catch (ResidentServiceException e) {
			assertTrue(false);
		}
		
		assertEquals(result.getGivenName(), filter.getGivenName());
	}
	@Test
	public void testGetUniqueResidentNonUnique(){
		Resident result;
		Resident notUniqueResident = new Resident("Hans", "Schopp", "Neue Straße 2", "Neustadt", new Date(1955, 1, 1));
	
		try {
			result = residentService.getUniqueResident(notUniqueResident);
		} catch (ResidentServiceException e) {
			assertTrue(true);
		}
	}
}
