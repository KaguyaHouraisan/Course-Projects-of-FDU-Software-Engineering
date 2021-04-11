function checkUsername() {
    var username=document.getElementById("username").value;
   // alert(username);
    if (username==""){
        document.getElementById("usernameWarning").innerHTML="<div class='alert alert-danger'>请填写用户名！</div>";
        return false;
    }
    else {
        document.getElementById("usernameWarning").innerHTML="";
        return true;
    }
}

function checkPassword() {
    var password=document.getElementById("password").value;
    if (password==""){
        document.getElementById("passwordWarning").innerHTML="<div class='alert alert-danger'>请填写密码！</div>";
        return false;
    }
    else {
        document.getElementById("passwordWarning").innerHTML="";
        return true;
    }
}

function checkLogin() {
    var valid=checkUsername() & checkPassword();
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    if (!valid) return false;

    var data=Qs.stringify({username: username,
        password: password});
    axios.post('register.jsp',data)
        .then(function(response) {
            //请求成功后执行的代码
            if (document.getElementById("remember").value) {
                setCookie("username", username, 30);
            } else {
                setCookieWithoutExpire("username", username);
            }
            window.location.href = "Index.html";
            return false;
        }).catch(function(error){
        alert("用户名或密码不正确！");
        return false;
        //请求失败
    });
}