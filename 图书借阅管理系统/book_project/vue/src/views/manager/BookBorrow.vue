<template>
  <div>
    <div style="margin-bottom: 10px">
      <el-input style="width: 260px" placeholder="请输入..." v-model="name"></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load">搜 索</el-button>
      <el-button type="info" @click="reset">重 置</el-button>
    </div>

    <div style="margin: 10px 0" v-if="user.role === 'ADMIN'">
      <el-popconfirm
        confirm-button-text='确 定'
        cancel-button-text='我再想想'
        icon="el-icon-info"
        icon-color="red"
        title="您确定批量删除这些数据吗？"
        @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除</el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" stripe :header-cell-style="{background:'#f4f3f9',color:'#606266'}"  @selection-change="handleSelectionChange">
      <el-table-column v-if="user.role === 'ADMIN'" type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
      <el-table-column align="center" prop="bookId" label="图书封面">
        <template v-slot="scope">
          <el-image style="width: 50px; height: 50px" :src="scope.row.bookImg" :preview-src-list="[scope.row.bookImg]"></el-image>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="bookName" label="图书名称" show-overflow-tooltip></el-table-column>
<!--      <el-table-column align="center" prop="userId" label="借书人id"></el-table-column>-->
      <el-table-column align="center" prop="username" label="借书人"></el-table-column>
      <el-table-column align="center" prop="num" label="借书数量"></el-table-column>
      <el-table-column align="center" prop="date" label="借书日期"></el-table-column>
      <el-table-column align="center" prop="backdate" label="审核状态">
        <template v-slot="scope">
          <el-tag type="info" v-if="scope.row.status === '待审核'">待审核</el-tag>
          <el-tag type="primary" v-if="scope.row.status === '通过'">通过</el-tag>
          <el-tag type="danger" v-if="scope.row.status === '拒绝'">拒绝</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="reason" label="审核意见"></el-table-column>
      <el-table-column align="center" prop="back" label="是否归还"></el-table-column>
      <el-table-column align="center" prop="backdate" label="还书日期"></el-table-column>
      <el-table-column align="center" label="审核" width="100" v-if="user.role === 'ADMIN'">
        <template v-slot="scope">
          <el-button type="primary" @click="handleChangeStatus(scope.row)" v-if="scope.row.status === '待审核'">审核</el-button>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="180" align="center" v-if="user.role === 'USER'">
        <template slot-scope="scope">
          <el-button type="primary" @click="returnBook(scope.row)" size="mini" v-if="scope.row.status === '通过' && scope.row.back === '否'">归还</el-button>
          <el-button type="primary" @click="handleBorrow(scope.row)" size="mini" v-if="scope.row.status === '通过' && scope.row.back === '否'">续借</el-button>
          <el-popconfirm
              v-if="user.role === 'ADMIN'"
              style="margin-left: 10px"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference" size="mini">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          style="padding: 0"
          background
          layout="total, prev, pager, next"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="信息" :visible.sync="dialogFormVisible" width="30%" :close-on-click-modal="false">
      <el-form label-width="100px" style="padding-right: 40px" :model="form" :rules="rules" ref="ruleForm">
        <el-form-item prop="bookId" label="图书id">
          <el-input v-model="form.bookId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="userId" label="借书人id">
          <el-input v-model="form.userId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="username" label="借书人">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="num" label="借书数量">
          <el-input v-model="form.num" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="date" label="借书数量">
          <el-date-picker v-model="form.date" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item prop="back" label="是否归还">
          <el-input v-model="form.back" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="backdate" label="还书日期">
          <el-date-picker v-model="form.backdate" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="审核" :visible.sync="dialogFormVisible1" width="30%" :close-on-click-modal="false">
      <el-form label-width="100px" style="padding-right: 40px" :model="form">
        <el-form-item prop="status" label="审核结果">
          <el-radio-group v-model="form.status">
            <el-radio label="通过"></el-radio>
            <el-radio label="拒绝"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="status" label="审核意见">
          <el-input type="textarea" v-model="form.reason" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="changeStatus">确 定</el-button>
        </div>
    </el-dialog>

    <el-dialog title="续借" :visible.sync="dialogFormVisible2" width="30%" :close-on-click-modal="false">
      <el-form label-width="100px" style="padding-right: 40px" :model="form">
        <el-form-item prop="backdate" label="归还日期">
          <el-date-picker style="width: 100%" v-model="form.newBackdate" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="continueBorrow">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "BookBorrow",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      name: "",
      form: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      multipleSelection: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      rules: {
        name: [
          { required: true, message: '请输入必填项', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    continueBorrow() {
      if (!this.form.newBackdate || new Date(this.form.newBackdate).getTime() <= new Date(this.form.backdate).getTime()) {
        this.$message.error('借书日期必须在前段借书日期之后')
        return
      }
      if (!this.form.backdate || new Date(this.form.backdate).getTime() <= new Date().getTime()) {
        this.$message.error('借书日期必须在今天之后')
        return
      }
      this.form.backdate = this.form.newBackdate
      this.form.status = '待审核'
      this.$request({
        method: 'PUT',
        url: "/bookBorrow",
        data: this.form
      }).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
          this.dialogFormVisible2 = false
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleBorrow(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible2 = true
    },
    handleChangeStatus(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.form.status = '通过'
      this.dialogFormVisible1 = true
    },
    changeStatus() {
      this.$request({
        method: 'PUT',
        url: "/bookBorrow",
        data: this.form
      }).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
          this.dialogFormVisible1 = false
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    returnBook(row) {
      this.$request.put("/bookBorrow/returnBook", row).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    load() {
      this.$request.get("/bookBorrow/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: 10,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data?.records
        this.total = res.data?.total
      })
    },
    save() {
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
           this.$request({
             method: this.form.id ? 'PUT' : 'POST',
             url: "/bookBorrow",
             data: this.form
           }).then(res => {
              if (res.code === '200') {
                this.$message.success("操作成功")
                this.dialogFormVisible = false
                this.load()
              } else {
                this.$message.error(res.msg)
              }
            })
          }
        })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
      this.$nextTick(() => {
        if(this.$refs.img) {
           this.$refs.img.clearFiles();
         }
         if(this.$refs.file) {
          this.$refs.file.clearFiles();
         }
      })
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
       this.$nextTick(() => {
         if(this.$refs.img) {
           this.$refs.img.clearFiles();
         }
         if(this.$refs.file) {
          this.$refs.file.clearFiles();
         }
       })
    },
    del(id) {
      this.$request.delete("/bookBorrow/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      if (!this.multipleSelection.length) {
        this.$message.error("请选择需要删除的数据")
        return
      }
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.$request.post("/bookBorrow/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    reset() {
      this.name = ""
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleFileUploadSuccess(res) {
      this.form.file = res
    },
    handleImgUploadSuccess(res) {
      this.form.img = res
    },
    download(url) {
      window.open(url)
    },
    exp() {
      window.open($baseUrl + "/bookBorrow/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }
  }
}
</script>


<style scope>

</style>
