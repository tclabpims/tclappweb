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
              	<a class="ad adbj1" href="#" id="menu1" data-spm-anchor-id="0.0.0.0">套餐管理</a>
                <ul style="display: none;" id="divmenu1" class="sub-menu">
                  <li><a id="sub1" href="/package/listPage.do?">列表</a></li>
                  <li><a id="sub2" href="/admin/packageAdd.do">创建</a></li>
                </ul>
              </li>
             <li class="active">
                 <a id="menu2" href="#" class="adbj2 ad" data-spm-anchor-id="0.0.0.0">医院管理</a>
                 <ul id="divmenu2" class="sub-menu" style="display: none;">
                     <li><a id="sub21" href="/hospital/list.do?type=">医院列表</a></li>
                 </ul>
             </li>
               <li class="active">
              	<a id="menu3" href="#" class="adbj3 ad" data-spm-anchor-id="0.0.0.0">广告管理</a>
                <ul id="divmenu3" class="sub-menu" style="display: none;">
                  <li><a id="sub31" href="/newspaper/list.do?type=">广告列表</a></li>
                </ul>
              </li>
               <li class="active">
              	<a id="menu4" href="#" class="adbj4 ad" data-spm-anchor-id="0.0.0.0">医生管理</a>
                <ul style="display: none;" id="divmenu4" class="sub-menu">
                  <li><a id="sub41" href="/doctor/list.do?type=">医生列表</a></li>
                  <li><a id="sub42" href="/doctor/add.do">增加医生</a></li>
                </ul>
              </li>
         </ul>
	</div>

</div>