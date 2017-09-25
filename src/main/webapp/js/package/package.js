/**
 * Created by LiuQi on 2017/8/7.
 */
/*通过ID删除某个套餐*/
function ItemDele(id) {
    layui.use('layer', function() {
        var layer = layui.layer;
        layer.confirm('您确定要删除吗？',{
            title: '提示',
            icon: 3,
            offset: '100px',
            area: ['240px', '160px']
        }, function(index) {
            $.ajax({
                url: "delete.do",
                type: "POST",
                data: {
                    id: id
                },
                dataType: "json",
                success: function(data) {
                    if(data.msg == "1") {
                        layer.msg('删除成功',{
                            time: 1000,
                            offset: '160px'
                        });
                        setTimeout(function() {
                            location.reload();
                        }, 1000);
                    }
                    else if(data.msg == "2") {
                        layer.msg('删除失败',{
                            offset: '160px',
                            time: 1000
                        });
                    } else if(data.msg == "3") {
                        layer.msg('参数有误',{
                            offset: '160px',
                            time: 1000
                        });
                    }

                },
                error: function(er) {
                    console.log(er);
                }
            });
            layer.close(index);
        })
    })
}
/*新增套餐*/
function addPackage(id) {
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        layer.open({
            type: 1,
            title: "增加套餐",
            skin: 'layui-layer-rim', //加上边框
            area: ["480px", "720px"],
            content: $("#add_package"),
            btn: ['增加', '取消'],
            yes: function (index, layero) {
                $("#picUrl_add_").val(packageImg_path);
                if(packageDetailImg_path != null && packageDetailImg_path != '' && packageDetailImg_path != undefined) {
                    $("#detailImg_add_").val("http://183.247.179.221:9099" + packageDetailImg_path);
                }
                $.ajax({
                    url: "add.do",
                    type: "POST",
                    data: $("#add_package_form").serializeArray(),
                    dataType: "json",
                    success: function (data) {
                        if (data.msg == "success") {
                            layer.msg("新增成功", {
                                time: 1000
                            });
                            setTimeout(function () {
                                location.reload();
                            }, 1000);
                        } else if (data.msg == "error") {
                            layer.msg("新增失败", {
                                time: 100
                            });
                            setTimeout(function () {
                                location.reload();
                            }, 1000);
                        }
                    },
                    error: function (er) {
                        console.log(er);
                    }
                });
            },
            btn2: function (index, layero) {
                layer.close(index);
            }
        });
        form.render();
    });
}
/*上传套餐主图*/
var packageImg_path;
function uploadPic() {
    var formData = new FormData();
    formData.append('file',$("#picUrl_add")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#picUrl_add")[0].title,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data:formData,
        dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success:function(data){
            layui.use('layer', function() {
                var layer = layui.layer;
                if(data.txt != null && data.txt != "" && data.txt != undefined) {
                    packageImg_path = data.txt;
                    layer.msg("上传成功", {
                        time: 1000,
                        offset: "160px"
                    })
                }else {
                    layer.msg("上传失败", {
                        time: 1000,
                        offset: "160px"
                    })
                }
            });
        },
        error:function(er){
            console.log(er);
        }
    });
}
var packageDetailImg_path;
function uploadDetailImg() {
    var formData = new FormData();
    formData.append('file',$("#detailImg_add")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#detailImg_add")[0].title,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data:formData,
        dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success:function(data){
            layui.use('layer', function() {
                var layer = layui.layer;
                if(data.txt != null && data.txt != "" && data.txt != undefined) {
                    packageDetailImg_path = data.txt;
                    layer.msg("上传成功", {
                        time: 1000,
                        offset: "160px"
                    })
                }else {
                    layer.msg("上传失败", {
                        time: 1000,
                        offset: "160px"
                    })
                }
            });
        },
        error:function(er){
            console.log(er);
        }
    });
}
/*$("document").ready(function(){
    $("#name_add").click(function () {
        layui.use(['layer', 'form'], function() {
            var layer = layui.layer;
            var form = layui.form();
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '240px'], //宽高
                content: $("#package_detail_add"),
                btn: ['确认', '取消'],
                yes: function(index) {

                }
            })
            form.render();
        })
    })
})*/

function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

