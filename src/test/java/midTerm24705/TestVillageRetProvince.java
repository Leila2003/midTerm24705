package midTerm24705;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import controller.VillageReProvinceController;
import model.ElocationType;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVillageRetProvince {

	private Session session;
    private VillageReProvinceController controller;

    @Before
    public void setUp() {
        session = mock(Session.class);
        controller = new VillageReProvinceController(session);
    }

    @Test
    public void testGetProvinceByVillage() {
        // Assuming expectedProvinceName 
        String expectedProvinceName = "Kigali";

        // Configure your query behavior in the mock session here
        Query<String> query = mock(Query.class);
        when(session.createQuery(anyString(), eq(String.class))).thenReturn(query);
        when(query.setParameter("villageName", "Amahoro")).thenReturn(query);
        when(query.setParameter("villageType", ElocationType.VILLAGE)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expectedProvinceName);

        String provinceName = controller.getProvinceByVillage("Amahoro", ElocationType.VILLAGE);
        assertEquals(expectedProvinceName, provinceName);
    }

}
