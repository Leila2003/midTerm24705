package midTerm24705;

import static org.junit.Assert.*;



import org.junit.Test;

import controller.ConnectionController;
import util.HibernateUtil;

public class DatabaseConnectionTest {

	@Test
	public void test() {
		ConnectionController connection = new ConnectionController();
		assertNotNull(connection.establishConnection());
		
	}

}
