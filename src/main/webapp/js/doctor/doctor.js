/**
 * Created by LiuQi on 2017/7/13.
 */
//从数据库中将某位医生删除
function ItemDele(id) {
    layui.use('layer', function(){
        var layer = layui.layer;
        //删除前确认一下
        var index = layer.confirm(
            '您确定要删除吗？',
            {title: "温馨提示",
                //设置弹框的位置
                offset: '100px',
                icon: 3},
            function(index){
                layer.close(index);
                $.ajax({
                    url: "delete.do",
                    type: "POST",
                    data: {
                        id : id
                    },
                    dataType: "json",
                    success: function(data) {
                        if(data.msg == "1") {
                            console.log("delete success!");
                            //删除成功后刷新当前页面
                            location.reload();
                        }else if(data.msg == "2"){
                            layer.msg("delete failed", {
                                offset: "160px",
                                time: 1500
                            });
                        } else {
                            layer.msg("param has error", {
                                offset: "160px",
                                time: 1500
                            });
                        }
                    },
                    error: function(er) {
                        console.log(er);
                    }
                });
            });
    })
}

//对某位医生的信息进行编辑
function ItemEdit(id) {
    var doctor;
    $.ajax({
        url: "getInfo.do",
        type: "POST",
        data: {id : id},
        dataType: "json",
        async: false,
        success: function(data) {
            doctor = data.doctor;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use(['layer','form', 'element'], function() {
        var layer = layui.layer;
        var form = layui.form();
        var element = layui.element();
        //对ajax获取的返回值进行简单判断
        if(doctor.userName == "paramIsError") {
            layer.msg("获取信息失败",{
                time:1000
            });
            return;
        }
        layer.open({
            type: 1,
            title: "基本信息修改",
            skin: 'layui-layer-rim',
            area: ['480px', '680px'],
            content:$("#infoEdit"),
            btn:['提交', '取消'],
            yes: function(index, layero) {
                $.ajax({
                    url: "update.do",
                    type: "POST",
                    data: $("#doctorInfo").serializeArray(),
                    dataType: "json",
                    success: function(data) {
                        if(data.msg == "success") {
                            layer.msg("更新成功", {
                                time: 1000
                            });
                            setTimeout(function(){
                                location.href = "list.do?type=";
                            }, 1000)
                        } else if(data.msg() == "error") {
                            layer.msg("更新失败", {
                                time: 1000
                            });
                            setTimeout(function(){
                                location.href = "list.do?type=";
                            }, 1000)
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

        })
        /*通过数据库中获取的数据动态确定radio选项*/
        $("input[name=sex][value='"+doctor.sex+"']").attr("checked", true);
        $("#doctor_type option[value='"+doctor.type+"']").attr("selected", 'selected');
        $("#id").val(doctor.id);
        $("#password").val(doctor.passWord);
        $("#hospitalId").val(doctor.hospitalId);
        $("#sfzNum").val(doctor.sfzNum);
        $("#touImg").val(doctor.touImg);
        $("#zzImg").val(doctor.zzImg);
        $("#status").val(doctor.status);
        $("#verificationCode").val(doctor.verificationCode);
        $("#codeSendTime").val(new Date(doctor.codeSendTime));
        $("#createTime").val(new Date(doctor.createTime));
        $("#userName").val(doctor.userName);
        $("#hospitalName").val(doctor.hospitalName);
        $("#doctorName").val(doctor.doctorName);
        $("#age").val(doctor.age);
        $("#title").val(doctor.title);
        $("#readReportNum").val(doctor.readReportNum);
        $("#diagnosisNum").val(doctor.diagnosisNum);
        $("#introduce").val(doctor.introduce);
        form.render();
    });
}

//对未审核的医生进行审核
function ItemAudite(id) {
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        layer.open({
            type: 1,
            title: "医生审核",
            skin: 'layui-layer-rim',
            area: ['480px', '300px'],
            content: $("#doctorAudite"),
            btn:['提交', '取消'],
            yes: function(index, layuio) {
                var audit_data = $("#Audite").serializeArray();
                $.ajax({
                    url: "audit.do",
                    type: "POST",
                    data: audit_data,
                    dataType: "json",
                    success: function(data) {
                        //当ajax返回的信息是"success"时，则表示操作成功；否则，表示操作失败
                        if(data.msg == "success") {
                            layer.msg("操作成功", {
                                time: 1000
                            });
                            setTimeout(function(){
                                location.href = "list.do?type=";
                            }, 1000)
                        } else {
                            //操作失败在后台打印相关信息
                            console.log(data.msg);
                            layer.msg("操作失败", {
                                time: 1000
                            });
                            setTimeout(function(){
                                location.href = "list.do?type=";
                            }, 1000)
                        }
                    },
                    error: function(er) {
                        console.log();
                    }
                });
            },
            btn2: function(index, layuio) {
                layer.close(index);
            }
        });
        form.render();
        $("#id2").val(id);
    });
}

//显示某位医生的详细信息
function ItemDetail(id) {
    var doctor;
    $.ajax({
        url: "getInfo.do",
        type: "POST",
        data: {id: id},
        dataType: "json",
        async: false,
        success: function(data) {
            doctor = data.doctor;
        },
        error: function(er) {
            console.log(er);
        }
    });
    layui.use("layer", function() {
        var layer = layui.layer;
        if(doctor.userName == "paramIsError") {
            layer.msg("获取信息失败", {
                time: 1000
            });
            return;
        }
        layer.open({
            type: 1,
            title: "基本信息",
            skin: 'layui-layer-rim',
            area: ['600px', '680px'],
            content: $("#detail_info")
        });
        var doctor_table = document.getElementById("doctor_table");
        doctor_table.rows[0].cells[1].innerHTML = doctor.doctorName;
        doctor_table.rows[0].cells[3].innerHTML = doctor.hospitalName;
        doctor_table.rows[1].cells[1].innerHTML = doctor.sex;
        doctor_table.rows[1].cells[3].innerHTML = doctor.age;
        if(doctor.type == "1") {
            doctor_table.rows[2].cells[1].innerHTML = "医生";
        } else if(doctor.type == "2") {
            doctor_table.rows[2].cells[1].innerHTML = "护士";
        }
        doctor_table.rows[2].cells[3].innerHTML = doctor.title;
        doctor_table.rows[3].cells[1].innerHTML = doctor.sfzNum;
        doctor_table.rows[4].cells[1].innerHTML = doctor.introduce;
        $("#doctor_picture").attr("src", doctor.touImg);
    })
}

/*添加医生*/
function addDoctor() {
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer;
        var form = layui.form();
        layer.open({
            type: 1,
            title: "新增医生",
            area: ["480px", "680px"],
            content: $("#add_doctor"),
            btn: ["增加", "取消"],
            yes: function(index, layero) {
                //确定当前选定的医院
                var obj = document.getElementById("hospital_name_select");
                var selected_index = obj.selectedIndex;
                $("#hospital_name").val(obj.options[selected_index].text);
                $.ajax({
                    url: "add.do",
                    type: "POST",
                    data: $("#add_doctor_form").serializeArray(),
                    dataType: "json",
                    success: function(data) {
                        if(data.msg == "success") {
                            layer.msg("添加成功", {
                                time: 1000
                            });
                        } else if(data.msg == "error") {
                            layer.msg("添加失败", {
                                time: 1000
                            });
                        }
                        setTimeout(function() {
                            location.reload();
                        }, 1000)
                    },
                    error: function(er) {
                        console.log(er);
                    }
                });
            },
            btn2: function(index, layero) {
                layer.close(index);
            }
        })
        form.render();
    });
}

function requireHospitals() {
    layui.use('form', function() {
        var form = layui.form();
        $.ajax({
            url: "/hospital/listHospitals.do",
            type: "POST",
            data: {},
            dataType: "json",
            success: function(data) {
                var hospitals = data.hospitals;
                var obj1 = document.getElementById("hospital_name_select");
                var obj2 = document.getElementById("query_hospital_id");
                for(i=0; i<hospitals.length; i++) {
                    obj1.options.add(new Option(hospitals[i].name, hospitals[i].id));
                    obj2.options.add(new Option(hospitals[i].name, hospitals[i].id));
                }
            },
            error: function(er) {
                console.log(er);
            }
        });
    });
}
requireHospitals();

/**
 * 上传头像照片
 */
function uploadTouImg() {
    $.ajax({
        url: "http://183.247.179.221:9099/fileUpload?file=" + $("#touimg_id").val(),
        type: "get",
        async: false,
        dataType: "jsonp",
        success: function(json) {
            console.log(json);
        },
        error: function(er) {
            console.log(er);
        }
    })
}

/**
 * 上传从业执照图片
 */
function uploadZZImg() {
    alert("zz");
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
            end.start = datas //将结束日的初始值设定为开始日
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
    }
    document.getElementById('createtime_range_end').onclick = function(){
        end.elem = this
        laydate(end);
    }
});
/*
function doctor_query() {
    var query_info = {
        id: $("#query_id").val(),
        hospitalId: $("#query_hospital_id").val(),
        doctorName: $("#query_doctor_name").val(),
        title: $("#query_title").val(),
        status: $("#query_status").val(),
        type: $("#query_type").val(),
        createTimeStart: $("#createtime_range_start").val(),
        createTimeEnd: $("#createtime_range_end").val()
    }
    $.ajax({
        url: "query.do",
        type: "POST",
        data: query_info,
        dataType: "json",
        success: function(data) {

        },
        error: function(er) {
            console.log(er);
        }
    })
}*/

