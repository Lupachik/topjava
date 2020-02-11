<%--
  Created by IntelliJ IDEA.
  User: alexey
  Date: 11.02.2020
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>List ${name}</h2>
<section>
    <style>
        tr.exceed {
            color: red;
        }
        tr.notExceed {
            color: green;
        }
    </style>

<table cellpadding="10" cellspacing="1">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${mealList}">

            ${meal.isExcess()?'<tr class="exceed">':'<tr class="notExceed">'}
                <td>${meal.getDateTime()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
    </c:forEach>

</table>
</section>

</body>
</html>
