package controller;
import org.hibernate.Session;
import org.hibernate.query.Query;
import model.ElocationType;

public class VillageReProvinceController {
	private Session session;

    public VillageReProvinceController(Session session) {
        this.session = session;
    }

    public String getProvinceByVillage(String villageName, ElocationType villageType) {
        String hql = "SELECT province.locationName FROM Location village " +
                     "JOIN village.parentLocation sector " +
                     "JOIN sector.parentLocation district " +
                     "JOIN district.parentLocation province " +
                     "WHERE village.locationName = :villageName " +
                     "AND village.locationType = :villageType";
        
        Query<String> query = session.createQuery(hql, String.class);
        query.setParameter("villageName", villageName);
        query.setParameter("villageType", villageType);

        return query.uniqueResult();
    }

}
