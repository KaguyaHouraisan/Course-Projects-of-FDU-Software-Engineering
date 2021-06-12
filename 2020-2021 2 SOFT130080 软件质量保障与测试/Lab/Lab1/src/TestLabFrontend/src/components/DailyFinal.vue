<template>
  <div>
    <el-form ref="select" :model="form" label-width="110px" style="margin: 20px" :rules="rules">
      <el-row>
        <el-col :span="18">
          <el-form-item prop="currentDate">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.currentDate" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-button type="primary" @click="dailyPay()">日终批量</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "DailyFinal",
  data(){
    return{
      form:{
        currentDate:""
      },
      rules:{
        currentDate: [
          //{ type: 'number', required:false, message: '请输入数字', trigger: 'blur' },
          { required: true, message: '请输入日期', trigger: 'blur, change' },
        ]
      },
    }
  },
  methods:{
    formatDate(date) {
      var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    
      if (month.length < 2) month = '0' + month;
      if (day.length < 2) day = '0' + day;
    
      return [year, month, day].join('-');
    },
    dailyPay(){
      //todo 日终结量
      console.log(this.form.currentDate)
      console.log(this.formatDate(this.form.currentDate))
      this.$axios.post('/autoPayLoanPlan',{
        token:this.$store.getters.getToken,
        currentDate:this.formatDate(this.form.currentDate)
      })
        .then(resp => {
          console.log(resp);
          if (resp.status === 200 ) {
            console.log(resp.data)
            this.tableData=[];
            if (!resp.data||resp.data){
              this.$message.success("成功！");
            }else{
              this.$message.error("当日无到期贷款！");

            }

          } else {
            alert("该用户不存在！");
          } 
        })
        .catch(error => {
          console.log(error);
        })
    }
  }
}
</script>

<style scoped>

</style>