<template>
  <div>
    <el-button type="primary" @click="ret" size="mini" style="margin: 20px;">← 返回借据</el-button>
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin: 20px;">
      <el-tab-pane v-if="planLoan.length>0" label="当前还款账单" name="2" align="center">
        <el-card :body-style="{ padding: '0px', margin:'20px'}" style="width: 300px" >
          <b>第{{pendingLoan.planNum}}期：</b><br>
          待还本金：{{pendingLoan.remainPrincipal}}<br>
          待还利息：{{pendingLoan.remainInterest}}<br>
          复利：{{pendingLoan.compoundInterest}}<br>
          罚息：{{pendingLoan.penaltyInterest}}<br>
          总计：{{pendingLoan.planAmount}}<br>
          应还款日期：{{pendingLoan.planDate}}<br>

          <el-popover
              placement="bottom"
              width="600"
              trigger="click"
              v-model="popover"
          title="模拟还款">
            <el-row>
              <el-col :span="11">
                计划利息（元）：{{pendingLoan.planInterest}}<br>
                计划本金（元）：{{pendingLoan.planPrincipal}}<br>
                计划总金额（元）： {{pendingLoan.planPrincipal+pendingLoan.planInterest}}<br>
              </el-col>
              <el-col :span="11" :offset="2">
                实际利息（元）：{{pendingLoan.planInterest}}<br>
                实际本金（元）：{{pendingLoan.planPrincipal}}<br>
                实际总金额（元）： {{pendingLoan.planPrincipal+pendingLoan.planInterest}}<br>
                复利（元）：{{pendingLoan.compoundInterest}}<br>
                罚息（元）：{{pendingLoan.penaltyInterest}}<br>
              </el-col>
            </el-row>
            <el-form ref="select" :model="form" label-width="110px" style="margin: 20px" :rules="rules">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="还款金额(元)" prop="amount">
                    <el-input v-model="form.amount"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="4">
                  <el-form-item>
                    <el-button type="primary" @click="partialPay('select')" size="mini">部分还款</el-button>
                  </el-form-item>
                </el-col>
                <el-col :span="4">
                  <el-form-item>
                   <el-button type="primary" @click="fullPay()" size="mini">全部还款</el-button>
                  </el-form-item>
                </el-col>
              </el-row>

            </el-form>
            <el-button type="primary" size="mini" slot="reference" style="margin: 20px">还款</el-button>
          </el-popover>
        </el-card>
      </el-tab-pane>
      <el-tab-pane v-if="paidLoan.length>0" label="已还款账单" name="1">
        <el-col :span="5" v-for="(o,index) in paidLoan" :key="index" :offset="0" style="margin: 20px">
          <b>第{{o.planNum}}期：</b><br>
          已还本金：{{o.planPrincipal-o.remainPrincipal}}<br>
          已还利息：{{o.planInterest-o.remainInterest}}<br>
          复利：{{o.compoundInterest}}<br>
          罚息：{{o.penaltyInterest}}<br>
          总计：{{o.planAmount}}<br>
          还款日期：{{o.updateTime}}
        </el-col>
      </el-tab-pane>
      <el-tab-pane v-if="overdueLoan.length>0" label="逾期未还账单" name="3">
        <el-col :span="5" v-for="(o) in overdueLoan" :key="o" :offset="0" style="margin: 20px">
          <b>第{{o.planNum}}期：</b><br>
          待还本金：{{o.remainPrincipal}}<br>
          待还利息：{{o.remainInterest}}<br>
          复利：{{o.compoundInterest}}<br>
          罚息：{{o.penaltyInterest}}<br>
          总计：{{o.planAmount}}<br>
          应还款日期：{{o.planDate}}
        </el-col>
      </el-tab-pane>
      <el-tab-pane v-if="planLoan.length>0" label="未还账单（还款计划）" name="4">
        <el-col :span="5" v-for="(o,index) in planLoan" :key="index" :offset="0" style="margin: 20px">
          <b>第{{o.planNum}}期：</b><br>
          待还本金：{{o.remainPrincipal}}<br>
          待还利息：{{o.remainInterest}}<br>
          复利：{{o.compoundInterest}}<br>
          罚息：{{o.penaltyInterest}}<br>
          总计：{{o.planAmount}}<br>
          应还款日期：{{o.planDate}}
        </el-col>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "LoanDetails",
  // eslint-disable-next-line vue/no-unused-components
  data(){
    return{
      loan:null,
      paidLoan:[
      ],
      form:{
        amount:0
      },
      rules:{
        amount: [
          //{ type: 'number', required:false, message: '请输入数字', trigger: 'blur' },
          { required: true, message: '请输入', trigger: 'blur, change' },
        ]
      },
      popover:false,
      pendingLoan:{//本期账单
      },
      overdueLoan:[//逾期未还账单
      ],
      planLoan:[{//计划账单
      }
      ],
      activeName:"2"
    }
  },
  created(){
    this.initialize();
  },
  methods: {
    goTo(path) {
      this.$router.replace(path);
    },
    initialize(){
        
      this.loan=this.$store.getters.getLoan;
      console.log(this.loan);
      this.$axios.post('/loanPlan',{
        token:this.$store.getters.getToken,
        repaymentStatus:2,
        iouNum: this.loan.iouNum,

      })
      .then(resp => {
        console.log(resp);
        this.paidLoan=resp.data;
      })
      .catch(error => {
        console.log(error);
      })
      this.$axios.post('/loanPlan',{
        token:this.$store.getters.getToken,
        repaymentStatus:1,
        iouNum: this.loan.iouNum,

      })
      .then(resp2 => {
        console.log("计划",resp2);
        this.planLoan=resp2.data;
        if (this.planLoan.length>0)this.pendingLoan=this.planLoan[0];
      })
      .catch(error => {
        console.log(error);
      })
      this.$axios.post('/loanPlan',{
        token:this.$store.getters.getToken,
        repaymentStatus:3,
        iouNum: this.loan.iouNum,

      })
      .then(resp3 => {
        console.log(resp3);
        this.overdueLoan=resp3.data;
      })
      .catch(error => {
        console.log(error);
      })
    },
    ret() {
      this.$router.replace("/manageLoan");
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    fullPay(){
      //todo 全额还款
      var myDate = new Date();
      this.$axios.post('/payLoan',{
            iouNum:this.$store.getters.getLoan.iouNum,
            token:this.$store.getters.getToken,
            updateTime:myDate.toString
          })
          .then(resp => {
            console.log(resp);
            this.initialize();
          })
          .catch(error => {
            console.log(error);
          })
      this.popover=false;
      this.$message({
        message: '还款成功！',
        type: 'success'
      });
    },
    partialPay(formName){
      this.$refs[formName].validate((valid) => {
        console.log(valid);
        var myDate = new Date();

        if (valid) {
          //todo 部分还款
          this.$axios.post('/payLoanPart',{
            iouNum:this.$store.getters.getLoan.iouNum,
            token:this.$store.getters.getToken,
            partialRepayment:this.form.amount,
            updateTime:myDate.toString()
          })
          .then(resp => {
            console.log(resp);
            this.popover=false;
            if (resp.data) {
              this.$message({
                message: '还款成功！',
                type: 'success'
              });
              this.initialize();
            }else{
              this.$message({
              message: '还款失败，请检查金额！',
              type: 'error'
            });
            }
          
          })
          .catch(error => {
            console.log(error);
          })
          
        } else {
          this.$message({
            message: '请正确输入还款金额！',
            type: 'warning'
          });
          return false;
        }
      });
    }
  },
}
</script>

<style scoped>

</style>