/*导出Excel表格*/
function exportExcel() {
    $(document).ready(function() {
        var temp_form = document.createElement("form");
        temp_form.action = getRootPath() + "/exportexcel.do";
        temp_form.method = "post";
        temp_form.style.display = "none";
        document.body.appendChild(temp_form);
        temp_form.submit();
    })
}
/*编辑套餐*/
function ItemEdit(id) {
    var package;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            package = data.package;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use(['layer', 'form', 'element'], function() {
        var layer = layui.layer;
        var form = layui.form();
        var element = layui.element();
        if(package.name == "paramIsError") {
            layer.msg('获取信息失败', {
                time: 1000,
                offset: '160px',
            });
            return;
        }
        layer.tab({
            id: "edit_tab",
            area: ['480px', '640px'],
            tab:[{
                    title: '套餐编辑',
                    content: $("#edit_package").html(),
                },{
                    title: '知识库编辑',
                    content: $("#edit_packageKnowledge").html(),
                },{
                    title: '采集手册编辑',
                    content: $("#edit_collect_manual").html(),
                }
            ],
            btn: ['提交', '取消'],
            yes: function(index, layero) {
                if(tab_flag === 1) {
                    $("#picUrl_edit_").val(packageImg_edit_path);
                    if (packageDetailImg_edit_path != null && packageDetailImg_edit_path != '' && packageDetailImg_edit_path != undefined) {
                        $("#detailImg_edit_").val("http://183.247.179.221:9099" + packageDetailImg_edit_path);
                    }
                    $.ajax({
                        url: "update.do",
                        type: "POST",
                        data: $("#edit_package_form").serializeArray(),
                        dataType: "json",
                        success: function (data) {
                            if (data.msg == "success") {
                                layer.msg("更新成功", {
                                    time: 1000,
                                    offset: '160px'
                                });
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            } else if (data.msg == "error") {
                                layer.msg("更新失败", {
                                    time: 1000,
                                    offset: '160px'
                                });
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            }
                        },
                        error: function (er) {
                            console.log(er);
                        }
                    });
                } else if(tab_flag === 2) {
                    $.ajax({
                        url: "/knowledge/update.do",
                        type: "POST",
                        data: $("#edit_package_knowledge_from").serializeArray(),
                        dataType: "json",
                        success: function (data) {
                            if (data.msg == "success") {
                                layer.msg("更新成功", {
                                    time: 1000,
                                    offset: '160px'
                                });
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            } else if (data.msg == "error") {
                                layer.msg("更新失败", {
                                    time: 1000,
                                    offset: '160px'
                                });
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            }
                        },
                        error: function (er) {
                            console.log(er);
                        }
                    });
                } else if(tab_flag ===3) {
                    if (collectImg_edit_path != null && collectImg_edit_path != '' && collectImg_edit_path != undefined) {
                        $("#collectImg_edit_").val("http://183.247.179.221:9099" + collectImg_edit_path);
                    }
                    $.ajax({
                        url: "/collectManual/update.do",
                        type: "POST",
                        data: $("#edit_package_collect_manual_from").serializeArray(),
                        dataType: "json",
                        success: function (data) {
                            if (data.msg == "success") {
                                layer.msg("更新成功", {
                                    time: 1000,
                                    offset: '160px'
                                });
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            } else if (data.msg == "error") {
                                layer.msg("更新失败", {
                                    time: 1000,
                                    offset: '160px'
                                });
                                setTimeout(function () {
                                    location.reload();
                                }, 1000);
                            }
                        },
                        error: function (er) {
                            console.log(er);
                        }
                    });
                }
            },
            btn2: function(index, layero) {
                layer.close(index);
            }
        });
        $("#edit_name").attr('value', package.name);
        $("#edit_useCrowd").attr('value', package.useCrowd);
        if(package.price != null) {
            $("#edit_price").attr('value', (parseFloat(package.price) / 100).toFixed(2));
        } else {
            $("#edit_price").attr('value', "");
        }
        $("#edit_reportTime").attr('value', package.reportTime);
        $("#edit_reportTimeDesc").attr('value', package.reportTimeDesc);
        $("#edit_wjCode").attr('value', package.wjCode);
        $("#edit_saleNum").attr('value', package.saleNum);
        $("#edit_status option[value='"+package.status+"']").attr("selected", 'selected');
        $("#edit_sampleType").attr('value', package.sampleType);
        $("#edit_testType").attr('value', package.testType);
        $("#edit_diseaseType").attr('value', package.diseaseType);
        $("#edit_takeType").attr('value', package.takeType);
        $("#edit_needAttention").text(package.needAttention);
        $("#edit_projectDesc").text(package.projectDesc);
        $("#edit_clause").text(package.clause);
        $("#edit_id").attr('value', id);
        //知识库
        $("#packageNameOfKnowledge").attr('value', package.name);
        $("#edit_knowledge_package_id").attr('value', id);
        if(package.knowledgeModel != null) {
            $("#edit_knowledge_id").attr('value', package.knowledgeModel.id);
            $("#edit_knowledge_introduction").attr('value', package.knowledgeModel.introduction);
            $("#edit_knowledge_objective").attr('value', package.knowledgeModel.objective);
            $("#edit_knowledge_text_time").attr('value', package.knowledgeModel.textTime);
            $("#edit_knowledge_need_attention").attr('value', package.knowledgeModel.needAttention);
        }
        $("#packageNameOfCollectManual").attr('value', package.name);
        $("#edit_collect_manual_package_id").attr('value', id);
        if(package.collectManualModel != null) {
            $("#edit_collect_manual_id").attr('value', package.collectManualModel.id)
            $("#edit_collect_manual_text_method").attr('value', package.collectManualModel.textMethod)
            $("#edit_collect_manual_collect_tube").attr('value', package.collectManualModel.collectTube)
            $("#edit_collect_manual_storage_condit").attr('value', package.collectManualModel.storageCondit)
            $("#edit_collect_manual_collect_require").attr('value', package.collectManualModel.collectRequire)
            $("#edit_collect_manual_need_attention").attr('value', package.collectManualModel.needAttention)
            $("#edit_collect_manual_remark").attr('value', package.collectManualModel.remark)
        }
        form.render();

        //确定当前在哪个tab页, 默认在一个tab页
        var tab_flag = 1;

        var edit_tab = document.getElementById("edit_tab");
        //监听选项卡的切换
        var previous = edit_tab.previousElementSibling;
        previous.onclick = function() {
            var childs = previous.childNodes;
            for (var i = 0; i < childs.length; i++) {
                var child = childs[i];
                if (child.getAttribute("class") === 'layui-layer-tabnow') {
                    if (i === 0) {
                        tab_flag = 1;
                    } else if (i === 1) {
                        tab_flag = 2;
                    } else {
                        tab_flag = 3;
                    }
                }
            }
        }
    })
}
/*修改采集图片*/
var collectImg_edit_path;
function uploadCollectImgEdit() {
    var formData = new FormData();
    formData.append('file',$("#collectImg_edit")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#collectImg_edit")[0].title,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data:formData,
        dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success:function(data){
            layui.use('layer', function() {
                var layer = layui.layer;
                if(data.txt != null && data.txt != "" && data.txt != undefined) {
                    collectImg_edit_path = data.txt;
                    layer.msg("上传成功", {
                        time: 1000,
                        offset: "160px"
                    })
                }else {
                    layer.msg("上传失败", {
                        time: 1000,
                        offset: "160px"
                    })
                }
            });
        },
        error:function(er){
            console.log(er);
        }
    });
}
/*修改套餐主图*/
var packageImg_edit_path;
function uploadPicEdit() {
    var formData = new FormData();
    formData.append('file',$("#picUrl_edit")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#picUrl_edit")[0].title,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data:formData,
        dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success:function(data){
            layui.use('layer', function() {
                var layer = layui.layer;
                if(data.txt != null && data.txt != "" && data.txt != undefined) {
                    packageImg_edit_path = data.txt;
                    layer.msg("上传成功", {
                        time: 1000,
                        offset: "160px"
                    })
                }else {
                    layer.msg("上传失败", {
                        time: 1000,
                        offset: "160px"
                    })
                }
            });
        },
        error:function(er){
            console.log(er);
        }
    });
}
/*修改套餐详情图片*/
var packageDetailImg_edit_path;
function uploadDetailImgEdit() {
    var formData = new FormData();
    formData.append('file',$("#detailImg_edit")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#detailImg_edit")[0].title,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data:formData,
        dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success:function(data){
            layui.use('layer', function() {
                var layer = layui.layer;
                if(data.txt != null && data.txt != "" && data.txt != undefined) {
                    packageDetailImg_edit_path = data.txt;
                    layer.msg("上传成功", {
                        time: 1000,
                        offset: "160px"
                    })
                }else {
                    layer.msg("上传失败", {
                        time: 1000,
                        offset: "160px"
                    })
                }
            });
        },
        error:function(er){
            console.log(er);
        }
    });
}

