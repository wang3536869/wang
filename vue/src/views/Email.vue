<template>
  <div class="homepage-hero-module">
    <div class="video-container">
      <div :style="fixStyle" class="filter">
        <div style="width: 400px; margin: 100px auto">
          <div style="font-size: 30px; text-align: center; padding: 30px 0; color: #333">欢迎登录</div>
          <el-form ref="loginForm" :model="EmailForm" size="normal">
            <el-form-item prop="email">
              <el-input prefix-icon="el-icon-user-solid" v-model="EmailForm.email" placeholder="请输入邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="validCode">
              <div style="display: flex">
                <el-input prefix-icon="el-icon-key" v-model="EmailForm.code" style="width: 50%;"
                          placeholder="请输入验证码"></el-input>
                <el-button @click="send" :disabled="canClick" style="margin-left: 30px">{{ verifyCode }}</el-button>
              </div>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 100%" type="primary" @click="login('loginForm')">登 录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import request from "@/utils/request";

export default {
  name: "Email",
  data() {
    return {
      canClick: false,
      verifyCode: '发送验证码',
      totalTime: 60,
      fixStyle: '',
      EmailForm: {
        email: '',
        code: ''
      }
    }
  },
  methods: {
    send() {
      if (this.canClick)
        return
      console.log(1)
      request.post("http://localhost:8001/email/send", this.EmailForm).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "发送成功"
          })
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      })
      this.canClick = true;
      this.verifyCode = this.totalTime + 's后重新发送'
      let clock = window.setInterval(() => {
        this.totalTime--
        this.verifyCode = this.totalTime + 's后重新发送'
        if (this.totalTime < 0) {
          window.clearInterval(clock)
          this.verifyCode = '重新发送验证码'
          this.totalTime = 60
          this.canClick = false
        }
      }, 1000)
    },
    login() {
      request.post("http://localhost:8001/email/login", this.EmailForm).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "登陆成功"
          })
          sessionStorage.setItem("user", JSON.stringify(res.data))
          console.log(res)
          this.$router.push("/user")
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>