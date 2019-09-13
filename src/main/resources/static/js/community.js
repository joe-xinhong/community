/*页面异步提交评论信息*/
function post() {
    //获取准备回复的问题id
   var questionId  = $("#question_id").val();
    //获取评论或者回复的内容
   var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data:JSON.stringify({
            'parentId':questionId,
            'content':content,
            'type':1
        }),
        success: function (response) {
            if(response.code==200){
                $("#comment_section").hide();
            }else {
                if(response.code=2003){
                    var isAccepted = confirm(response.message);
                    //判断是否返回true
                    if(isAccepted){
                        window.open("https://gitee.com/oauth/authorize?client_id=0612af6cad44d6f2e90134725436fe18def380fce1c6c717117ce0ca5b85eec3&redirect_uri=http://localhost:8088/callback&response_type=code&scope=user_info");
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}