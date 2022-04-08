<%-- 
    Document   : creategroup
    Created on : Mar 5, 2022, 10:59:07 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AdminLTE 2 | Simple Tables</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <!--<link rel="stylesheet" href="../../bower_components/bootstrap/dist1/css/bootstrap.min.css">-->
        <link rel="stylesheet" href="bower_components/bootstrap/dist1/css/bootstrap.min.css">

        <!-- Font Awesome -->
        <!--<link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">-->
        <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">

        <!-- Ionicons -->
        <!--<link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">-->
        <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">

        <!-- Theme style -->
        <!--<link rel="stylesheet" href="../../dist1/css/AdminLTE.min.css">-->
        <link rel="stylesheet" href="dist1/css/AdminLTE.min.css">

        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <!--<link rel="stylesheet" href="../../dist1/css/skins/_all-skins.min.css">-->
        <link rel="stylesheet" href="dist1/css/skins/_all-skins.min.css">

        <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    </head>

    <body class="hold-transition skin-blue sidebar-mini">
        <c:url var="logout" value="LogoutController">

        </c:url>
        <div class="wrapper">

            <header class="main-header">
                <!-- Logo -->
                <a href="../../index2.html" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>P</b>S</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Projects System</b></span>
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <c:url var="link" value="MessageController">

                    </c:url>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->
<!--                            <li class="dropdown messages-menu">
                                <a href="${link}" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-envelope-o"></i>
                                    <span class="label label-success">4</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 4 messages</li>
                                    <%--<c:forEach items="${sessionScope.INVITATION}" var="invite" varStatus="count">--%>
                                    <li>
                                         inner menu: contains the actual data 
                                        <ul class="menu">
                                            <li>
                                                 start message 
                                                <a href="#">
                                                    <div class="pull-left">
                                                        a
                                                    </div>
                                                    <h4>
                                                        b
                                                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                    </h4>
                                                    <p>Fighting!!!</p>
                                                </a>
                                            </li>
                                             end message                                          
                                        </ul>
                                    </li>
                                    <%--</c:forEach>--%>
                                    <li class="footer"><a href="#">See All Messages</a></li>
                                </ul>
                            </li>-->
                            <!-- Notifications: style can be found in dropdown.less -->
<!--                            <li class="dropdown notifications-menu">

                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-warning">6</span>
                                </a>-->

                                <!--                                <a href="index.jsp" class="csw-btn-button fa fa-bell-o" rel="nofollow" target="_blank" ></a>-->

                                <!--                                <button onclick="window.location.href = '/page2'">Continue</button>
                                                                <form action="https://chuanseoweb.com">
                                                                    <button class="csw-btn-button" type="submit">Gửi Form</button>
                                                                </form>-->
                                <!--                                <form action="login.jsp">
                                                                    <button type="submit" class="dropdown-toggle" style="background: ;background: bottom;border: none;padding-top: 15px;padding-left: 10px; " data-toggle="dropdown" >
                                                                        <i class="fa fa-bell-o whiteiconcolor" style="color: white"></i>
                                                                    </button>
                                                                </form>-->
                                <%--<jsp:useBean id="menu" class="com.group6.capstoneprojectregistration.daos.InvitationPendingDAO" scope="session">                                </jsp:useBean>--%>
                                <%--<c:forEach items="${menu.getUserPending(sessionScope.USER.email)}" var="list">--%>
