<template>
<div>
  <Header/>
  <!--    主体-->
  <div style="display: flex">
    <!--      侧边栏-->
    <Aside/>
    <!--      内容区域-->
    <router-view style="flex: 1"/>
  </div>
</div>
</template>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
export default {
  name: "layout",
  components: {Aside, Header}
}
</script>

<style scoped>

</style>