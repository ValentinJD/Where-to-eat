package ru.wheretoeat.web.user;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.wheretoeat.exceptions.NotFoundException;
import ru.wheretoeat.repository.inMemory.InMemoryUserRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.wheretoeat.UserTestData.ADMIN_ID;

public class InMemoryAdminRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(InMemoryAdminRestControllerTest.class);

    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;
    private static InMemoryUserRepository repository;

    @BeforeAll
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app-test.xml");
        log.info("\n{}\n", Arrays.toString(appCtx.getBeanDefinitionNames()));
        controller = appCtx.getBean(AdminRestController.class);
        repository = appCtx.getBean(InMemoryUserRepository.class);
    }

    @AfterAll
    public static void afterClass() {
        appCtx.close();
    }

    @BeforeEach
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
        Assertions.assertThrows(NotFoundException.class, () -> controller.delete(1));
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