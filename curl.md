meals getAll

curl -s http://localhost:8080/topjava/rest/meals

meals get

curl -s http://localhost:8080/topjava/rest/meals/100007

meals create

curl -s -X POST -d '{"dateTime": "2020-06-01T18:00","description": "Созданный ужин","calories": 300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/meals

meals update

curl -s -X PUT -d '{"dateTime": "2020-01-30T10:00","description": "Обновленный завтрак","calories": 200}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/meals/100002

meals delete

curl -s -X DELETE http://localhost:8080/topjava/rest/meals/100011

meals filter

curl "http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&startTime=09:00:00&endDate=2020-01-31&endTime=11:00:00"