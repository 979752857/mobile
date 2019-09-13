package com.tendy.controller;

import com.tendy.common.BusinessConstants;
import com.tendy.common.ReplyMap;
import com.tendy.dao.DataMapperUtil;
import com.tendy.dao.bean.MobileBussiness;
import com.tendy.dao.bean.UserAccountPhone;
import com.tendy.service.PhoneInfoService;
import com.tendy.utils.DataTablesUtil;
import com.tendy.utils.ExcelUtil;
import com.tendy.utils.FileUtil;
import com.tendy.utils.JsonMapper;
import com.tendy.utils.ParamUtil;
import com.tendy.utils.StringUtils;
import com.tendy.utils.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/phoneInfo")
public class PhoneInfoController {

	private Logger logger = Logger.getLogger(PhoneInfoController.class);

	@Autowired
	private PhoneInfoService phoneInfoService;

	/**
	 * 分页查询显示table实例
	 *
	 * @param aoData
	 * @return
	 */
	@RequestMapping(value = "/phoneList", method = RequestMethod.GET)
	@ResponseBody
	public String getPhoneList(@RequestParam(value = "aoData") String aoData, HttpSession httpSession) {
		//解析参数
		Map<String, Object> map = DataTablesUtil.getParamMap(aoData);
		//插件返回的起始行数
		Integer iDisplayStart = (Integer) map.get("iDisplayStart");
		//插件返回的每页长度
		Integer iDisplayLength = (Integer) map.get("iDisplayLength");
		List<Map<String, Object>> list = new ArrayList<>();
		/*----------   数据库操作返回list数据   ----------*/
		String keyword = String.valueOf(map.get("keyword"));
		String status = String.valueOf(map.get("status"));
		String tag = String.valueOf(map.get("tag"));
		String type = String.valueOf(map.get("type"));
		String notPhone = String.valueOf(map.get("notPhone"));
		String position = String.valueOf(map.get("position"));
		Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
		Integer cityId = Integer.valueOf(String.valueOf(httpSession.getAttribute("city")));
		String content = String.valueOf(httpSession.getAttribute("content"));
		Map<String, Object> resultMap = phoneInfoService.getDataDetail(keyword, iDisplayStart, iDisplayLength, status, businessId, tag, notPhone, cityId, position, content, type);
		Integer total = (Integer) resultMap.get("total");
		if (resultMap.get("list") != null) {
			list = (List<Map<String, Object>>) resultMap.get("list");
		}
		/*------------------------------------------------*/
		String result = DataTablesUtil.getResultString(list, total, map);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/updatePhone", method = RequestMethod.GET)
	public String updatePhone(@RequestParam("id") String id, @RequestParam("phone") String phone,
							  @RequestParam("url") String url, @RequestParam("price") String price,
							  @RequestParam("status") String status, HttpSession httpSession) {
		ReplyMap replyMap = new ReplyMap();
		if (ParamUtil.checkParamIsNull(phone)) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
			return replyMap.toJson();
		}
		UserAccountPhone accountPhone = new UserAccountPhone();
		if (StringUtils.isNotBlank(id)) {
			accountPhone.setId(Long.valueOf(id));
		}
		accountPhone.setPhone(phone);
		if (!StringUtils.isBlank(url)) {
			accountPhone.setUrl(url);
		}
		if (!StringUtils.isBlank(price)) {
			accountPhone.setPrice(new BigDecimal(price));
		}
		if (!StringUtils.isBlank(status)) {
			accountPhone.setStatus(status);
		}
		Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
		replyMap = phoneInfoService.updateUserAccountPhone(accountPhone, businessId);
		logger.info("PhoneInfoController.updatePhone   accountPhone:" + JsonMapper.toJson(accountPhone) + "   replyMap:" + replyMap.toJson());
		return replyMap.toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/phoneInfo", method = RequestMethod.GET)
	public String phoneInfo(@RequestParam("phone") String phone, HttpSession httpSession) {
		ReplyMap replyMap = new ReplyMap();
		if (StringUtils.isBlank(phone) || ParamUtil.checkPhoneIllegal(phone)) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
			return replyMap.toJson();
		}
		Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
		replyMap = phoneInfoService.getPhoneInfo(phone, businessId);
		return replyMap.toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/lockedPhone", method = RequestMethod.GET)
	public String lockedPhone(@RequestParam("phone") String phone, @RequestParam("status") String status,
							  HttpSession httpSession) {
		ReplyMap replyMap = new ReplyMap();
		if (ParamUtil.checkParamIsNull(phone, status)) {
			replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
			return replyMap.toJson();
		}
		Integer businessId = Integer.valueOf(String.valueOf(httpSession.getAttribute("id")));
		replyMap = phoneInfoService.updateLockPhone(phone, status, businessId);
		logger.info("PhoneInfoController.lockedPhone   phone:" + phone + "   status:" + status + "   replyMap:" + replyMap.toJson());
		return replyMap.toJson();
	}

	@RequestMapping(value = "/readExcel", method = RequestMethod.POST)
	@ResponseBody
	public String readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile, @RequestParam(value = "busName") String busName, HttpSession httpSession) {
		ReplyMap replyMap = new ReplyMap();
		try {
			if (excelFile == null || ParamUtil.checkParamIsNull(busName)) {
				replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, BusinessConstants.PARAM_ERROR_MSG);
				return replyMap.toJson();
			}
			MobileBussiness business = DataMapperUtil.selectMobileBussinessByName(busName);
			if (business == null) {
				replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "请输入运营商账号");
				return replyMap.toJson();
			}
			File excelReadFile = new File(System.getProperty("java.io.tmpdir"),
					TimeUtil.formatDate(new Date(), TimeUtil.YYYYMMDDHHMMSS) + FileUtil.getExtensionName(excelFile.getOriginalFilename()));
			if (!excelReadFile.exists()) {
				excelReadFile.mkdirs();
			}
			excelFile.transferTo(excelReadFile);
			int successNum = 0;
			int failNum = 0;
			int processRow = 0;
			//excel格式：phone,price
			List<String[]> infoList = ExcelUtil.readExcelReturnList(excelReadFile, null);
			if (CollectionUtils.isEmpty(infoList)) {
				replyMap.fail(BusinessConstants.PARAM_ERROR_CODE, "没有获取到excel的内容");
				return replyMap.toJson();
			}
			List<Map<String, String>> failList = new ArrayList<>();
			replyMap.put("failList", failList);
			replyMap.put("processRow", processRow);
			replyMap.put("successNum", successNum);
			replyMap.put("failNum", failNum);
			//每次截取100个进行插入操作
			int processSize = 100;
			List<String[]> processList = new ArrayList<>();
			for (int i = infoList.size() - 1; i >= 0; i--) {
				String[] item = infoList.get(i);
				processList.add(item);
				infoList.remove(i);
				if (processList.size() == processSize) {
					replyMap = phoneInfoService.insertPhoneProcessExcel(processList, business.getId(), business.getCityId(), replyMap);
					processList.clear();
					Thread.sleep(1000);
					logger.info("PhoneInfoController readExcel  replyMap:" + replyMap.toJson());
				}
			}
			if (!CollectionUtils.isEmpty(processList)) {
				replyMap = phoneInfoService.insertPhoneProcessExcel(processList, business.getId(), business.getCityId(), replyMap);
				processList.clear();
			}
			logger.info("PhoneInfoController readExcel  处理完毕  replyMap:" + replyMap.toJson());
			replyMap.success();
		} catch (Exception e) {
			logger.error("PhoneInfoController readExcel is error fileName:" + excelFile.getOriginalFilename(), e);
			replyMap.fail(BusinessConstants.SERVER_ERROR_CODE, BusinessConstants.SERVER_ERROR_MSG);
		}
		logger.info("PhoneInfoController readExcel  busName:" + busName + "   replyMap:" + JsonMapper.toJson(replyMap));
		return replyMap.toJson();
	}
}