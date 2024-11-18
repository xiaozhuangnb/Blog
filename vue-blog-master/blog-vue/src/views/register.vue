<template>
  <div class="register">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form">
      <h3 class="title">Blog后台管理系统</h3>
      <el-form-item prop="username">
        <el-input v-model="registerForm.username" type="text" auto-complete="off" placeholder="账号">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="registerForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input
          v-model="registerForm.confirmPassword"
          type="password"
          auto-complete="off"
          placeholder="确认密码"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="code" v-if="captchaOnOff">
        <el-input
          v-model="registerForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter.native="handleRegister"
        >
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <div class="register-code">
          <img :src="codeUrl" @click="getCode" class="register-code-img"/>
        </div>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleRegister"
        >
          <span v-if="!loading">注 册</span>
          <span v-else>注 册 中...</span>
        </el-button>
        <div style="float: right;">
          <router-link class="link-type" :to="'/login'">使用已有账户登录</router-link>
        </div>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-register-footer">
      <span>欢迎你使用该Blog © 创建属于你的Blog.</span>
    </div>
  </div>
</template>

<script>
import { getCodeImg, register } from "@/api/login";

export default {
  name: "Register",
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.registerForm.password !== value) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    return {
      codeUrl: "",
      registerForm: {
        username: "",
        password: "",
        confirmPassword: "",
        code: "",
        uuid: ""
      },
      registerRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" },
          { min: 2, max: 20, message: '用户账号长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, trigger: "blur", message: "请再次输入您的密码" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      captchaOnOff: true
    };
  },
  created() {
    this.getCode();
  },
  methods: {
    getCode() {
      getCodeImg().then(res => {
        this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff;
        if (this.captchaOnOff) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.registerForm.uuid = res.uuid;
        }
      });
    },
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.loading = true;
          register(this.registerForm).then(res => {
            const username = this.registerForm.username;
            this.$alert("<font color='red'>恭喜你，您的账号 " + username + " 注册成功！</font>", '系统提示', {
              dangerouslyUseHTMLString: true,
              type: 'success'
            }).then(() => {
              this.$router.push("/login");
            }).catch(() => {});
          }).catch(() => {
            this.loading = false;
            if (this.captchaOnOff) {
              this.getCode();
            }
          })
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh; /* 确保内容垂直居中，满屏高度 */
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  backdrop-filter: blur(5px); /* 背景模糊效果 */
}

.title {
  margin: 0 auto 30px auto;
  text-align: center;
  font-size: 24px; /* 字体大小 */
  font-weight: 600; /* 字体加粗 */
  color: #4a4a4a; /* 深灰色 */
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1); /* 阴影 */
}

.register-form {
  border-radius: 10px; /* 圆角 */
  background: rgba(255, 255, 255, 0.9); /* 半透明白色背景 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* 阴影 */
  width: 380px; /* 宽度 */
  padding: 30px; /* 内边距 */
}

.register-form .el-form-item {
  margin-bottom: 20px; /* 表单项之间的间距 */
}

.register-form .el-input {
  height: 42px;
}

.register-form .el-input input {
  height: 42px;
  padding: 0 10px; /* 内边距 */
  border-radius: 6px; /* 圆角 */
  border: 1px solid #ddd; /* 边框 */
  transition: all 0.3s ease;
}

.register-form .el-input input:focus {
  border-color: #6ca6f5; /* 聚焦时的边框颜色 */
  box-shadow: 0 0 8px rgba(108, 166, 245, 0.2); /* 聚焦时阴影 */
}

.input-icon {
  height: 42px;
  width: 20px;
  margin-left: 5px;
}

.register-tip {
  font-size: 14px;
  color: #a1a1a1;
  margin-top: 10px;
}

.register-code {
  width: 35%;
  height: 42px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
    border-radius: 4px;
  }
}

.el-register-footer {
  height: 50px;
  line-height: 50px;
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.7); /* 背景颜色 */
  color: #fff;
  text-align: center;
  font-size: 12px;
  letter-spacing: 1px;
}

.register-code-img {
  height: 42px;
}


</style>
