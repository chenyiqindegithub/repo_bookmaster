<template>
  <div>
    <!-- header-->
    <div style="height: 40px; line-height: 40px; background-color: #05a081; display: flex">
      <div style="width: 200px; padding-left: 20px">
        <i :class="icon" style="font-size: 20px; color: #eee; position: relative; top: 2px; left: 2px; cursor: pointer;" @click="handleCollapse"></i>
        <span style="font-size: 16px; color: #eee; margin-left: 10px; font-weight: bold;">图书管理系统</span>
      </div>

      <div style="flex: 1">
        <span style="color: white; font-size: 14px; margin-left: 20px">北京时间： {{ time }}</span>
      </div>

      <div style="width: 400px;  display: flex; justify-content: flex-end; padding-right: 30px;">
        <el-dropdown placement="bottom" style="text-align: right;" size="small">
          <el-avatar :size="30" style="margin-top: 5px" :src="user.avatar"></el-avatar>
          <span style="color: white; position: relative; top: -10px; font-size: 14px; margin-left: 5px">{{ user.name }}</span>

          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><div @click="$router.push('/person')">个人信息</div></el-dropdown-item>
            <el-dropdown-item><div @click="$router.push('/password')">修改密码</div></el-dropdown-item>
            <el-dropdown-item><div @click="logout">退出登录</div></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </div>
    </div>

    <!-- bottom -->
    <div style="display: flex; min-height: calc(100vh - 40px)">
      <!-- aside -->
      <div :style="{'width': menuWidth }">
        <el-menu style="min-height: 100%; overflow-x: hidden;"
                 background-color="#eaf4fc"
                 :default-active="$route.path"
                 :collapse="collapse"
                 :collapse-transition="false"
                 router
        >
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">主页</span>
          </el-menu-item>
          <el-menu-item index="/book">
            <i class="el-icon-document"></i>
            <span slot="title">图书信息</span>
          </el-menu-item>
          <el-menu-item index="/bookBorrow">
            <i class="el-icon-document-add"></i>
            <span slot="title">我的借阅</span>
          </el-menu-item>
          <el-menu-item index="/goods">
            <i class="el-icon-goods"></i>
            <span slot="title">积分商城</span>
          </el-menu-item>
          <el-menu-item index="/goodsExchange">
            <i class="el-icon-goods"></i>
            <span slot="title">兑换记录</span>
          </el-menu-item>
          <el-menu-item index="/person">
            <i class="el-icon-user"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
          <el-menu-item index="/notice" v-if="user.role === 'ADMIN'">
            <i class="el-icon-news"></i>
            <span slot="title">公告管理</span>
          </el-menu-item>
          <el-menu-item index="/user" v-if="user.role === 'ADMIN'">
            <i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
        </el-menu>
      </div>

      <!-- main -->
      <div style="width: 0; flex: 1; padding: 10px">
        <router-view @refreshUser="getUser" />
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Manager",
  data() {
    return {
      user: {},
      time: getTime(),
      collapse: false,
      menuWidth: '200px',
      icon: 'el-icon-s-fold',
    }
  },
  created() {
    // 从后台获取最新的User数据
    this.getUser()

    setInterval(() => {
      this.time = getTime()
    }, 1000)
  },
  methods: {
    handleCollapse() {
      this.collapse = !this.collapse
      this.menuWidth = this.menuWidth === '64px' ? '200px' : '64px'
      this.icon = this.icon === 'el-icon-s-unfold' ? 'el-icon-s-fold' : 'el-icon-s-unfold'
    },
    getUser() {
      let userId = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).id : 0
      if (userId) {
        // 从后台获取User数据
        this.$request.get("/user/" + userId).then(res => {
          // 重新赋值后台的最新User数据
          this.user = res.data
        })
      } else {
        this.$router.push('/login')
      }
    },
    logout() {
      localStorage.removeItem('user')
      this.$router.push('/login')
    }
  }
}

function getTime() {
  // 获取时间戳
  const newDate = new Date();
  const year = newDate.getFullYear();
  let month = newDate.getMonth() + 1;
  const date = newDate.getDate();
  let hour = newDate.getHours();
  let minute = newDate.getMinutes();
  let second = newDate.getSeconds();
  if (hour < 10) {
    hour = "0" + hour
  }
  if (minute < 10) {
    minute = "0" + minute
  }
  if (second < 10) {
    second = "0" + second
  }
  return `${year}/${month}/${date} ${hour}:${minute}:${second}`;
}
</script>

<style scoped>
.el-submenu /deep/.el-submenu__title {
  height: 40px;
  line-height: 40px;
}
/deep/.el-menu-item {
  height: 40px;
  line-height: 40px;
}

/deep/.el-menu .el-menu-item:hover{
  outline: 0 !important;
  color: #2E95FB !important;
  background: #eaf4fc!important;
}
/deep/.el-menu .el-menu-item.is-active {
  color: #2E95FB !important;
  background: #eaf4fc!important;
}
.el-submenu /deep/.el-submenu__title:hover {
  color: #2E95FB !important;
  background: #eaf4fc!important;
}


</style>