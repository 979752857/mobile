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
});

//Sort开始
$(document).ready(function(){
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
});

$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
        };
    });
});

$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
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
