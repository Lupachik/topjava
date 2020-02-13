<%--
  Created by IntelliJ IDEA.
  User: alexey
  Date: 11.02.2020
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <style>
        tr.exceed {
            color: red;
        }

        tr.notExceed {
            color: green;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
    <p><a href="meals?action=create">Добавить</a></p>
    <table cellpadding="10" cellspacing="1">
        <tr>
            <td>Дата/Время</td>
            <td>Описание</td>
            <td>Калории</td>
            <td colspan=2></td>
        </tr>
        <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
        <c:forEach var="meal" items="${mealList}">
            <tr class=${meal.excess?'"exceed"': '"notExceed"'}>
                <td>${TimeUtil.dateToString(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=edit&mealId=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&mealId=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
