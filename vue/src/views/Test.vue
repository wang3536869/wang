<template>
<div>
  <el-button @click="send">发送请求</el-button>
  <el-button style="margin-left: 40px" @click="get">接受请求</el-button>
</div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Test",
  methods:{
    send(){
      request.post("http://localhost:8001/send").then(res=>{
        console.log(res.data)
      })
    },
    get(){
      request.post("http://localhost:8001/get").then(res=>{
        console.log(res.data)
      })
    }
  }
}
</script>

<style scoped>

</style>