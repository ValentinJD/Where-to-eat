### curl samples (application deployed at application context `Where_to_eat`).
> For windows use `Git Bash`

#### get All Users
curl -i http://localhost:8080/Where_to_eat/rest/admin/users

#### get Users 100001
curl -i http://localhost:8080/Where_to_eat/rest/admin/users/100001

#### get MealByRestaurantId 100002
curl -i http://localhost:8080/Where_to_eat/rest/meals/100002

#### get Meal 100005
curl -i http://localhost:8080/Where_to_eat/rest/meal/100005

#### delete Meal 100005
curl -i -X DELETE http://localhost:8080/Where_to_eat/rest/meal/100005

#### create Meal
curl -s -X POST -d '{"description":"new meal","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/Where_to_eat/rest/meal/100002

#### filter GetByName Restaurants
curl -s http://localhost:8080/Where_to_eat/rest/restaurants/filter/перч`

#### getRestaurants
curl -s http://localhost:8080/Where_to_eat/rest/restaurants`

#### vote in Restaurants 100002
curl -s -X POST -d '{"userId":"100000", "date_vote":"2020-02-01T09:00","restaurantId":"100002", "vote": "300"}' -H 'Content-Type:application/json;charset=UTF-8'  http://localhost:8080/Where_to_eat/rest/vote

#### update Meals
curl -s -X PUT -d '{"id":"100005", "description":"updated meal","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/Where_to_eat/rest/meal/100002