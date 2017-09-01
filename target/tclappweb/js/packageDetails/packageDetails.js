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
/*新增检验细项*/
function addPackageDetails(id) {
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        layer.open({
            type: 1,
            title: "增加套餐",
            skin: 'layui-layer-rim', //加上边框
            area: ["480px", "480px"],
            content: $("#add_packageDetails"),
            btn: ['增加', '取消'],
            yes: function (index, layero) {
                var hisId_check = document.getElementById("hisId_check");
                if($("#hisId_add").val() == "") {
                    hisId_check.innerHTML = "his项目编号不能为空";
                    return;
                } else{
                    hisId_check.innerHTML = "";
                }
                var packageId_check = document.getElementById("packageId_check")
                if($("#packageId_add").val() == "") {
                    packageId_check.innerHTML = "套餐编号不能为空";
                    return;
                } else {
                    packageId_check.innerHTML = "";
                }
                $.ajax({
                    url: "add.do",
                    type: "POST",
                    data: $("#add_packageDetails_form").serializeArray(),
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

/*编辑检验细项*/
function ItemEdit(id) {
    var packageDetails;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            packageDetails = data.packageDetails;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        if(packageDetails.name == "paramIsError") {
            layer.msg('获取信息失败', {
                time: 1000,
                offset: '160px',
            });
            return;
        }
        layer.open({
            type: 1,
            title: '套餐编辑',
            area: ['480px', '480px'],
            skin: "layui-layer-rim",
            content: $("#edit_packageDetails"),
            btn: ['提交', '取消'],
            yes: function(index, layero) {
                var hisId_check_edit = document.getElementById("hisId_check_edit");
                if($("#hisId_edit").val() == "") {
                    hisId_check_edit.innerHTML = "his项目编号不能为空";
                    return;
                } else{
                    hisId_check_edit.innerHTML = "";
                }
                var packageId_check_edit = document.getElementById("packageId_check_edit")
                if($("#packageId_edit").val() == "") {
                    packageId_check_edit.innerHTML = "套餐编号不能为空";
                    return;
                } else {
                    packageId_check_edit.innerHTML = "";
                }
                $.ajax({
                    url: "update.do",
                    type: "POST",
                    data: $("#edit_packageDetails_form").serializeArray(),
                    dataType: "json",
                    success: function(data) {
                        if(data.msg == "success") {
                            layer.msg("更新成功", {
                                time: 1000,
                                offset: '160px'
                            });
                            setTimeout(function() {
                                location.reload();
                            }, 1000);
                        }else if(data.msg == "error") {
                            layer.msg("更新失败", {
                                time: 1000,
                                offset: '160px'
                            });
                            setTimeout(function() {
                                location.reload();
                            }, 1000);
                        }
                    },
                    error: function(er) {
                        console.log(er);
                    }
                });
            },
            btn2: function(index, layero) {
                layer.close(index);
            }
        });
        $("#hisId_edit").val(packageDetails.hisId);
        $("#hisName_edit").val(packageDetails.hisName);
        $("#hisPrice_edit").val((parseFloat(packageDetails.hisPrice) / 100).toFixed(2));
        $("#packageId_edit").val(packageDetails.packageId);
        $("#name_edit").val(packageDetails.name);
        $("#price_edit").val((parseFloat(packageDetails.price) / 100).toFixed(2));
        $("#edit_id").val(id);
        form.render();
    })
}

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
            return;
        }
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

