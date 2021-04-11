$(function(){
    //得到当前时间
    var dateNow = new Date();
    //得到当前年份
    var year = dateNow.getFullYear();
    //得到当前月份
    //注：
    //  1：js中获取Date中的month时，会比当前月份少一个月，所以这里需要先加一
    //  2: 判断当前月份是否小于10，如果小于，那么就在月份的前面加一个 '0' ， 如果大于，就显示当前月份
    var month = dateNow.getMonth()+1 < 10 ? "0"+(dateNow.getMonth()+1) : (dateNow.getMonth()+1);
    //得到当前日子（多少号）
    var date = dateNow.getDate() < 10 ? "0"+dateNow.getDate() : dateNow.getDate();
    //设置input标签的max属性
    $("#MeetingTime").attr("min",year+"-"+month+"-"+date);
    $("#PaperTime").attr("min",year+"-"+month+"-"+date);
    $("#DistributeDate").attr("min",year+"-"+month+"-"+date);
});

function checkSubmit() {
    var meetingTime = document.getElementById("MeetingTime").value;
    var paperTime = document.getElementById("PaperTime").value;
    var distributeTime = document.getElementById("DistributeDate").value;
    if (meetingTime < paperTime || meetingTime < distributeTime) {
        $("#MeetingTimeWarning").innerHTML = "<div class='alert alert-danger'>请正确填写会议日期！</div>";
        return false;
    } else {
        $("#MeetingTimeWarning").innerHTML = "";
    }
    if (paperTime > distributeTime) {
        $("#DistributeDateWarning").innerHTML = "<div class='alert alert-danger'>请正确填写评审结果发布日期！</div>";
        return false;
    } else {
        $("#DistributeDateWarning").innerHTML = "";
    }
    var meetingNameBrief = $("#MeetingNameBrief").value;
    var meetingName = $("#MeetingName").value;
    var meetingAddress = $("#MeetingAddress").value;

    var data = Qs.stringify({
        meetingNameBrief: meetingNameBrief,
        meetingName: meetingName,
        meetingAddress: meetingAddress,
        meetingTime: meetingTime,
        distributeTime: distributeTime,
        paperTime: paperTime
    });
    axios.post('register.jsp', data)
        .then(function (response) {
            //请求成功后执行的代码
            alert("会议发布成功！");
            window.location.href="Index.html";
            return false;
        }).catch(function (error) {
        alert("会议发布失败！");
        return false;
        //请求失败
    });
}

//     ajax({
//         url: './meeting.aspx',
//         type: 'POST',
//         dataType: 'json',
//         data: {
//
//         },
//         success: function (response, xml) {
//             //请求成功后执行的代码
//             alert("会议发布成功！");
//             return true;
//         },
//         failure: function (status) {
//             alert("会议发布失败！");
//             return false;
//         },
//         error: function (status) {
//             //失败后执行的代码
//             alert("其他错误！");
//             return false;
//         }
//     });
// }