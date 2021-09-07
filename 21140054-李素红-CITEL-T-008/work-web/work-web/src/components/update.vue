<template>
  <div id="main">
    <el-dialog title="更新" :visible.sync="dialogFormVisible">
      <el-form :model="userInfo" method="post">
        <el-form-item label="人员ID" :label-width="formLabelWidth">
          <el-input v-model="userInfo.id" autocomplete="off" readonly ></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="userInfo.sex"  style="width: 100%">
            <el-option label="女" value="0"></el-option>
            <el-option label="男" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生年份" :label-width="formLabelWidth" maxlength="4" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '请输入正确的年份格式',trigger: 'blur'},
            ]">
          <el-input v-model.number="userInfo.year" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="总旅行里程" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '必须为数字值',trigger: 'blur'},
            ]">
          <el-input v-model.number="userInfo.totalMileage" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="总旅行时间" :label-width="formLabelWidth" :rules="[
            { required: true, message: '不能为空', trigger: 'blur'},
            { type: 'number', message: '必须为数字值',trigger: 'blur'},
            ]">
          <el-input v-model.number="userInfo.totalTime" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div id="foot" slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUpdate(userInfo)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    props: ['dialogFormVisible','userInfo','isShowData'],

    data(){
      return{
        dialogVisible: this.dialogFormVisible,
        formLabelWidth: '120px',

      }
    },
    methods:{
      submitUpdate(userInfo){
        //console.log(userInfo.id);
        //this.$emit("cancel");
        this.$emit("closeDialog");
        this.$http({
          method: 'post',
          url: 'http://localhost:8083/user/update',
          data: userInfo
        }).then(() => {
        }).catch();
      }
    },

  }
</script>
