package ru.whereToEat;

import ru.whereToEat.exceptions.NotFoundException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.model.Restaurant;
import ru.whereToEat.model.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.whereToEat.model.Vote;
import ru.whereToEat.repository.MealRepository;
import ru.whereToEat.repository.RestaurantRepository;
import ru.whereToEat.repository.UserRepository;
import ru.whereToEat.repository.VotesRepository;
import ru.whereToEat.repository.jdbc.JDBCMealRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NotFoundException {

        UserRepository userRepository;
//                new JDBCUserRepository();

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));

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
        VotesRepository votesRepository = context.getBean(VotesRepository.class);
        List<Vote> votes = votesRepository.getAll(100002);
        votes.forEach(System.out::println);
        Vote vote = new Vote();
        vote.setUserId(100000);
        vote.setRestaurantId(100002);
        vote.setDate_vote(LocalDateTime.now());
        vote.setVote(99);
        votesRepository.save(vote);
        votes = votesRepository.getAll(100004);
        votes.forEach(System.out::println);

        /*Date date = new Date();
        date.setTime(5);
        LocalDateTime ldt = LocalDateTime.now();*/
//        ldt.getLong()
    }
}
