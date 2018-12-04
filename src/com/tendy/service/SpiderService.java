package com.tendy.service;

import com.tendy.common.ReplyMap;
import com.tendy.spider.GuangDongPhone;
import com.tendy.spider.HenanPhone;
import com.tendy.spider.Phone;
import com.tendy.spider.SendAlertUtil;
import com.tendy.utils.TimeUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SpiderService extends BaseService {

    private Boolean runFlag = false;
    private final Map<String, Integer> busMap = new HashMap<>();
    private final Map<String, Integer> cityMap = new HashMap<>();
    {
        busMap.put("200", 3);
        cityMap.put("guangzhou", 200);
        busMap.put("769", 2);
        cityMap.put("dongguan", 769);
        busMap.put("377", 1);
        cityMap.put("nanyang", 377);
    }

    public ReplyMap startRead(String cityId){
        ReplyMap replyMap = new ReplyMap();
        if(runFlag){
            replyMap.success();
            replyMap.put("message", "已经开启");
            replyMap.put("flag", runFlag);
            return replyMap;
        }
        runFlag = true;
        final List<String> citys = new ArrayList<>();
        String[] cityArray = cityId.split("\\|");
        for(String city : cityArray){
            citys.add(city);
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new BasicThreadFactory.Builder().namingPattern("loaddata-single-pool-%d").daemon(true).build());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    while (runFlag){
                        String format = "HH:mm:ss";
                        Date nowTime = new SimpleDateFormat(format).parse(TimeUtil.formatDate(new Date(), format));
                        Date startTime = new SimpleDateFormat(format).parse("06:30:00");
                        Date endTime = new SimpleDateFormat(format).parse("23:00:00");
                        if(!TimeUtil.isEffectiveDate(nowTime, startTime, endTime)){
                            System.out.println(TimeUtil.formatDate(new Date(), format)+"********不在时间范围内");
                            Thread.sleep(1000*60*5);
                            continue;
                        }
                        SendAlertUtil.init();
                        if(citys.contains("nanyang")){
                            Integer cityId = cityMap.get("nanyang");
                            Phone nanyang = new HenanPhone(20, 1, 1, cityId, busMap.get(String.valueOf(cityId)));
                            phoneProcess(nanyang);
                            Thread.sleep(1000*60*3);
                        }
                        if(citys.contains("dongguan")){
                            Integer cityId = cityMap.get("dongguan");
                            Phone dongguan = new GuangDongPhone(10, 1, 10, cityId, busMap.get(String.valueOf(cityId)));
                            phoneProcess(dongguan);
                            Thread.sleep(1000*60*10);
                        }
                        if(citys.contains("guangzhou")){
                            Integer cityId = cityMap.get("guangzhou");
                            Phone guangzhou = new GuangDongPhone(10, 1, 10, cityId, busMap.get(String.valueOf(cityId)));
                            phoneProcess(guangzhou);
                            Thread.sleep(1000*60*10);
                        }
                    }
                    logger.info("爬虫执行完毕");
                }catch(Exception e){
                    logger.error("爬虫执行异常", e);
                }
            }
        });
        replyMap.success();
        replyMap.put("message", "开启成功");
        replyMap.put("flag", runFlag);
        return replyMap;
    }

    public ReplyMap stopRead(){
        ReplyMap replyMap = new ReplyMap();
        if(!runFlag){
            replyMap.success();
            replyMap.put("flag", runFlag);
            replyMap.put("message", "已经关闭");
            return replyMap;
        }
        runFlag = false;
        replyMap.success();
        replyMap.put("flag", runFlag);
        replyMap.put("message", "关闭成功");
        return replyMap;
    }

    private void phoneProcess(Phone phone) throws Exception {
        for(int i = phone.getPageStart(); i <= phone.getPageEnd(); i++){
            System.out.println(phone.getCityId()+"*********当前获取第"+i+"页数据,每页大小"+phone.getPageSize()+"***********");
            phone.execute(i);
            System.out.println(TimeUtil.formatDate(new Date(), "HH:mm:ss") + "*****" + phone.getCityId()+"*********第"+i+"页数据处理完毕,每页大小"+phone.getPageSize()+"   成功数："+phone.getSuccessNum()+"   更新数："+
                    phone.getUpdateNum()+"   失败数："+phone.getFailNum()+"   ***********");
            Random random = new Random();
            int sleep = random.nextInt(20)*1000 + 8000;
            Thread.sleep(sleep);
        }
    }
}