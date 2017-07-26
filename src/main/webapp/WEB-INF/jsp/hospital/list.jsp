<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>app 后台</title>
    <%@include file="../../head.jsp"%>
    <%@include file="../../jquery.jsp"%>
    <link href="${pageContext.request.contextPath}/css/customStyle.css" rel="stylesheet">
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
        <input id="strMenuId" type="hidden" value="2"/>
        <input id="strSubMenuId" type="hidden" value="21"/>
        <div>
            <br/>
            <form action="" method="post">
                &nbsp;&nbsp;&nbsp;<label class="label_Style">医院名称:</label>
                &nbsp;&nbsp;&nbsp;<input class="input_text_style" name="name"/>

                &nbsp;&nbsp;&nbsp;<label class="label_Style">电话:</label>
                &nbsp;&nbsp;&nbsp;<input class="input_text_style" name="telphone"/>

                &nbsp;&nbsp;&nbsp;
                <input type="submit" class="layui-btn layui-btn-radius layui-btn-small" value="查询" />

                &nbsp;&nbsp;&nbsp;
                <button type="button" class="layui-btn layui-btn-radius layui-btn-small" onclick="addHospital()">新增</button>
            </form>
        </div>
        <table class="sui-table table-bordered" style="margin-top:20px;">
            <thead>
            <tr>
                <th class="center left-con" width="8%">编号</th>
                <th class="center left-con" width="18%">医院名称</th>
                <th class="center left-con" width="32%">主图</th>
                <th class="center left-con" width="10%">地址</th>
                <th class="center left-con" width="10%">电话</th>
                <th class="center left-con" width="30%">操作</th>
            </tr>
            </thead>
            <tbody id="all_task0">
            <c:if test="${fn:length(list)==0 }">
                <tr ><td colspan="6" class="center left-con" >亲，暂时没有活动哦！</td></tr>
            </c:if>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td class="center left-con">${item.id}</td>
                    <td class="center left-con">${item.name}</td>
                    <td class="center left-con"><img src="<%@include file="../../constants.jsp"%>${item.picUrl}" height="50px" width="50px"/></td>
                    <td class="center left-con">${item.address}</td>
                    <td class="center left-con">${item.telphone}</td>
                    <td class="center left-con">
                       <span class=""><a href="javascript:void(0)" onclick="ItemDele('${item.id}');">删除</a></span>
                        <span class=""><a href="javascript:void(0)" onclick="ItemEdit('${item.id}');">编辑</a></span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
    <div align="center" style="font-size: small;position: fixed;left: 400px;top: 600px;right: 200px">
        第几页&nbsp;&nbsp;
        上一页&nbsp;&nbsp;
        下一页&nbsp;&nbsp;
        共几页

    </div>
   </div>
</div>
 <%--<%@include file="mbottom.jsp"%>--%>
</body>
</html>