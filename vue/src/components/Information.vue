<template>
  <div>
    <el-container class="home-container">
      <el-aside width="200px">
        <el-menu
          default-active="4"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          router>
          <el-menu-item index="information">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>个人信息</span>
            </template>
          </el-menu-item>
          <el-menu-item index="order">
            <i class="el-icon-menu"></i>
            <span slot="title">订单管理</span>
          </el-menu-item>
          <el-menu-item index="brand">
            <i class="el-icon-document"></i>
            <span slot="title">商品管理</span>
          </el-menu-item>
          <el-menu-item index="user" :disabled = "this.account.flag">
            <i class="el-icon-setting"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/home.html' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>个人信息</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
          <el-descriptions title="个人信息">
            <el-descriptions-item label="用户名">{{ this.account.username }}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{ this.account.email }}</el-descriptions-item>
            <el-descriptions-item label="手机号">{{ this.account.phone }}</el-descriptions-item>
            <el-descriptions-item label="权限">{{ this.account.position }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: "Information",
  computed: {
    ...mapState(['account'])
  },
  data()  {
    return {
      msgFromLogin:{
        username: '',
        password: '',
        email:'',
        phone:'',
        position:''
      },
    }
  },
  methods: {
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
  },
}
</script>

<style scoped>
.el-table .warning-row {
  background: oldlace;
}
.home-container {
  height: 100%;
}
.el-table .success-row {
  background: #f0f9eb;
}
.el-aside {
  background-color: RGB(84,92,100);
  height: 100vh;
}
.el-main {
  background-color: #EAEDF1;
}
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}


.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 12px;
}
</style>
