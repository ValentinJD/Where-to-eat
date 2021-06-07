### curl samples (application deployed at application context `Where_to_eat`).

> For windows use `Git Bash`

#### get All Users

curl -i http://localhost:8080/Where_to_eat/rest/admin/users --user admin@gmail.com:admin

#### get Users 100001

curl -i http://localhost:8080/Where_to_eat/rest/admin/users/100001 --user admin@gmail.com:admin

#### register Users

curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:
application/json;charset=UTF-8' http://localhost:8080/Where_to_eat/rest/profile/register

#### get Profile

curl -s http://localhost:8080/Where_to_eat/rest/profile --user test@mail.ru:test-password

#### get MealByRestaurantId 100002

curl -i http://localhost:8080/Where_to_eat/rest/meals/100002 --user admin@gmail.com:admin

#### get Meal 100005

curl -i http://localhost:8080/Where_to_eat/rest/meal/100005 --user admin@gmail.com:admin

#### delete Meal 100005

curl -i -X DELETE http://localhost:8080/Where_to_eat/rest/meal/100005 --user admin@gmail.com:admin

#### create Meal

curl -s -X POST -d '{"description":"new meal","price":300}' -H 'Content-Type:
application/json;charset=UTF-8' http://localhost:8080/Where_to_eat/rest/meal/100002 --user admin@gmail.com:admin

#### filter GetByName Restaurants

curl -s http://localhost:8080/Where_to_eat/rest/restaurants/filter/перч --user user@yandex.ru:password`

#### getRestaurants

curl -s http://localhost:8080/Where_to_eat/rest/restaurants --user user@yandex.ru:password`

#### vote in Restaurants 100002

curl -s -X POST -d '{"userId":"100000", "date_vote":"2020-02-01T09:00","restaurantId":"100002", "vote": "300"}' -H '
Content-Type:application/json;charset=UTF-8'  http://localhost:8080/Where_to_eat/rest/vote --user user@yandex.ru:
password

#### update Meals

curl -s -X PUT -d '{"id":"100005", "description":"updated meal","price":300}' -H 'Content-Type:
application/json;charset=UTF-8' http://localhost:8080/Where_to_eat/rest/meal/100002  --user admin@gmail.com:admin

#### validate with Error

curl -s -X POST -d '{}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/admin/users --user
admin@gmail.com:admin
