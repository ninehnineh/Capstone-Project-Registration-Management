<%-- 
    Document   : adminRandom
    Created on : Mar 5, 2022, 11:36:29 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Admin | Students</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="../../bower_components/bootstrap/dist1/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="../../bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="../../dist1/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="../../dist1/css/skins/_all-skins.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>

    <body class="hold-transition skin-blue sidebar-mini">
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

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Messages: style can be found in dropdown.less-->
                            <li class="dropdown messages-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-envelope-o"></i>
                                    <span class="label label-success">4</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 4 messages</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu">
                                            <li>
                                                <!-- start message -->
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="Chinh Truong.png" class="img-circle" alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Chinh Truong - Be
                                                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                    </h4>
                                                    <p>Fighting!!!</p>
                                                </a>
                                            </li>
                                            <!-- end message -->
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="Van Trung.jpg" class="img-circle" alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Van Trung - Fe
                                                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                                    </h4>
                                                    <p>Fighting!!!</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="Thanh Phu.jpg" class="img-circle" alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Thanh Phu - Be
                                                        <small><i class="fa fa-clock-o"></i> Today</small>
                                                    </h4>
                                                    <p>Fighting!!!</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="Thanh Hai.jpg" class="img-circle" alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Thanh Hai - Be
                                                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                                    </h4>
                                                    <p>Fighting!!!</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <div class="pull-left">
                                                        <img src="Diu Huong.jpg" class="img-circle" alt="User Image">
                                                    </div>
                                                    <h4>
                                                        Diu Huong - Fe
                                                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                                                    </h4>
                                                    <p>Fighting!!!</p>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">See All Messages</a></li>
                                </ul>
                            </li>
                            <!-- Notifications: style can be found in dropdown.less -->
                            <li class="dropdown notifications-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-warning">6</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 6 notifications</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-aqua"></i> 3 new members joined today
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-warning text-yellow"></i> you have received 1 invited
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-red"></i> 2 new members joined
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">View all</a></li>
                                </ul>
                            </li>

                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="admin.png" class="user-image" alt="User Image">
                                    <span class="hidden-xs">Admin</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="admin.png" class="img-circle" alt="User Image">

                                        <p>
                                            Admin
                                            <small>Member since Feb. 2022</small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="#" class="btn btn-default btn-flat">Sign out</a>
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
                            <img src="admin.png" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Admin</p>
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
                                <i class="fa fa-dashboard"></i> <span>Dash Board</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <!-- <li class="treeview">
                                  <a href="#"><i class="fa fa-circle-o"></i> Manage Students
                                    <span class="pull-right-container">
                                      <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                  </a>
                                  <ul class="treeview-menu">
                                    <li class="active"><a href="adminHasGroup.html"><i class="fa fa-circle-o"></i> Group</a></li>
                                    <li><a href="adminNoGroup.html"><i class="fa fa-circle-o"></i> No Group</a></li>
                                  </ul>
                                </li> -->
                                <li class="active"><a href="#"><i class="fa fa-circle-o"></i> Manage Students</a></li>
                                <li><a href="adminGroups.jsp"><i class="fa fa-circle-o"></i> Manage Groups</a></li>
                                <li><a href="adminProject.jsp"><i class="fa fa-circle-o"></i> Manage Projects</a></li>
                                <li><a href="adminLecturers.jsp"><i class="fa fa-circle-o"></i> Manage Lecturers</a></li>/a>
                            </ul>
                        </li>
                    </ul>
                    </li>
                    <!-- <li>
                      <a href="../mailbox/mailbox.html">
                        <i class="fa fa-envelope"></i> <span>Mailbox</span>
                        <span class="pull-right-container">
                          <small class="label pull-right bg-yellow">12</small>
                          <small class="label pull-right bg-green">16</small>
                          <small class="label pull-right bg-red">5</small>
                        </span>
                      </a>
                    </li> -->

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Manage Students
                        <!-- <small>without group</small> -->
                    </h1>
                    <div class="list d-flex align-items-center justify-content-start">
                        <button type="button" class="btn btn-success btn-sm btn-student">Upload Student</button>
                        <button type="button" class="btn btn-warning btn-sm btn-student">Import New Semester</button>
                        <button type="button" class="btn btn-primary btn-sm btn-student">Import Excel</button>
                    </div>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active"><a href="#">Manage Students</a></li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- START CUSTOM TABS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- Custom Tabs -->
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#tab_1" data-toggle="tab">Students <small>have Group</small></a></li>
                                    <li><a href="#tab_2" data-toggle="tab">Students <small>have no Group</small></a></li>
                                    <li><a href="#tab_3" data-toggle="tab">Random Students <small>for no Group</small></a></li>
                                    <!-- <li class="dropdown">
                                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                            Dropdown <span class="caret"></span>
                                            </a>
                                            <ul class="dropdown-menu">
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
                                            <li role="presentation" class="divider"></li>
                                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
                                            </ul>
                                        </li> -->
                                    <!-- <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear">Status</i></a></li> -->
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tab_1">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Students</h3>
                                                        <small>with group</small>
                                                        <!-- <div class="box-tools">
                                                          
                                                          <div class="input-group input-group-sm" style="width: 150px;">
                                                            <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">
                                          
                                                            <div class="input-group-btn">
                                                              <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                                            </div>
                                                          </div>
                                                        </div> -->
                                                    </div>
                                                    <!-- /.box-header -->
                                                    <div class="box-header clearfix">
                                                        <ul class="pagination pagination-sm no-margin pull-left">
                                                            <i>Show</i>
                                                            <select class="select">
                                                                <option><a href="#">10</a></option>
                                                                <option><a href="#">15</a></option>
                                                                <option><a href="#">25</a></option>
                                                                <option><a href="#">30</a></option>
                                                            </select>
                                                            <i>entries</i>
                                                        </ul>
                                                        <ul class="pagination pagination-sm no-margin pull-right">
                                                            <i class="fas fa-filter">Filter</i>
                                                            <select class="select">
                                                                <option><a href="#">Spring 2022</a></option>
                                                                <option><a href="#">Fall 2021</a></option>
                                                                <option><a href="#">Summer 2021</a></option>
                                                                <option><a href="#">Spring 2021</a></option>
                                                            </select>
                                                        </ul>
                                                    </div>
                                                    <!-- /.box-header -->
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th style="width : 10px">#</th>
                                                                <th>Student Id</th>
                                                                <th>Name</th>
                                                                <th>Group Name</th>
                                                                <th style="width : 120px">Semester</th>
                                                            </tr>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>SE151278</td>
                                                                <td>Ngo Chi Bao</td>
                                                                <td>Rose</td>
                                                                <td>Spring 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Spring 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Spring 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Spring 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Summer 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>6</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Summer 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>7</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Summer 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>8</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Summer 2022</td>
                                                            </tr>
                                                            <tr>
                                                                <td>9</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                                <td>Rose</td>
                                                                <td>Fall 2022</td>
                                                            </tr>
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
                                    </div>
                                    <!-- /.tab-pane -->
                                    <div class="tab-pane" id="tab_2">
                                        <p>No students</p>
                                    </div>
                                    <div class="tab-pane" id="tab_3">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Group 1</h3>
                                                        <!-- <small>with group</small> -->
                                                    </div>
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th style="width : 10px">#</th>
                                                                <th>Student Id</th>
                                                                <th>Name</th>
                                                            </tr>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>SE151278</td>
                                                                <td>Ngo Chi Bao</td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                            <div class="col-md-4">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Group 2</h3>
                                                        <!-- <small>with group</small> -->
                                                    </div>
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th style="width : 10px">#</th>
                                                                <th>Student Id</th>
                                                                <th>Name</th>
                                                            </tr>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                            <div class="col-md-4">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Group 3</h3>
                                                        <!-- <small>with group</small> -->
                                                    </div>
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th style="width : 10px">#</th>
                                                                <th>Student Id</th>
                                                                <th>Name</th>
                                                            </tr>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                            <div class="col-md-4">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Group 4</h3>
                                                        <!-- <small>with group</small> -->
                                                    </div>
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th style="width : 10px">#</th>
                                                                <th>Student Id</th>
                                                                <th>Name</th>
                                                            </tr>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                            <div class="col-md-4">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Group 5</h3>
                                                        <!-- <small>with group</small> -->
                                                    </div>
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th style="width : 10px">#</th>
                                                                <th>Student Id</th>
                                                                <th>Name</th>
                                                            </tr>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                            <div class="col-md-4">
                                                <div class="box">
                                                    <div class="box-header">
                                                        <h3 class="box-title">Group 6</h3>
                                                        <!-- <small>with group</small> -->
                                                    </div>
                                                    <div class="box-body table-responsive no-padding">
                                                        <table class="table table-hover">
                                                            <tr>
                                                                <th style="width : 10px">#</th>
                                                                <th>Student Id</th>
                                                                <th>Name</th>
                                                            </tr>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>2</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>3</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>4</td>
                                                                <td>SE15xxxx</td>
                                                                <td>xxxxxxx</td>
                                                            </tr>
                                                            <tr>
                                                                <td>5</td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <!-- /.box-body -->
                                                    <div class="box-footer clearfix">
                                                        <button type="button" class="btn btn-primary btn-sm btn-student pull-right">Save</button> 
                                                    </div>
                                                </div>
                                                <!-- /.box -->
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.tab-pane -->
                                </div>
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

            <!-- Add the sidebar's background. This div must be placed
               immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="../../bower_components/jquery/dist1/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="../../bower_components/bootstrap/dist1/js/bootstrap.min.js"></script>
        <!-- SlimScroll -->
        <script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="../../bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="../../dist1/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="../../dist1/js/demo.js"></script>
        <!-- page script -->
    </body>

</html>