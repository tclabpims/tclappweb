/**
 * Created by LiuQi on 2017/9/5.
 */

layui.use('laydate', function() {
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
    document.getElementById('time_range_start').onclick = function(){
        start.elem = this;
        start.format = 'YYYY-MM';
        laydate(start);
    };
    document.getElementById('time_range_end').onclick = function(){
        end.elem = this;
        end.format = 'YYYY-MM';
        laydate(end);
    }
})
