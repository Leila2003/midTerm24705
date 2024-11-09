package midTerm24705;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import controller.LocationController;
import model.ElocationType;
import model.Location;

public class TestForLocation {

    private LocationController locationController;

    @Before
    public void setUp() {
        locationController = new LocationController();
    }

    @Test
    public void testSaveLocationHierarchy() {
        // Create Province Location
        Location province = new Location();
        province.setLocationCode("PR01");
        province.setLocationName("Kigali");
        province.setLocationType(ElocationType.PROVINCE);
        
        // Save Province
        String provinceResult = locationController.saveLocation(province);
        assertEquals("Location saved successfully", provinceResult);

        // Create District Location with Province as parent
        Location district = new Location();
        district.setLocationCode("DI01");
        district.setLocationName("Gasabo");
        district.setLocationType(ElocationType.DISTRICT);
        district.setParentLocation(province);

        // Save District
        String districtResult = locationController.saveLocation(district);
        assertEquals("Location saved successfully", districtResult);

        // Create Sector Location with District as parent
        Location sector = new Location();
        sector.setLocationCode("SE01");
        sector.setLocationName("Kacyiru");
        sector.setLocationType(ElocationType.SECTOR);
        sector.setParentLocation(district);

        // Save Sector
        String sectorResult = locationController.saveLocation(sector);
        assertEquals("Location saved successfully", sectorResult);

        // Create Cell Location with Sector as parent
        Location cell = new Location();
        cell.setLocationCode("CE01");
        cell.setLocationName("Kibaza");
        cell.setLocationType(ElocationType.CELL);
        cell.setParentLocation(sector);

        // Save Cell
        String cellResult = locationController.saveLocation(cell);
        assertEquals("Location saved successfully", cellResult);

        // Create Village Location with Cell as parent
        Location village = new Location();
        village.setLocationCode("VI01");
        village.setLocationName("Amahoro");
        village.setLocationType(ElocationType.VILLAGE);
        village.setParentLocation(cell);

        // Save Village
        String villageResult = locationController.saveLocation(village);
        assertEquals("Location saved successfully", villageResult);
    }
}
