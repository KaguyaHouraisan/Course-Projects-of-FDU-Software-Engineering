<template>
  <div>
    <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">返回主页</el-menu-item>
      <el-submenu index="2">
        <template slot="title">贷款业务</template>
        <el-menu-item index="2-1">贷款账户管理</el-menu-item>
        <el-menu-item index="2-2">贷款日终处理</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">公共业务</template>
        <el-menu-item index="3-1">交易流水查询</el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">存款业务</template>
        <el-menu-item index="4-1">查看/购买产品</el-menu-item>
        <el-menu-item index="4-2">产品盈亏查看</el-menu-item>
      </el-submenu>
    </el-menu>
    <div id="desktop"></div>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  methods: {
    goTo(path)
    {
      this.$router.replace(path);
    },
    handleSelect(key, keyPath) {
      switch (keyPath.join()){
        case "1":
          if (this.$route.path!=="/homepage") this.goTo("/homepage");
          break;
        case "2,2-1"://贷款账户管理
          this.goTo('/manageLoan');
          break;
        case "2,2-2"://贷款日终处理
          this.goTo('/dailyFinal');
          break;
        case "3,3-1"://交易流水查询
          this.goTo('/Transaction');
          break;
        case "4,4-1"://查看产品种类
          this.goTo("/checkCustomer");
          break;
        case "4,4-2"://查看已经购买的产品
          this.goTo('/checkWMP');
          break;
      }
    }
  }
}
</script>

<style scoped>

</style>