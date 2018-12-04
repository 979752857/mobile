package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.service.SpiderService;
import com.tendy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spider")
public class SpiderController extends BaseController{

	@Autowired
	private SpiderService spiderService;

	@RequestMapping(value = "/startRead", method = {RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String startRead(@RequestParam(value = "city") String city){
		ReplyMap replyMap = new ReplyMap();
		if(StringUtils.isBlank(city)){
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请输入查询的关键号码");
			return replyMap.toJson();
		}
		replyMap = spiderService.startRead(city);
		logger.info("SpiderController.startRead   city:{}   replyMap:{}", city, replyMap.toJson());
		return replyMap.toJson();
	}

	@RequestMapping(value = "/stopRead", method = {RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String stopRead(){
		ReplyMap replyMap = spiderService.stopRead();
		logger.info("SpiderController.stopRead   replyMap:{}", replyMap.toJson());
		return replyMap.toJson();
	}
}
