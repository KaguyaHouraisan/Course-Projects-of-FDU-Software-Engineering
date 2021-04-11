window.onload=function () {
    if (getCookie("username")!=="")
    {
        $("#Login_or_Logout").text("Logout");
        $("#Login_or_Logout").attr("href","javascript:logout()");
        $("#Register_or_UserInformation").text(getCookie("username"));
        $("#Register_or_UserInformation").attr("href","UserInformation.html");
        $("#startMeeting").attr("aria-disabled","false");
        $("#startMeeting").removeClass("disabled");

    }
};

function logout() {
    setCookie("username","");
    window.location.href="Index.html";
}