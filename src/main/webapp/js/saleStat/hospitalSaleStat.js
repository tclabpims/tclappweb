/**
 * Created by LiuQi on 2017/9/5.
 */
function formRender() {
    layui.use('form', function() {
        var form = layui.form();
        form.render();
    });
}

function selectConfirm() {
    //确定采集点的选择框
    var hospitalName_input = $("#hospitalName_input").val();
    if(hospitalName_input != null && hospitalName_input != '' && hospitalName_input != undefined) {
        var hospitalName_query = document.getElementById("hospitalName_query");
        for(var i=0; i<hospitalName_query.options.length; i++){
            if(hospitalName_query.options[i].text == hospitalName_input){
                hospitalName_query.options[i].selected = true;
                break;
            }
        }
    }
    //确定年份的选择框
    var year_input = $("#year_input").val();
    if(year_input != null && year_input != '' && year_input != undefined) {
        var year_id = document.getElementById("year_id");
        for(var i=0; i<year_id.options.length; i++){
            if(year_id.options[i].text == year_input){
                year_id.options[i].selected = true;
                break;
            }
        }
    }
}

function generateSelect() {
    $.ajax({
        url: "/hospital/listHospitals.do",
        type: "POST",
        async: false,
        success: function(data) {
            var hospitals = data.hospitals;
            var hospitalName = document.getElementById("hospitalName_query");
            for(var i=0; i<hospitals.length; i++) {
                hospitalName.options.add(new Option(hospitals[i].name, hospitals[i].name));
            }
            //formRender();
        },
        error: function(er) {
            console.log(er);
        }
    });
    var year = document.getElementById("year_id");
    for(var i = 2017; i <= parseInt(new Date().getFullYear().toString()); i++) {
        year.options.add(new Option(i, i));
    }
    selectConfirm();
}
generateSelect();

