<template>
  <div class="homepage-hero-module">
    <div class="video-container">
      <div :style="fixStyle" class="filter">
        <div style="width: 400px; margin: 100px auto">
          <div style="font-size: 30px; text-align: center; padding: 30px 0; color: #333">欢迎登录</div>
          <el-form ref="loginForm" :model="loginForm" size="normal" :rules="rules">
            <el-form-item prop="username">
              <el-input prefix-icon="el-icon-user-solid" v-model="loginForm.username" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input prefix-icon="el-icon-lock" v-model="loginForm.password" show-password
                        placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item prop="validCode">
              <div style="display: flex">
                <el-input prefix-icon="el-icon-key" v-model="loginForm.validCode" style="width: 50%;" placeholder="请输入验证码"></el-input>
                <ValidCode @input="createValidCode" />
              </div>
            </el-form-item>
            <!--            <el-form-item>-->
            <!--              <div style="display: flex">-->
            <!--                <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 50%;" placeholder="请输入验证码"></el-input>-->
            <!--                <ValidCode @input="createValidCode" />-->
            <!--              </div>-->
            <!--            </el-form-item>-->
            <!--            <el-form-item>-->
            <!--              <el-radio v-model="form.role" :label="1" style="color: white">管理员</el-radio>-->
            <!--              <el-radio v-model="form.role" :label="2" style="color: white">普通用户</el-radio>-->
            <!--            </el-form-item>-->
            <el-form-item>
              <el-button style="width: 100%" type="primary" @click="login('loginForm')">登 录</el-button>
            </el-form-item>
            <el-form-item>
              <div>
              <el-button type="text" @click="$router.push('/register')">前往注册 >></el-button>
                <el-button type="text" @click="$router.push('/email')" style="margin-left: 200px">忘记密码？</el-button>
              </div>
            </el-form-item>

          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import ValidCode from "@/components/ValidCode";
export default {
  name: "Login",
  components:{
    ValidCode
  },
  data() {
    return {
      fixStyle: '',
      loginForm: {
        username: '',
        password: '',
        validCode:''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        validCode:[
          {require:true,message:'验证码不能为空',trigger:'blur'}
        ]
      }
    }
  },
  mounted() {
    sessionStorage.removeItem("info")
    // window.onresize = () => {
    //   const windowWidth = document.body.clientWidth
    //   const windowHeight = document.body.clientHeight
    //   const windowAspectRatio = windowHeight / windowWidth
    //   let videoWidth
    //   let videoHeight
    //   if (windowAspectRatio < 0.5625) {
    //     videoWidth = windowWidth
    //     videoHeight = videoWidth * 0.5625
    //     this.fixStyle = {
    //       height: windowWidth * 0.5625 + 'px',
    //       width: windowWidth + 'px',
    //       'margin-bottom': (windowHeight - videoHeight) / 2 + 'px',
    //       'margin-left': 'initial'
    //     }
    //   } else {
    //     videoHeight = windowHeight
    //     videoWidth = videoHeight / 0.5625
    //     this.fixStyle = {
    //       height: windowHeight + 'px',
    //       width: windowHeight / 0.5625 + 'px',
    //       'margin-left': (windowWidth - videoWidth) / 2 + 'px',
    //       'margin-bottom': 'initial'
    //     }
    //   }
    // }
    // window.onresize()
  },
  methods: {
    createValidCode(data){
      this.validCode=data
    },
    login(loginForm) {
      this.$refs[loginForm].validate((valid) => {
        if (valid) {
          if(!this.loginForm.validCode)
          {
            this.$message.error("请填写验证码")
            return
          }
          if(this.loginForm.validCode.toLowerCase() !== this.validCode.toLowerCase()){
            this.$message.error("验证码错误")
            return
          }
          request.post("http://localhost:8001/user/login", this.loginForm).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "登陆成功"
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))
              this.$router.push("/user")
            } else {
              this.$message({
                type: 'error',
                message: res.msg
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
.homepage-hero-module,
.video-container {
  position: relative;
  height: 100vh;
  overflow: hidden;
}

.video-container .poster img {
  z-index: 0;
  position: absolute;
}

.video-container .filter {
  z-index: 1;
  position: absolute;
  /*background: rgba(0, 0, 0, 0.4);*/
  width: 100%;
}

.fillWidth {
  width: 100%;
}
</style>