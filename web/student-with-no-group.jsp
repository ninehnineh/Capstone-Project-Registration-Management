<%-- 
    Document   : student-with-no-group
    Created on : Mar 16, 2022, 10:29:09 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>-->

    </head>
    <body>
        <div class="row" >
            <div class="col-xs-12" >
                <div class="box" >
                    <div class="box-header" >
                        <h3 class="box-title">User</h3>
                        <h4 style="color: red" >${requestScope.INVITE}</h4>
                        <form action="#" method="get">
                            <input type="text" name="txtEmail" placeholder="Search Email..." oninput="searchByEmail(this)" value="${param.txtEmail}">
                            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </form>
                    </div>
                    <!--search form--> 
                    <div class="box-body table-responsive no-padding" id="show">
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
                    </div>
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right">
                            <li><a href="#">&laquo;</a></li> 
                            <li><a href="#">Previous</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">Next</a></li>
                            <li><a href="#">&raquo;</a></li> 
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
