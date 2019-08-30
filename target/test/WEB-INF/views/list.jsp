<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/getExcelData" method="post" enctype="multipart/form-data"  >
    <input type="file" name="myfile" />
    <input type="submit" value="导入"/>
</form>
<hr/>
<table>
    <c:if test="${!empty all}">
        <tr>
            <td colspan="6" align="right">
                <a href="insert">新增</a></td>
        </tr>
        <tr>
            <td>编号</td>
            <td>用户名</td>
            <td>密码</td>
            <td>加入时间</td>
            <td>用户状态</td>
            <td>  </td>
        </tr>
        <c:forEach items="${all}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.loginname}</td>
                <td>${user.password}</td>
                <td>${user.createdate}</td>
                <td>${user.status==0 ? '未启用':'启用'}</td>
                <td>
                    <a href="update?id=${user.id}">更新</a>
                    <a href="delete?id=${user.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<form action="export" method="post">
    <input type="submit" value="导出">
</form>
</body>
</html>
