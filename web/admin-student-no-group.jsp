<%-- 
    Document   : student-no-group
    Created on : Mar 19, 2022, 4:15:46 PM
    Author     : PC
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
                    <div class="box-header">
                        <h3 class="box-title">Students</h3>
                        <small>without group</small>
                        <div class="box-tools">
                            <button type="button" class="btn btn-default btn-sm btn-student">
                                <a href="StudentNoGroupRandomController">Random</a>
                            </button>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-header clearfix">
                        <ul class="pagination pagination-sm no-margin pull-left">
                        </ul>
                        <ul class="pagination pagination-sm no-margin pull-right">
                        </ul>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body table-responsive no-padding">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Student Id</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.ADMIN_LIST_USER_NO_GROUP}" var="list" varStatus="count">
                                    <tr>
                                        <td style="width: 250px">${count.count}</td>
                                        <td style="width: 500px">${list.userId}</td>
                                        <td style="width: 500px">${list.userName}</td>
                                        <td style="width: 500px">${list.email}</td>
                                        
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right">
                            <!-- <li><a href="#">&laquo;</a></li> -->
                            <li><a href="#">Previous</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">Next</a></li>
                            <!-- <li><a href="#">&raquo;</a></li> -->
                        </ul>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </body>
</html>
