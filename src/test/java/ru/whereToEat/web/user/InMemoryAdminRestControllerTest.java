package ru.whereToEat.web.user;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.repository.UserRepository;
import ru.whereToEat.repository.inMemory.InMemoryUserRepository;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.whereToEat.UserTestData.*;

public class InMemoryAdminRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(InMemoryAdminRestControllerTest.class);

    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;
    private static InMemoryUserRepository repository;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app-test.xml");
        log.info("\n{}\n", Arrays.toString(appCtx.getBeanDefinitionNames()));
        controller = appCtx.getBean(AdminRestController.class);
        repository = appCtx.getBean(InMemoryUserRepository.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Before
    public void setUp() throws Exception {
        // re-initialize
        repository.init();
    }

    @Test
    public void delete() {
        controller.delete(ADMIN_ID);
        assertNull(repository.get(ADMIN_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        Assert.assertThrows(NotFoundException.class, () -> controller.delete(1));
    }

    /*@Test
    public void getAll() {
    }

    @Test
    public void get() {
    }

    @Test
    public void create() {
    }


    @Test
    public void update() {
    }

    @Test
    public void getByMail() {
    }*/
}