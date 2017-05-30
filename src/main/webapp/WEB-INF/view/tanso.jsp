<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="template/head.jsp"/>

<body>

<!-- Navigation -->
<jsp:include page="template/navbar.jsp"/>

<div class="container">
    <!-- Blog Entries Column -->
    <div class="row">
        <div class="col-md-12">
            <ul class="list-group menu-admin">
                <li class="list-inline btn btn-danger mgl-10 mgr-10  mgb-15" ><a href="/manager-post"><img src="<s:url value="public/asserts/images/post1.png" />" class="mgr-10">${messageSource.getMessage("managerPost",null,locale)}</a></li>
                <li class="list-inline btn btn-success mgr-10  mgb-15"><a href="/configuration"><span class="glyphicon glyphicon-cog mgr-5"></span>${messageSource.getMessage("configSystem",null,locale)}</a></li>
                <li class="list-inline btn btn-warning mgl-10  mgb-15"> <a href="/manager-user"><i class="glyphicon glyphicon-user mgr-10"></i>${messageSource.getMessage("managerUser",null,locale)}</a></li>
            </ul>
         </div>
    <!-- /.row -->
        <div id="chart1"  class="mgb-30"></div>

    <hr>
    <jsp:include page="template/footer.jsp"/>
    </div>
</div>

<script src="<s:url value="/public/asserts/js/main.js"/>"></script>
<script src="<s:url value="/public/asserts/js/search.js"/>"></script>

<script src="<s:url value="public/hightchar/highcharts.js"/>"></script>
<script src="<s:url value="public/hightchar/exporting.js"/>"></script>

<script>



//        $(function () {
//            Highcharts.chart('chart1', {
//
//                title: {
//                    text: 'Solar Employment Growth by Sector, 2010-2016'
//                },
//
//                subtitle: {
//                    text: 'Source: thesolarfoundation.com'
//                },
//                xAxis: {
//                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',"July","Aug"]
//                 },
//                yAxis: {
//                    title: {
//                        text:"Number"
//                    }
//                },
//                legend: {
//                    layout: 'vertical',
//                    align: 'right',
//                    verticalAlign: 'middle'
//                },
//
//                plotOptions: {
//                    series: {
//                        pointStart:  0
//                    }
//                },
//
//                series: [{
//                    name: 'Value',
//                    data: [5, 10, 15, 20, 25, 30, 35, 40]
//                }]
//
//            });
//        });

        jQuery(function ($) {
            var data1={};
            data1['msg']="getStatisticPost";
//            alert($("#chart1"));
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/getStatisticPost",
                data : JSON.stringify(data1),
                dataType : 'json',
                timeout : 1000,
                success : function(data) {
                    Highcharts.chart('chart1', {

                        title: {
                            text: 'Solar Employment Growth by Sector, 2010-2016'
                        },

                        subtitle: {
                            text: 'Source: thesolarfoundation.com'
                        },
                        xAxis: {
                            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',"July","Aug"]
                        },
                        yAxis: {
                            title: {
                                text:"Number"
                            }
                        },
                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'middle'
                        },

                        plotOptions: {
                            series: {
                                pointStart:  0
                            }
                        },

                        series: [{
                            name: 'Value',
                            data:data
                        }]

                    });
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                },
                done : function(e) {
                    console.log("DONE");
                }
            });
        });

</script>
</body>
</html>
