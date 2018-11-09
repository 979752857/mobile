package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.service.IndexService;
import com.tendy.utils.ParamUtil;
import com.tendy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

	@Autowired
	private IndexService indexService;

	@RequestMapping(value = "/phoneList", method = {RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String phoneList(@RequestParam(value = "key") String phone, @RequestParam(value = "cid", required = false) String cid,
							@RequestParam(value = "no", required = false) Integer pageNo, @RequestParam(value = "status", required = false) String status,
							@RequestParam(value = "tag", required = false) String tag, @RequestParam(value = "notPhone", required = false) String notPhone){
		ReplyMap replyMap = new ReplyMap();
		if(StringUtils.isBlank(phone) && StringUtils.isBlank(tag) && StringUtils.isBlank(notPhone)){
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请输入查询的关键号码");
			return replyMap.toJson();
		}
		if(ParamUtil.checkParamIsNull(cid)){
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请重新扫描二维码");
			return replyMap.toJson();
		}
		if(pageNo == null){
			pageNo = 0;
		}
		if(StringUtils.isBlank(status)){
			status = "private";
		}
		Integer pageSize = 10;
		replyMap = indexService.getData(phone, cid, pageNo, pageSize, status, tag, notPhone);
		return replyMap.toJson();
	}
	
}
