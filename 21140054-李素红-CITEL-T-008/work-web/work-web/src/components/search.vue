<template>
  <div>
    <el-form :inline="true"  class="demo-form-inline">
      <el-form-item label="查询分类">
        <el-select v-model="item" placeholder="请选择查询类别">
          <el-option label="按出生年份查询" value="year"></el-option>
          <el-option label="按飞行里程查询" value="mile"></el-option>
          <el-option label="按飞行时间查询" value="time"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="查询范围">
        <el-input v-model="scopeA" placeholder="查询起始范围" style="width: 150px"></el-input>
        <span>-</span>
        <el-input v-model="scopeB" placeholder="查询终止范围" style="width: 150px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSearch">查询</el-button>
        <el-button type="primary" @click="onAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <!--调用子组件add对话框-->
      <el-dialog title="添加新信息" :visible.sync="addDialogFormVisible">
        <el-form :model="addUser" method="post" >
         <!-- <el-form-item   prop="id" label="人员ID" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '必须为数字值',trigger: 'blur'},
            ]">
            <el-input  v-model.number="addUser.id" autocomplete="off" aria-placeholder="请输入ID"
                      v-on: input = "check"></el-input>
          </el-form-item>-->
          <!--<el-form-item  prop="sex" label="性别" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '必须为数字值',trigger: 'blur'},
            { pattern:/^[0-1]/, message: '请输入0或1',trigger: 'blur'},
            ]">
            <el-input v-model.number="addUser.sex" autocomplete="off" maxlength="1" ></el-input>
            <span>请输入0或1，0- 女 1- 男</span>
          </el-form-item>-->
          <el-form-item label="出生年份" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '请输入正确的年份格式',trigger: 'blur'},
            { pattern:/^[1-9]d*$/, message: '请输入正确的格式',trigger: 'blur'}
            ]">
            <el-input v-model.number="addUser.year" autocomplete="off" maxlength="4"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'}]" >
            <el-select v-model="addUser.sex" placeholder="请选择性别" style="width: 100%">
              <el-option label="女" value="0"></el-option>
              <el-option label="男" value="1"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="总旅行里程" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '必须为数字值',trigger: 'blur'},
            { pattern:/^d*.?d+$/, message: '请输入正确的格式',trigger: 'blur'}
            ]">
            <el-input v-model.number="addUser.totalMileage" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="总旅行时间" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '必须为数字值',trigger: 'blur'},
            ]">
            <el-input v-model.number="addUser.totalTime" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div id="foot" slot="footer" class="dialog-footer">
          <el-button @click="addDialogFormVisible = false">取 消</el-button>
          <el-button v-if="addUser.id!=''&&addUser.sex!=''
            &&addUser.year!=''&&addUser.totalMileage!=''
            &&addUser.totalTime!=''" type="primary" @click="submitAdd(addUser)" >确 定</el-button>
          <el-button v-else type="primary" :disabled="true">确 定</el-button>
        </div>
      </el-dialog>
    <!--<add :addDialogFormVisible="addDialogFormVisible"></add>-->
    <!--没有查到信息时，显示提示对话框-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%">
      <span>没有查到相关信息，请重新输入。</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>

  import add from "./add";
  import update from "./update";
  export default {
    components:{add},
    //props: ['userInfo'],
    data(){
      return{
        addDialogFormVisible: false,
        //updateInfo:null,
        scopeA:'',//查询范围
        scopeB:'',//查询范围
        item:'',//查询条件
        formLabelWidth: '120px',
        searchData:'',
        dialogVisible: false,//查询没有结果时对话框
        addUser:{
          sex:'',
          year:'',
          totalMileage:'',
          totalTime:''
        },
        //输入框验证
        rules: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '必须为数字值',trigger: 'blur'},
          ],
        }
      }
    },
    methods:{
      onAdd(){
        this.addDialogFormVisible= true;
      },
      onSearch() {
        //判断是否输入条件
        if(this.item==''||this.scopeA==''||this.scopeB==''){
          this.$alert('请输入查询条件', '提示', {
            confirmButtonText: '确定',
            /*callback: action => {
              this.$message({
                type: 'info',
                message: `action: ${ action }`
              });
            }*/
          });
        }else{
          //var url = "http://localhost:8083/user/delete?id="+id;
          var url = "http://localhost:8083/user/" + this.item + "?scope=" + this.scopeA+"-"+this.scopeB;
          let _this = this;
          this.$http.get(url).then(
            ({data}) => {
              if (data.length>0) {
                //localStorage.setItem("searchData",JSON.stringify(this.searchData));
                this.searchData = data;
                this.$store.commit('setResultData',data);
                _this.$router.push("/result");
                console.log(data);
              } else {
                this.dialogVisible=true;
              }
            })
        }

      },
      submitAdd(addUser){
        console.log(addUser.id);
          this.$http({
            method: 'post',
            url: 'http://localhost:8083/user/add',
            data: this.addUser,
            dataType:"json",
            contentType:"application/json;charset=UTF-8",

          }).then(resp => {
            if (resp.status === 200){
              this.$emit('update:dialogFormVisible',false);
              this.addDialogFormVisible= false;
              console.log("Success....");
              this.$alert('添加成功', '温馨提示', {
                confirmButtonText: '确定',
              });
            }

          }).catch(()=>{
              this.addDialogFormVisible= false;
              this.$alert('此id已存在，请确认后输入', '温馨提示', {
              confirmButtonText: '确定',
            });
          });
      },

      }
  }
</script>


