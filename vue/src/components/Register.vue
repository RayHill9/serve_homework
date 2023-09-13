<template>
  <div class="register_container">
    <div class="register_box">
      <div class="avarar_box">
        <img src="../assets/logo.png">
      </div>
      <div>
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">

            <el-form-item prop="username">
              <el-input placeholder="请输入用户名" prefix-icon="el-icon-user" type="username" v-model="ruleForm.username" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item prop="password">
              <el-input placeholder="请输入密码" prefix-icon="el-icon-lock" type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item prop="email">
              <el-input placeholder="请输入邮箱" prefix-icon="el-icon-s-custom" type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item prop="phone">
              <el-input placeholder="请输入手机号" prefix-icon="el-icon-eleme" type="phone" v-model="ruleForm.phone" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item class="btns">
              <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>

          </el-form>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    var validateUser = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      }
      else{
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.password !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };

    var validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'));
      }
      else{
        callback();
      }
    };
    var validatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入手机号'));
      }
      else{
        callback();
      }
    };
    return {
      ruleForm: {
        username: '',
        password: '',
        email:'',
        phone:'',
        position:''
      },
      rules: {
        username: [
          { validator: validateUser, message: "请输入用户名",triggerId: "blur"},
          { min: 3, max: 15, message: "长度在3到15个字符", trigger: "blur" }
        ],
        password: [
          { validator: validatePass, message: "请输入密码",triggerId: "blur"},
          { min: 6, max: 15, message: "长度在6到15个字符", trigger: "blur" }
        ],
        email: [
          { validator: validateEmail, message: "请输入邮箱",triggerId: "blur"}
        ],
        phone: [
          { validator: validatePhone, message: "请输入手机号",triggerId: "blur"}
        ]

      },
    };
  },
  methods: {
    submitForm(ruleForm) {

        this.axios.post('http://localhost:80/user/register',this.ruleForm).then((resp) =>{
          let data = resp.data;
          if(data.success){
            this.loginForm = {};
            this.$message({
              message: '注册成功',
              type: 'success'
            });
            this.$router.push({path:'/login'})
          }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style lang="less" scoped>
.btns {
  display: flex;
  justify-content: flex-end;
}
.register_container{
  background-color: #2b4b6b;
  height: 100%;
}
.register_box{
  width: 450px;
  height: 500px;
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
.demo-ruleForm{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 40px;
  box-sizing: border-box;
  margin: auto;
  left: 0;
  right: 0;
}
}
</style>
