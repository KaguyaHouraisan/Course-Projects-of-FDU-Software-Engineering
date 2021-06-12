<template>
  <div>
    <el-form ref="select" label-width="110px" style="margin: 20px 20px 20px 20px">
      <el-row>
        <el-col :span="18">
          <el-form-item prop="currentDate" label="客户编号：">
            <el-input placeholder="客户编号" v-model="customerNum"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-button type="primary" @click="check()">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <div style="margin: 20px">客户号：{{customerInfo.customerNum}}</div>

    <el-table
        :data="tableData"
        stripe
    >
      <el-table-column
          prop="productNum"
          label="产品编号"
          align="center">
      </el-table-column>
      <el-table-column
          prop="productType"
          label="理财产品类型"
          align="center">
<!--       股票/基金/定期 -->
      </el-table-column>
      <el-table-column
          prop="returns"
          label="收益率(%)"
          align="center">
<!--       股票/基金/定期 -->
      </el-table-column>
      <el-table-column
          prop="productName"
          label="理财产品名称"
          align="center">
      </el-table-column>
      <el-table-column
          prop="rate"
          label="利率"
          align="center">
      </el-table-column>
      <el-table-column
          prop="deposit"
          label="买入金额"
          align="center">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="购买时间"
          align="center">
      </el-table-column>
      <el-table-column
          prop="depositNum"
          label="购买股数"
          align="center">
      </el-table-column>
      <el-table-column
          prop="dueDeposit"
          label="卖出金额"
          align="center">
      </el-table-column>
      <el-table-column
          prop="accountNum"
          label="收款账号"
          align="center">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "CheckWMP",
  data(){
    return{
      customerNum: "6161694700992917584",
      customerInfo:{
        customerNum:""
      },
      tableData: [{
        productNum:"ZCLQ",
        rate:1.35,
        productName:"942998",
        productType:"定期",
        deposit: 23948,
        createTime:"2021-04-10",
        depositNum:"-",
        dueDeposit: 23948*1.0135,
        accountNum:"6161694700992917584"
      }, {
        productNum:"KKSP998",
        productName:"wtwtt",
        productType:"股票",
        deposit: 23948,
        createTime:"-",
        depositNum:30,
        nextDue:"2022-04-10",
        dueDeposit: 23948*1.0942,
        rate: 9.42,
        accountNum:"6161694700992917584"
        }
      ]
    }
  },
  created(){
    this.tableData=[]
  },
  methods:{
    getType(i){
      switch (i) {
        case 1:return "股票";
        case 2:return "基金";
        case 3:return "活期";
      }
    },
    goTo(path) {
      this.$router.replace(path);
    },
    check(){//查看账单
      this.customerInfo.customerNum=this.customerNum
      this.$axios.post('/getPurchaseRecord',{
        token:this.$store.getters.getToken,
        accountNum: this.customerNum
      })
          .then(resp => {
            console.log(resp);
            if (resp.status === 200 ) {
              for(var i=0;i<resp.data.length;i++){
                if (resp.data[i].productType==1){
                  this.tableData.push({
                    productNum:resp.data[i].productNum,
                    productName:resp.data[i].productName,
                    productType:  this.getType(resp.data[i].productType),
                    createTime:resp.data[i].createTime,
                    deposit:"-",
                    depositNum:resp.data[i].shareAmount,
                    nextDue:"",
                    rate:"-",
                    accountNum:this.customerNum,
                    dueDeposit:"-",
                    returns:1
                  })
                }else{
                  this.tableData.push({
                    productNum:resp.data[i].productNum,
                    productName:resp.data[i].productName,
                    productType:  this.getType(resp.data[i].productType),
                    createTime:resp.data[i].createTime,
                    deposit:resp.data[i].principal,
                    depositNum:"-",
                    nextDue:"",
                    rate:resp.data[i].rate,
                    accountNum:this.customerNum,
                    dueDeposit:resp.data[i].principal*(1.0+resp.data[i].rate/100.0),
                    returns:resp.data[i].rate
                  })
                }
              }
              console.log(resp.data)
            } else {
              alert("该用户不存在！");
            } 
          })
          .catch(error => {
            console.log(error);
          })
      //todo 发送customerName给后端，后端返回给前端tableData
    },
  }
}
</script>

<style scoped>

</style>