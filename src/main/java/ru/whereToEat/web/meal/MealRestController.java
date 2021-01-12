package ru.whereToEat.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.whereToEat.exceptions.NotEnoughRightsException;
import ru.whereToEat.model.Meal;
import ru.whereToEat.service.MealService;
import ru.whereToEat.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController extends AbstractMealController{
}
