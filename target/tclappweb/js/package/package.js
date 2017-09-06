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
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        if(package.name == "paramIsError") {
            layer.msg('获取信息失败', {
                time: 1000,
                offset: '160px',
            });
            return;
        }
        layer.open({
            type: 1,
            title: '套餐编辑',
            area: ['480px', '720px'],
            skin: "layui-layer-rim",
            content: $("#edit_package"),
            btn: ['提交', '取消'],
            yes: function(index, layero) {
                $("#picUrl_edit_").val(packageImg_edit_path);
                if(packageDetailImg_edit_path != null && packageDetailImg_edit_path != '' && packageDetailImg_edit_path != undefined) {
                    $("#detailImg_edit_").val("http://183.247.179.221:9099" + packageDetailImg_edit_path);
                }
                $.ajax({
                    url: "update.do",
                    type: "POST",
                    data: $("#edit_package_form").serializeArray(),
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
        $("#edit_name").val(package.name);
        $("#edit_useCrowd").val(package.useCrowd);
        $("#edit_price").val((parseFloat(package.price) / 100).toFixed(2));
        $("#edit_reportTime").val(package.reportTime);
        $("#edit_reportTimeDesc").val(package.reportTimeDesc);
        $("#edit_wjCode").val(package.wjCode);
        $("#edit_saleNum").val(package.saleNum);
        $("#edit_status option[value='"+package.status+"']").attr("selected", 'selected');
        $("#edit_sampleType").val(package.sampleType);
        $("#edit_testType").val(package.testType);
        $("#edit_diseaseType").val(package.diseaseType);
        $("#edit_takeType").val(package.takeType);
        $("#edit_needAttention").val(package.needAttention);
        $("#edit_projectDesc").val(package.projectDesc);
        $("#edit_clause").val(package.clause);
        $("#edit_id").val(id);
        form.render();
    })
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
        layer.open({
            type: 1,
            title: "详细信息",
            skin: 'layui-layer-rim',
            area: ['600px', '680px'],
            content: $("#detail_package")
        });
        var package_table = document.getElementById("detail_package_table");
        package_table.rows[0].cells[1].innerHTML = package.name;
        package_table.rows[1].cells[1].innerHTML = (parseFloat(package.price) / 100).toFixed(2) + " 元";
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