/*套餐详情*/
function ItemDetail(id) {
    var package;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            package = data.package;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use('layer', function() {
        var layer = layui.layer;
        if(package.name == "paramIsError") {
            layer.msg('获取信息失败', {
                time: 1000,
                offset: '160px',
            });
            return;
        }
        layer.tab({
            area: ['600px', '680px'],
            tab:[
                {
                    title: "套餐详情",
                    content: $("#detail_package").html()
                },{
                    title: "知识库详情",
                    content: $('#knowledge_details').html()
                },{
                    title: "采集手册详情",
                    content: $("#collect_manual_details").html()
                }
            ]
        });
        var package_table = document.getElementById("detail_package_table");
        package_table.rows[0].cells[1].innerHTML = package.name;
        if(package.price != null) {
            package_table.rows[1].cells[1].innerHTML = (parseFloat(package.price) / 100).toFixed(2) + " 元";
        }else {
            package_table.rows[1].cells[1].innerHTML = "";
        }
        if(package.status == "0") {
            package_table.rows[2].cells[1].innerHTML = "未发布";
        }else if(package.status == "1") {
            package_table.rows[2].cells[1].innerHTML = "已发布";
        }else if(package.status == "2") {
            package_table.rows[2].cells[1].innerHTML = "已下线";
        }
        package_table.rows[3].cells[1].innerHTML = package.reportTime + " 小时";
        package_table.rows[4].cells[1].innerHTML = package.reportTimeDesc;
        if(package.saleNum == null) {
            package_table.rows[5].cells[1].innerHTML = "0 次";
        }else {
            package_table.rows[5].cells[1].innerHTML = package.saleNum + " 次";
        }
        package_table.rows[6].cells[1].innerHTML = package.wjCode;
        package_table.rows[7].cells[1].innerHTML = package.testType;
        package_table.rows[8].cells[1].innerHTML = package.sampleType;
        package_table.rows[9].cells[1].innerHTML = package.useCrowd;

        package_table.rows[10].cells[1].innerHTML = package.diseaseType;
        package_table.rows[11].cells[1].innerHTML = package.takeType;
        package_table.rows[12].cells[1].innerHTML = package.needAttention;
        package_table.rows[13].cells[1].innerHTML = package.projectDesc;
        package_table.rows[14].cells[1].innerHTML = package.clause;
        $("#package_picture").attr("src", $("#package_picture")[0].title + package.picUrl);
        $("#packageDetailImg").attr("src", package.detailImg);
        //知识库
        var knowledge_table = document.getElementById("detail_knowledge_table");
        knowledge_table.rows[0].cells[1].innerHTML = package.name;
        if(package.knowledgeModel != null) {
            knowledge_table.rows[1].cells[1].innerHTML = package.knowledgeModel.introduction;
            knowledge_table.rows[2].cells[1].innerHTML = package.knowledgeModel.objective;
            knowledge_table.rows[3].cells[1].innerHTML = package.knowledgeModel.textTime;
            knowledge_table.rows[4].cells[1].innerHTML = package.knowledgeModel.needAttention;
        }
        //采集手册
        var detail_collect_manual_table = document.getElementById("detail_collect_manual_table");
        detail_collect_manual_table.rows[0].cells[1].innerHTML = package.name;
        if(package.collectManualModel != null) {
            detail_collect_manual_table.rows[1].cells[1].innerHTML = package.collectManualModel.textMethod;
            detail_collect_manual_table.rows[2].cells[1].innerHTML = package.collectManualModel.collectTube;
            detail_collect_manual_table.rows[3].cells[1].innerHTML = package.collectManualModel.collectImg;
            detail_collect_manual_table.rows[4].cells[1].innerHTML = package.collectManualModel.storageCondit;
            detail_collect_manual_table.rows[5].cells[1].innerHTML = package.collectManualModel.collectRequire;
            detail_collect_manual_table.rows[6].cells[1].innerHTML = package.collectManualModel.needAttention;
            detail_collect_manual_table.rows[7].cells[1].innerHTML = package.collectManualModel.remark;
        }
    })
}

