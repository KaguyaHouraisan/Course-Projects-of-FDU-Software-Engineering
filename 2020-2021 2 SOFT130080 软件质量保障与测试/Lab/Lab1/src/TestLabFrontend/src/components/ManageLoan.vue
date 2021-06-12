<template>
  <div>
    <el-form ref="select" :model="form" label-width="80px" style="align-content: center; margin: 20px 50px 20px 50px">
      <el-col :span="7">
        <el-form-item label="借据号">
          <el-input v-model="form.iouNum" placeholder="请搜索借据号"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7">
        <el-form-item label="客户号">
          <el-input v-model="form.customerCode" placeholder="请搜索客户号"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5">
        <el-form-item label="贷款状态">
          <el-select v-model="form.loanSettleStatus" placeholder="请选择贷款状态">
            <el-option label="未还清" value="1"></el-option>
            <el-option label="已还清" value="2"></el-option>
          </el-select>
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
          prop="iouNum"
          label="借据号"
          align="center">
      </el-table-column>
      <el-table-column
          prop="customerCode"
          label="客户号"
          align="center">
      </el-table-column>
      <el-table-column
          prop="customerName"
          label="客户名"
          align="center">
      </el-table-column>
      <el-table-column
          prop="loanSettleStatus"
          label="贷款状态"
          align="center">
      </el-table-column>
      <el-table-column
          prop="productName"
          label="贷款产品"
          align="center">
      </el-table-column>
      <el-table-column
          prop="productCode"
          label="贷款产品编号"
          align="center">
      </el-table-column>
      <el-table-column
          prop="overdueBalance"
          label="逾期金额（元）"
          align="center">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="发放日期"
          align="center">
      </el-table-column>
      <el-table-column
          label="操作"
          align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="checkBills(scope.row)" size="mini">查看账单</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "ManageLoan",
  // eslint-disable-next-line vue/no-unused-components
  data(){
    return{
      form: {
        customerCode: '',
        loanSettleStatus: null,
        iouNum: ''
      },
      tableData: []
    }
  },
  methods:{
    getType(i){
      switch (i) {
        case 0:return "其他";
        case 1:return "未还清";
        case 2:return "已还清";
        case 3:return "其他";
      }
    },
    checkBills(row){
      console.log(row)
      this.$store.commit("setLoan",row);
      this.$router.push({
        path:"/loanDetails",
        name:"LoanDetails"
          }
      );
    },
    onSubmit() {
      if (this.form.iouNum==''&&this.form.customerCode==''){
        alert("请输入相关信息！")
        return;
      }
      console.log('submit!');
      console.log(this.form);
      this.$axios.post('/getLoanAccountList',{
        token:this.$store.getters.getToken,
      })
        .then(resp => {
          console.log(resp);
          if (resp.status === 200 ) {
            console.log(resp.data)
            this.tableData=[];
            for(var i=0;i<resp.data.length;i++){
              
              if ( (this.form.iouNum==''||this.form.iouNum==resp.data[i].iouNum)&&(this.form.customerCode==''||this.form.customerCode==resp.data[i].customerCode) &&((this.form.loanSettleStatus==null||this.form.loanSettleStatus==resp.data[i].loanSettleStatus)) ) {
                resp.data[i].loanSettleStatus=this.getType(resp.data[i].loanSettleStatus);                
                this.tableData.push(resp.data[i]);
              }
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