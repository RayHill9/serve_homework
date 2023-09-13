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
          <el-breadcrumb-item>订单管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
          <el-row :gutter="20">
            <el-col :span="4">
              <el-button type="primary" @click="addDialogVisible=true">添加订单</el-button>
            </el-col>
          </el-row>
          <template>
            <el-table
              :data="orderlist"
              style="width: 100%"
              :row-class-name="tableRowClassName"
              @cell-click="handle">
              <el-table-column
                prop="id"
                label="id"
                align="center">
              </el-table-column>
              <el-table-column
                prop="brand"
                label="商品名"
                align="center">
              </el-table-column>
              <el-table-column

                prop="username"
                label="售出人"
                align="center">
              </el-table-column>
              <el-table-column
                prop="price"
                align="center"
                label="总价">
              </el-table-column>
              <el-table-column
                prop="quantity"
                align="center"
                label="数量">
              </el-table-column>
              <el-table-column
                align="center"
                label="操作">
                <template v-slot="scope">
                  <el-button type="danger" @click="removeBrandById(scope.row.id)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>

          </template>
        </el-card>
<!--        显示用户信息-->
        <el-dialog
          title="提示"
          :visible.sync="UserVisible"
          width="30%">
          <span>用户名:{{ this.UserForm.username }}</span>
          <br>
          <span>邮箱:{{ this.UserForm.email }}</span>
          <br>
          <span>手机号:{{ this.UserForm.phone }}</span>
          <br>
          <span>权限:{{ this.UserForm.position }}</span>
          <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="UserVisible = false">确 定</el-button>
        </span>
        </el-dialog>
<!--        添加窗口-->
        <el-dialog
          title="添加订单"
          :visible.sync="addDialogVisible"
          width="50%" @close="addDialogClosed">
          <el-form :model="addForm" status-icon :rules="addFormRules" ref="addFormRef" label-width="70px">

            <el-form-item label="商品名:" prop="brand">
              <el-input  placeholder="请输入商品名:" v-model="addForm.brand" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="售出人:" prop="username">
              <el-input  placeholder="请输入售出人:" v-model="addForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="数量:" prop="quantity">
              <el-input  placeholder="请输入数量:" v-model="addForm.quantity" autocomplete="off"></el-input>
            </el-form-item>


          </el-form>
          <!--          按钮区-->
          <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addOrder">确 定</el-button>
          <el-button @click="addDialogVisible = false">取 消</el-button>

          </span>
        </el-dialog>





      </el-main>
    </el-container>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "Order",
  computed: {
    ...mapState(['account'])
  },
  data: function() {
    return {
      addFormRules: {
        brand: [
          {required: true, message: "请输入商品名", triggerId: "blur"},
        ],
        name: [
          {required: true, message: "请输入售出人", triggerId: "blur"},
        ],
        stock: [
          {required: true, message: "请输入数量", triggerId: "blur"},
        ],
      },

      addDialogVisible:false,
      orderlist: [],
      UserForm: {
        username: '',
        email: '',
        phone: '',
        position: ''
      },
      addForm: {
        brand: '',
        username: '',
        quantity: '',
      },

      canClick: true,
      UserVisible: false,
    }
  },
  methods: {
    async getOrderList() {
      const {data: res} = await this.$http.get('http://localhost:81/order', {})
      if (res.success) {
        this.orderlist = res.content;
      }
    },
    async removeBrandById(id) {
      const confirmResult = await this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(confirmResult !== 'confirm'){
        return this.$message.info('已取消删除')
      }
      const {data: res} = await this.$http.delete('http://localhost:81/order/'+id)
      if(!res.success)
        return this.$message.error('删除失败')
      this.$message.success('删除成功')
      await this.getOrderList();
    },

    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    addOrder() {
      this.$refs.addFormRef.validate(async valid => {
        if(!valid) return
        const {data: res} = await this.$http.post('http://localhost:81/order',this.addForm)
        if(!res.success) {
          this.addDialogVisible = false;
          if(res.message==null)
            return this.$message.error('添加失败')
          return this.$message.error(res.message+'添加失败')
        }
        this.$message.success('添加成功')
        this.addDialogVisible = false;
        await this.getOrderList();
      })
    },

    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    handle(row,column,event,cell) {
      if(column.label==="售出人")
        this.showUserDialog(row.username);
    },
    async showUserDialog(username) {
      const {data: res} = await this.$http.get('http://localhost:81/order/User/'+ username)
      if(!res.success){
        return this.$message.error('查询失败')
      }
      this.UserForm = res.content;
      this.UserVisible = true;
    },

  },
  created() {
    this.getOrderList();
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
