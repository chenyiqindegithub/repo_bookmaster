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
      <el-table-column align="center" prop="bookId" label="商品图片">
        <template v-slot="scope">
          <el-image style="width: 50px; height: 50px" :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]"></el-image>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="goodsName" label="商品名称" show-overflow-tooltip></el-table-column>
<!--      <el-table-column align="center" prop="userId" label="借书人id"></el-table-column>-->
      <el-table-column align="center" prop="username" label="兑换人"></el-table-column>
      <el-table-column align="center" prop="num" label="兑换数量"></el-table-column>
      <el-table-column align="center" prop="date" label="兑换日期"></el-table-column>
      <el-table-column align="center" prop="account" label="扣除积分"></el-table-column>

      <el-table-column label="操作" width="180" align="center">
        <template slot-scope="scope">
          <el-popconfirm
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
    load() {
      this.$request.get("/goodsExchange/page", {
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
             url: "/goodsExchange",
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
      this.$request.delete("/goodsExchange/" + id).then(res => {
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
      this.$request.post("/goodsExchange/del/batch", ids).then(res => {
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
      window.open($baseUrl + "/goodsExchange/export")
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
