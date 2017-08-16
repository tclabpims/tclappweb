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
        <input id="strMenuId" type="hidden" value="1"/>
        <input id="strSubMenuId" type="hidden" value="1"/>

        <%--查询 start--%>
        <div>
            <br/>
            <form action="${pageContext.request.contextPath}/packageDetails/query.do" METHOD="post">
                &nbsp;&nbsp;&nbsp;<label class="label_Style">his名称</label>
                &nbsp;&nbsp;&nbsp;<input type="text" class="input_text_style" name="hisName" />

                &nbsp;&nbsp;&nbsp;<label class="label_Style">套餐名称</label>
                &nbsp;&nbsp;&nbsp;<input type="text" class="input_text_style" name="packageName" />

                &nbsp;&nbsp;&nbsp;<label class="label_Style">项目名称</label>
                &nbsp;&nbsp;&nbsp;<input type="text" class="input_text_style" name="name" />


                &nbsp;&nbsp;&nbsp;<input type="submit" class="layui-btn layui-btn-radius layui-btn-small" value="查询">
                &nbsp;&nbsp;&nbsp;<button type="button" class="layui-btn layui-btn-radius layui-btn-small" onclick="addPackageDetails()">增加</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="layui-btn layui-btn-small" onclick="exportExcel()">导出Excel</button>
            </form>
        </div>
        <%--查询 end--%>

        <table class="sui-table table-bordered" style="margin-top:20px;">
            <thead>
            <tr>
                <th class="center left-con" width="8%">编号</th>
                <th class="center left-con" width="18%">his名称</th>
                <th class="center left-con" width="10%">his价格到分</th>
                <th class="center left-con" width="21%">套餐名称</th>
                <th class="center left-con" width="21%">项目名称</th>
                <th class="center left-con" width="10%">价格</th>
                <th class="center left-con" width="30%">操作</th>
            </tr>
            </thead>
            <tbody id="all_task0">
            <c:if test="${fn:length(list)==0}">
                <tr ><td colspan="6" class="center left-con" >亲，暂时没有活动哦！</td></tr>
            </c:if>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td class="center left-con">${item.id}</td>
                    <td class="center left-con">${item.hisName}</td>
                    <td class="center left-con">${item.hisPrice}</td>
                    <td class="center left-con">${item.packageModel.name}</td>
                    <td class="center left-con">${item.name}</td>
                    <td class="center left-con">${item.price}</td>
                    <td class="center left-con">
                       <span class=""><a href="javascript:void(0)" onclick="ItemDele('${item.id}');">删除</a></span>&nbsp;&nbsp;
                       <span class=""><a href="javascript:void(0)" onclick="ItemEdit('${item.id}');">编辑</a></span>&nbsp;&nbsp;
                    </td>
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
                    <a href="${pageContext.request.contextPath}/packageDetails/list.do?pageNo=${pageNo - 1}">上一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">上一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageNo < totalPage}">
                    <a href="${pageContext.request.contextPath}/packageDetails/list.do?pageNo=${pageNo + 1}">下一页</a>&nbsp;&nbsp;
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

 <%--<%@include file="mbottom.jsp"%>--%>
<%--增加检验细项 start--%>
<div id="add_packageDetails" style="display: none">
    <br/><form id="add_packageDetails_form" action="" class="layui-form">
    <%--套餐名称--%>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">his项目编号</label>
            <div class="layui-input-inline">
                <input type="text" id="hisId_add" name="hisId" required lay-verify="required" class="layui-input" style="width: 200px">
            </div>
        </div>
        <div class="layui-inline">
            <span id="hisId_check" style="color: red"></span>
        </div>
    </div>
    <%--使用人群---%>
    <div class="layui-form-item">
        <label class="layui-form-label">his项目名称</label>
        <div class="layui-input-block">
            <input type="text" name="hisName" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--价格---%>
    <div class="layui-form-item">
        <label class="layui-form-label">his价格到分</label>
            <div class="layui-input-block">
                <input type="text" name="hisPrice" required lay-verify="required" class="layui-input" style="width: 260px">
            </div>
    </div>
    <%--报告时间说明---%>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">套餐编号</label>
            <div class="layui-input-inline">
                <input type="text" id="packageId_add" name="packageId" required lay-verify="required" class="layui-input" style="width: 200px">
            </div>
        </div>
        <div class="layui-inline">
            <span id="packageId_check" style="color: red"></span>
        </div>
    </div>
    <%--物价编号---%>
    <div class="layui-form-item">
        <label class="layui-form-label">项目名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--使用数量---%>
    <div class="layui-form-item">
        <label class="layui-form-label">项目价格</label>
        <div class="layui-input-block">
            <input type="text" name="price" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
