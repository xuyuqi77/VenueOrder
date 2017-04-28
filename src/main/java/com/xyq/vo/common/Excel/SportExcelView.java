package com.xyq.vo.common.Excel;

import com.xyq.vo.common.Tools.Tools;
import com.xyq.vo.model.Sport;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SportExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
									  HSSFWorkbook workbook, HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMddHHmmss");
		HSSFSheet sheet;
		HSSFCell cell;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		sheet = workbook.createSheet("项目");
		
		List<String> titles = (List<String>) model.get("titles");
		int len = titles.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		short width = 20,height=25*20;
		sheet.setDefaultColumnWidth(width);
		for(int i=0; i<len; i++){ //设置标题
			String title = titles.get(i);
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell,title);
		}
		sheet.getRow(0).setHeight(height);
		
		HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<Sport> sportList = (List<Sport>) model.get("sportList");
		int sportCount = sportList.size();
		for(int i=0; i<sportCount; i++){
			Sport sport = sportList.get(i);
			String sportname = sport.getSport_name();
			cell = getCell(sheet, i+1, 0);
			cell.setCellStyle(contentStyle);
			setText(cell,sportname);
			
			String venuename = sport.getVenue().getVenue_name();
			cell = getCell(sheet, i+1, 1);
			cell.setCellStyle(contentStyle);
			setText(cell,venuename);

			String opentimes = sport.getOpenning_times();
			cell = getCell(sheet, i+1, 2);
			cell.setCellStyle(contentStyle);
			setText(cell,opentimes);
			
			String sportnum = sport.getSport_num();
			cell = getCell(sheet, i+1, 3);
			cell.setCellStyle(contentStyle);
			setText(cell,sportnum);
		}
		
	}

}
