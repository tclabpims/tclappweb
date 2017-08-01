/**
 * Created by LiuQi on 2017/7/31.
 */
/*增加新闻*/
function addNewspaper(){
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        layer.open({
            type: 1,
            title: "增加广告",
            skin: 'layui-layer-rim', //加上边框
            area: ["480px", "520px"],
            content: $("#add_newspaper"),
            btn: ['增加', '取消'],
            yes: function (index, layero) {
                $("#imgUrl_add_").val(newspaperImg_path);
                $.ajax({
                    url: "add.do",
                    type: "POST",
                    data: $("#add_newspaper_form").serializeArray(),
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

/*上传广告主图*/
var newspaperImg_path;
function uploadPic() {
    var formData = new FormData();
    formData.append('file',$("#imgUrl_add")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#imgUrl_add")[0].title,
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
                        newspaperImg_path = data.txt;
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

/*删除广告*/
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

/*编辑广告*/
function ItemEdit(id) {
    var newspaper;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            newspaper = data.newspaper;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        if(newspaper.title == "paramIsError") {
            layer.msg('获取信息失败', {
                time: 1000,
                offset: '160px',
            });
            return;
        }
        layer.open({
            type: 1,
            title: '信息编辑',
            area: ['480px', '520px'],
            skin: "layui-layer-rim",
            content: $("#edit_newspaper"),
            btn: ['提交', '取消'],
            yes: function(index, layero) {
                $("#imgUrl_edit_").val(newspaperImg_edit_path);
                $.ajax({
                    url: "update.do",
                    type: "POST",
                    data: $("#edit_newspaper_form").serializeArray(),
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
        $("#title_edit").val(newspaper.title);
        $("#contentUrl_edit").val(newspaper.contentUrl);
        $("#type_edit option[value='"+newspaper.type+"']").attr("selected", 'selected');
        $("#status_edit option[value='"+newspaper.status+"']").attr("selected", 'selected');
        $("#descInfo_edit").val(newspaper.descInfo);
        $("#id_edit").val(newspaper.id);
        form.render();
    })
}
/*编辑时上传图片*/
var newspaperImg_edit_path;
function uploadPicEdit() {
    var formData = new FormData();
    formData.append('file',$("#imgUrl_edit")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#imgUrl_edit")[0].title,
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
                        newspaperImg_edit_path = data.txt;
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

/*显示详情*/
function ItemDetail(id){
    var newspaper;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            newspaper = data.newspaper;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use('layer', function() {
        var layer = layui.layer;
        if(newspaper.title == "paramIsError") {
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
            content: $("#detail_newspaper")
        });
        var newspaper_table = document.getElementById("detail_newspaper_table");
        newspaper_table.rows[0].cells[1].innerHTML = newspaper.title;
        newspaper_table.rows[1].cells[1].innerHTML = newspaper.type;
        newspaper_table.rows[2].cells[1].innerHTML = newspaper.status;
        newspaper_table.rows[3].cells[1].innerHTML = newspaper.createUserid;
        newspaper_table.rows[4].cells[1].innerHTML = new Date(newspaper.createTime).format(('yyyy-MM-dd hh:mm:ss'));
        newspaper_table.rows[5].cells[1].innerHTML = newspaper.contentUrl;
        newspaper_table.rows[6].cells[1].innerHTML = newspaper.descInfo;
        $("#newspaper_picture").attr("src", $("#newspaper_picture")[0].title + newspaper.imgUrl);
    })
}

/*修改时间的格式*/
Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}