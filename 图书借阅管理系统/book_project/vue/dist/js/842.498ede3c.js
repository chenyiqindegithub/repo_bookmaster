"use strict";(self["webpackChunkmanager_vue"]=self["webpackChunkmanager_vue"]||[]).push([[842],{4842:function(e,t,l){l.r(t),l.d(t,{default:function(){return c}});var r=function(){var e=this,t=e._self._c;return t("div",[t("div",{staticStyle:{"margin-bottom":"10px"}},[t("el-input",{staticStyle:{width:"260px"},attrs:{placeholder:"请输入..."},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),t("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"primary"},on:{click:e.load}},[e._v("搜 索")]),t("el-button",{attrs:{type:"info"},on:{click:e.reset}},[e._v("重 置")])],1),t("div",{staticStyle:{margin:"10px 0"}},["ADMIN"===e.user.role?t("el-button",{attrs:{type:"primary"},on:{click:e.handleAdd}},[e._v("新 增")]):e._e(),"ADMIN"===e.user.role?t("el-popconfirm",{staticStyle:{"margin-left":"10px"},attrs:{"confirm-button-text":"确 定","cancel-button-text":"我再想想",icon:"el-icon-info","icon-color":"red",title:"您确定批量删除这些数据吗？"},on:{confirm:e.delBatch}},[t("el-button",{attrs:{slot:"reference",type:"danger"},slot:"reference"},[e._v("批量删除")])],1):e._e()],1),t("el-table",{attrs:{data:e.tableData,stripe:"","header-cell-style":{background:"#f4f3f9",color:"#606266"}},on:{"selection-change":e.handleSelectionChange}},["ADMIN"===e.user.role?t("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}):e._e(),t("el-table-column",{attrs:{prop:"id",label:"序号",width:"80",align:"center",sortable:""}}),t("el-table-column",{attrs:{align:"center",prop:"username",label:"用户名"}}),t("el-table-column",{attrs:{align:"center",prop:"name",label:"名称"}}),t("el-table-column",{attrs:{align:"center",prop:"sex",label:"性别"}}),t("el-table-column",{attrs:{align:"center",prop:"birth",label:"生日"}}),t("el-table-column",{attrs:{align:"center",prop:"phone",label:"手机"}}),t("el-table-column",{attrs:{align:"center",prop:"address",label:"地址"}}),t("el-table-column",{attrs:{align:"center",prop:"account",label:"积分"}}),"ADMIN"===e.user.role?t("el-table-column",{attrs:{label:"禁用"},scopedSlots:e._u([{key:"default",fn:function(l){return[t("el-switch",{attrs:{"active-color":"red"},on:{change:function(t){return e.disableUser(l.row)}},model:{value:l.row.disable,callback:function(t){e.$set(l.row,"disable",t)},expression:"scope.row.disable"}})]}}],null,!1,3540944188)}):e._e(),t("el-table-column",{attrs:{label:"操作",width:"240",align:"center"},scopedSlots:e._u([{key:"default",fn:function(l){return["ADMIN"===e.user.role?t("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.handleEdit(l.row)}}},[e._v("编辑")]):e._e(),"ADMIN"===e.user.role?t("el-button",{attrs:{type:"warning",size:"mini"},on:{click:function(t){return e.resetPass(l.row)}}},[e._v("重置密码")]):e._e(),"ADMIN"===e.user.role?t("el-popconfirm",{staticStyle:{"margin-left":"10px"},attrs:{"confirm-button-text":"确定","cancel-button-text":"我再想想",icon:"el-icon-info","icon-color":"red",title:"您确定删除吗？"},on:{confirm:function(t){return e.del(l.row.id)}}},[t("el-button",{attrs:{slot:"reference",type:"danger",size:"mini"},slot:"reference"},[e._v("删除")])],1):e._e()]}}])})],1),t("div",{staticStyle:{margin:"10px 0"}},[t("el-pagination",{staticStyle:{padding:"0"},attrs:{background:"",layout:"total, prev, pager, next","current-page":e.pageNum,total:e.total},on:{"current-change":e.handleCurrentChange}})],1),t("el-dialog",{attrs:{title:"信息",visible:e.dialogFormVisible,width:"30%","close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[t("el-form",{ref:"ruleForm",staticStyle:{"padding-right":"40px"},attrs:{"label-width":"100px",model:e.form,rules:e.rules}},[t("el-form-item",{attrs:{prop:"username",label:"用户名"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),t("el-form-item",{attrs:{prop:"name",label:"名称"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),t("el-form-item",{attrs:{prop:"sex",label:"性别"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.sex,callback:function(t){e.$set(e.form,"sex",t)},expression:"form.sex"}})],1),t("el-form-item",{attrs:{prop:"birth",label:"生日"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.birth,callback:function(t){e.$set(e.form,"birth",t)},expression:"form.birth"}})],1),t("el-form-item",{attrs:{prop:"phone",label:"手机"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.phone,callback:function(t){e.$set(e.form,"phone",t)},expression:"form.phone"}})],1),t("el-form-item",{attrs:{prop:"address",label:"地址"}},[t("el-input",{attrs:{type:"textarea",autocomplete:"off"},model:{value:e.form.address,callback:function(t){e.$set(e.form,"address",t)},expression:"form.address"}})],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.save}},[e._v("确 定")])],1)],1)],1)},s=[],a={name:"User",data(){return{tableData:[],total:0,pageNum:1,name:"",form:{},dialogFormVisible:!1,multipleSelection:[],user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},rules:{username:[{required:!0,message:"请输入必填项",trigger:"blur"}],name:[{required:!0,message:"请输入必填项",trigger:"blur"}]}}},created(){this.load()},methods:{load(){this.$request.get("/user/page",{params:{pageNum:this.pageNum,pageSize:10,name:this.name}}).then((e=>{this.tableData=e.data?.records,this.total=e.data?.total}))},save(){this.$refs["ruleForm"].validate((e=>{e&&(this.form.password||(this.form.password="123"),this.$request({method:this.form.id?"PUT":"POST",url:"/user",data:this.form}).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.dialogFormVisible=!1,this.load()):this.$message.error(e.msg)})))}))},handleAdd(){this.dialogFormVisible=!0,this.form={},this.$nextTick((()=>{this.$refs.img&&this.$refs.img.clearFiles(),this.$refs.file&&this.$refs.file.clearFiles()}))},handleEdit(e){this.form=JSON.parse(JSON.stringify(e)),this.dialogFormVisible=!0,this.$nextTick((()=>{this.$refs.img&&this.$refs.img.clearFiles(),this.$refs.file&&this.$refs.file.clearFiles()}))},del(e){this.$request.delete("/user/"+e).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.load()):this.$message.error(e.msg)}))},handleSelectionChange(e){console.log(e),this.multipleSelection=e},delBatch(){if(!this.multipleSelection.length)return void this.$message.error("请选择需要删除的数据");let e=this.multipleSelection.map((e=>e.id));this.$request.post("/user/del/batch",e).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.load()):this.$message.error(e.msg)}))},reset(){this.name="",this.load()},handleCurrentChange(e){this.pageNum=e,this.load()},resetPass(e){e.password="123",this.updateUser(e)},disableUser(e){let t=Object.assign({},e);t.disable=!!t.disable,this.updateUser(t)},updateUser(e){this.$request({method:"PUT",url:"/user",data:e}).then((e=>{"200"===e.code?(this.$message.success("操作成功"),this.load()):this.$message.error(e.msg)}))},handleFileUploadSuccess(e){this.form.file=e},handleImgUploadSuccess(e){this.form.img=e},download(e){window.open(e)},exp(){window.open($baseUrl+"/user/export")},handleExcelImportSuccess(){this.$message.success("导入成功"),this.load()}}},o=a,i=l(1001),n=(0,i.Z)(o,r,s,!1,null,null,null),c=n.exports}}]);
//# sourceMappingURL=842.498ede3c.js.map