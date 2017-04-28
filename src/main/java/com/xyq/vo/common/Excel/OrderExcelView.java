package com.xyq.vo.common.Excel;

import com.xyq.vo.common.Tools.Tools;
import com.xyq.vo.model.Order;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderExcelView extends AbstractExcelView {

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
		sheet = workbook.createSheet("订单");
		
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
		List<Order> orderList = (List<Order>) model.get("orderList");
		int sportCount = orderList.size();
		for(int i=0; i<sportCount; i++){
			Order order = orderList.get(i);
			String loginname = order.getUser().getLogin_name();
			cell = getCell(sheet, i+1, 0);
			cell.setCellStyle(contentStyle);
			setText(cell,loginname);
			
			String venuename = order.getVenue().getVenue_name();
			cell = getCell(sheet, i+1, 1);
			cell.setCellStyle(contentStyle);
			setText(cell,venuename);

			String sportname = order.getSport_name();
			cell = getCell(sheet, i+1, 2);
			cell.setCellStyle(contentStyle);
			setText(cell,sportname);
			
			String ordertimes = order.getOrder_time();
			cell = getCell(sheet, i+1, 3);
			cell.setCellStyle(contentStyle);
			setText(cell,ordertimes);

			String orderdate = order.getOrder_date();
			cell = getCell(sheet, i+1, 4);
			cell.setCellStyle(contentStyle);
			setText(cell,orderdate);
		}
		
	}

}
