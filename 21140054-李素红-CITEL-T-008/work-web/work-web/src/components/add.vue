<template>
  <div id="add">
    <el-dialog title="添加" :visible.sync="addDialogFormVisible">
      <el-form :model="addUser" method="post">
        <el-form-item   prop="id" label="人员ID" :label-width="formLabelWidth"
            :rules="[
            { required: true, message: 'ID不能为空'},
            { type: 'number', message: 'id必须为数字值'}
            ]">
          <el-input type="id" v-model.number="addUser.id" autocomplete="off" aria-placeholder="请输入ID" ></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-input v-model="addUser.sex" autocomplete="off"></el-input>
          <span>0- 女 1- 男</span>
        </el-form-item>
        <el-form-item label="出生年份" :label-width="formLabelWidth">
          <el-input v-model="addUser.year" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="总旅行里程" :label-width="formLabelWidth">
          <el-input v-model="addUser.totalMileage" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="总旅行时间" :label-width="formLabelWidth">
          <el-input v-model="addUser.totalTime" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div id="foot" slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitAdd(addUser)" :disabled="isActive">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    props: ['addDialogFormVisible'],
    data(){
      return{
        dialogFormVisible: this.addDialogFormVisible,
        addUser:{
          id:'',
          sex:'',
          year:'',
          totalMileage:'',
          totalTime:''
        },
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px',
        isActive:true//提交按钮

      }
    },
    methods:{
      submitAdd(addUser){
        console.log(addUser.id);
        if(addUser.id.length!=0){
          this.isActive=false;
          this.$http({
            method: 'post',
            url: 'http://localhost:8083/user/add',
            data: this.addUser
          }).then(() => {
            this.$emit('update:dialogFormVisible',false);
            this.addDialogFormVisible= false;
            console.log("Success....")
          }).catch(
          );
        }
      }
    }
  }
</script>
