package ru.whereToEat.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.whereToEat.Profiles;
import ru.whereToEat.RestaurantTestData;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Restaurant;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(Profiles.HSQL_DB)
public class RestaurantServiceTest {
    private static final Logger log = getLogger("result");

    private static final StringBuilder results = new StringBuilder();

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }


    @Autowired
    private RestaurantService service;

    @Test
    public void get() {
        Restaurant expected = service.get(RestaurantTestData.PERCHINI_ID);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(RestaurantTestData.PERCHINI, expected);
    }

    @Test
    public void delete() {
        service.delete(RestaurantTestData.PERCHINI_ID);
        assertThrows(EntityNotFoundException.class, () -> service.delete(RestaurantTestData.PERCHINI_ID));
    }


    @Test
    public void update() {
        Restaurant actual = RestaurantTestData.getUpdated();
        Restaurant updated = service.update(RestaurantTestData.getUpdated());
        Integer id = updated.getId();
        actual.setId(id);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }

    @Test
    public void getAll() {
        List<Restaurant> expected = service.getAll();
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(RestaurantTestData.RESTAURANTS, expected);
    }

    @Test
    public void create() {
        Restaurant actual = RestaurantTestData.getNew();
        Restaurant newRestaurant = service.create(RestaurantTestData.getNew());
        int id = newRestaurant.id();
        actual.setId(id);
        TestMatcher<Restaurant> testMatcher = TestMatcher.usingFieldsComparator("menu");
        testMatcher.assertMatch(actual, newRestaurant);
        testMatcher.assertMatch(actual, service.get(id));
    }
}