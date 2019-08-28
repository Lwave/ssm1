<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title><spring:message code="title"/> </title>
</head>
<body>
<form>
    <input type="text" name="username" placeholder="<spring:message code="uname"/>">
    <input type="password" name="password"  placeholder="<spring:message code="upwd"/>">
    <input type="submit" value="<spring:message code="login"/>">
    <input type="reset" value="<spring:message code="reset"/>">
</form>
</body>
</html>
