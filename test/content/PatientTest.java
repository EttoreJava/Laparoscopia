package content;

import static org.junit.Assert.*;

import org.junit.Test;

public class PatientTest 
{
	Patient testPatient = new Patient();

	@Test
	public void testToString() 
	{
		assertEquals("Età: def%nPeso: def%n", testPatient.toString());
	}
	
	@Test
	public void testSetInfos()
	{
		String[] testInfos = new String[]{"Altezza", "1.70"};
		testPatient.setInfos(testInfos);
		
		assertEquals("Altezza: 1.70%n", testPatient.toString());
	}
}
