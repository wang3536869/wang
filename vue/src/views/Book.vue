<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
    </div>
    <!--    搜素区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%"></el-input>
      <el-button type="primary" icon="el-icon-search" style="margin-left: 5px" @click="load">搜索</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="ID"
          sortable>
      </el-table-column>
      <el-table-column
          prop="name"
          label="书名">
      </el-table-column>
      <el-table-column
          prop="author"
          label="作者">
      </el-table-column>
      <el-table-column
          prop="price"
          label="价格">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="出版时间">
      </el-table-column>
      <el-table-column
          label="封面照片">
        <template slot-scope="scope">
          <el-image
              style="width: 50px; height: 50px"
              :src="scope.row.cover"
              :preview-src-list="[scope.row.cover]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleEdit(scope.row)">编辑
          </el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <el-button slot="reference" size="mini" type="danger" style="margin-left: 5px">删除
            </el-button>
          </el-popconfirm>
          <!--          <el-button-->
          <!--              size="mini"-->
          <!--              type="danger"-->
          <!--              @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[2, 4, 6, 8]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
      <el-dialog
          :title="title"
          :visible.sync="dialogVisible"
          width="30%">
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="书名">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="form.price" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="form.author" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="出版时间">
            <el-date-picker
                v-model="form.createTime"
                value-format="yyyy-MM-dd"
                type="date"
                clearable
                placeholder="选择日期"
                style="width: 80%">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="封面">
            <el-upload ref="upload"
                       action="http://localhost:8001/files/upload" :on-success="filesUploadSuccess">
              <el-button type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import request from "@/utils/request";

export default {
  name: 'book',
  data() {
    return {
      search: '',
      currentPage: 1,
      pageSize: 2,
      dialogVisible: false,
      form: {},
      total: 0,
      title: '',
      value: '',
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    filesUploadSuccess(res) {
      console.log(res)
      this.form.cover = res.data
    },
    load() {
      request.get("http://localhost:8001/book", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
        // pageNum:this.currentPage,
        // pageSize:this.pageSize,
        // search:this.search
      }).then(res => {
        // console.log(res)
        this.tableData = res.data.records;
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true
      // console.log(1)
      this.form = {}
      this.$nextTick(() => {
        this.$refs['upload'].clearFiles()
      })
      // this.$refs['upload'].clearFiles()
    },
    save() {
      if (this.form.id) {
        request.put("http://localhost:8001/book", this.form).then(res => {//更新
          // console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
            this.load()
            this.dialogVisible = false
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
      } else {
        request.post("http://localhost:8001/book", this.form).then(res => {//增加
          // console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
            this.load()
            this.dialogVisible = false
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      //
      this.$nextTick(() => {
        this.$refs['upload'].clearFiles()
      })
      // this.$refs['upload'].clearFiles()
    },
    handleDelete(id) {
      // console.log(id)
      request.delete("http://localhost:8001/book/" + id).then(res => {  //删除
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
          this.load()
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    },
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.pageSize = val
      this.load()
    },
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`);
      this.currentPage = val
      this.load()
    }
  }
}
</script>
