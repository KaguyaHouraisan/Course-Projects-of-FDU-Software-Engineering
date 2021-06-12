<template>
  <el-form ref="select" :model="form" label-width="110px" style="margin: 20px" :rules="rules">
    <el-row>
      <el-col :span="18">
        <el-form-item prop="customerCode">
          <el-input placeholder="用户编号" v-model="form.customerCode" style="width: 100%;"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="6">
        <el-form-item>
          <el-button type="primary" @click="findCustomer()">查找该用户</el-button>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
export default {
  name: "CheckCustomer",
  data(){
    return{
      form:{
        customerCode:""
      },
      rules:{
        customerCode: [
          //{ type: 'number', required:false, message: '请输入数字', trigger: 'blur' },
          { required: true, message: '请输入用户编号', trigger: 'blur, change' },
        ]
      },
    }
  },
  methods:{
    goTo(path) {
      this.$router.push({
        path:path
      }
      );
    },
    findCustomer(){
      this.$axios.post('/getProduct',{
        token:this.$store.getters.getToken,
        accountNum: this.form.customerCode
      })
          .then(resp => {
            console.log(resp);
            if (resp.status === 200 ) {
              this.$store.commit('setAccount',this.form.customerCode)
              this.$store.commit('setProduct', resp.data);
              this.goTo("/purchaseWMP");
            } else {
              alert("该用户不存在！");
            } 
          })
          .catch(error => {
            console.log(error);
          })
    
      //todo 给后端发送请求，得到用户权限，不需要保存，后段可以查到
      //todo 把用户编号存到store里面
      //成功：
      //失败： this.$message.error("该用户不存在！");
    }
  }
}
</script>

<style scoped>

</style>