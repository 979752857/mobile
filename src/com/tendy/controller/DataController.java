package com.tendy.controller;

import com.tendy.common.ReplyMap;
import com.tendy.common.BusinessConstants;
import com.tendy.service.DataService;
import com.tendy.utils.ParamUtil;
import com.tendy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/data")
public class DataController extends BaseController{

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/toOpTable", method = {RequestMethod.GET})
	public ModelAndView toOpTable(){
		return new ModelAndView("optable");
	}

	@RequestMapping(value = "/phoneList", method = {RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String phoneList(@RequestParam(value = "key") String phone, @RequestParam(value = "city") String city,
							@RequestParam(value = "no", required = false) Integer pageNo, @RequestParam(value = "status", required = false) String status){
		ReplyMap replyMap = new ReplyMap();
		if(ParamUtil.checkParamIsNull(phone, city)){
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请输入查询的关键号码");
			return replyMap.toJson();
		}
		if(pageNo == null){
			pageNo = 0;
		}
		if(StringUtils.isBlank(status)){
			status = "new";
		}
		Integer pageSize = 10;
		replyMap = dataService.getData(phone, city, pageNo, pageSize, status);
		return replyMap.toJson();
	}

	@RequestMapping(value = "/phoneListDetail", method = {RequestMethod.GET})
	@ResponseBody
	public String phoneListDetail(@RequestParam(value = "key") String phone, @RequestParam(value = "city") String city,
							@RequestParam(value = "no", required = false) Integer pageNo, @RequestParam(value = "status", required = false) String status){
		ReplyMap replyMap = new ReplyMap();
		if(ParamUtil.checkParamIsNull(phone, city)){
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
			return replyMap.toJson();
		}
		if(pageNo == null){
			pageNo = 1;
		}
		Integer pageSize = 10;
		replyMap = dataService.getDataDetail(phone, city, pageNo, pageSize, status);
		return replyMap.toJson();
	}
	
}
