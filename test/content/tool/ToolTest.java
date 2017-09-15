package content.tool;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Manuel Gallina
 */
public class ToolTest 
{

	Tool testForceps = new Forceps("Forcipe 01");
	Tool testVacuumCleaner = new VacuumCleaner("Aspiratore 01");
	Tool testScalpel = new Scalpel("Bisturi 01");	
	
	/**
	 * Test method for {@link content.tool.Tool#act()}.
	 */
	@Test
	public void testAct() 
	{
		assertEquals("Clutching", testForceps.act());
		assertEquals("Vacuuming", testVacuumCleaner.act());
		assertEquals("Cutting", testScalpel.act());
	}

}
