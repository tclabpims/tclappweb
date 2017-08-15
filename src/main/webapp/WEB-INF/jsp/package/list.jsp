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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/package/package.js"></script>
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
            <form action="${pageContext.request.contextPath}/package/query.do" METHOD="post">
                &nbsp;&nbsp;&nbsp;<label class="label_Style">套餐名称</label>
                &nbsp;&nbsp;&nbsp;<input type="text" class="input_text_style" name="name" />

                &nbsp;&nbsp;&nbsp;<label class="label_Style">状态</label>&nbsp;&nbsp;&nbsp;
                <select class="select_style" name="status" >
                    <option value=""></option>
                    <option value="0">未发布</option>
                    <option value="1">已发布</option>
                    <option value="2">已下线</option>
                </select>

                &nbsp;&nbsp;&nbsp;<label class="label_Style">物价编码</label>
                &nbsp;&nbsp;&nbsp;<input type="text" class="input_text_style" name="wjCode" />

                &nbsp;&nbsp;&nbsp;<label class="label_Style">价格</label>&nbsp;&nbsp;&nbsp;
                <input type="text" class="input_text_style" placeholder="最低价格" name="min_price" />&nbsp;——&nbsp;
                <input type="text" class="input_text_style" placeholder="最高价格" name="max_price" />

                <br/><br/>
                &nbsp;&nbsp;&nbsp;<label class="label_Style">销售次数</label>&nbsp;&nbsp;&nbsp;
                <input type="text" class="input_text_style" placeholder="最少次数" name="min_saleNum" />&nbsp;——&nbsp;
                <input type="text" class="input_text_style" placeholder="最多次数" name="max_saleNum" />

                &nbsp;&nbsp;&nbsp;<input type="submit" class="layui-btn layui-btn-radius layui-btn-small" value="查询">
                &nbsp;&nbsp;&nbsp;<button type="button" class="layui-btn layui-btn-radius layui-btn-small" onclick="addPackage()">增加</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="layui-btn layui-btn-small" onclick="exportExcel()">导出Excel</button>
            </form>
        </div>
        <%--查询 end--%>

        <table class="sui-table table-bordered" style="margin-top:20px;">
            <thead>
            <tr>
                <th class="center left-con" width="8%">编号</th>
                <th class="center left-con" width="18%">套餐名称</th>
                <th class="center left-con" width="10%">图片</th>
                <th class="center left-con" width="32%">使用人群</th>
                <th class="center left-con" width="10%">价格</th>
                <th class="center left-con" width="10%">注意事项</th>
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
                    <td class="center left-con">${item.name}</td>
                    <td class="center left-con"><img src="<%@include file="../../ImgUrl/acquireUrl.jsp"%>${item.picUrl}" height="50px" width="50px"/></td>
                    <td class="center left-con">${item.useCrowd}</td>
                    <td class="center left-con">${item.price}</td>
                    <td class="center left-con">${item.needAttention}</td>
                    <td class="center left-con">
                       <span class=""><a href="javascript:void(0)" onclick="ItemDele('${item.id}');">删除</a></span>&nbsp;&nbsp;
                       <span class=""><a href="javascript:void(0)" onclick="ItemEdit('${item.id}');">编辑</a></span>&nbsp;&nbsp;
                       <span class=""><a href="javascript:void(0)" onclick="ItemDetail('${item.id}');">详情</a></span>&nbsp;&nbsp;
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
                    <a href="${pageContext.request.contextPath}/package/query.do?pageNo=${pageNo - 1}&name=${name}&status=${status}&wjCode=${wjCode}&min_price=${min_price}&max_price=${max_price}&min_saleNum=${min_saleNum}&max_saleNum=${max_saleNum}">上一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">上一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageNo < totalPage}">
                    <a href="${pageContext.request.contextPath}/package/query.do?pageNo=${pageNo + 1}&name=${name}&status=${status}&wjCode=${wjCode}&min_price=${min_price}&max_price=${max_price}&min_saleNum=${min_saleNum}&max_saleNum=${max_saleNum}">下一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">下一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${pageNo > 1}">
                    <a href="${pageContext.request.contextPath}/package/listPage.do?pageNo=${pageNo - 1}">上一页</a>&nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="#">上一页</a>&nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${pageNo < totalPage}">
                    <a href="${pageContext.request.contextPath}/package/listPage.do?pageNo=${pageNo + 1}">下一页</a>&nbsp;&nbsp;
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
<%--增加套餐 start--%>
<div id="add_package" style="display: none">
    <br/><form id="add_package_form" action="" class="layui-form">
    <%--套餐名称--%>
    <div class="layui-form-item">
        <label class="layui-form-label">套餐名称</label>
        <div class="layui-input-block">
            <input type="text" id="name_add" name="name" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--使用人群---%>
    <div class="layui-form-item">
        <label class="layui-form-label">使用人群</label>
        <div class="layui-input-block">
            <input type="text" name="useCrowd" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--价格---%>
    <div class="layui-form-item">
        <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <input type="text" name="price" required lay-verify="required" class="layui-input" style="width: 260px">
            </div>
    </div>
    <%--报告时间说明---%>
    <div class="layui-form-item">
        <label class="layui-form-label">报告时间</label>
        <div class="layui-input-block">
            <input type="text" name="reportTime" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--物价编号---%>
    <div class="layui-form-item">
        <label class="layui-form-label">物价编号</label>
        <div class="layui-input-block">
            <input type="text" name="wjCode" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--使用数量---%>
    <div class="layui-form-item">
        <label class="layui-form-label">使用数量</label>
        <div class="layui-input-block">
            <input type="text" name="saleNum" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--套餐图片--%>
    <div class="layui-form-item">
        <label class="layui-form-label">套餐图片</label>
        <div class="layui-input-block">
            <input type="file" id="picUrl_add" style="width: 210px" title="<%@include file="../../ImgUrl/uploadUrl.jsp"%>">
            <input type="hidden" id="picUrl_add_" name="picUrl" />
            <input type="button"  class="layui-btn layui-btn-radius layui-btn-small" value="上传" onclick="uploadPic()">
        </div>
    </div>

    <%--套餐状态--%>
    <div class="layui-form-item">
        <label class="layui-form-label">套餐状态</label>
        <div class="layui-input-block" style="width: 260px">
            <select name="status" lay-verify="">
                <option value=""></option>
                <option value="0">未发布</option>
                <option value="1">已发布</option>
                <option value="2">已下线</option>
            </select>
        </div>
    </div>

    <%--检验分类---%>
    <div class="layui-form-item">
        <label class="layui-form-label">检验分类</label>
        <div class="layui-input-block">
            <input type="text" name="testType" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>

    <%--疾病分类---%>
    <div class="layui-form-item">
        <label class="layui-form-label">疾病分类</label>
        <div class="layui-input-block">
            <input type="text" name="diseaseType" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>

    <%--采集分类---%>
    <div class="layui-form-item">
        <label class="layui-form-label">采集分类</label>
        <div class="layui-input-block">
            <input type="text" name="takeType" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>

    <%--注意事项--%>
    <div class="layui-form-item">
        <label class="layui-form-label">注意事项</label>
        <div class="layui-input-block">
            <textarea name="needAttention" required lay-verify="required" class="layui-textarea" style="width: 320px"></textarea>
        </div>
    </div>
    <%--检验项目说明--%>
    <div class="layui-form-item">
        <label class="layui-form-label">项目说明</label>
        <div class="layui-input-block">
            <textarea name="projectDesc" required lay-verify="required" class="layui-textarea" style="width: 320px"></textarea>
        </div>
    </div>
    <%--相关问题及免责条款--%>
    <div class="layui-form-item">
        <label class="layui-form-label">相关问题及免责条款</label>
        <div class="layui-input-block">
            <textarea name="clause" required lay-verify="required" class="layui-textarea" style="width: 320px"></textarea>
        </div>
    </div>
