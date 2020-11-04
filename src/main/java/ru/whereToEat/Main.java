package ru.whereToEat;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.exceptions.NotSaveOrUpdateException;
import ru.whereToEat.repository.UserRepository;
import ru.whereToEat.repository.springJdbc.SpringJdbcUserRepository;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NotFoundException, NotSaveOrUpdateException {
        System.out.println("ТЕст");
        UserRepository userRepository;
//                new JDBCUserRepository();

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));

        SpringJdbcUserRepository repository = context.getBean(SpringJdbcUserRepository.class);
        repository.get(100000);
        /*User
        repository.save()*/

        /*MealRestController mealRestController = context.getBean(MealRestController.class);
        System.out.println(mealRestController.get(100028));*/
        // Тестирование userRepository
        /*userRepository = context.getBean(UserRepository.class);
        User user = userRepository.get(100000);
        System.out.println("Хелоу");
        List<User> users = userRepository.getAll();
        context.close();
        users.forEach(System.out::println);*/
        /*System.out.println(userRepository.delete(16));*/
        /*userRepository.save(new User("Вася", "vasya@mail.ru", "password", Role.USER));
        userRepository.save(new User("Петя", "petya@mail.ru", "password", Role.USER));
        userRepository.save(new User("Иван", "ivan@mail.ru", "password", Role.USER));*/
       /* User user1 = userRepository.get(100);
        System.out.println(user1);*/
        //userRepository.save(user1);
        /*List<User> list = userRepository.getAll();
        list.forEach((user)-> System.out.println(user));*/


        // Тестирование RestaurauntRepository
        /*RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);
        Restaurant restaurant = restaurantRepository.get(100004); // Перчини Пицца & Паста
        List<Restaurant> restaurants = restaurantRepository.getAll(); // 3 шт
        Restaurant restaurant1 = new Restaurant("Ресторан");
        restaurantRepository.save(restaurant1);
        restaurantRepository.delete(100018);
        context.close();
        restaurants.forEach(System.out::println);*/

        // Тестирование JDBCMealRepository
        /*MealRepository mealRepository = new JDBCMealRepository();
        List<Meal> meals = mealRepository.getAll(0);
        meals.forEach(System.out::println);*/

        // Тестирование VotesRepository
        //VotesRepository votesRepository = context.getBean(VotesRepository.class);
        //Получение голосов конкретного ресторана
        //List<Vote> votes = votesRepository.getAll(100002);
        //votes.forEach(System.out::println);

        //Сохранение голоса
        /*Vote vote = new Vote();
        vote.setUserId(100000);
        vote.setRestaurantId(100004);
        vote.setDate_vote(LocalDateTime.now());
        vote.setVote(0);
        System.out.println(votesRepository.save(vote));*/

       /* votes = votesRepository.getAll(100002);
        votes.forEach(System.out::println);*/

        //Получение голоса
        // System.out.println(votesRepository.get(100033));
        // Удаление голоса
        // votesRepository.delete(100033);

        /*System.out.println(votesRepository.isNewVote(100009, 100003));
        System.out.println(votesRepository.isNewVote(100001, 100003));*/

        //System.out.println(bool);

        //Тестирование VoteService
       /* VoteService voteService = new VoteService(votesRepository);
        System.out.println(voteService.getCountVote(100004));*/

    }
}
