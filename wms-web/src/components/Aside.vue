<script>

export default {
  data(){
    return {
      // isCollapse:true
    }
  },
  props:{
    isCollapse:Boolean
  },
}


</script>

<template>
  <el-menu style="height: 100%;"
           default-active="/Home"
           background-color="#545c64"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse="isCollapse"
           :collapse-transition="false">
    <el-menu-item  index="/Home">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>

    <el-menu-item  index="/One">
      <i class="el-icon-s-flag"></i>
      <span slot="title">导航1</span>
    </el-menu-item>

    <el-menu-item index="/Two">
      <i class="el-icon-s-promotion"></i>
      <span slot="title">导航2</span>
    </el-menu-item>


  </el-menu>
</template>

<style>




</style>