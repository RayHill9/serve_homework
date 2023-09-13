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
          <el-breadcrumb-item>商品管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
          <el-row :gutter="20">
            <el-col :span="4">
              <el-button type="primary" @click = "addDialogVisible = true">添加商品</el-button>
            </el-col>
          </el-row>
          <template>
            <el-table
              :data="brandlist"
              style="width: 100%"
              :row-class-name="tableRowClassName">
              <el-table-column
                prop="id"
                label="id"
                align="center">
              </el-table-column>
              <el-table-column
                prop="brand"
                label="品牌"
                align="center">
              </el-table-column>
              <el-table-column
                prop="name"
                label="商品名"
                align="center">
              </el-table-column>
              <el-table-column
                prop="stock"
                align="center"
                label="库存数">
              </el-table-column>
              <el-table-column
                prop="money"
                align="center"
                label="单价">
              </el-table-column>
              <el-table-column
                align="center"
                label="操作">
                  <template v-slot="scope">
                    <el-button type="primary" @click="showEditDialog(scope.row.id)" >修改</el-button>
                    <el-button type="danger" @click="removeBrandById(scope.row.id)">删除</el-button>
                  </template>
              </el-table-column>
            </el-table>
          </template>
        </el-card>

        <el-dialog
          title="添加商品"
          :visible.sync="addDialogVisible"
          width="50%" @close="addDialogClosed">
          <el-form :model="addForm" status-icon :rules="addFormRules" ref="addFormRef" label-width="70px">

            <el-form-item label="品牌:" prop="brand">
              <el-input  placeholder="请输入品牌" v-model="addForm.brand" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="商品名:" prop="name">
              <el-input  placeholder="请输入商品名" v-model="addForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="库存:" prop="stock">
              <el-input  placeholder="请输入库存数" v-model="addForm.stock" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="价格:" prop="money">
              <el-input  placeholder="请输入价格" v-model="addForm.money" autocomplete="off"></el-input>
            </el-form-item>


          </el-form>
          <!--          按钮区-->
          <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addBrand">确 定</el-button>
          <el-button @click="addDialogVisible = false">取 消</el-button>

          </span>
        </el-dialog>


        <el-dialog
          title="修改商品"
          :visible.sync="editDialogVisible"
          width="50%" @close="editDialogClosed">
          <el-form data="edit" :model="editForm" status-icon :rules="editFormRules" ref="editFormRef" label-width="70px">

            <el-form-item label="品牌:" prop="brand">
              <el-input  placeholder="请输入品牌" v-model="editForm.brand" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="商品名:" prop="name">
              <el-input  placeholder="请输入商品名" v-model="editForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="库存:" prop="stock">
              <el-input  placeholder="请输入库存数" v-model="editForm.stock" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="价格:" prop="money">
              <el-input  placeholder="请输入价格" v-model="editForm.money" autocomplete="off"></el-input>
            </el-form-item>


          </el-form>
          <!--          按钮区-->
          <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="editBrand">确 定</el-button>
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
  name: "Brand",
  computed: {
    ...mapState(['account'])
  },
  data: function() {
    return {
      editDialogVisible:false,
      editForm: {
        brand: '',
        name: '',
        stock: '',
        money: ''
      },
      addForm: {
        brand: '',
        name: '',
        stock: '',
        money: ''
      },
      addFormRules: {
        brand: [
          {required: true, message: "请输入品牌", triggerId: "blur"},
        ],
        name: [
          {required: true, message: "请输入商品名", triggerId: "blur"},
        ],
      },
      editFormRules: {
        brand: [
          {required: true, message: "请输入品牌", triggerId: "blur"},
        ],
        name: [
          {required: true, message: "请输入商品名", triggerId: "blur"},
        ],
      },
      addDialogVisible: false,
      visible: false,
      brandlist: []
    }
  },
  methods: {
    async removeBrandById(id) {
      const confirmResult = await this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if(confirmResult !== 'confirm'){
        return this.$message.info('已取消删除')
      }
      const {data: res} = await this.$http.delete('http://localhost:82/brand/'+id)
      if(!res.success)
        return this.$message.error('删除失败')
      this.$message.success('删除成功')
      await this.getBrandList();
    },
    editBrand() {
      this.$refs.editFormRef.validate(async vaild => {
        if(!vaild) return
        const {data: res} = await this.$http.put('http://localhost:82/brand/'+this.editForm.id, {
          brand: this.editForm.brand,
          name: this.editForm.name,
          stock: parseInt(this.editForm.stock),
          money: parseInt(this.editForm.money)
        })
        if(!res.success)
          return this.$message.error('更改失败')

        this.editDialogVisible = false
        await this.getBrandList();
        this.$message.success('更改成功')
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
    addBrand() {
      this.$refs.addFormRef.validate(async valid => {
        if(!valid) return
        const {data: res} = await this.$http.post('http://localhost:82/brand',this.addForm)
        if(!res.success)
          this.$message.error('添加失败')
        this.$message.success('添加成功')
        this.addDialogVisible = false;
        await this.getBrandList();
      })
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    async getBrandList() {
      const {data: res} = await this.$http.get('http://localhost:82/brand', {})
      if (res.success) {
        this.brandlist = res.content;
      }
    },
    async showEditDialog(id) {
      const {data: res} = await this.$http.get('http://localhost:82/brand/'+ id)
      if(!res.success){
        return this.$message.error('修改失败')
      }
      this.editForm = res.content;
      this.editDialogVisible = true;
    }
  },
  created() {
    this.getBrandList();
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
