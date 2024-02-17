<template>

    <div>
        <el-row :span="10" style="margin-bottom: 60px">
            <el-col :span="6">
                <el-card style="color: #409EFF">

                    <div><i class="el-icon-user"></i>用户总数</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        100
                    </div>

                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #07e065">
                    <div><i class="el-icon-s-home"></i>门店数额</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        100
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #27c9ff">
                    <div><i class="el-icon-shopping-cart-2"></i>销售总量
                    </div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        100￥
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #ff4040">
                    <div><i class="el-icon-bank-card"></i>收益数额</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        100￥
                    </div>
                </el-card>
            </el-col>

        </el-row>


        <el-col :span="12">
            <div id="main" style="width: 500px;height: 450px"></div>
        </el-col>
        <el-col :span="12">
            <div id="pie" style="width: 500px;height: 450px"></div>
        </el-col>
    </div>
</template>

<script>
import * as echarts from 'echarts';

export default {

    data() {
        return {}
    },
    mounted() {//页面元素渲染之后触发

        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        option = {

            title: {
                text: '各季度数量统计',
                subtext: '趋势图',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            xAxis: {
                type: 'category',
                data: ["第一季度", "第二季度", "第三季度", "第四季度"]
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:"星巴克",
                    data: [],
                    type: 'line'
                },
                {
                    name:"星巴克",
                    data: [],
                    type: 'bar'
                },
                {
                    name:"瑞信",
                    data: [],
                    type: 'line'
                },
                {
                    name:"瑞信",
                    data: [],
                    type: 'bar'
                }
            ]
        };


        this.request.get("/echarts/members").then(res => {
            // option.xAxis.data = res.data.x
            option.series[0].data = res.data
            option.series[1].data = res.data

            option.series[2].data =[5,6,7,8]
            option.series[3].data =[5,6,7,8]
            myChart.setOption(option);

            Pieoption.series[0].data = [
                {name: "第一季度", value: res.data[0]},
                {name: "第二季度", value: res.data[1]},
                {name: "第三季度", value: res.data[2]},
                {name: "第四季度", value: res.data[3]},
            ]
            myPieChart.setOption(Pieoption);
        })
//饼图
        var PieDom = document.getElementById('pie');
        var myPieChart = echarts.init(PieDom);
        var Pieoption;

        Pieoption = {
            title: {
                text: '各季度数量统计',
                subtext: '趋势图',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left'
            },
            series: [
                {
                    name:'星巴克',
                    type: 'pie',
                    radius: '50%',
                    center:['25%','50%'],
                    label: {
                        normal: {
                            show: true,
                            position: 'inner',
                            textStyle: {
                                fontWeight: 300,
                                fontsize: 16,
                                color: "#fff"
                            },

                            formatter: '{d}%'
                        }
                    },

                    data: [],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                },
                {
                    name:'瑞星咖啡',
                    type: 'pie',
                    radius: '50%',

                    center:['75%','50%'],
                    label: {
                        normal: {
                            show: true,
                            position: 'inner',
                            textStyle: {
                                fontWeight: 300,
                                fontsize: 16,
                                color: "#fff"
                            },

                            formatter: '{d}%'
                        }
                    },

                    data: [
                        {name: "第一季度", value:5},
                        {name: "第二季度", value:6},
                        {name: "第三季度", value:7},
                        {name: "第四季度", value: 8},
                    ],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };


    }


}


</script>


<style scoped>

</style>