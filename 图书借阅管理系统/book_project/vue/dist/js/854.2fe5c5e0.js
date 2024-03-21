"use strict";(self["webpackChunkmanager_vue"]=self["webpackChunkmanager_vue"]||[]).push([[854],{854:function(t,e,s){s.r(e),s.d(e,{default:function(){return d}});s(7658);var i=function(){var t=this,e=t._self._c;return e("div",[e("div",{staticStyle:{height:"40px","line-height":"40px","background-color":"#05a081",display:"flex"}},[e("div",{staticStyle:{width:"200px","padding-left":"20px"}},[e("i",{class:t.icon,staticStyle:{"font-size":"20px",color:"#eee",position:"relative",top:"2px",left:"2px",cursor:"pointer"},on:{click:t.handleCollapse}}),e("span",{staticStyle:{"font-size":"16px",color:"#eee","margin-left":"10px","font-weight":"bold"}},[t._v("图书管理系统")])]),e("div",{staticStyle:{flex:"1"}},[e("span",{staticStyle:{color:"white","font-size":"14px","margin-left":"20px"}},[t._v("北京时间： "+t._s(t.time))])]),e("div",{staticStyle:{width:"400px",display:"flex","justify-content":"flex-end","padding-right":"30px"}},[e("el-dropdown",{staticStyle:{"text-align":"right"},attrs:{placement:"bottom",size:"small"}},[e("el-avatar",{staticStyle:{"margin-top":"5px"},attrs:{size:30,src:t.user.avatar}}),e("span",{staticStyle:{color:"white",position:"relative",top:"-10px","font-size":"14px","margin-left":"5px"}},[t._v(t._s(t.user.name))]),e("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[e("el-dropdown-item",[e("div",{on:{click:function(e){return t.$router.push("/person")}}},[t._v("个人信息")])]),e("el-dropdown-item",[e("div",{on:{click:function(e){return t.$router.push("/password")}}},[t._v("修改密码")])]),e("el-dropdown-item",[e("div",{on:{click:t.logout}},[t._v("退出登录")])])],1)],1)],1)]),e("div",{staticStyle:{display:"flex","min-height":"calc(100vh - 40px)"}},[e("div",{style:{width:t.menuWidth}},[e("el-menu",{staticStyle:{"min-height":"100%","overflow-x":"hidden"},attrs:{"background-color":"#eaf4fc","default-active":t.$route.path,collapse:t.collapse,"collapse-transition":!1,router:""}},[e("el-menu-item",{attrs:{index:"/home"}},[e("i",{staticClass:"el-icon-s-home"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("主页")])]),e("el-menu-item",{attrs:{index:"/book"}},[e("i",{staticClass:"el-icon-document"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("图书信息")])]),e("el-menu-item",{attrs:{index:"/bookBorrow"}},[e("i",{staticClass:"el-icon-document-add"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("我的借阅")])]),e("el-menu-item",{attrs:{index:"/goods"}},[e("i",{staticClass:"el-icon-goods"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("积分商城")])]),e("el-menu-item",{attrs:{index:"/goodsExchange"}},[e("i",{staticClass:"el-icon-goods"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("兑换记录")])]),e("el-menu-item",{attrs:{index:"/person"}},[e("i",{staticClass:"el-icon-user"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("个人信息")])]),"ADMIN"===t.user.role?e("el-menu-item",{attrs:{index:"/notice"}},[e("i",{staticClass:"el-icon-news"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("公告管理")])]):t._e(),"ADMIN"===t.user.role?e("el-menu-item",{attrs:{index:"/user"}},[e("i",{staticClass:"el-icon-user"}),e("span",{attrs:{slot:"title"},slot:"title"},[t._v("用户管理")])]):t._e()],1)],1),e("div",{staticStyle:{width:"0",flex:"1",padding:"10px"}},[e("router-view",{on:{refreshUser:t.getUser}})],1)])])},l=[],o={name:"Manager",data(){return{user:{},time:n(),collapse:!1,menuWidth:"200px",icon:"el-icon-s-fold"}},created(){this.getUser(),setInterval((()=>{this.time=n()}),1e3)},methods:{handleCollapse(){this.collapse=!this.collapse,this.menuWidth="64px"===this.menuWidth?"200px":"64px",this.icon="el-icon-s-unfold"===this.icon?"el-icon-s-fold":"el-icon-s-unfold"},getUser(){let t=localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")).id:0;t?this.$request.get("/user/"+t).then((t=>{this.user=t.data})):this.$router.push("/login")},logout(){localStorage.removeItem("user"),this.$router.push("/login")}}};function n(){const t=new Date,e=t.getFullYear();let s=t.getMonth()+1;const i=t.getDate();let l=t.getHours(),o=t.getMinutes(),n=t.getSeconds();return l<10&&(l="0"+l),o<10&&(o="0"+o),n<10&&(n="0"+n),`${e}/${s}/${i} ${l}:${o}:${n}`}var a=o,r=s(1001),c=(0,r.Z)(a,i,l,!1,null,"d3a9d370",null),d=c.exports}}]);
//# sourceMappingURL=854.2fe5c5e0.js.map