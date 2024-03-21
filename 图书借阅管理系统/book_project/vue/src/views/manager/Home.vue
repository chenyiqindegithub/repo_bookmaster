<template>
  <div>
    <div>
      您好，<strong style="color: orange">{{ user.name }}</strong> 希望您今天过得开心！
    </div>

    <el-row :gutter="10" style="margin: 20px 0">
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">活跃用户</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ nums.user }}
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">图书数量</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ nums.book }}
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">今日借阅</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ nums.borrow }}
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="box">
          <div style="color: #666">剩余库存</div>
          <div style="margin-top: 10px; font-size: 20px">
            {{ nums.store }}
          </div>
        </div>
      </el-col>
    </el-row >

    <div style="display: flex; margin: 20px 0">
      <el-card style="width: 50%; margin-right: 10px">
        <h2 style="margin-bottom: 20px">公告列表</h2>
        <div >
          <el-timeline  reverse slot="reference">
            <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time">
              <el-popover
                  placement="right"
                  width="200"
                  trigger="hover"
                  :content="item.content">
                <span slot="reference">{{ item.name }}</span>
              </el-popover>
            </el-timeline-item>
          </el-timeline>
        </div>
      </el-card>
      <el-card style="width: 50%;">
        <div id="pie" style="width: 100%; height: 400px"></div>
      </el-card>
    </div>


  </div>
</template>

<script>
import * as echarts from 'echarts';
const pieOption  = {
  title: {
    text: '图书借阅统计',
    subtext: '饼图',
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
      name: '借阅数量',
      type: 'pie',
      radius: '50%',
      data: [],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}

export default {
  name: 'Home',
  data() {
    return {
      notices: [],
      nums: {},
      like: true,
      value1: 4154.564,
      value2: 2222,
      title: '今年的增长',
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  mounted() {
    const pieDom = document.getElementById('pie')
    const pieChart = echarts.init(pieDom)


    this.$request.get("/notice").then(res => {
      this.notices = res.data?.splice(0, 5)
    })

    this.$request.get("/nums").then(res => {
      this.nums = res.data
    })

    this.$request.get("/pie").then(res => {
      res.data?.forEach(item => {
        if (item.name.length > 5) {
          item.name = item.name.substring(0, 5) + '...'
        }
      })
      pieOption.series[0].data = res.data
      pieChart.setOption(pieOption)
    })
  },
  methods: {}
}
</script>

<style scoped>
.box {
  width: 100%;
  box-shadow: 0 0 10px -2px rgba(0, 0, 0, .2);
  border-radius: 5px;
  padding: 20px;
  text-align: center;
}
</style>
