<%-- 
    Document   : messagee
    Created on : Mar 15, 2022, 11:45:43 AM
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
                    <div class="box-header">
                        <h3 class="box-title">Message</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-header clearfix">

                    </div>
                    <!-- /.box-header -->
                    <c:if test="${sessionScope.EVENT eq null}">
                        <h4 style="text-align-last: center">There are no messages yet</h4>
                    </c:if>
                    <div class="box-body table-responsive no-padding">
                        <c:if test="${sessionScope.EVENT ne null}">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Message</th>
                                        <th></th>
                                        <th>Sender</th>
                                        <th></th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <c:forEach var="event" items="${sessionScope.EVENT}" varStatus="count">
                                    <tbody>
                                        <tr>
                                            <td style="width: 50px">${count.count}</td>
                                            <td style="width: 500px">${event.event.messageContent}</td>
                                            <td style="width: 250px">by</td>
                                            <td style="width: 250px">${event.sender.userName}</td>
                                            <td style="width: 250px" hidden>${event.event.messageEvent}</td>
                                            <td style="width: 250px" hidden>${event.receiver}</td>
                                            <td style="width: 250px" hidden>${event.sender.userId}</td>
                                            <c:if test="${event.event.messageEvent eq 'Invite'}">
                                                <td>
                                                    <form action="StudentDecisionController">
                                                        <input type="hidden" name="sender" value="${event.sender.userId}"/>
                                                        <input type="hidden" name="invitedUserId" value="${sessionScope.USER.userId}"/>
                                                        <input type="hidden" name="emailReceiver" value="${sessionScope.USER.email}"/>
                                                        <input type="submit" name="studentDecision" value="Accept"/>
                                                    </form>
                                                </td>
                                                <td>
                                                    <form action="StudentDenyGroupInvitationController">       
                                                        <input type="hidden" name="sender" value="${event.sender.userId}"/>
                                                        <input type="hidden" name="receiver" value="${event.receiver}"/>
                                                        <input type="submit" value="Deny"/>
                                                    </form>
                                                </td>
                                            </c:if>
                                            <c:if test="${event.event.messageEvent ne 'Invite'}">
                                                <td style="width: 25px">
                                                    <form action="DeleteMessageController">
                                                        <input type="hidden" name="loginedUserEmail" value="${sessionScope.USER.email}"/>
                                                        <input type="hidden" name="sender" value="${event.sender.userId}"/>
                                                        <input type="hidden" name="receiver" value="${event.receiver}"/>
                                                        <input type="hidden" name="event" value="${event.event.messageEvent}"/>
                                                        <!--<input type="submit" value="Delete"/>-->
                                                        <button type="button" id="btnDeleteMessage" value="Delete">Delete</button>
                                                    </form>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer clearfix">
                        <c:if test="${sessionScope.EVENT ne null}">
                            <%--<c:if test="${sessionScope.EVENT != null}">--%>
                            <form class="pull-right">
                                <input type="hidden" id="loginedUser" value="${sessionScope.USER.email}"/>
                                <button type="button" id="btnDeleteAllMessage" value="DeleteAll">Delete All</button>
                            </form>                        
                            <%--</c:if>--%>
                        </c:if>
                    </div>
                </div>
                <!-- /.box -->
            </div>
        </div>
    </body>
</html>
