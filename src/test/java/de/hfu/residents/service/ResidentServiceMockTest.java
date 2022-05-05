package de.hfu.residents.service;

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
	ResidentRepository residentRepoMock;
	
	
	@Before
	public void setResidentRepository() {
		residentRepoMock = EasyMock.createMock(ResidentRepository.class);
		
		Resident vincent = new Resident("Vincent", "Mattes", "Geißbühlstr. 15", "Balingen", new Date(1998, 2, 10));
		Resident torben = new Resident("Torben", "Schulz", "Imaginärstr. 1", "Stuttgart", new Date(2000, 1, 1));
		Resident hans1 = new Resident("Hans", "Schopp", "Neue Straße 2", "Neustadt", new Date(1955, 1, 1));
		Resident hans2 = new Resident("Hans", "Schopp", "Neue Straße 2", "Neustadt", new Date(1955, 1, 1));
		
		List<Resident> listWithAllResidents = new ArrayList<Resident>();
		listWithAllResidents.add(vincent);
		listWithAllResidents.add(torben);
		listWithAllResidents.add(hans1);
		listWithAllResidents.add(hans2);
		EasyMock.expect(residentRepoMock.getResidents()).andReturn(listWithAllResidents);		
		
		residentService.setResidentRepository(residentRepoMock);		
	}
	
	//GetFilteredResidentList
		@Test
		public void testGetFilteredResidentListEmptyFilter() {
			EasyMock.replay(residentRepoMock);
			Resident filter = new Resident();
			List<Resident> res = residentService.getFilteredResidentsList(filter);
			assertEquals(res.size(), 4);
			EasyMock.verify(residentRepoMock);
		}
		@Test
		public void testGetFilteredResidentListAllSchFamilyName() {
			EasyMock.replay(residentRepoMock);
			Resident filter = new Resident("*", "Sch*", "*", "*", null);
			List<Resident> res = residentService.getFilteredResidentsList(filter);
			assertEquals(res.size(), 3);
			EasyMock.verify(residentRepoMock);
		}
		@Test
		public void testGetFilteredResidentListAllFilter() {
			EasyMock.replay(residentRepoMock);
			Resident filter = new Resident("*", "*", "*", "*", null);
			List<Resident> res = residentService.getFilteredResidentsList(filter);
			assertEquals(res.size(), 4);
			EasyMock.verify(residentRepoMock);
		}
		@Test
		public void testGetFilteredResidentListOnlyVincent() {
			EasyMock.replay(residentRepoMock);
			Resident filter = new Resident("Vincent", "Mattes", "Geißbühlstr. 15", "Balingen", new Date(1998, 2, 10));
			List<Resident> res = residentService.getFilteredResidentsList(filter);
			assertEquals(res.size(),1);
			assertEquals(res.get(0).getGivenName(), "Vincent");
			EasyMock.verify(residentRepoMock);
		}
		/*
		@Test
		public void testGetUniqueResidentWithWildcardStreet()  {
			EasyMock.replay(residentRepoMock);
			Resident result;
			Resident residentWithWildcard = new Resident("Vincent", "mattes", "geißb*", "balingen", new Date(1998, 2, 10));
			
			try {
				result = residentService.getUniqueResident(residentWithWildcard);
				System.out.println(result.getGivenName());
			} catch (ResidentServiceException e) {
				assertTrue(true);
			}
			EasyMock.verify(residentRepoMock);
		}*/
		/*
		@Test
		public void testGetUniqueResidentWithWildcardPrename()  {
			EasyMock.replay(residentRepoMock);
			Resident result;
			Resident residentWithWildcard = new Resident("Vin*", "", "", "", new Date(1998, 2, 10));
			
			try {
				result = residentService.getUniqueResident(residentWithWildcard);
			} catch (ResidentServiceException e) {
				assertTrue(true);
			}
			EasyMock.verify(residentRepoMock);
		}
		
		@Test
		public void testGetUniqueResidentWithWildcardSurname()  {
			EasyMock.replay(residentRepoMock);
			Resident result;
			Resident residentWithWildcard = new Resident("Vincent", "matt*", "", "balingen", new Date(1998, 2, 10));
			
			try {
				result = residentService.getUniqueResident(residentWithWildcard);
			} catch (ResidentServiceException e) {
				assertTrue(true);
			}
			EasyMock.verify(residentRepoMock);
		}*/
		@Test
		public void testGetUniqueResidentSuccessVincent() {
			EasyMock.replay(residentRepoMock);
			Resident result = null;
			Resident filter = new Resident("Vincent", "Mattes", "Geißbühlstr. 15", "Balingen", new Date(1998, 2, 10));
			
			try {
				result = residentService.getUniqueResident(filter);
			} catch (ResidentServiceException e) {
				assertTrue(false);
			}
			
			assertEquals(result.getGivenName(), filter.getGivenName());
			EasyMock.verify(residentRepoMock);
		}
		@Test
		public void testGetUniqueResidentNonUnique(){
			EasyMock.replay(residentRepoMock);
			Resident result;
			Resident notUniqueResident = new Resident("Hans", "Schopp", "Neue Straße 2", "Neustadt", new Date(1955, 1, 1));
		
			try {
				result = residentService.getUniqueResident(notUniqueResident);
			} catch (ResidentServiceException e) {
				assertTrue(true);
			}
			EasyMock.verify(residentRepoMock);
		}
}
