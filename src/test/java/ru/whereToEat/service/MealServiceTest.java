package ru.whereToEat.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.whereToEat.TestMatcher;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Meal;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.whereToEat.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
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
    private MealService service;

    @Test
    public void get() {
        Meal expected = service.get(MEDALYONY_IZ_GOVYADINY_ID);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEAL_FOR_GET, expected);
    }

    @Test
    public void delete() {
        service.delete(MEDALYONY_IZ_GOVYADINY_ID);
        assertThrows(EntityNotFoundException.class,
                () -> service.delete(MEDALYONY_IZ_GOVYADINY_ID));
    }


    @Test
    public void update() {
        Meal actual = getUpdated();
        Meal updated = service.update(getUpdated());
        Integer id = updated.getId();
        actual.setId(id);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }

    @Test
    public void getAll() {
        List<Meal> expected = service.getAll();
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEALS, expected);
    }

    @Test
    public void getAllByRestaurant() {
        List<Meal> expected = service.getAll(PERCHINI_ID);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(MEALS_PERCHINI, expected);
    }

    @Test
    public void create() {
        Meal actual = getUpdated();
        Meal updated = service.create(getUpdated());
        int id = updated.id();
        actual.setId(id);
        TestMatcher<Meal> testMatcher = TestMatcher.usingFieldsComparator("restaurant");
        testMatcher.assertMatch(actual, updated);
        testMatcher.assertMatch(actual, service.get(id));
    }
}