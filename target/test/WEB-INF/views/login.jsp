<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="title"/></title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/changLocale?request_locale=zh_CN">中文</a>
|
<a href="${pageContext.request.contextPath}/changLocale?request_locale=en_US">英文</a>
<form>
    <label for="username">用户名：</label><input id="username" type="text" name="username"
                                             placeholder="<spring:message code="uname"/>">
    <br>
    <label for="password">密&nbsp;&nbsp;码：</label><input type="password" id="password" name="password"
                                                        placeholder="<spring:message code="upwd"/>">
    <br>
    <input type="submit" value="<spring:message code="login"/>">&nbsp;&nbsp;
    <input type="reset" value="<spring:message code="reset"/>">
</form>
</body>
</html>
