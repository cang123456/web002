<script>
  import Aside from "@/components/Aside.vue";
  import Header from "@/components/Header.vue";
  import Main from "@/components/Main.vue";

  export default {
    name: "Index",
    components: {Main, Header, Aside},
    data(){
      return {
        isCollapse:false,
        aside_width:'200px',
        icon:'el-icon-s-fold'
      }
    },
    methods: {
      doCollapse(){
        this.isCollapse = !this.isCollapse;
        if(this.isCollapse===true){//关闭
          this.aside_width='64px';
          this.icon = 'el-icon-s-unfold'
        } else {
          this.aside_width='200px';//展开
          this.icon = 'el-icon-s-fold'
        }
      }
    }
  };
</script>

<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_width" style="margin-left: -1px;margin-top: -1px;">
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container  style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px;border-bottom: rgba(169,169,169,0.3) 1px solid;">
        <Header :icon="icon" @doCollapse="doCollapse"></Header>
      </el-header>

      <el-main  style="height: 100%;">
        <Main></Main>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.el-header {
  color: #333;
  line-height: 60px;
}


</style>