</form>
</div>
<%--增加检验细项 end--%>

<%--编辑检验细项 start--%>
<div id="edit_packageDetails" style="display: none">
    <br/><form id="edit_packageDetails_form" action="" class="layui-form">
    <%--套餐名称--%>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">his项目编号</label>
            <div class="layui-input-inline">
                <input type="hidden" id="edit_id" name="id">
                <input type="text" id="hisId_edit" name="hisId" required lay-verify="required" class="layui-input" style="width: 200px">
            </div>
        </div>
        <div class="layui-inline">
            <span id="hisId_check_edit" style="color: red"></span>
        </div>
    </div>
    <%--使用人群---%>
    <div class="layui-form-item">
        <label class="layui-form-label">his项目名称</label>
        <div class="layui-input-block">
            <input type="text" id="hisName_edit" name="hisName" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--价格---%>
    <div class="layui-form-item">
        <label class="layui-form-label">his价格到分</label>
        <div class="layui-input-block">
            <input type="text" id="hisPrice_edit" name="hisPrice" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--报告时间说明---%>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">套餐编号</label>
            <div class="layui-input-inline">
                <input type="text" id="packageId_edit" name="packageId" required lay-verify="required" class="layui-input" style="width: 200px">
            </div>
        </div>
        <div class="layui-inline">
            <span id="packageId_check_edit" style="color: red"></span>
        </div>
    </div>
    <%--物价编号---%>
    <div class="layui-form-item">
        <label class="layui-form-label">项目名称</label>
        <div class="layui-input-block">
            <input type="text" id="name_edit" name="name" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--使用数量---%>
    <div class="layui-form-item">
        <label class="layui-form-label">项目价格</label>
        <div class="layui-input-block">
            <input type="text" id="price_edit" name="price" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
</form>
</div>
<%--编辑检验细项 end--%>

<%--套餐详情 start--%>
<div id="detail_package" style="display: none">
    <table id="detail_package_table" class="layui-table" lay-even lay-skin="nob">
        <colgroup align="left" span="4">
            <col width="20%">
            <col width="30%">
            <col width="20%">
            <col width="30%">
            <%--<col width="27%">
            <col width="23%">--%>
        </colgroup>
        <tbody>
        <tr>
            <td align="center">套餐名称</td>
            <td align="center"></td>
            <td rowspan="4" colspan="2"><img id="package_picture" alt="照片" title="<%@include file="../../ImgUrl/acquireUrl.jsp"%>"
                                 style="width: 260px;height: 140px"></td>
        </tr>
        <tr>
            <td align="center">价格</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td align="center">套餐状态</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td align="center">报告时间说明</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td align="center">物价编码</td>
            <td align="center"></td>
            <td align="center">已使用数量</td>
            <td align="center"></td>
        </tr>
        <tr>
            <td align="center">使用人群</td>
            <td align="center" colspan="3"></td>
        </tr>
        <tr>
            <td align="center">检验分类</td>
            <td align="center" colspan="3"></td>
        </tr>
        <tr>
            <td align="center">疾病分类</td>
            <td align="center" colspan="3"></td>
        </tr>
        <tr>
            <td align="center">采集分类</td>
            <td align="center" colspan="3"></td>
        </tr>
        <tr>
            <td valign="top" align="center" style="height: 120px">注意事项</td>
            <td colspan="3" style="height: 120px" valign="top"></td>
        </tr>
        <tr>
            <td valign="top" align="center" style="height: 160px">检验项目说明</td>
            <td colspan="3" style="height: 160px" valign="top"></td>
        </tr>
        <tr>
            <td valign="top" align="center" style="height: 160px">相关问题及免责条款</td>
            <td colspan="3" style="height: 160px" valign="top"></td>
        </tr>
        </tbody>
    </table>
</div>
<%--套餐详情 end--%>
</body>
</html>