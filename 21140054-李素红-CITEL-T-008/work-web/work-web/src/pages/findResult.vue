<template>
  <div id = "home" >
    <h1>{{header}}</h1>
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>信息查询</template>
            <el-menu-item-group>
              <template slot="title">查询分类</template>
              <el-menu-item index="2-1">按出生年份查询</el-menu-item>
              <el-menu-item index="2-2">按飞行里程查询</el-menu-item>
              <el-menu-item index="2-2">按飞行时间查询</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>LIMIKI</span>
        </el-header>

        <el-main>
          <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)">
            <el-table-column prop="id" label="人员 ID" width="140">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="120">
            </el-table-column>
            <el-table-column prop="year" label="出生年份">
            </el-table-column>
            <el-table-column prop="totalMileage" label="总旅行里程">
            </el-table-column>
            <el-table-column prop="totalTime" label="总旅行时间">
            </el-table-column>
            <el-table-column  label="操作">
              <template slot-scope="scope">
                <div>
                  <el-button type="primary button" @click="onUpdate(scope.row)">修改</el-button>
                  <el-button type="primary button" @click="onDelete(scope.row)">删除</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            background
            :current-page.sync="currentPage"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20, 40]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="10000">
          </el-pagination>
        </el-main>
      </el-container>
    </el-container>
    <!--调用子组件update对话框-->
    <update :dialogFormVisible="dialogFormVisible" :userInfo="userInfo"></update>

    <!--调用子组件add对话框-->
    <!--<add  :dialogFormVisible.sync="dialogFormVisible"></add>-->
  </div>
</template>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
  h1{
    color:blue;
  }
</style>

<script>
  import search from "../components/search";
  import update from "../components/update";
  import add from "../components/add";

  export default {
    name: "Result",
    components:{search,update,add},
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        //tableData:JSON.parse(localStorage.getItem("searchData")) ,
        tableData:this.$store.getters.getResultData ,
        header:'信息统计',
        userInfo:null,
        currentPage:1,
        pageSize:10,//默认每页显示10条
        totalItem:null,//总数据条数
        dialogFormVisible:false,//是否显示更新操作对话框
        userInfo:null//接收修改的数据
      };

    },
    methods:{
      //删除数据
      onDelete(val) {
        this.$confirm('是否删除本条数据？', '确认信息', {
          showClose:true,
          distinguishCancelAndClose: true,
          confirmButtonText: '删除',
          cancelButtonText: '取消'
        }).then(() => {
          this.$message({
            type: 'info',
            message: '数据已删除',
          });
          var id = val.id;
          console.log(val.id);
          var url = "http://localhost:8083/user/delete?id="+id;
          this.$http.get(url).then().catch();

        })
          .catch(action => {
            this.$message({
              type: 'info',
              message: action === 'cancel'
                ? '您已取消删除操作'
                : '您已取消删除操作'
            })
          });

      },
      //修改数据
      onUpdate(val) {
        this.dialogFormVisible = true;
        this.userInfo = val;
      },

      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        //console.log(`当前页: ${val}`);
      }
    },

    created() {
      //this.getData();
    },
    mounted(){
      //this.getData();
    }
  };
</script>
