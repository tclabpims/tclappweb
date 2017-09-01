<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/customStyle.css" rel="stylesheet">

<title>app 后台</title>
    <%@include file="../../head.jsp"%>
    <%@include file="../../jquery.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/packageDetails/packageDetails.js"></script>
    <%
        request.setAttribute("nav", "draw");
        request.setAttribute("tab", "set");
        String host = "";
        host = request.getHeader("Host");
        if (host.indexOf(':') > 0) {
            host = host.substring(0, host.indexOf(':'));
        }
    %>
</head>
<body style="background: #f6f1eb none repeat scroll 0 0;">
<%@include file="../top.jsp"%>
<div class="nr">
    <%@include file="../navigation.jsp"%>
    <div class="pagemain">
        <input id="strMenuId" type="hidden" value="8"/>
        <input id="strSubMenuId" type="hidden" value="81"/>

        <%--查询 start--%>
        <%--<div>
            <br/>
            <form action="${pageContext.request.contextPath}/packageDetails/query.do" METHOD="post">

                &nbsp;&nbsp;&nbsp;<label class="label_Style">采集点名称</label>
                &nbsp;&nbsp;&nbsp;<input type="text" class="input_text_style" name="hospitalName" />

                &nbsp;&nbsp;&nbsp;<input type="submit" class="layui-btn layui-btn-radius layui-btn-small" value="查询">
            </form>
        </div>--%>
        <%--查询 end--%>

        <table class="sui-table table-bordered" style="margin-top:20px;">
            <thead>
            <tr>
                <th class="center left-con" width="8%">编号</th>
                <th class="center left-con" width="22%">采集点名称</th>
                <th class="center left-con" width="20%">订单量（个）</th>
                <th class="center left-con" width="20%">完成的套餐量（个）</th>
                <th class="center left-con" width="20%">销售额（元）</th>
            </tr>
            </thead>
            <tbody id="all_task0">
            <c:if test="${fn:length(list)==0}">
                <tr ><td colspan="6" class="center left-con" >亲，暂时没有活动哦！</td></tr>
            </c:if>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td class="center left-con">${item.id}</td>
                    <td class="center left-con">${item.hospitalName}</td>
                    <td class="center left-con">${item.orderNumber}</td>
                    <td class="center left-con">${item.totalPackages}</td>
                    <td class="center left-con">${item.saleSum}</td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
   </div>
</div>

<%--分页 start--%>
<div align="center" class="page_num_style">
    第${pageNo}页&nbsp;&nbsp;
    <c:choose>
        <c:when test="${query_flag == true}">
            <c:choose>
                <c:when test="${pageNo > 1}">
                    <a href="${pageContext.request.contextPath}/packageDetails/query.do?pageNo=${pageNo - 1}&hisName=${hisName}&packageName=${packageName}&name=${name}">上一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">上一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageNo < totalPage}">
                    <a href="${pageContext.request.contextPath}/packageDetails/query.do?pageNo=${pageNo + 1}&hisName=${hisName}&packageName=${packageName}&name=${name}">下一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">下一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${pageNo > 1}">
                    <a href="${pageContext.request.contextPath}/finaManage/hospitalFiStatistic.do?pageNo=${pageNo - 1}">上一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">上一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageNo < totalPage}">
                    <a href="${pageContext.request.contextPath}/finaManage/hospitalFiStatistic.do?pageNo=${pageNo + 1}">下一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">下一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    共${totalPage}页
</div>
<%--分页 end--%>
</body>
</html>