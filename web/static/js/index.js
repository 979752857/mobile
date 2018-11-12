//Brand开始
$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')) {
            $('.Category-eject').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.screening').attr('style',' ');
        } else {
            $('.Category-eject').addClass('grade-w-roll');
			$(this).addClass('current');
			$(".Sort").removeClass('current');
			$('.screening').attr('style','position: fixed;top:0;');
        }
    });
    $(".Sort").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')) {
            $('.Sort-eject').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.screening').attr('style',' ');
        } else {
            $('.Sort-eject').addClass('grade-w-roll');
			$(this).addClass('current');
			$(".Brand").removeClass('current');
			$('.screening').attr('style','position: fixed;top:0;');
        }
    });
    $(".Position").click(function(){
        if ($('.Position-eject').hasClass('grade-w-roll')) {
            $('.Position-eject').removeClass('grade-w-roll');
            $(this).removeClass('current');
            $('.screening').attr('style',' ');
        } else {
            $('.Position-eject').addClass('grade-w-roll');
            $(this).addClass('current');
            $(".Sort").removeClass('current');
            $('.screening').attr('style','position: fixed;top:0;');
        }
    });
});

$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
        if ($('.Position-eject').hasClass('grade-w-roll')){
            $('.Position-eject').removeClass('grade-w-roll');
        };
    });
    $(".Sort").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
        if ($('.Position-eject').hasClass('grade-w-roll')){
            $('.Position-eject').removeClass('grade-w-roll');
        };
    });
    $(".Position").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
    });
});

//js点击事件监听开始
function Categorytw(wbj, notPhone, txt){
    var arr = document.getElementById("Categorytw").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
	wbj.style.background = "#eee";
    $("#notPhone").val(notPhone);
    $("#brand-word").html(txt);
    $(".Brand").click();
}

function Positiontw(wbj, position, txt){
    var arr = document.getElementById("Positiontw").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
	wbj.style.background = "#eee";
    $("#position").val(position);
    $("#position-word").html(txt);
    $(".Position").click();
}

function Sorts(sbj, tag, txt){
    var arr = document.getElementById("Sort-Sort").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
    sbj.style.background = "#eee";
    $("#tag").val(tag);
    $("#sort-word").html(txt);
    $(".Sort").click();
}
