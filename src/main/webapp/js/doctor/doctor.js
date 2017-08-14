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
                $.ajax({
                    url: "delete.do",
                    type: "POST",
                    data: {
                        id : id
                    },
                    dataType: "json",
                    success: function(data) {
                        if(data.msg == "1") {
                            layer.msg('删除成功',{
                                offset: '160px',
                                time: 1500
                            });
                            //删除成功后刷新当前页面
                            setTimeout(function() {
                                location.reload();
                            }, 1500);
                        }else if(data.msg == "2"){
                            layer.msg("删除失败", {
                                offset: "160px",
                                time: 1500
                            });
                        } else {
                            layer.msg("参数有误", {
                                offset: "160px",
                                time: 1500
                            });
                        }
                    },
                    error: function(er) {
                        console.log(er);
                    }
                });
                layer.close(index);
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
                var obj = document.getElementById("hospital_Id");
                var selected_index = obj.selectedIndex;
                $("#hospitalName").val(obj.options[selected_index].text);
                $("#touimg_edit_").val(tou_img_edit_path);
                $("#zzImg_edit_").val(zzImg_edit_path);
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
                                location.reload();
                            }, 1000)
                        } else if(data.msg() == "error") {
                            layer.msg("更新失败", {
                                time: 1000
                            });
                            setTimeout(function(){
                                location.reload();
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

        });
        /*通过数据库中获取的数据动态确定radio选项*/
        $("input[name=sex][value='"+doctor.sex+"']").attr("checked", true);
        $("#doctor_type option[value='"+doctor.type+"']").attr("selected", 'selected');
        $("#id").val(doctor.id);
        $("#userName").val(doctor.userName);
        $("#hospital_Id option[value='"+doctor.hospitalId+"']").attr("selected", 'selected');
        $("#doctorName").val(doctor.doctorName);
        $("#age").val(doctor.age);

        $("#title option[value='"+doctor.title+"']").attr("selected", 'selected');
        $("#readReportNum").val(doctor.readReportNum);
        $("#diagnosisNum").val(doctor.diagnosisNum);
        $("#introduce").val(doctor.introduce);
        form.render();
    });
}

/**
 * 编辑时上传头像照片
 */
var tou_img_edit_path;
function uploadTouImgEdit() {
    var formData = new FormData();
    formData.append('file',$("#touimg_edit")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#touimg_edit")[0].title,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data:formData,
        dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success:function(data){
            layui.use('layer', function() {
                var layer = layui.layer;
                if(data.txt != null || data.txt != "") {
                    tou_img_edit_path = data.txt;
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

/**
 * 编辑时上传从业执照图片
 */
var zzImg_edit_path;
function uploadZZImgEdit() {
    var formData = new FormData();
    formData.append('file',$("#zzImg_edit")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#zzImg_edit")[0].title,
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
                        zzImg_edit_path = data.txt;
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
                                location.reload();
                            }, 1000)
                        } else {
                            //操作失败在后台打印相关信息
                            console.log(data.msg);
                            layer.msg("操作失败", {
                                time: 1000
                            });
                            setTimeout(function(){
                                location.reload();
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
        $("#doctor_picture").attr("src", $("#doctor_picture")[0].title + doctor.touImg);
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
                $("#touimg_id_").val(tou_img_path);
                $("#zzImg_id_").val(zzImg_path);
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
        });
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
                var obj3 = document.getElementById("hospital_Id");
                for(i=0; i<hospitals.length; i++) {
                    obj1.options.add(new Option(hospitals[i].name, hospitals[i].id));
                    obj2.options.add(new Option(hospitals[i].name, hospitals[i].id));
                    obj3.options.add(new Option(hospitals[i].name, hospitals[i].id));
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
var tou_img_path;
function uploadTouImg() {
    var formData = new FormData();
    formData.append('file',$("#touimg_id")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#touimg_id")[0].title,
        async: false,
        contentType: false,    //这个一定要写
        processData: false, //这个也一定要写，不然会报错
        data:formData,
        dataType:'json',    //返回类型，有json，text，HTML。这里并没有jsonp格式，所以别妄想能用jsonp做跨域了。
        success:function(data){
            layui.use('layer', function() {
                var layer = layui.layer;
                if(data.txt != null || data.txt != "") {
                    tou_img_path = data.txt;
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

/**
 * 上传从业执照图片
 */
var zzImg_path;
function uploadZZImg() {
    var formData = new FormData();
    formData.append('file',$("#zzImg_id")[0].files[0]);    //将文件转成二进制形式
    $.ajax({
        type: "post",
        url: $("#zzImg_id")[0].title,
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
                        zzImg_path = data.txt;
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

