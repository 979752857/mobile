package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.service.IndexService;
import com.tendy.utils.ParamUtil;
import com.tendy.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
public class IndexController {

	private Logger logger = Logger.getLogger(IndexController.class);

	@Autowired
	private IndexService indexService;

	@RequestMapping(value = "/phoneList", method = {RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String phoneList(@RequestParam(value = "key") String phone, @RequestParam(value = "cid", required = false) String cid,
							@RequestParam(value = "no", required = false) Integer pageNo, @RequestParam(value = "status", required = false) String status,
							@RequestParam(value = "tag", required = false) String tag, @RequestParam(value = "notPhone", required = false) String notPhone,
							@RequestParam(value = "position", required = false) String position, @RequestParam(value = "type", required = false) String type) {
		ReplyMap replyMap = new ReplyMap();
		if (ParamUtil.checkParamIsNull(cid)) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请重新扫描二维码");
			return replyMap.toJson();
		}
		if (StringUtils.isBlank(phone)) {
			phone = null;
		}
		if (pageNo == null) {
			pageNo = 0;
		}
		if (StringUtils.isBlank(status)) {
			status = "private";
		}
		if (StringUtils.isBlank(position)) {
			position = null;
		}
		if (StringUtils.isBlank(type)) {
			type = null;
		}
		Integer pageSize = 10;
		replyMap = indexService.getData(phone, cid, pageNo, pageSize, status, tag, notPhone, position, type);
		logger.info("IndexController.phoneList   key:" + phone + "   cid:" + cid + "   pageNo:" + pageNo + "   status:" + status + "   tag:" + tag + "   notPhone:" + notPhone + "   position:" + position + "   type:" + type + "   replyMap:" + replyMap.toJson());
		return replyMap.toJson();
	}

	@RequestMapping(value = "/businessInfo", method = {RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String businessInfo(@RequestParam(value = "cid") String cid) {
		ReplyMap replyMap = new ReplyMap();
		if (StringUtils.isBlank(cid)) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
			return replyMap.toJson();
		}
		replyMap = indexService.getBusinessInfo(cid);
		logger.info("IndexController.businessInfo   cid:" + cid + "   replyMap:" + replyMap.toJson());
		return replyMap.toJson();
	}
}
