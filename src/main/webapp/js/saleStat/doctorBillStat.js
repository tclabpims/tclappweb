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
    var year = document.getElementById("year_id");
    for(var i = 2017; i <= parseInt(new Date().getFullYear().toString()); i++) {
        year.options.add(new Option(i, i));
    }
    selectConfirm();
}
generateSelect();

