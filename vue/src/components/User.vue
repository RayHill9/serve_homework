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
          <el-breadcrumb-item>用户列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
          <template>
            <el-table
              :data="userlist"
              style="width: 100%"
              :row-class-name="tableRowClassName">
              <el-table-column
                prop="id"
                label="id"
                align="center">
              </el-table-column>
              <el-table-column
                prop="username"
                label="用户名"
                align="center">
              </el-table-column>

              <el-table-column
                prop="email"
                label="邮箱"
                align="center">
              </el-table-column>
              <el-table-column
                prop="phone"
                align="center"
                label="电话">
              </el-table-column>
              <el-table-column
                prop="position"
                align="center"
                label="权限">
              </el-table-column>

              <el-table-column
                align="center"
                label="操作">
                <template v-slot="scope">
                  <el-button type="primary" @click="showEditDialog(scope.row.id)" >修改</el-button>
                  <el-button type="danger" @click="removeUserById(scope.row.id)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </template>
        </el-card>
<!--        修改用户-->
        <el-dialog
          title="修改用户"
          :visible.sync="editDialogVisible"
          width="50%" @close="editDialogClosed">
          <el-form data="edit" :model="editForm" status-icon :rules="editFormRules" ref="editFormRef" label-width="70px">

            <el-form-item label="姓名:" prop="username">
              <el-input  placeholder="请输入姓名" v-model="editForm.username" autocomplete="off" disabled=""></el-input>
            </el-form-item>
            <el-form-item label="邮箱:" prop="email">
              <el-input  placeholder="请输入邮箱" v-model="editForm.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话:" prop="phone">
              <el-input  placeholder="请输入电话" v-model="editForm.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="权限:" prop="position">
              <el-input placeholder="请输入权限" v-model="editForm.position" autocomplete="off"></el-input>
            </el-form-item>


          </el-form>
          <!--          按钮区-->
          <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="editUser">确 定</el-button>
          <el-button @click="editDialogVisible = false">取 消</el-button>

          </span>
        </el-dialog>

      </el-main>
    </el-container>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "User",
  computed: {
    ...mapState(['account'])
  },
  data: function () {
    return {

      editDialogVisible: false,
      userlist: [],

      // 控制添加对话框隐藏

      editFormRules: {
        username: [
          {required: true, message: "请输入姓名", triggerId: "blur"},
        ],
        email: [
          {required: true, message: "请输入邮箱", triggerId: "blur"},
        ],
        phone: [
          {required: true, message: "请输入电话", triggerId: "blur"},
        ],
        position: [
          {required: true, message: "请输入权限", triggerId: "blur"},
        ]
      },
      // 查询到的用户信息对象
      editForm: {
        username: '',
        password: '',
        email: '',
        phone: '',
        position: ''
      }
    }

  },
  created() {
    this.getUserList();
  },
  methods: {
    async removeUserById(id) {
      const confirmResult = await this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(confirmResult !== 'confirm'){
        return this.$message.info('已取消删除')
      }
      const {data: res} = await this.$http.delete('http://localhost:80/user/'+id)
      if(!res.success)
        return this.$message.error('删除失败')
      this.$message.success('删除成功')
      await this.getUserList();
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    async getUserList() {
      const {data: res} = await this.$http.get('http://localhost:80/user', {})
      if (res.success) {
        this.userlist = res.content;

      }
    },
    //监听添加对话框关闭
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    editUser() {
      this.$refs.editFormRef.validate(async vaild => {
        if(!vaild) return
        const {data: res} = await this.$http.put('http://localhost:80/user/'+this.editForm.id, {
          username: this.editForm.username,
          password: this.editForm.password,
          email: this.editForm.email,
          phone: this.editForm.phone,
          position: this.editForm.position
        })
        if(!res.success)
          return this.$message.error('更改失败')
        this.editDialogVisible = false
        await this.getUserList();
        this.$message.success('更改成功')
      })
    },

    async showEditDialog(id) {
      const {data: res} = await this.$http.get('http://localhost:80/user/'+ id)
      if(!res.success){
        return this.$message.error('修改失败')
      }
      this.editForm = res.content;
      this.editDialogVisible = true;
    }

  },

  mounted() {

  }
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
  background-color: #333744;
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
