<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #cccccc;display: flex">
    <div style="width: 200px;padding-left: 30px;color: dodgerblue">后台管理</div>
    <div style="flex: 1;"></div>
    <div style="width: 100px;position: relative">
      <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link">
<!--    {{ user.nickName }}-->
    {{ this.user.nickName }}
    <i class="el-icon-arrow-down el-icon--right"></i>
  </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="a">个人信息</el-dropdown-item>
          <el-dropdown-item command="b">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Header",
  data() {
    return {
      user: {},
    }
  },
  mounted() {
    this.user = JSON.parse(sessionStorage.getItem("user"))
    console.log(this.user.icon)
  },
  methods: {
    handleCommand(command) {
      if (command === 'a') {
        this.$router.push("/person")
      } else if (command === 'b') {
        this.$router.push("/login")
        sessionStorage.removeItem("user")
        request.post("http://localhost:8001/user/loginout").then(res=>{
          console.log(res.msg)
        })
      }
    }
  }
}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}
</style>