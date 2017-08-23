<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="frg">
            <div class="jyuiq" id="menudiv">
         <ul>
         <li class="active">
              	<a id="menu0" class="ad adbj0">首页</a>
                <ul style="display:none;">
                <li style="display:none;"><a href="#" id="sub51" style="color: rgb(255, 255, 255); background: rgb(78, 152, 229) none repeat scroll 0% 0%;">&nbsp;</a></li>
                </ul>
         </li>
              <li class="active">
              	<a class="ad adbj1" href="#" id="menu1" data-spm-anchor-id="0.0.0.0">基础字典</a>
                <ul style="display: none;" id="divmenu1" class="sub-menu">
                  <li><a id="sub1" href="/packageDetails/list.do">检验细项</a></li>
                  <li><a id="sub2" href="/package/listPage.do">套餐列表</a></li>
                  <%--<li><a id="sub3" href="#">采集手册</a></li>--%>
                </ul>
              </li>
             <li class="active">
                 <a id="menu2" href="#" class="adbj2 ad" data-spm-anchor-id="0.0.0.0">采集点管理</a>
                 <ul id="divmenu2" class="sub-menu" style="display: none;">
                     <li><a id="sub21" href="/hospital/list.do?type=">采集点列表</a></li>
                 </ul>
             </li>
               <li class="active">
              	<a id="menu3" href="#" class="adbj3 ad" data-spm-anchor-id="0.0.0.0">广告管理</a>
                <ul id="divmenu3" class="sub-menu" style="display: none;">
                  <li><a id="sub31" href="/newspaper/list.do?type=">广告列表</a></li>
                </ul>
             </li>
             <li class="active">
              	<a id="menu5" href="#" class="adbj5 ad" data-spm-anchor-id="0.0.0.0">医生管理</a>
                <ul id="divmenu5" class="sub-menu" style="display: none;">
                  <li><a id="sub511" href="/doctor/list.do?type=">医生列表</a></li>
                  <%--<li><a id="sub42" href="/doctor/add.do">增加医生</a></li>--%>
                </ul>
              </li>
             <li class="active">
                <a id="menu6" href="#" class="adbj6 ad" data-spm-anchor-id="0.0.0.0">用户管理</a>
                  <ul id="divmenu6" class="sub-menu" style="display: none;">
                    <li><a id="sub61" href="/user/list.do">用户列表</a></li>
                  </ul>
             </li>
             <li class="active">
                 <a id="menu7" href="#" class="adbj6 ad" data-spm-anchor-id="0.0.0.0">订单管理</a>
                 <ul id="divmenu7" class="sub-menu" style="display: none;">
                     <li><a id="sub71" href="/cart/list.do">购物车列表</a></li>
                     <li><a id="sub72" href="/trade/list.do">订单列表</a></li>
                     <li><a id="sub73" href="/order/list.do">订单子项列表</a></li>
                 </ul>
             </li>
         </ul>
	</div>

</div>