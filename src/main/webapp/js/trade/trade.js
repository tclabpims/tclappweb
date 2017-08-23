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

/*编辑购物车内容*/
function ItemEdit(id) {
    var trade;
    $.ajax({
        url: 'acquire.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            trade = data.trade;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        if(trade.name == "paramIsError") {
            layer.msg('获取信息失败', {
                time: 1000,
                offset: '160px',
            });
            return;
        }
        layer.open({
            type: 1,
            title: '套餐编辑',
            area: ['480px', '680px'],
            skin: "layui-layer-rim",
            content: $("#edit_trade"),
            btn: ['提交', '取消'],
            yes: function(index, layero) {
                $.ajax({
                    url: "update.do",
                    type: "POST",
                    data: $("#edit_trade_form").serializeArray(),
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
        $("#userId_edit").val(trade.userId);
        $("#applicantId_edit").val(trade.applicantId);
        $("#status_edit option[value='"+trade.status+"']").attr("selected", 'selected');
        $("#price_edit").val(trade.price);
        $("#num_edit").val(trade.num);
        $("#price_edit").val(trade.price);
        $("#name_edit").val(trade.name);
        $("#doctorId_edit").val(trade.doctorId);
        $("#doctorName_edit").val(trade.doctorName);
        $("#hospitalId_edit").val(trade.hospitalId);
        $("#hospitalIdName_edit").val(trade.hospitalIdName);
        $("#payType_edit").val(trade.payType);
        $("#yzDoctorId_edit").val(trade.yzDoctorId_);
        $("#yzDoctorName_edit").val(trade.yzDoctorName);
        $("#yzDepartmentNum_edit").val(trade.yzDepartmentNum);
        $("#yzDepartmentName_edit").val(trade.yzDepartmentName);
        $("#createType_eidt option[value='"+trade.createType+"']").attr("selected", 'selected');
        $("#relationId_edit").val(trade.relationId);
        $("#needRead_eidt option[value='"+trade.needRead+"']").attr("selected", 'selected');
        $("#doctorMsg_edit").val(trade.doctorMsg);
        $("#edit_id").val(id);
        form.render();
    })
}

/*订单详情*/
function ItemDetail(id) {
    var trade;
    $.ajax({
        url: 'acquireTrade.do',
        type: 'POST',
        data: {
            id: id
        },
        async: false,
        dataType: 'json',
        success: function(data) {
            trade = data.trade;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use('layer', function() {
        var layer = layui.layer;
        if(trade.name == "paramIsError") {
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
            content: $("#detail_trade")
        });
        var detail_trade_table = document.getElementById("detail_trade_table");
        detail_trade_table.rows[0].cells[1].innerHTML = trade.userId;
        detail_trade_table.rows[0].cells[3].innerHTML = trade.userModel.userName;
        detail_trade_table.rows[1].cells[1].innerHTML = trade.applicantId;
        detail_trade_table.rows[1].cells[3].innerHTML = trade.applicantModel.applyName;
        detail_trade_table.rows[2].cells[1].innerHTML = trade.tradeNum;
        if(trade.status == "-2") {
            detail_trade_table.rows[2].cells[3].innerHTML = "未确认（预约医生）";
        }
        if(trade.status == "-1") {
            detail_trade_table.rows[2].cells[3].innerHTML = "已确认（预约医生）";
        }
        if(trade.status == "0") {
            detail_trade_table.rows[2].cells[3].innerHTML = "已下单";
        }
        if(trade.status == "1") {
            detail_trade_table.rows[2].cells[3].innerHTML = "已付款";
        }
        if(trade.status == "2") {
            detail_trade_table.rows[2].cells[3].innerHTML = "已开医嘱";
        }
        if(trade.status == "3") {
            detail_trade_table.rows[2].cells[3].innerHTML = "已采集";
        }
        if(trade.status == "4") {
            detail_trade_table.rows[2].cells[3].innerHTML = "检验中";
        }
        if(trade.status == "5") {
            detail_trade_table.rows[2].cells[3].innerHTML = "已报告";
        }
        if(trade.status == "6") {
            detail_trade_table.rows[2].cells[3].innerHTML = "已解读";
        }
        detail_trade_table.rows[3].cells[1].innerHTML = trade.price;
        detail_trade_table.rows[3].cells[3].innerHTML = trade.num;
        detail_trade_table.rows[4].cells[1].innerHTML = trade.name;
        detail_trade_table.rows[4].cells[3].innerHTML = trade.doctorName;
        detail_trade_table.rows[5].cells[1].innerHTML = trade.hospitalName;
        detail_trade_table.rows[5].cells[3].innerHTML = trade.payType;
        detail_trade_table.rows[6].cells[1].innerHTML = trade.orderedTime != null ? new Date(trade.orderedTime).format("yyyy-MM-dd hh:mm:ss") : "";
        detail_trade_table.rows[6].cells[3].innerHTML = trade.payTime != null ? new Date(trade.payTime).format("yyyy-MM-dd hh:mm:ss") : "";
        detail_trade_table.rows[7].cells[1].innerHTML = trade.yzDoctorId;
        detail_trade_table.rows[7].cells[3].innerHTML = trade.yzDoctorName;
        detail_trade_table.rows[8].cells[1].innerHTML = trade.yzDepartmentNum;
        detail_trade_table.rows[8].cells[3].innerHTML = trade.yzDepartmentNum;
        detail_trade_table.rows[9].cells[1].innerHTML = trade.yzTime != null ? new Date(trade.yzTime).format("yyyy-MM-dd hh:mm:ss") : "";
        if(trade.createType == "0") {
            detail_trade_table.rows[9].cells[3].innerHTML = "客户创建";
        }
        if(trade.createType == "1") {
            detail_trade_table.rows[9].cells[3].innerHTML = "解读医生创建";
        }
        detail_trade_table.rows[10].cells[1].innerHTML = trade.relationId;
        if(trade.needRead == "0") {
            detail_trade_table.rows[10].cells[3].innerHTML = "不需要";
        }
        if(trade.needRead == "1") {
            detail_trade_table.rows[10].cells[3].innerHTML = "需要";
        }
        detail_trade_table.rows[11].cells[1].innerHTML = trade.createTime != null ? new Date(trade.createTime).format("yyyy-MM-dd hh:mm:ss") : "";
        detail_trade_table.rows[11].cells[3].innerHTML = trade.modifyTime != null ? new Date(trade.modifyTime).format("yyyy-MM-dd hh:mm:ss") : "";
        detail_trade_table.rows[12].cells[1].innerHTML = trade.doctorMsg;
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

/**
 * 显示年月日
 */
layui.use('laydate', function(){
    var laydate = layui.laydate;
    var start = {
        max: laydate.now()
        ,istoday: false
        ,choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas; //将结束日的初始值设定为开始日
        }
    };

    var end = {
        max: laydate.now()
        ,istoday: false
        ,choose: function(datas){
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };

    document.getElementById('createtime_range_start').onclick = function(){
        start.elem = this;
        laydate(start);
    };
    document.getElementById('createtime_range_end').onclick = function(){
        end.elem = this;
        laydate(end);
    }
});

