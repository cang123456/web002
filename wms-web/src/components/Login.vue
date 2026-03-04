<script>
export default {
  // eslint-disable-next-line
  name:"Login",
  data() {
    return {
      loginForm: {
        no: '',
        password: ''
      },
      rules: {
        no: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输密码', trigger: 'blur' }
        ]
      },
      confirm_disabled: false // 补充缺失的变量定义
    }
  },
  methods: {
    // 这里可以继续补充 confirm 方法等逻辑
    confirm() {
      this.confirm_disabled = true;
      this.$refs.loginForm.validate((valid) => {
        if (valid) { // valid成功为true，失败为false
          // 去后台验证用户名密码
          this.$axios.post(this.$httpUrl+'/user/login', this.loginForm)
              .then(res => res.data)
              .then(res => {
                console.log(res);
                if (res.code === 200) {
                  // 存储
                  sessionStorage.setItem("CurUser", JSON.stringify(res.data));
                  // 跳转到主页
                  this.$router.replace('/Index');
                } else {
                  this.confirm_disabled = false;
                  alert('校验失败，用户名或密码错误！');
                  return false;
                }
              })
              .catch(error => { // 补充异常处理，防止请求失败后按钮一直禁用
                console.error('登录请求失败:', error);
                this.confirm_disabled = false;
                alert('网络异常，请稍后重试！');
              });
        } else {
          this.confirm_disabled = false; // 表单验证失败时恢复按钮可用状态
        }
      });
    }
  }
}
</script>

<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">用户登录</h1>
        <el-form
            :model="loginForm"
            label-width="100px"
            :rules="rules"
            ref="loginForm"
            class="login-form"
        >
          <el-form-item label="账号" prop="no">
            <el-input
                style="width: 200px"
                type="text"
                v-model="loginForm.no"
                autocomplete="off"
                size="small"
                placeholder="请输入您的账号"
                class="login-input"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
                style="width: 200px"
                type="password"
                v-model="loginForm.password"
                show-password
                autocomplete="off"
                size="small"
                @keyup.enter.native="confirm"
                placeholder="请输入您的密码"
                class="login-input"
            ></el-input>
          </el-form-item>
          <el-form-item class="btn-group">
            <el-button type="primary" @click="confirm" :disabled="confirm_disabled" class="login-btn">确定</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 全局登录背景 */
.loginBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #f5f7fa;
  /* 添加渐变背景，提升视觉效果 */
  background: linear-gradient(135deg, #B3C0D1 0%, #c9d6e8 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
  padding: 0;
}

/* 登录卡片 */
.loginDiv {
  width: 450px;
  height: 330px;
  background: #ffffff;
  border-radius: 12px; /* 优化圆角大小 */
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12); /* 添加阴影提升层次感 */
  display: flex;
  justify-content: center;
  align-items: center;
  /* 取消绝对定位，改用flex居中，适配性更好 */
}

/* 登录内容区域 */
.login-content {
  width: 100%;
  padding: 0 50px;
  box-sizing: border-box;
  text-align: center;
}

/* 登录标题 */
.login-title {
  font-size: 24px;
  color: #1f2d3d;
  margin: 0 0 30px 0;
  font-weight: 600;
  position: relative;
  padding-bottom: 15px;
}

/* 标题下方装饰线 */
.login-title::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 60px;
  height: 3px;
  background-color: #409eff;
  border-radius: 3px;
}

/* 登录表单 */
.login-form {
  text-align: left;
  margin-top: 10px;
}

/* 表单项样式 */
.el-form-item {
  margin-bottom: 20px;
}

/* 表单标签样式 */
.el-form-item__label {
  font-size: 14px;
  color: #4e5969;
  font-weight: 500;
}

/* 输入框样式 */
.login-input {
  border-radius: 6px;
  border: 1px solid #e5e6eb;
  transition: all 0.3s ease;
}

/* 输入框聚焦样式 */
.login-input:focus {
  border-color: #409eff;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.2);
}

/* 按钮组样式 */
.btn-group {
  margin-top: 10px;
  text-align: center;
}

/* 登录按钮样式 */
.login-btn {
  width: 200px;
  height: 36px;
  border-radius: 6px;
  background-color: #409eff;
  border: none;
  font-size: 14px;
  font-weight: 500;
  transition: all 0.3s ease;
}

/* 按钮hover效果 */
.login-btn:hover {
  background-color: #66b1ff;
  transform: translateY(-1px);
}

/* 按钮禁用状态样式 */
.login-btn:disabled {
  background-color: #a0cfff;
  cursor: not-allowed;
  transform: none;
}

/* 适配小屏幕 */
@media (max-width: 480px) {
  .loginDiv {
    width: 90%;
    height: auto;
    padding: 30px 0;
  }

  .login-content {
    padding: 0 30px;
  }

  .login-input, .login-btn {
    width: 100%;
  }
}
</style>
