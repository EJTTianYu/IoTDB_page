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
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>

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
        <div class="col-xs-9 " style="padding:0px">
            <table class="table table-hover" id="showTime" data-pagination="true" data-search="true">
            </table>
        </div>
    </div>
</div>
<script>

        $('#showTime').bootstrapTable('destroy').bootstrapTable({
            pageSize: 10,
            pageList: [4, 5, 6],

            cache: false,
            method: "get",
            columns: [{
                field: "Timeseries",
                title: '时间序列'
            }, {
                field: 'Storage Group',
                title: '存储组'
            }, {
                field: 'DataType',
                title: '数据类型'
            }, {
                field: 'Encoding',
                title: '编码方式'
            }
            ],
            data: <%=request.getAttribute("json")%>


        })





</script>

<div class="footer navbar-fixed-bottom" >
    <div class="container">
        <p>ALL RIGHTS RESERVED.</p>
    </div>
</div>


</body>
</html>