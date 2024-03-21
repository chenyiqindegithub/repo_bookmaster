<template>
  <div>
    <el-card style="width: 500px; margin: 10px auto">
      <el-form label-width="120px" style="padding-right: 30px" label-position="right" :model="form" :rules="rules" ref="ruleForm">
        <el-form-item prop="password" label="原密码">
          <el-input v-model="form.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item prop="newPass" label="新密码">
          <el-input v-model="form.newPass" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item prop="newPass" label="确认新新密码">
          <el-input v-model="form.confirmNewPass" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center"><el-button type="primary" @click="changePassword">保存</el-button></div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Password",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      form: {},
      rules: {
        password: [
          { required: true, message: '请输入必填项', trigger: 'blur'},
        ],
        newPass: [
          { required: true, message: '请输入必填项', trigger: 'blur'},
        ],
        confirmNewPass: [
          { required: true, message: '请输入必填项', trigger: 'blur'}
        ],
        user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      }
    }
  },
  created() {

  },
  methods: {
    changePassword() {
      if (this.form.password === this.form.newPass) {
        this.$message.error("新密码不能跟老密码相同")
        return
      }
      if (this.form.newPass !== this.form.confirmNewPass) {
        this.$message.error("确认的新密码跟新密码不同")
        return
      }
      this.form.id = this.user.id
      this.form.password = this.form.newPass
      this.$request.put('/user', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
          this.$router.push('/login')
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>