/**
 * Created by LiuQi on 2017/7/26.
 */
/**
 * 添加医院
 */
function addHospital(){
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        layer.open({
            type: 1,
            title: "增加医院",
            skin: 'layui-layer-rim', //加上边框
            area: ["480px", "680px"],
            content: $("#add_hospital_page"),
            btn: ['增加', '取消'],
            yes: function(index, layero) {
                $("#picUrl_add_").val(hospitalImg_path);
                $.ajax({
                    url: "add.do",
                    type: "POST",
                    data: $("#add_hospital_form").serializeArray(),
                    dataType: "json",
                    success: function(data) {
                        if(data.msg == "success") {
                            layer.msg("新增成功", {
                                time: 1000
                            });
                            setTimeout(function() {
                                location.reload();
                            }, 1000);
                        }else if(data.msg == "error") {
                            layer.msg("新增失败", {
                                time: 100
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
        form.render();
    })
}

/*上传医院照片*/
var hospitalImg_path;
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
                        hospitalImg_path = data.txt;
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

/**
 * 删除医院
 * @param id
 * @constructor
 */
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

/**
 * 编辑医院信息
 * @param id
 * @constructor
 */
function ItemEdit(id) {
    var hospital;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            hospital = data.hospital;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        if(hospital.name == "paramIsError") {
            layer.msg('获取信息失败', {
                time: 1000,
                offset: '160px',
            });
            return;
        }
        layer.open({
            type: 1,
            title: '信息编辑',
            area: ['480px', '680px'],
            skin: "layui-layer-rim",
            content: $("#hospital_info_edit"),
            btn: ['提交', '取消'],
            yes: function(index, layero) {
                $("#edit_picUrl_").val(hospitalImg_edit_path);
                $.ajax({
                    url: "update.do",
                    type: "POST",
                    data: $("#edit_hospital_form").serializeArray(),
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
        $("#edit_name").val(hospital.name);
        $("#edit_address").val(hospital.address);
        $("#edit_telphone").val(hospital.telphone);
        $("#edit_longitude").val(hospital.longitude);
        $("#edit_latitude").val(hospital.latitude);
        $("#edit_alipayPayAccount").val(hospital.alipayPayAccount);
        $("#edit_weixinPayAccount").val(hospital.weixinPayAccount);
        $("#edit_details").val(hospital.details);
        $("#edit_projectDesc").val(hospital.projectDesc);
        $("#edit_specialist").val(hospital.specialist);
        $("#id_form").val(hospital.id);
    })
}
/*编辑时上传图片*/
var hospitalImg_edit_path;
function uploadPicEdit() {
    var formData = new FormData();
    formData.append('file',$("#edit_picUrl")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#edit_picUrl")[0].title,
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
                        hospitalImg_edit_path = data.txt;
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
/*获取经纬度*/
function getLongitudeAndLatitude() {
    layui.use('layer', function() {
        layer = layui.layer;
        layer.open({
            type: 2,
            title: '经纬度获取',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: 'http://api.map.baidu.com/lbsapi/getpoint/index.html' //iframe的url
        });
    })
}

/**
 * 显示医院详细信息
 * @param id
 * @constructor
 */
function ItemDetail(id) {
    var hospital;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            hospital = data.hospital;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use('layer', function() {
        var layer = layui.layer;
        if(hospital.name == "paramIsError") {
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
            content: $("#detail_info")
        });
        var hospital_table = document.getElementById("detail_info_table");
        hospital_table.rows[0].cells[1].innerHTML = hospital.name;
        hospital_table.rows[1].cells[1].innerHTML = hospital.telphone;
        hospital_table.rows[2].cells[1].innerHTML = hospital.longitude;
        hospital_table.rows[3].cells[1].innerHTML = hospital.latitude;
        hospital_table.rows[4].cells[1].innerHTML = hospital.address;
        hospital_table.rows[5].cells[1].innerHTML = hospital.details;
        hospital_table.rows[6].cells[1].innerHTML = hospital.projectDesc;
        hospital_table.rows[7].cells[1].innerHTML = hospital.specialist;
        $("#hospital_picture").attr("src", $("#hospital_picture")[0].title + hospital.picUrl);
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
