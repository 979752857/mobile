package com.tendy.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String SUFFIX_XLS = ".xls";
    private static final String SUFFIX_XLSX = ".xlsx";

    /**
     * resolve data of excel file
     * @param excelFile
     * @return Map key:sheet name value:data
     * @throws Exception
     */
    public static Map<String,List<String[]>> readExcelProcessFunc(MultipartFile excelFile) throws Exception {
		File cutMoneyReadFile = new File(System.getProperty("java.io.tmpdir"), TimeUtil.formatDate(new Date(), TimeUtil.YYYYMMDDHHMMSS)+ FileUtil.getExtensionName(excelFile.getOriginalFilename()));
		if (!cutMoneyReadFile.exists()){
			cutMoneyReadFile.mkdirs();
		}
		excelFile.transferTo(cutMoneyReadFile);
		Map<String,List<String[]>> dataInfo = readExcelProcessFunc(cutMoneyReadFile);
		logger.info("ExcelUtil.readExcelProcessFunc delete file,fileName:{},result:{}",cutMoneyReadFile.getAbsolutePath(),cutMoneyReadFile.delete());
		return dataInfo;
	}

    public static Map<String,List<String[]>> readExcelProcessFunc(File excelFile) throws Exception {
		//store data of the excel
    	Map<String,List<String[]>> data = new HashMap<String,List<String[]>>();

        InputStream input = new FileInputStream(excelFile);

        String fileName = excelFile.getName();
        Workbook workbook = null;
        if (fileName.toLowerCase().endsWith(SUFFIX_XLS)) {
            workbook = new HSSFWorkbook(input);
        } else if (fileName.toLowerCase().endsWith(SUFFIX_XLSX)) {
            workbook = new XSSFWorkbook(input);
        } else {
        	throw new RuntimeException("excel文档格式不正确");
        }

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
			//store data of one sheet
			List<String[]> sheetData = new ArrayList<String[]>();
            for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
            	Row row = sheet.getRow(j);
            	if (row == null) {
            		continue;
            	}
            	short cellNum = row.getLastCellNum();
            	if (cellNum < 1) {
            		continue;
            	}
				//store data of one row
				String[] rowData = new String[cellNum];
            	for (int k = 0; k < cellNum; k++) {
            		Cell cell = row.getCell(k);
                    if (cell != null) {
                    	String cellValue = null;
                    	switch (cell.getCellType()) {
                    	case Cell.CELL_TYPE_STRING:
                    		cellValue = cell.getStringCellValue();
                    		break;
                    	case Cell.CELL_TYPE_NUMERIC:
                    		cellValue = new BigDecimal(cell.getNumericCellValue()).toPlainString();
                    		break;
                    	case Cell.CELL_TYPE_BOOLEAN:
                    		cellValue = Boolean.toString(cell.getBooleanCellValue());
                    		break;
                    	case Cell.CELL_TYPE_BLANK:
                    		cellValue = "";
                    		break;
                    	case Cell.CELL_TYPE_FORMULA:
							try {
								cellValue = String.valueOf(cell.getNumericCellValue());
							} catch (IllegalStateException e) {
								cellValue = String.valueOf(cell.getRichStringCellValue());
							}
							break;
                    	case Cell.CELL_TYPE_ERROR:
                    		cellValue = Byte.toString(cell.getErrorCellValue());
                    		break;
                    	default:
                    		cellValue = cell.toString();
                    	}
                        rowData[k] = cellValue;
                     }
                }
                sheetData.add(rowData);
            }
            data.put(sheet.getSheetName(), sheetData);
        }
        return data;
    }

	/**
	 * 读取excel文件返回每行数据列表
	 * @param excelFile
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> readExcelReturnList(File excelFile, MultipartFile multipartFile) throws Exception {
		Map<String, List<String[]>> readmap = null;
		if(excelFile != null){
			readmap = ExcelUtil.readExcelProcessFunc(excelFile);
		}else{
			readmap = ExcelUtil.readExcelProcessFunc(multipartFile);
		}
		logger.info("ExcelUtil.readExcelReturnList delete file,fileName:{},result:{}",excelFile.getAbsolutePath(),excelFile.delete());
		List<String[]> list = new ArrayList<String[]>();
		for(Map.Entry<String, List<String[]>> m : readmap.entrySet()){
			if(!m.getValue().isEmpty()){
				list.addAll(m.getValue());
			}
		}
		return list;
	}

	/**
	 * 获取excel表行数
	 * @param excelFile
	 * @return
	 */
	public static int getTotleRowNum(File excelFile) throws Exception {
		InputStream input = new FileInputStream(excelFile);
		String fileName = excelFile.getName();
		Workbook workbook = null;
		if (fileName.toLowerCase().endsWith(SUFFIX_XLS)) {
			workbook = new HSSFWorkbook(input);
		} else if (fileName.toLowerCase().endsWith(SUFFIX_XLSX)) {
			workbook = new XSSFWorkbook(input);
		} else {
			throw new RuntimeException("excel文档格式不正确");
		}
		//获取第一个sheet
		Sheet sheet = workbook.getSheetAt(0);
		int endRowNum = sheet.getLastRowNum();
		return endRowNum;
	}

	/**
	 * 读取第一个sheet区间内的数据
	 * @param excelFile
	 * @param startRowNum
	 * @param endRowNum
	 * @return Map key:sheet name value:data
	 * @throws Exception
	 */
	public static List<String[]> readExcelOneSheet(File excelFile, int startRowNum, int endRowNum) throws Exception {
		//store data of the excel
		Map<String,List<String[]>> data = new HashMap<String,List<String[]>>();

		InputStream input = new FileInputStream(excelFile);

		String fileName = excelFile.getName();
		Workbook workbook = null;
		if (fileName.toLowerCase().endsWith(SUFFIX_XLS)) {
			workbook = new HSSFWorkbook(input);
		} else if (fileName.toLowerCase().endsWith(SUFFIX_XLSX)) {
			workbook = new XSSFWorkbook(input);
		} else {
			throw new RuntimeException("excel文档格式不正确");
		}

		Sheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			return null;
		}
		//store data of one sheet
		List<String[]> sheetData = new ArrayList<String[]>();
		for (int j = startRowNum; j < endRowNum + 1; j++) {
			Row row = sheet.getRow(j);
			if (row == null) {
				continue;
			}
			short cellNum = row.getLastCellNum();
			if (cellNum < 1) {
				continue;
			}
			//store data of one row
			String[] rowData = new String[cellNum];
			for (int k = 0; k < cellNum; k++) {
				Cell cell = row.getCell(k);
				if (cell != null) {
					String cellValue = null;
					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							cellValue = new BigDecimal(cell.getNumericCellValue()).toPlainString();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							cellValue = Boolean.toString(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_BLANK:
							cellValue = "";
							break;
						case Cell.CELL_TYPE_FORMULA:
							cellValue = cell.getCellFormula();
							break;
						case Cell.CELL_TYPE_ERROR:
							cellValue = Byte.toString(cell.getErrorCellValue());
							break;
						default:
							cellValue = cell.toString();
					}
					rowData[k] = cellValue;
				}
			}
			sheetData.add(rowData);
		}
		return sheetData;
	}

	public static void main(String[] arg){
		try {
		    File file = new File("D:/test.xlsx");
			int total = getTotleRowNum(file);
			logger.info("文件总行数："+total);
			int startRowNum = 0;
			List<String[]> list = readExcelOneSheet(file, startRowNum, total-1);
			for(int i = 0; i<list.size(); i++){
				String[] strings = list.get(i);
				String phone = strings[0];
				ItemRule item = MobileRule.checkPhone(phone);
				if(item != null){
					logger.info("第"+(startRowNum+i)+"行数据："+phone+"   tag:"+item.getTag());
				}else{
					logger.info("第"+(startRowNum+i)+"行数据："+phone);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
