<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>IoTDB</title>
    <link type="text/css" rel="stylesheet" href="css/simple-sidebar.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.css" rel="stylesheet">
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
                    <a href="indexData.jsp">总览</a>
                </li>
                <li>
                    <a href="dataOperation.jsp">数据管理</a>
                </li>

            </ul>
        </div>
        <div class="col-xs-1" style="padding:0px;height: 100%;"></div>
        <div class="col-xs-9" style="padding:0px">
            <div class="panel row" style="margin-top: 20px;box-shadow: 3px 3px 3px rgba(0,0,0,.05);margin-left: 0px;margin-right: 0px">
                <div class="col-xs-8"  style="padding:0px">
                    <h1>存储组</h1>
                    <p><strong><span style="color: #fcac45;font-weight: 600;font-size:20px">${groupNum}</span></strong> 个</p>
                </div>
                <div class="col-xs-4" style="margin-top: 30px;padding:0px">
                    <form action="Login" method="post">
                        <p>数据源地址<input name="IP"></p>
                        <p>数据源接口<input name="port"><button class="btn" type="submit">连接</button></p>
                    </form>
                </div>
            </div>
            <div class="row" style="margin-left: 0px;margin-right: 0px;box-shadow: 3px 3px 3px rgba(0,0,0,.05)">

                <div class="panel-heading container" style="padding:20px 0px">
                    <h3 class="panel-title">存储组当前状态</h3>

                </div>


                <div class="col-xs-12 panel " style="padding: 0px;box-shadow:none;height: 400px;overflow: auto" >

                    <table class="table table-hover" data-toggle="table" id="mytab" data-pagination="true" >

                        <thead>
                        <tr>
                            <th>GROUP_Name</th>
                            <th>TimeSe_Num</th>
                            <th>TimeSe_Def</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%
                                int a=(int)session.getAttribute("groupNum");
                                int totalPage;
                                if(a%10==0){
                                    totalPage=a/10;
                                }
                                else {
                                    totalPage=a/10+1;
                                }
                                for(int i=0;i<10;i++){
                                    if(session.getAttribute("groupName"+i)!=null){
                            %>
                            <tr class="IoTr"><td id="GName2"><%=session.getAttribute("groupName"+i)%></td><td><%=session.getAttribute("groupNo"+i)%></td><td><a href="chaKan?action=<%=session.getAttribute("groupName"+i)%>"><button class="btn" >查看</button></a></td></tr>
                            <%}}%>



                        </tbody>
                    </table>

                    <ul class="pagination pagination-sm" style="margin-top:20px">
                        <%
                            for(int j=1;j<=totalPage;j++){
                        %>
                        <li><a href="getPage?action=<%=j%>"  id="page<%=j%>"><%=j%></a></li>
                        <%
                            }
                        %>

                    </ul>

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