<template>
  <div style="background-color: #f2f3f5; min-height: 100vh">
<!--    header -->
    <div style="display: flex; height: 50px; line-height: 50px; box-shadow: 2px 0 6px rgba(0,21,41,.35);">
      <div style="flex: 1; display: flex; align-items: center; justify-content: flex-end; padding-right: 50px ">
        <img style="width: 30px" src="@/assets/logo1.png" alt=""> <strong style="margin-left: 5px; font-size: 18px">用户端</strong>
      </div>
      <div style="width: 70%; display: flex">
        <div class="nav-item active">首页</div>
      </div>
      <div style="flex: 1">

      </div>
    </div>

<!--    main -->
    <div style="width: 70%; margin: 10px auto; ">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "Front",
  data() {
    return {

    }
  },
  created() {

  },
  methods: {

  }
}
</script>

<style scoped>
.nav-item {
  padding: 0 15px;
  cursor: pointer;
  transition: border-bottom-width .1s;
}
.nav-item:hover {
  border-bottom: 2px solid dodgerblue;
  color: #666;
}
.active {
  color: dodgerblue;
}
</style>