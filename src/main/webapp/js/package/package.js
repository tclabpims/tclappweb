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
/*新增订单*/
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
            if(data.txt != null || data.txt != "") {
                layui.use('layer', function() {
                    var layer = layui.layer;
                    if(data.txt != null || data.txt != "") {
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
            }
        },
        error:function(er){
            console.log(er);
        }
    });
}
$("document").ready(function(){
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
})
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
        $("#edit_price").val(package.price);
        $("#edit_reportTime").val(package.reportTime);
        $("#edit_wjCode").val(package.wjCode);
        $("#edit_saleNum").val(package.saleNum);
        $("#edit_status option[value='"+package.status+"']").attr("selected", 'selected');
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
            if(data.txt != null || data.txt != "") {
                layui.use('layer', function() {
                    var layer = layui.layer;
                    if(data.txt != null || data.txt != "") {
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
            }
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
            area: ['600px', '580px'],
            content: $("#detail_package")
        });
        var package_table = document.getElementById("detail_package_table");
        package_table.rows[0].cells[1].innerHTML = package.name;
        package_table.rows[1].cells[1].innerHTML = package.price + " 元";
        if(package.status == "0") {
            package_table.rows[2].cells[1].innerHTML = "未发布";
        }else if(package.status == "1") {
            package_table.rows[2].cells[1].innerHTML = "已发布";
        }else if(package.status == "2") {
            package_table.rows[2].cells[1].innerHTML = "已下线";
        }
        package_table.rows[3].cells[1].innerHTML = package.reportTime;
        package_table.rows[4].cells[1].innerHTML = package.wjCode;
        package_table.rows[4].cells[3].innerHTML = package.saleNum + " 次";
        package_table.rows[5].cells[1].innerHTML = package.useCrowd;
        package_table.rows[6].cells[1].innerHTML = package.testType;
        package_table.rows[7].cells[1].innerHTML = package.diseaseType;
        package_table.rows[8].cells[1].innerHTML = package.takeType;
        package_table.rows[9].cells[1].innerHTML = package.needAttention;
        package_table.rows[10].cells[1].innerHTML = package.projectDesc;
        package_table.rows[11].cells[1].innerHTML = package.clause;
        $("#package_picture").attr("src", $("#package_picture")[0].title + package.picUrl);
    })
}