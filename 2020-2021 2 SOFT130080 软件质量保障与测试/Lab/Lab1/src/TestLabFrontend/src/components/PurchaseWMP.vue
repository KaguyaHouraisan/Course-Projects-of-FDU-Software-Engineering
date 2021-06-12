<template>
  <div>
    <el-form ref="select" :model="form" label-width="80px" style="align-content: center; margin: 20px 50px 20px 50px">
      <el-col :span="7">
        <el-form-item label="产品号">
          <el-input v-model="form.productNum" placeholder="请搜索产品号"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7">
        <el-form-item label="产品名称">
          <el-input v-model="form.productName" placeholder="请搜索产品名称"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="5">
        <el-form-item label="产品类型">
          <el-select v-model="form.productType" placeholder="请选择产品类型">
            <el-option label="股票" :value="1"></el-option>
            <el-option label="基金" :value="2"></el-option>
            <el-option label="定期" :value="3"></el-option>
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
        :itemid="itemId"
    >
      <!--        :itemid="itemId"-->
      <el-table-column
          prop="productNum"
          label="产品编号"
          align="center">
      </el-table-column>
      <el-table-column
          prop="productTypeString"
          label="理财产品类型"
          align="center">
      </el-table-column>
      <el-table-column
          prop="productName"
          label="理财产品名称"
          align="center">
      </el-table-column>
      <el-table-column
          prop="rate"
          label="利率(%/年)"
          align="center">
      </el-table-column>
      <el-table-column
          prop="price"
          label="股价"
          align="center">
      </el-table-column>

      <el-table-column
          label="操作"
          align="center">
        <template slot-scope="scope">

          <el-button type="primary" @click="goToWMP(scope.row)" size="mini">买入</el-button>      
        </template>

      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "PurchaseWMP",
  data(){
    return {
      form: {
        productCode: '',
        productName: '',
        productType: 3
      },
      tableData: [],
      itemId:0
    }
  },
  created(){
    console.log(this.$store.getters.getProduct)
    if (this.tableData.length==0)this.tableData=this.$store.getters.getProduct;
    for(var i=0;i<this.tableData.length;i++){
      this.tableData[i].productTypeString=this.getType(this.tableData[i].productType
      )
    }
  },
  methods:{
    getType(i){
      switch (i) {
        case 1:return "股票";
        case 2:return "基金";
        case 3:return "活期";
      }
    },
    onSubmit() {
      console.log('submit!');
      //todo 筛选
    },
    goTo(path) {
      this.$router.replace(path);
    },
    goToWMP(row){
      console.log(row)
      this.$router.push({
        path:"/WMP",
        name:"WMP",
        query:{
          productType:row.productType,
          productName:row.productName,
          productCode:row.productNum
        }
          }
      );
    }
  }
}
</script>

<style scoped>

</style>