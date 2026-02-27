<script setup>

</script>

<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">用户登录</h1>
        <el-form :model="loginForm" label-width="100px" :rules="rules" ref="loginForm">
          <el-form-item label="账号" prop="no">
            <el-input style="" type="text" v-model="loginForm.no" autocomplete="off" size="small"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input style="" type="password" v-model="loginForm.password" show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirm" :disabled="confirm_disabled">确定</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>