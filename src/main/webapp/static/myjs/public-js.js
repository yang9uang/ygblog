$(function(){
    var nav=$(".nav"); //得到导航对象
    var win=$(window); //得到窗口对象
    var sc=$(document);//得到document文档对象。
    win.scroll(function(){
        if(sc.scrollTop()>=100){
            nav.addClass("fixednav");
            $(".navTmp").fadeIn();
        }else{
            nav.removeClass("fixednav");
            $(".navTmp").fadeOut();
        }
    })
})

//初始化弹出框页面
$(function () {
    $('[data-toggle="popover"]').popover()
})