<!--                                <ul class="dropdown-menu" id="notification">
                                    <li class="header">You have 6 notifications</li>
                                    <li>
                                         inner menu: contains the actual data 
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-aqua"></i> ee
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">View all</a></li>
                                </ul>
                                <%--</c:forEach>--%>
                            </li>-->

                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="pages/account/admin.png" class="user-image" alt="User Image">
                                    <span class="hidden-xs">${sessionScope.USER.userName}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="pages/account/admin.png" class="img-circle" alt="User Image">
                                        <p>
                                            ${sessionScope.USER.userName}
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="${logout}" class="btn btn-default btn-flat">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="pages/account/admin.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>${sessionScope.USER.userName}</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <!-- <form action="#" method="get" class="sidebar-form">
                      <div class="input-group">
                        <input type="text" name="q" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                          <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                          </button>
                        </span>
                      </div>
                    </form> -->
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="treeview active">
                            <a href="#">
                                <i class="fa fa-dashboard"></i> <span>DashBoard</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <!--<li><a href="account.jsp"><i class="fa fa-circle-o"></i> Account</a></li>-->
                                <li class="active"><a href="#"><i class="fa fa-circle-o"></i> Groups</a></li>
                                <li><a href="ProjectController"><i class="fa fa-circle-o"></i> Projects</a></li>
                                <li><a href="StudentProjectPendingController"><i class="fa fa-circle-o"></i> Projects Had Signed</a></li>
                            </ul>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Group:
                        <small>${sessionScope.USER.group.name}</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active"><a href="#">Groups</a></li>
                    </ol>
                    <div class="createbutton">
                        <form action="CreateGroupController">
                            Group Name: <input type="text" name="groupName" value="" required="" placeholder="enter your group name...">
                            <input type="hidden" name="email" value="${sessionScope.USER.email}"/>
                            <input type="submit" name="action" value="Create" style="border-radius: 50px"/>

                            <%--<c:if test="${sessionScope.LIST_USER_IN_GROUP eq null}">--%>
                            <!--<button disabled="" class="btn btn-outline-primary fa fa-user-plus" type="button" data-toggle="popover" data-trigger="click" data-placement="bottom" data-html="true" data-title="Invite someone by email" data-original-title="" title="">-->
                            <!--<i class="fa fa-user-plus"></i>-->
                            <!--</button>-->
                            <%--</c:if>--%>
                            <%--<c:if test="${sessionScope.LIST_USER_IN_GROUP ne null}">--%>
                            <!--<button class="btn btn-outline-primary fa fa-user-plus" type="button" data-toggle="popover" data-trigger="click" data-placement="bottom" data-html="true" data-title="Invite someone by email" data-original-title="" title="">-->
                            <!--<i class="fa fa-user-plus"></i>-->
                            <!--</button>-->
                            <%--</c:if>--%>
                        </form>
                        <section>
                            <div id="PopoverContent" style="display: none;">
                                <div class="input-group header">
                                    <form action="InviteUserController" method="post">
                                        <input type="hidden" name="sender" value="${sessionScope.USER.email}"/>
                                        <input type="hidden" name="groupName" value="${sessionScope.USER.group.name}"/>
                                        <input type="text" name="receiver" value="" class="form-control" placeholder="What's email?" aria-label="Email with two button addons" aria-describedby="button-addon1" >
                                        <input type="submit" name="acount" value="Invite" class="input-group-append" id="button-addon1"/>
                                    </form>
                                </div>
                            </div>
                        </section>

                    </div>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- START CUSTOM TABS -->
                    <div class="row" >
                        <div class="col-xs-12">
                            <!-- Custom Tabs -->
                            <div class="nav-tabs-custom" id="tabs">
                                <ul class="nav nav-tabs" id="nav1">
                                    <li class="active"><a href="#tab_1" data-toggle="tab" onclick="showGroup()">Group</a></li>
                                    <li class=""><a href="#tab_2" data-toggle="tab" onclick="showMessage()">Message</a></li>
                                        <c:if  test="${sessionScope.USER.leader eq true}">
                                        <li class=""><a href="#tab_3" data-toggle="tab" onclick="showUserPending()">User Pending</a></li>
                                        <li class=""><a href="#tab_4" data-toggle="tab" onclick="showUserNoGroup()" id="action">Invitation</a></li>
                                        </c:if>                                       
                                </ul>
                                <div class="tab-content" id="form">
                                    <div class="tab-pane active" id="tab_1">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Your Group</h3></br>
                                                        <h4 style="color: red">${requestScope.INVITE}</h4>
                                                        <h4 style="color: red">${requestScope.BUG}</h4>
                                                        <h4 style="color: red">${requestScope.DUPLICATE}</h4>
                                                        <h4 style="color: red">${requestScope.ACCEPT}</h4>
                                                        <h4 style="color: red">${requestScope.REMOVE}</h4>
                                                        <h4 style="color: red">${requestScope.MESSAGE_DELETE_USER_PENDING}</h4>
                                                        <h4 style="color: red">${requestScope.UPDATE_STATUS}</h4>
                                                        <h4 style="color: red">${requestScope.USER_HAVE_GROUP}</h4>
                                                        <h4 style="color: red">${requestScope.MESSAGE_DISBAND}</h4>
                                                        <h4 style="color: red">${requestScope.LEAVE}</h4>
                                                        <span id="error"></span>
                                                    </div>
                                                    <!-- /.box-header -->
                                                    <div class="box-header clearfix">

                                                    </div>
                                                    <!-- /.box-header -->
                                                    <c:if test="${sessionScope.LIST_USER_IN_GROUP eq null}">
                                                        <h4 style="text-align-last: center">${requestScope.MESSAGE}</h4>
                                                    </c:if>
                                                    <div class="box-body table-responsive no-padding" id="table">
                                                        <c:if test="${sessionScope.LIST_USER_IN_GROUP ne null}">
                                                            <table class="table table-hover">
                                                                <thead>
                                                                    <tr>
                                                                        <th>#</th>
                                                                        <th>User Id</th>
                                                                        <th>Username</th>
                                                                        <th>Gender</th>
                                                                        <th>Email</th>
                                                                        <th>Role</th>
                                                                        <th></th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${sessionScope.LIST_USER_IN_GROUP}" var="user" varStatus="count">
                                                                        <tr>
                                                                            <td style="width: 250px">${count.count}</td>
                                                                            <td style="width: 500px">${user.userId}</td>
                                                                            <td style="width: 500px">${user.userName}</td>
                                                                            <td style="width: 500px">${user.gender}</td>
                                                                            <td style="width: 500px">${user.email}</td>
                                                                            <c:if test="${user.leader eq true}">
                                                                                <td style="width: 500px">Leader</td>
                                                                            </c:if>
                                                                            <c:if test="${user.leader ne true}">
                                                                                <td style="width: 500px">Member</td>
                                                                            </c:if>
                                                                            <c:if test="${sessionScope.USER.leader eq true && user.userName ne sessionScope.USER.userName && sessionScope.GROUP.project eq null && sessionScope.DETAIL.group.groupId eq null}">
                                                                                <td style="width: 500px">
                                                                                    <form action="LeaderRemoveStudentsController">
                                                                                        <input type="hidden" name="groupId" value="${user.group.groupId}"/>
                                                                                        <input type="hidden" name="groupName" value="${sessionScope.USER.group.name}"/>
                                                                                        <input type="hidden" name="receiverId" value="${user.userId}"/>
                                                                                        <input type="hidden" name="receiverEmail" value="${user.email}"/>
                                                                                        <input type="hidden" name="sender" value="${sessionScope.USER.userId}"/>
                                                                                        <input type="submit" name="remove" value="Remove"/>
                                                                                    </form>
                                                                                </td>
                                                                            </c:if>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                    </div>
                                                    <!-- /.box-body -->
                                                    <div class="box-footer clearfix">
                                                        <c:if test="${ sessionScope.DETAIL eq null && sessionScope.USER.group.groupId ne null }">
                                                            <c:if test="${sessionScope.USER.leader eq true }">
                                                                <form class="pull-right" action="LeaderDisbandGroupController" id="disbandForm">
                                                                    <input type="hidden" name="groupId" value="${sessionScope.USER.group.groupId}"/>
                                                                    <input type="hidden" name="currentUserId" value="${sessionScope.USER.userId}"/>
                                                                    <input type="hidden" name="groupName" value="${sessionScope.USER.group.name}"/>
                                                                    <input type="submit" id="bttDisband" value="Disband"/>
                                                                </form>
                                                            </c:if>
                                                            <c:if test="${sessionScope.USER.leader ne true }">
                                                                <form class="pull-right" action="MemberLeaveGroupController">
                                                                    <input type="hidden" name="groupId" value="${sessionScope.USER.group.groupId}"/>
                                                                    <input type="hidden" name="currentUser" value="${sessionScope.USER.userId}"/>
                                                                    <input type="submit" value="Leave"/>
                                                                </form>
                                                            </c:if>
                                                        </c:if>
                                                        ${requestScope.MESSAGE_LEAVE}
                                                    </div>
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="tab_2">
                                    </div>
                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="tab_3">
                                    </div>
                                    <div class="tab-pane" id="tab_4">

                                        <!-- /.tab-content -->
                                    </div>
                                    <!-- nav-tabs-custom -->
                                </div>
                                <!-- /.col -->
                            </div>
                            </section>
                            <!-- /.content -->
                        </div>
                        <!-- /.content-wrapper -->
                        <footer class="main-footer">
                            <div class="pull-right hidden-xs">
                                <b>Version</b> 26.02
                            </div>
                            <strong>Copyright &copy; 2022 baolucky1901 <a href="https://github.com/baolucky1901">My GitHub</a>.</strong>
                        </footer>
                        <!-- /.control-sidebar -->
                        <!-- Add the sidebar's background. This div must be placed
                           immediately after the control sidebar -->
                        <div class="control-sidebar-bg"></div>
                    </div> 
            </div>
        </div>

    </section>

    <!-- ./wrapper -->

    <!-- jQuery 3 -->
    <script src="bower_components/jquery/dist1/jquery.min.js"></script>
    <!--<script src="bower_components/jquery/dist1/jquery-3.2.1.js.min.js"></script>-->
    <!-- Bootstrap 3.3.7 -->
    <script src="bower_components/bootstrap/dist1/js/bootstrap.min.js"></script>
    <!-- Slimscroll -->
    <script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="bower_components/fastclick/lib/fastclick.js"></script>
    <!-- AdminLTE App -->
    <script src="dist1/js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="dist1/js/demo.js"></script>
    <script>
                                            $(function () {
                                                $('[data-toggle="popover"]').popover({
                                                    sanitize: false,
                                                    content: function () {
                                                        return $("#PopoverContent").html();
                                                    }
                                                });
                                            }).on('shown.bs.popover', function () {
                                                $('#ExecutorSNPSearchStr').focus();
                                            });</script>

    <!--show message ajax-->
    <script>
        function showMessage() {
            var xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                document.getElementById("tab_2").innerHTML = this.responseText;
            };
            xhttp.open("GET", "MessageController");
            xhttp.send();
        }
    </script>
    <!--show user pending ajax-->

    <script>
        function showUserPending() {
            var xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                document.getElementById("tab_3").innerHTML = this.responseText;
            };
            xhttp.open("GET", "InvitationPendingController");
            xhttp.send();
        }
    </script>
    <!--show user no group ajax-->
    <script>
        function showUserNoGroup() {
            var xhttp = new XMLHttpRequest();
            xhttp.onload = function () {
                document.getElementById("tab_4").innerHTML = this.responseText;
            };
            xhttp.open("GET", "StudentNoGroupController");
            xhttp.send();
        }
    </script>
    <!--invite user using ajax-->
    <script>
        $('#tab_4').on('click', '#btnInvite', function () {
            var currentRow = $(this).closest("tr");
            var col2 = currentRow.find("td:eq(2)").text();
            var currentUser = $('#sender').val();
            var currentGroupName = $('#groupName').val();
            $.ajax({
                url: 'InviteUserController',
                method: 'post',
                data: {
                    receiver: col2,
                    sender: currentUser,
                    groupName: currentGroupName
                },
                success: function (res) {
                    alert('Invite ' + col2 + ' successfully!');
                    showUserNoGroup();
                }
            });
        });
    </script>
    <!--delete user pending using ajax-->
    <script>
        $('#tab_3').on('click', '#btnDeleteUserPending', function () {
            var currentRow = $(this).closest("tr");
            var col = currentRow.find("td:eq(1)").text();
            var leaderId = $('#leaderId').val();
            var action = $('#btnDeleteUserPending').val();
            $.ajax({
                url: 'LeaderDeleteUserPendingController',
                method: 'post',
                data: {
                    userInvited: col,
                    userId: leaderId,
                    action: action
                },
                success: function () {
                    showUserPending();
                }
            });
        });
    </script>
    <!--delete message using ajax-->
    <script>
        $('#tab_2').on('click', '#btnDeleteMessage', function () {
            var currentRow = $(this).closest("tr");
            var sender = currentRow.find("td:eq(6)").text();
            var event = currentRow.find("td:eq(4)").text();
            var receiver = currentRow.find("td:eq(5)").text();
            var action = $('#btnDeleteMessage').val();

            $.ajax({
                url: 'DeleteMessageController',
                method: 'post',
                data: {
                    sender: sender,
                    receiver: receiver,
                    event: event,
                    action: action
                },
                success: function () {
                    showMessage();
                }
            });
        });
    </script>
    <!--delete all user pending using ajax-->
    <script>
        $('#tab_3').on('click', '#btnDeleteAllUserPending', function () {
            var leaderId = $('#leaderId').val();
            var action = $('#btnDeleteAllUserPending').val();
            $.ajax({
                url: 'LeaderDeleteUserPendingController',
                method: 'post',
                data: {
                    userId: leaderId,
                    action: action
                },
                success: function () {
                    showUserPending();
                }
            });
        });
    </script>
    <!--delete all message using ajax-->
    <script>
        $('#tab_2').on('click', '#btnDeleteAllMessage', function () {
            var loginedUser = $('#loginedUser').val();
            var action = $('#btnDeleteAllMessage').val();
            $.ajax({
                url: 'DeleteMessageController',
                method: 'post',
                data: {
                    loginedUser: loginedUser,
                    action: action
                },
                success: function () {
                    showMessage();
                }
            });
        });
    </script>
    <!--search-->
    <script>
        function searchByEmail(param) {
            var txtSearch = param.value;
            $.ajax({
                url: 'SearchUserController',
                method: 'get',
                data: {
                    txtEmail: txtSearch
                },
                success: function (data) {
                    var row = document.getElementById("show");
                    row.innerHTML = data;
                }
            });
        }
    </script>
</body>
</html>
