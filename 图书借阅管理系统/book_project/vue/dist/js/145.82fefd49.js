"use strict";(self["webpackChunkmanager_vue"]=self["webpackChunkmanager_vue"]||[]).push([[145],{9145:function(e,s,r){r.r(s),r.d(s,{default:function(){return n}});var t=function(){var e=this,s=e._self._c;return s("div",[s("el-card",{staticStyle:{width:"500px",margin:"10px auto"}},[s("el-form",{ref:"ruleForm",staticStyle:{"padding-right":"30px"},attrs:{"label-width":"120px","label-position":"right",model:e.form,rules:e.rules}},[s("el-form-item",{attrs:{prop:"password",label:"原密码"}},[s("el-input",{attrs:{autocomplete:"off","show-password":""},model:{value:e.form.password,callback:function(s){e.$set(e.form,"password",s)},expression:"form.password"}})],1),s("el-form-item",{attrs:{prop:"newPass",label:"新密码"}},[s("el-input",{attrs:{autocomplete:"off","show-password":""},model:{value:e.form.newPass,callback:function(s){e.$set(e.form,"newPass",s)},expression:"form.newPass"}})],1),s("el-form-item",{attrs:{prop:"newPass",label:"确认新新密码"}},[s("el-input",{attrs:{autocomplete:"off","show-password":""},model:{value:e.form.confirmNewPass,callback:function(s){e.$set(e.form,"confirmNewPass",s)},expression:"form.confirmNewPass"}})],1)],1),s("div",{staticStyle:{"text-align":"center"}},[s("el-button",{attrs:{type:"primary"},on:{click:e.changePassword}},[e._v("保存")])],1)],1)],1)},a=[],o=(r(7658),{name:"Password",data(){return{user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},form:{},rules:{password:[{required:!0,message:"请输入必填项",trigger:"blur"}],newPass:[{required:!0,message:"请输入必填项",trigger:"blur"}],confirmNewPass:[{required:!0,message:"请输入必填项",trigger:"blur"}],user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}}}},created(){},methods:{changePassword(){this.form.password!==this.form.newPass?this.form.newPass===this.form.confirmNewPass?(this.form.id=this.user.id,this.form.password=this.form.newPass,this.$request.put("/user",this.form).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.$router.push("/login")):this.$message.error(e.msg)}))):this.$message.error("确认的新密码跟新密码不同"):this.$message.error("新密码不能跟老密码相同")}}}),l=o,i=r(1001),m=(0,i.Z)(l,t,a,!1,null,"3e2c5990",null),n=m.exports}}]);
//# sourceMappingURL=145.82fefd49.js.map