/*导入Excel表格中的数据*/
function importExcel() {
    $(document).ready(function() {
        if($("#selectedExcelFile")[0].files[0] == undefined) {
            layui.use('layer', function() {
                var layer = layui.layer;
                layer.msg('请选择文件', {
                    time: 1000,
                    offset: '160px'
                })
            })
            return;        }
        if($("#selectedExcelFile").val().lastIndexOf(".xls") < 0) {
            layui.use('layer', function() {
                var layer = layui.layer;
                layer.msg('只能上传Excel文件', {
                    time: 1000,
                    offset: '160px'
                })
                setTimeout(function() {
                    location.reload();
                }, 1000)
            })
            return;
        }
        var formData = new FormData();
        formData.append("excelFile", $("#selectedExcelFile")[0].files[0]);
        $.ajax({
            type: "post",
            url: "excelImport.do",
            async: false,
            contentType: false,    //这个一定要写
            processData: false, //这个也一定要写，不然会报错
            data:formData,
            dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
            success:function(data){
                layui.use('layer', function() {
                    var layer = layui.layer;
                    layer.msg(data.msg, {
                        time: 1000,
                        offset: '160px'
                    })
                    setTimeout(function(){
                        location.reload();
                    }, 1000)
                });
            },
            error:function(er){
                console.log(er);
            }
        });
    })
}