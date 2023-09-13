<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avarar_box">
        <img src="../assets/logo.png">
      </div>
      <div>
        <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form">
<!--          用户名-->
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" prefix-icon="el-icon-user"></el-input>
          </el-form-item>
<!--          密码-->
          <el-form-item prop="password">
            <el-input v-model="loginForm.password" type="password" prefix-icon="el-icon-lock"></el-input>
          </el-form-item>
          <el-form-item class="btns">
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="info" @click="toRegister">注册</el-button>
          </el-form-item>
          </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  computed: {
    ...mapState(['account'])
  },
  data() {
    return {
      // 登录表单数据绑定对象
    loginForm: {
      username: "",
      password: ""
    },
    ruleForm: {
      id: '',
      username: '',
      password: '',
      email:'',
      phone:'',
      position:''
      },
    loginFormRules: {
      username: [
         { required: true, message: "请输入用户名",triggerId: "blur"},
         { min: 3, max: 15, message: "长度在3到15个字符", trigger: "blur" }
        ],
      password: [
         { required: true, message: "请输入密码",triggerId: "blur"},
         { min: 6, max: 15, message: "长度在6到15个字符", trigger: "blur" }
        ],
      }
    };
  },
  methods: {
    login() {
      this.$refs.loginFormRef.validate((valid) => {
        if(!valid) return;
        this.axios.post('http://localhost:80/user/login',this.loginForm).then((resp) =>{
          let data = resp.data;
          if(data.success){
            this.loginForm = {};
            this.ruleForm = data.content;
            this.$message({
              message: '登陆成功',
              type: 'success'
            });
            this.account.id = this.ruleForm.id;
            this.account.username = this.ruleForm.username;
            this.account.email = this.ruleForm.email;
            this.account.phone = this.ruleForm.phone;
            this.account.position = this.ruleForm.position;
            if(this.ruleForm.position==='店长')
              this.account.flag = false;
            this.$router.push({path:'/information'})
          }
        })
      })
    },
    toRegister() {
      this.$router.push({path:'/register'})
    }
  },

}
</script>

<style lang="less" scoped>

.btns {
  display: flex;
  justify-content: flex-end;
}
.login_container{
  background-color: #2b4b6b;
  height: 100%;
}
.login_box{
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avarar_box{
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%,-50%);
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;

    }
  }
  .login_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 40px;
    box-sizing: border-box;
  }
}
</style>
