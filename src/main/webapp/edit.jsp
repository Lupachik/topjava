<%--
  Created by IntelliJ IDEA.
  User: alexey
  Date: 13.02.2020
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Add meal</title>
</head>
<body>
<form method="POST" action='meals'>
    <input type="hidden" name="mealId" value="${meal.id}">
    Date/time : <input
        type="datetime-local" name="date"
        value="${meal.dateTime}" required/> <br />
    Description : <input
        type="text" name="description"
        value="${meal.description}" required> <br />
    Calories : <input
        type="number" name="calories"
        value="${meal.calories}" required/> <br />
    <input type="submit" value="Submit" />
</form>
<p><a href="meals">List meals</a></p>

</body>
</html>
