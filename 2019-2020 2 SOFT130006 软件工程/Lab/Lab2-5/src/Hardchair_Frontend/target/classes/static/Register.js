function checkUsername() {
    var username = document.getElementById("username").value;
    var usernameFormat = /^[A-Za-z\-][A-Za-z\d_\-]{4,31}$/;
    // alert(username);
    if (username === "") {
        document.getElementById("usernameWarning").innerHTML = "<div class='alert alert-danger'>请填写用户名！</div>";
        return false;
    }
    else if (!usernameFormat.test(username)) {
        document.getElementById("usernameWarning").innerHTML = "<div class='alert alert-danger'>请正确填写用户名！</div>";
        return false;
    } else {
        document.getElementById("usernameWarning").innerHTML = "";
        return true;
    }
}

function checkPassword() {
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;
    var passwordFormat=/(?:\d.*[\-_])|(?:[\-_].*\d)|(?:[A-Za-z].*[\-_])|(?:[\-_].*[A-Za-z])|(?:[A-Za-z].*\d)|(?:\d.*[A-Za-z]){6,32}/;
    if (password===""){
        document.getElementById("passwordWarning").innerHTML="<div class='alert alert-danger'>请填写密码！</div>";
        return false;
    }
    else if (!passwordFormat.test(password) || (password.length<6) || (password.length>32) || (password.search(username)!==-1)){
        document.getElementById("passwordWarning").innerHTML="<div class='alert alert-danger'>请正确填写密码！</div>";
        return false;
    } else {
        document.getElementById("passwordWarning").innerHTML="";
        return true;
    }
}


function checkPasswordConfirm() {
    var passwordConfirm = document.getElementById("passwordConfirm").value;
    var password = document.getElementById("password").value;
    if (passwordConfirm === "") {
        document.getElementById("passwordConfirmWarning").innerHTML = "<div class='alert alert-danger'>请再次填写密码！</div>";
        return false;
    }
    else if (password !== passwordConfirm) {
        document.getElementById("passwordConfirmWarning").innerHTML = "<div class='alert alert-danger'>请正确填写密码！</div>";
        return false;
    } else {
        document.getElementById("passwordConfirmWarning").innerHTML = "";
        return true;
    }
}

function checkEmail() {
    var email = document.getElementById("email").value;
    var emailFormat = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
    if (email === "") {
        document.getElementById("emailWarning").innerHTML = "<div class='alert alert-danger'>请填写邮箱！</div>";
        return false;
    }
    else if (!email.match(emailFormat)) {
        document.getElementById("emailWarning").innerHTML = "<div class='alert alert-danger'>请正确填写邮箱！</div>";
        return false;
    } else {
        document.getElementById("emailWarning").innerHTML = "";
        return true;
    }
}

function checkInstitution() {
    var institution = document.getElementById("institution").value;
    if (institution === "") {
        document.getElementById("institutionWarning").innerHTML = "<div class='alert alert-danger'>请填写所属机构！</div>";
        return false;
    }
    else {
        document.getElementById("institutionWarning").innerHTML = "";
        return true;
    }
}

function checkRegion() {
    var region = document.getElementById("region").value;
    if (region === "") {
        document.getElementById("regionWarning").innerHTML = "<div class='alert alert-danger'>请填写地区！</div>";
        return false;
    }
    else {
        document.getElementById("regionWarning").innerHTML = "";
        return true;
    }
}

function checkSubmit() {
    var pass = checkEmail() & checkPassword() & checkUsername() & checkInstitution() & checkRegion() & checkPasswordConfirm();
    if (!pass) return false;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var email = document.getElementById("email").value;
    var institution = document.getElementById("institution").value;
    var region = document.getElementById("region").value;

    var data=Qs.stringify({username: username,
                password: password,
                email: email,
                institution: institution,
                region: region});
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
            alert("用户名有重复！");
            return false;
            //请求失败
    })
}