</form>
</div>
<%--增加套餐 end--%>

<%--套餐关联信息 start--%>
<div id="package_detail_add" style="display: none">
    <br/><form id="package_detail_add_form" action="" class="layui-form">
    <%--his项目名称--%>
    <div class="layui-form-item">
        <label class="layui-form-label">his项目名称</label>
        <div class="layui-input-block" style="width: 260px">
            <select name="status" lay-verify="">
                <option value=""></option>
            </select>
        </div>
    </div>
    <%--his价格到分--%>
    <div class="layui-form-item">
        <label class="layui-form-label">his价格到分</label>
        <div class="layui-input-block">
            <input type="text" name="hisPrice" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    </form>
</div>
<%--套餐关联信息 end--%>

<%--编辑套餐 start--%>
<div id="edit_package" style="display: none">
    <br/><form id="edit_package_form" action="" class="layui-form">
    <%--套餐名称--%>
    <div class="layui-form-item">
        <label class="layui-form-label">套餐名称</label>
        <div class="layui-input-block">
            <input type="hidden" id="edit_id" name="id">
            <input type="text" id="edit_name" name="name" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--使用人群---%>
    <div class="layui-form-item">
        <label class="layui-form-label">使用人群</label>
        <div class="layui-input-block">
            <input type="text" id="edit_useCrowd" name="useCrowd" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--价格---%>
    <div class="layui-form-item">
        <label class="layui-form-label">价格</label>
        <div class="layui-input-block">
            <input type="text" id="edit_price" name="price" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--报告时间说明---%>
    <div class="layui-form-item">
        <label class="layui-form-label">报告时间</label>
        <div class="layui-input-block">
            <input type="text" id="edit_reportTime" name="reportTime" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--物价编号---%>
    <div class="layui-form-item">
        <label class="layui-form-label">物价编号</label>
        <div class="layui-input-block">
            <input type="text" id="edit_wjCode" name="wjCode" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--使用数量---%>
    <div class="layui-form-item">
        <label class="layui-form-label">使用数量</label>
        <div class="layui-input-block">
            <input type="text" id="edit_saleNum" name="saleNum" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>
    <%--套餐图片--%>
    <div class="layui-form-item">
        <label class="layui-form-label">套餐图片</label>
        <div class="layui-input-block">
            <input type="file" id="picUrl_edit" style="width: 210px" title="<%@include file="../../ImgUrl/uploadUrl.jsp"%>">
            <input type="hidden" id="picUrl_edit_" name="picUrl" />
            <input type="button"  class="layui-btn layui-btn-radius layui-btn-small" value="上传" onclick="uploadPicEdit()">
        </div>
    </div>

    <%--套餐状态--%>
    <div class="layui-form-item">
        <label class="layui-form-label">套餐状态</label>
        <div class="layui-input-block" style="width: 260px">
            <select id="edit_status" name="status" lay-verify="">
                <option value=""></option>
                <option value="0">未发布</option>
                <option value="1">已发布</option>
                <option value="2">已下线</option>
            </select>
        </div>
    </div>

    <%--检验分类---%>
    <div class="layui-form-item">
        <label class="layui-form-label">检验分类</label>
        <div class="layui-input-block">
            <input type="text" id="edit_testType" name="testType" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>

    <%--疾病分类---%>
    <div class="layui-form-item">
        <label class="layui-form-label">疾病分类</label>
        <div class="layui-input-block">
            <input type="text" id="edit_diseaseType" name="diseaseType" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>

    <%--采集分类---%>
    <div class="layui-form-item">
        <label class="layui-form-label">采集分类</label>
        <div class="layui-input-block">
            <input type="text" id="edit_takeType" name="takeType" required lay-verify="required" class="layui-input" style="width: 260px">
        </div>
    </div>

    <%--注意事项--%>
    <div class="layui-form-item">
        <label class="layui-form-label">注意事项</label>
        <div class="layui-input-block">
            <textarea id="edit_needAttention" name="needAttention" required lay-verify="required" class="layui-textarea" style="width: 320px"></textarea>
        </div>
    </div>
    <%--检验项目说明--%>
    <div class="layui-form-item">
        <label class="layui-form-label">项目说明</label>
        <div class="layui-input-block">
            <textarea id="edit_projectDesc" name="projectDesc" required lay-verify="required" class="layui-textarea" style="width: 320px"></textarea>
        </div>
    </div>
    <%--相关问题及免责条款--%>
    <div class="layui-form-item">
        <label class="layui-form-label">相关问题及免责条款</label>
        <div class="layui-input-block">
            <textarea id="edit_clause" name="clause" required lay-verify="required" class="layui-textarea" style="width: 320px"></textarea>
        </div>
    </div>
</form>
</div>
<%--编辑套餐 end--%>

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