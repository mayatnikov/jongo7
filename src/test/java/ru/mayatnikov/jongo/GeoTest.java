package ru.mayatnikov.jongo;

import org.jongo.MongoCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mayatnikov.datamodel.GeoPoint;
import ru.mayatnikov.datamodel.GeoPointDist;

import java.net.UnknownHostException;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;


public class GeoTest {
	
	/** Unit under test. */
	private MongoCollection geoPoints;
    private String marker = "DELETE_ME";


	@Before
    public void beginCase() throws UnknownHostException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:/META-INF/application-context-root.xml");
        geoPoints = ctx.getBean("geoATM ",iGeoJongoBean.class).getCollection();
    }

    @After
    public void endCase() {
    }

    @Test public void firstInitCase() {

    }

    @Test
    public void check_num_records() {
        long sz= geoPoints.count();
        System.out.println("Test DB size");
        assertEquals(sz, 2000000);
    }

    @Test
    public void should_find_first_GeoPoint_with_id_eq_1() {

        GeoPoint result = geoPoints.findOne("{'properties.ptype': 'ATM'}").as(GeoPoint.class);
        GeoPoint bad = geoPoints.findOne("{'properties.ptype': 'any-bad-text'}").as(GeoPoint.class);
        System.out.println("Find one record");
        System.out.println(">>>"+result);
        assertNotNull(result);
        int id=result.getId();
        assertEquals(1,id);
        assertNull(bad);
    }

    @Test
    public void modify_and_update_record() {
        String address="TEST_ADDRESS";
        System.out.println("Modify and update record");
        GeoPoint store = geoPoints.findOne("{'properties.ptype': 'Питание'}").as(GeoPoint.class);
        assertNotNull(store);
        store.getProperties().setAddress(address);
        GeoPoint store4Restore = geoPoints.findOne("{'_id': # }",store.get_id()).as(GeoPoint.class);
        assertNotNull(store4Restore);
        geoPoints.update(store.get_id()).with(store);

        GeoPoint store3 = geoPoints.findOne("{'_id': # }",store.get_id()).as(GeoPoint.class);
        assertEquals(address,store3.getProperties().getAddress());
        geoPoints.update(store.get_id()).with(store4Restore);  // restore database

    }

    @Test
    public void insert_record() {
        System.out.println("Insert record");
        GeoPoint store = geoPoints.findOne("{'properties.ptype': 'Сервис'}").as(GeoPoint.class);
        assertNotNull(store);
        store.set_id(null);
        store.getProperties().setNotes(marker);
        geoPoints.save(store);

        long sz= geoPoints.count();
        assertEquals(sz,2000001);
    }

    @Test
    public void aggreagte_request_with_GeoNear_func() {

        String req = "{$geoNear: {" +
                "near: [55.701, 37.709]," +
                "distanceField: \"dist.calculated\"," +
                "maxDistance: 0.001," +
                "query: { \"properties.ptype\": \"ATM\" }," +  // only ATM point type
                "includeLocs: \"dist.location\"," +
                "uniqueDocs: false," +
                "num: 100" +
                "}}";
        System.out.println("Search geoNear ...");
        Iterable<GeoPointDist> result = geoPoints.aggregate(req).
                and("{$match:{type:'Feature'}}").
                and("{$limit:100}").
                as(GeoPointDist.class);
        int tik=0;
        System.out.println(">>>>>>>>>>>>>>> GeoNear result <<<<<<<<<<<<<<<<<");
        for(GeoPoint res : result) {
            tik++;
            System.out.println(tik + ">>> " + res);
        }
        assertEquals(4,tik);

    }

    @Test
    public void delete_test_records() {
        geoPoints.remove("{'properties.notes':#}",marker);
        long sz= geoPoints.count();
        assertEquals(sz, 2000000);
         System.out.println("Clear DB");
    }


}
