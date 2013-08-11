package test;

import java.io.File;
import java.util.List;

import main.SmartFileReader;
import main.Team;

import org.junit.Test;

/**
 * Test on the SmartFileReader
 * 
 * @author Jason
 */
public class SmartFileReaderTest
{
	private static final String userDir = System.getProperty("user.dir");
	

	@Test
	public void testReadInto()
	{
		SmartFileReader reader = new SmartFileReader();
		
		File file = new File(userDir+"\\src\\data\\football.dat");
		List<Team> teams = reader.readInto(file, Team.class);
		
		
		assert(teams != null);
		assert(teams.size() != 0);
		assert(teams.get(0).getTeam() != null);
	}
	

}
