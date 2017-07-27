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
            title: "基本信息",
            skin: 'layui-layer-rim', //加上边框
            area: ["480px", "680px"],
            content: $("#add_hospital_page"),
            btn: ['增加', '取消'],
            yes: function(index, layero) {
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
                            })
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
            })
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

            },
            btn2: function(index, layero) {
                layer.close(index);
            }
        })
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
    })
}

/**
 * 显示医院详细信息
 * @param id
 * @constructor
 */
function ItemDetail(id) {
    alert(id);
}