<template>
  <div>
    <el-steps :active="active" finish-status="success" style="margin: 50px" align-center>
      <el-step title="填写信息"></el-step>
      <el-step title="确认信息"></el-step>
      <el-step title="转账成功"></el-step>
    </el-steps>

    <div style="margin: 50px">
      <h1>产品代码：{{productCode}}  产品名称：{{productName}}  产品类型：{{getType(productType)}}</h1>
      <div v-if="active===0">
        <el-form ref="select" :model="form" label-width="110px" style="margin: 20px" :rules="rules1" v-if="this.productType!==1">
          <el-form-item label="买入金额" prop="amount">
            <el-input v-model="form.amount" type="number"></el-input>
          </el-form-item>
        </el-form>
        <el-form ref="select" :model="form" label-width="110px" style="margin: 20px" :rules="rules2" v-else>
          <el-form-item label="买入股份数量" prop="shareAmount">
            <el-input v-model="form.shareAmount" type="number"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="active===1">
        买入产品编号：{{productCode}}
        <div v-if="productType===1">
          买入股票数量：{{form.shareAmount}} 股
        </div>
        <div v-else>
          买入金额：{{form.amount}} 元
        </div>
      </div>
      <div v-if="active===2">
        <el-alert
            title="交易成功！"
            type="success"
            show-icon>
        </el-alert>
      </div>
    </div>
    <center>
      <el-button style="margin-top: 12px" @click="up">上一步</el-button>
      <el-button style="margin-top: 12px" @click="next">下一步</el-button>
    </center>
  </div>
</template>

<script>
export default {
  name: "WMP",

  data() {
    return {
      productType: this.$route.query.productType,
      productCode: this.$route.query.productCode,
      productName: this.$route.query.productName,
      active: 0,
      form: {
        amount: 0,
        shareAmount: 0
      },
      rules1: {
        amount: [
          {required: true, message: '请输入购买金额', trigger: 'blur, change'},
        ]
      },
      rules2: {
        shareAmount: [
          {required: true, message: '请输入购买股票数', trigger: 'blur, change'},
        ]
      }
    };
  },
  created(){
    console.log(this.$store.getters.getAccount)
    this.productType= this.$route.query.productType
    this.productCode= this.$route.query.productCode
    this.productName= this.$route.query.productName
  },

  methods: {
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
    next() {
      if (this.active === 0) {
        if (!this.form.amount&&!this.form.shareAmount){
          alert("请输入购买数量！")
          return;
        }
        this.$refs['select'].validate((valid) => {
          if (!valid) return false;
        });
      }
      if (this.active === 1) {//todo 购买
        if (!this.form.amount&&!this.form.shareAmount){
          alert("请输入购买数量！")
          return;
        }
        var myDate = new Date();
        this.$axios.post('/buyProduct',{
          token:this.$store.getters.getToken,
          accountNum:this.$store.getters.getAccount,
          productNum: this.productCode,
          amount: this.form.amount,
          shareAmount:this.form.shareAmount,
          purchaseTime:myDate.toString()

        })
          .then(resp => {
            console.log(resp);
          })
          .catch(error => {
            console.log(error);
          })
      }
      if (this.active === 2) this.goTo("/checkWMP");
      if (this.active++ > 2) this.active = 3;
    },
    up() {
      if (this.active === 2) return;
      if (this.active-- < 1) this.goTo("/purchaseWMP");
    }
  }
}
</script>

<style scoped>

</style>