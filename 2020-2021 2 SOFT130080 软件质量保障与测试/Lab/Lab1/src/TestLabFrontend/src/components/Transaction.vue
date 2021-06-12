<template>
  <div>
    <navigation></navigation>
    <el-form ref="select" :model="form" label-width="80px" style="align-content: center; margin: 20px 50px 20px 50px">
      <el-col :span="7">
        <el-form-item label="流水号">
          <el-input v-model="form.transactionNum" placeholder="请搜索流水号"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7">
        <el-form-item label="交易账号">
          <el-input v-model="form.account" placeholder="请搜索交易账号"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="9">
        <el-form-item label="开始日期">
           <el-date-picker
            v-model="form.startTime"
            type="date"
            placeholder="选择日期">
            </el-date-picker>
        </el-form-item>
      </el-col>
      <el-col :span="93">
        <el-form-item label="结束日期">
           <el-date-picker
            v-model="form.endTime"
            type="date"
            placeholder="选择日期">
            </el-date-picker>
        </el-form-item>
      </el-col>
      <el-col :span="3">
        <el-form-item>
          <el-button type="primary" @click="onSubmit">筛选</el-button>
        </el-form-item>
      </el-col>
    </el-form>

    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
    >
      <el-table-column
          prop="transactionNum"
          label="流水号"
          align="center">
      </el-table-column>
      <el-table-column
          prop="account"
          label="交易账户"
          align="center">
      </el-table-column>
      <el-table-column
          prop="branchName"
          label="办理机构"
          align="center">
      </el-table-column>
      <el-table-column
          prop="operatorTime"
          label="操作时间"
          align="center">
      </el-table-column>
      <el-table-column
          prop="amount"
          label="金额（元）"
          align="center">
      </el-table-column>
      <el-table-column
          prop="transactionType"
          label="交易类型"
          align="center">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>


import Navigation from "@/components/Navigation";
export default {
  name: "ManageLoan",
  components: {Navigation},
  // eslint-disable-next-line vue/no-unused-components
  data(){
    return{
      form: {
        account: '',
        transactionNum: '',
        startTime: null,
        endTime: null
      },
      tableData: [{
        transactionNum: "L2104101241451",
        account: "aa1111202103283",
        customerName: "wtt",
        loanStatus: "正常",
        productName: "个体工商户小额贷款",
        productCode: 40001,
        overdueBalance: 0.0,
        loanTime: "2021-4-10",
        options: [true, true, true]
      }]
    }
  },
  created(){
    this.getFlow();
  },
  methods:{
    onSubmit() {
      console.log('submit!');
      this.getFlow();
      //todo 筛选
    },
    goTo(path) {
      this.$router.replace(path);
    },
    getFlow() {
      //console.log(this.$store.getters.getToken)
      this.$axios.post('/getFlow',{
        "token":this.$store.getters.getToken
      })
          .then(resp => {
            //console.log(resp.data);
            console.log(this.form)
            this.tableData=[];
            for(var i=0;i<resp.data.length;i++){
              var operatorTime=new Date(resp.data[i].operatorTime)
              //console.log(operatorTime)
              if ((this.form.account==''||this.form.account==resp.data[i].account)&&(this.form.transactionNum==''||this.form.transactionNum==resp.data[i].account)&&(this.form.startTime==null||this.form.startTime<=operatorTime)&&(this.form.endTime==null||this.form.endTime>=operatorTime)) {
                this.tableData.push(resp.data[i])
              }
            }
            console.log(this.tableData)
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