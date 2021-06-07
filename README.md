[![Codacy Badge](https://app.codacy.com/project/badge/Grade/730b8c819b384cc383f02cf00cb249ef)](https://www.codacy.com/gh/ValentinJD/Where-to-eat/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ValentinJD/Where-to-eat&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.com/ValentinJD/topjava.svg?branch=master)](https://travis-ci.com/ValentinJD/topjava.svg)

# Where-to-eat
Vote for the restaurant with the best food (Задание на дипломную работу на стажировке topjava)
-
Разработка и реализация REST API с использованием
в backend: Hibernate / Spring/ SpringMVC  (или Spring-Boot) 
в frontend: HTML, CSS, Javascript, JQuery, AJAX, Bootstrap
Задача такова:
Постройте систему голосования для принятия решения о том, где пообедать.

2 типа пользователей: администратор и обычные пользователи
Администратор может ввести ресторан, это обеденное меню дня (обычно 2-5 пунктов, просто название блюда и цена)
Меню меняется каждый день (администраторы делают обновления)
Пользователи могут проголосовать за то, в каком ресторане они хотят пообедать
На одного пользователя приходится только один голос
Если пользователь проголосует снова в тот же день:
Если это произойдет до 11: 00, мы предполагаем, что он передумал.
Если это после 11: 00, то уже слишком поздно, голосование не может быть изменено
Каждый день в каждом ресторане предлагается новое меню.

В результате предоставьте ссылку на репозиторий github. Он должен содержать код, README.md с документацией API и парой команд curl для его тестирования.

P.S.: убедитесь, что все работает с последней версией, которая находится на github :)

П. П. С.: предполагается, что ваш API будет использоваться frontend-разработчиком, чтобы построить интерфейс на основании вашего API.
