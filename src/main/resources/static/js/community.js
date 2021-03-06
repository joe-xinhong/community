/**
 * 页面异步提交评论信息
 */
function post() {
    //获取准备回复的问题id
   var questionId  = $("#question_id").val();
    //获取评论或者回复的内容
   var content = $("#comment_content").val();
   comment2target(questionId,1,content);

}

/**
 * 封装提交方法
 * @param targetId
 * @param type
 * @param content
 */
function comment2target(targetId,type,content) {
    if(!content){
        alert("不能回复空内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data:JSON.stringify({
            'parentId':targetId,
            'content':content,
            'type':type
        }),
        success: function (response) {
            if(response.code==200){
                //执行成功后隐藏输入框
                //$("#comment_section").hide();
                //执行成功后刷新页面
                window.location.reload();
            }else {
                if(response.code=2003){
                    var isAccepted = confirm(response.message);
                    //判断是否返回true
                    if(isAccepted){
                        window.open("https://gitee.com/oauth/authorize?client_id=0612af6cad44d6f2e90134725436fe18def380fce1c6c717117ce0ca5b85eec3&redirect_uri=http://localhost:8083/tologincallback&response_type=code&scope=user_info");
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

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    console.log(commentId,content);
    comment2target(commentId,2,content);

}
/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    //获取二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if(collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else {
        var subCommentContainer = $("#comment-"+id);

        if(subCommentContainer.children().length !=1 ){
            //展开二级评论
            comments.addClass("in");
            //标记二级评论的展开状态
            e.setAttribute("data-collapse","in");
            e.classList.add("active");
        }else {
            $.getJSON( "/comment/"+id, function(data) {
                //拼接
                $.each( data.data.reverse(), function(index,comment) {
                    var mediaLeftElement = $("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object img-rounded",
                        "src":comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>",{
                        "class":"media-body"
                    }).append($("<h5/>",{
                        "class":"media-heading",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.content
                    })).append($("<div/>",{
                        "class":"menu"
                    }).append($("<span/>",{
                        "class":"pull-right",
                        "html":moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));


                    var mediaElement = $("<div/>",{
                        "class":"media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>",{
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);
                    subCommentContainer.prepend(commentElement);
                });

            });
            //展开二级评论
            comments.addClass("in");
            //标记二级评论的展开状态
            e.setAttribute("data-collapse","in");
            e.classList.add("active");
        }

    }
}

/**
 * 展开标签选项
 */
function showSelectTag() {
    $("#select-tag").show();
}
/**
 * 标签选择
 * @param value
 */
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var  previous= $("#tag").val();
    if(previous.indexOf(value)==-1){
        if(previous){
            $("#tag").val(previous+','+value);
        }else {
            $("#tag").val(value);
        }
    }
}

