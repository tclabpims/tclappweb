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

var LODOP;
function f_print() {
    //CheckIsInstall();
    //var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
    LODOP = getLodop();
    LODOP.PRINT_INIT("采集点销售统计");
    LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
    LODOP.SET_PRINT_STYLE("FontSize", 9);
    LODOP.SET_PRINT_STYLE("Bold", 1);
    var strBodyStyle="<style>table,th, td{ border: 1 solid #000000;border-collapse:collapse;text-align: center }</style>";
    var strHtml = strBodyStyle + "<body>" + document.getElementById("table_stat").innerHTML + "</body>";
    LODOP.ADD_PRINT_HTM(50,30,700,950,strHtml);
    LODOP.PREVIEW();
}

