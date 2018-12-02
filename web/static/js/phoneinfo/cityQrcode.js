function getQrcodeByCity(city, url, phone){
    if(city == '377'){
        return cityNanyang(url, phone);
    }
    if(city == '769'){
        return cityDongguan(url);
    }
    if(city == '200'){
        return cityDongguan(url);
    }
}

function cityNanyang(url, phone){
    //http://wap.ha.10086.cn/pay/card-sale!toforward.action?url=meal&mealid=86&region=R&cardnum=18838888888&plancode=XWAPCARDNCS
    var result = "http://wap.ha.10086.cn/pay/card-sale!toforward.action?url=meal&mealid=86&region=R&cardnum="+phone+"&plancode="+url;
    return result;
}

function cityDongguan(url){
    return url;
}