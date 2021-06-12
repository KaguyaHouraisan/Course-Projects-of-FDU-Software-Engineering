<template>
  <div>
    <div id="form">
      <el-form :model="loginForm"
               :rules="rules"
               class="login_container"
               label-position="left"
               label-width="100px"
               v-loading="loading">
        <h3 class="login_title">Login</h3>
        <el-form-item prop="username" label="柜员账号：">
          <el-input type="text"
                    v-model="loginForm.username"
                    auto-complete="off"
                    placeholder="username"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="柜员密码：">
          <el-input type="password"
                    v-model="loginForm.password"
                    auto-complete="off"
                    placeholder="password"></el-input>
        </el-form-item>
        <div id="information"></div>
        <el-form-item style="width: 100%">
          <el-button type="primary"
                     style="width: 40%;background: #afb4db;border: none"
                     v-on:click="login">login</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{required: true, message: '', trigger: 'blur'}],
        password: [{required: true, message: '', trigger: 'blur'}]
      },
      loading: false
    }
  },
  methods: {
    goTo(path) {
      this.$router.replace(path);
    },
    login() {
      this.$axios.post('/login',{
        username: this.loginForm.username,
        password: this.loginForm.password
      })
          .then(resp => {
            console.log(resp);
            console.log(resp.data.hasOwnProperty.call(resp.data, "token"));
            if (resp.status === 200 && resp.data.hasOwnProperty.call(resp.data, "token")) {
              this.$store.commit('login', resp.data);
              document.getElementById("information").innerHTML = "";
              sessionStorage.setItem("user", this.loginForm.username);
              this.$router.replace({path: '/Homepage'})
            } else if (resp.status === 404) {
              document.getElementById("information").innerHTML = "<div class='alert alert-danger'>用户不存在！</div>";
            } else if (resp.status === 200 || resp.status === 403) {
              document.getElementById("information").innerHTML = "<div class='alert alert-danger'>密码错误！</div>";
            }
          })
          .catch(error => {
            console.log(error);
            document.getElementById("information").innerHTML = "<div class='alert alert-danger'>login error</div>";
          })
    }
  }
}
</script>

<style scoped>
body{
  margin: 0;
  padding: 0;
}
.login_container{
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0 auto 40px auto;
  text-align: center;
  color: #494e8f;
}
</style>