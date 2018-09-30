<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>IoTDB</title>
    <link type="text/css" rel="stylesheet" href="css/simple-sidebar.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">


    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js" type=”text/javascript></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" type=”text/javascript></script>
    <script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<div class="header">
    <nav class="navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <abbr title="We are a database for time series data management developed by Tsinghua University"> <a class="navbar-brand" href="#"><strong><span style="color: #fcac45;font-weight: 600;">IoT</span></strong>DB</a></abbr>
            </div>

            <div>
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="http://tsfile.org/index">Home</a></li>
                    <li><a href="#">Contact</a></li>
                    <li><a href="#" class="dropdown-toggle" data-toggle="dropdown"></a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="main container" style="height: 100%;width: 100%;padding:0px;">
    <!-- Sidebar -->
    <div class="row" style="margin-left: 0px">
        <div class="col-xs-1" style="padding:0px;height: 100%;">
            <ul class="sidebar-nav col-3">
                <li>
                    <a href="index.jsp">总览</a>
                </li>
                <li>
                    <a href="index2.jsp">数据管理</a>
                </li>
                <li>
                    <a href="#">数据实时接入</a>
                </li>
            </ul>
        </div>
        <a href="refresh2"><button class="btn">refresh</button> </a>
        <div class="col-xs-1" style="padding:0px;height: 100%;"></div>
        <div class="col-xs-9" style="padding:0px">
            <div class="panel" style="margin-top: 20px;box-shadow: 3px 3px 3px rgba(0,0,0,.05)">
                <h1>存储组 <strong><span style="color: #fcac45;font-weight: 600;font-size:35px">${info}</span></strong> 个</h1>
            </div>
            <div class="row" style="margin-left: 0px;margin-right: 0px;box-shadow: 3px 3px 3px rgba(0,0,0,.05)">

                <div class="col-xs-12 panel " style="padding: 0px;box-shadow:none;height: 420px" >

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>存储组名</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            int a=(int)request.getAttribute("info");
                            int totalPage;
                            if(a%7==0){
                                totalPage=a/7;
                            }
                            else {
                                totalPage=a/7+1;
                            }
                            for(int i=0;i<7;i++){
                        %>
                        <tr class="IoTr"><td id="GName2"><%=request.getAttribute("groupName"+i)%></td>
                            <td><p style="text-align: right">
                                <a href="#">查看 </a>
                                <a href="querySQL.jsp">查询 </a>
                                </p> </td></tr>
                        <%}%>
                        </tbody>

                    </table>
                    <ul class="pagination pagination-sm" style="margin-top:20px">
                            <%
                            for(int j=1;j<=totalPage;j++){
                        %>
                        <li><a href="getGroup?action=<%=j%>"  id="page<%=j%>"><%=j%></a></li>
                            <%
                            }
                        %>
                </div>
            </div>
        </div>


    </div>

</div>
</div>

<div class="footer navbar-fixed-bottom" >
    <div class="container">
        <p>ALL RIGHTS RESERVED.</p>
    </div>
</div>

</body>
</html>