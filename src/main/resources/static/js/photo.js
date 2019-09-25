$(function() {
    enlargeImg();
})
/**
 * 点击查看大图
 */
function enlargeImg() {
    $(".photo").click(function() {
        $(this).after("<div onclick='closeImg()'  class='enlargeImg_wrapper'></div>");
        var imgSrc = $(this).attr('src');
        $(".enlargeImg_wrapper").css("background-image", "url(" + imgSrc + ")");
        $('.enlargeImg_wrapper').fadeIn(200);
    })

    /*$(".enlargeImg_wrapper").click(function () {
        $('.enlargeImg_wrapper').fadeOut(200).remove();
    })*/

}
//关闭并移除图层
function closeImg() {
    $('.enlargeImg_wrapper').fadeOut(200).remove();
}
