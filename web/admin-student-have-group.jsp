<%-- 
    Document   : admin-student-have-group
    Created on : Apr 3, 2022, 5:11:38 PM
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
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <!--                    <div class="box-header">
                                            <div class="box-tools pull-left">
                    <c:if test="${sessionScope.COUNT_STU > 4}">
                        <button type="button" class="btn btn-default btn-sm btn-student">
                            <a href="AdminRandomStudentNoGroupController">Random</a>
                        </button>
                    </c:if>
                </div>
            </div>-->
                    <c:if test="${sessionScope.LIST_USER_HAVE_GROUP eq null}">
                        <h4 style="text-align-last: center">${requestScope.MESSAGE_ADMIN}</h4>
                    </c:if>
                    <div class="box-body table-responsive no-padding">
                        <c:if test="${sessionScope.LIST_USER_HAVE_GROUP ne null}">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Student Id</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Group Name</th>
                                        <th>Project Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${sessionScope.LIST_USER_HAVE_GROUP}" var="list" varStatus="count">
                                        <tr>
                                            <td style="width: 250px">${count.count}</td>
                                            <td style="width: 500px">${list.userId}</td>
                                            <td style="width: 500px">${list.userName}</td>
                                            <td style="width: 500px">${list.email}</td>
                                            <td style="width: 500px">${list.group.name}</td>
                                            <td style="width: 500px">${list.group.project.projectId}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer clearfix">
                        <!--                                                        <ul class="pagination pagination-sm no-margin pull-right">
                                                                                     <li><a href="#">&laquo;</a></li> 
                                                                                    <li><a href="#">Previous</a></li>
                                                                                    <li><a href="#">1</a></li>
                                                                                    <li><a href="#">2</a></li>
                                                                                    <li><a href="#">3</a></li>
                                                                                    <li><a href="#">Next</a></li>
                                                                                     <li><a href="#">&raquo;</a></li> 
                                                                                </ul>-->
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </body>
</html>
