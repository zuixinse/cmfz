package com.baizhi.util;

import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExportExcle {
    public static void expporet(String titles, String ids, HttpServletResponse response, List<User> users) throws IOException {
        String[] title = titles.split(",");
        String[] fields = ids.split(",");
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("第一张工作表");
        Row row = sheet.createRow(0);
        for (int i = 0; i < title.length; i++) {
            String tit = title[i];
            Cell cell = row.createCell(i);
            cell.setCellValue(tit);
        }
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);

        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            Class<? extends User> userClass = users.get(i).getClass();
            for (int j = 0; j < fields.length; j++) {
                String field = fields[j];
                Cell cell = row1.createCell(j);
                String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);

                try {
                    Object invoke = userClass.getDeclaredMethod(methodName, null).invoke(users.get(i), null);
                    if (invoke instanceof Date) {
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue((Date) invoke);
                    } else {

                        cell.setCellValue(invoke.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }
        String name = "用户表.xls";
        String fileName = "";
        try {
            fileName = new String(name.getBytes("UTF-8"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;fileName=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        workbook.write(response.getOutputStream());
    }

}
