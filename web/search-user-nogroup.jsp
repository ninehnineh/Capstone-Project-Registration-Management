<%-- 
    Document   : search-user-nogroup
    Created on : Mar 29, 2022, 4:12:58 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th style="width : 10px">#</th>
                    <th>Id</th>
                    <th>Email</th>
                    <th>UserName</th>
                    <th></th>
                </tr>
            </thead>
            <c:forEach var="user" items="${sessionScope.LIST_USER_NO_GROUP}" varStatus="count">
                <tbody>
                    <tr>
                        <td style="width: 50px">${count.count}</td>
                        <td style="width: 100px">${user.userId}</td>
                        <td style="width: 100px">${user.email}</td>
                        <td style="width: 100px">${user.userName}</td>
                        <td style="width: 100px"> 
                            <form id="idForm" action="#" method="POST">
                                <input type="hidden" id="receiver" name="receiver" value="${user.email}"/>
                                <input type="hidden" id="sender" name="sender" value="${sessionScope.USER.email}"/>
                                <input type="hidden" id="groupName" name="groupName" value="${sessionScope.USER.group.name}"/>
                                <c:if test="${sessionScope.CURRENT_PROJECT eq null && sessionScope.CURRENT_GROUP eq null}">
                                    <button type="button" id="btnInvite">Invite</button>                                   
                                </c:if>
                            </form>
                        </td>                                       
                    </tr>
                </tbody>
            </c:forEach>
        </table>
    </body>
</html>
