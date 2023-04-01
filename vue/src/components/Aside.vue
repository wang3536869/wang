<template>
  <div>
    <el-menu
        :default-active="path"
        style="width: 200px;min-height: calc(100vh - 50px)"
        router
        class="el-menu-vertical-demo">
      <el-submenu index="1" v-if="user.role===1">
        <template slot="title">
          <i class="el-icon-s-tools"></i>
          <span>系统</span>
        </template>
        <el-menu-item index="user" class="el-icon-user-solid">用户管理</el-menu-item>
      </el-submenu>
      <el-menu-item index="book" class="el-icon-s-management">书籍管理</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Aside",
  data() {
    return {
      user: {},
      path: this.$route.path
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
    request.get("http://localhost:8001/user/" + this.user.id).then(res => {
      if (res.code === '0') {
        this.user = res.data
      }
    })
  }
}
</script>

<style scoped>

</style>