<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<style>
    .box{
      margin: auto;
    }
    caption{
        margin-bottom: 20px;
    }
</style>
<body>
<%--${requestScope.all}--%>
<div class="box">
<table border="1px solid gray" align="center">
    <caption style="color: grey;font-size: 25px">展示页面</caption>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${all}" var="item">
    <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.gender}</td>
        <td>${item.age}</td>
        <td>${item.email}</td>
    </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
