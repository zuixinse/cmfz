package com.baizhi.test;

import com.baizhi.annotation.UserAnnotation;
import com.baizhi.testEntity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PoiTest {
    @Test
    public void export() throws IOException {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(Font.COLOR_RED);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("姓名");
        workbook.write(new FileOutputStream(new File("f:/用户.xls")));






    }

    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        List<User> users=new ArrayList<>();
        users.add(new User("1","小黑","123456",new Date()));
        users.add(new User("2","小白","123456",new Date()));
        users.add(new User("3","小绿","123456",new Date()));
        users.add(new User("4","小红","123456",new Date()));
        users.add(new User("5","小蓝","123456",new Date()));
        users.add(new User("6","小黄","123456",new Date()));
        users.add(new User("7","小贱人","123456",new Date()));

        Workbook workbook=new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("第一张");
        Class<User> userClass=User.class;
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        Field[] declaredFields = userClass.getDeclaredFields();
        String[] titles=new String[declaredFields.length];
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            Annotation annotation = declaredFields[i].getAnnotation(UserAnnotation.class);
            Cell cell = row.createCell(i);
            cell.setCellValue(((UserAnnotation) annotation).name());
        }
        for (int i = 0; i < users.size(); i++) {
            User user=users.get(i);
            Row row1 = sheet.createRow(i + 1);

            for (int j = 0; j < declaredFields.length; j++) {
                String name="get"+declaredFields[j].getName().substring(0,1).toUpperCase()+declaredFields[j].getName().substring(1);
                Object invoke = user.getClass().getDeclaredMethod(name, null).invoke(user, null);
                if(invoke instanceof  Date){
                    Cell cell = row1.createCell(j);

                    cell.setCellValue((Date)invoke);
                    cell.setCellStyle(cellStyle);
                }else {
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(invoke.toString());
                }
            }
        }
        workbook.write(new FileOutputStream(new File("f:/用户.xls")));
    }


    @Test
    public void importE() throws Exception {
        Workbook workbook=new HSSFWorkbook(new FileInputStream("f:/用户.xls"));
        Sheet sheet = workbook.getSheet("第一张");
        List<User> users=new ArrayList<>();
        Class<? extends User> userClass=User.class;
        Field[] declaredFields = userClass.getDeclaredFields();
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i+1);
            User user=new User();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);

                String methodName="set"+declaredFields[j].getName().substring(0,1).toUpperCase()+declaredFields[j].getName().substring(1);
                Class<?> type = declaredFields[j].getType();
                if(type==Date.class){
                    user.setDate(cell.getDateCellValue());
                }else{
                    user.getClass().getDeclaredMethod(methodName,String.class).invoke(user,cell.getStringCellValue());
                }
            }
            users.add(user);
        }
        System.out.println(users);



    }

}
