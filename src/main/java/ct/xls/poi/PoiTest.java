package ct.xls.poi;

import ct.regularexpression.RegularExpression;
import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @program: CTProject
 * @description: Poix读写Excel
 * @author: chentao
 * @create: 2020-10-22 16:26
 **/

public class PoiTest {
    public static void main(String[] args) throws IOException {
        PoiTest poiTest = new PoiTest();
//        poiTest.writeExcel();
//        poiTest.readExcel();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a",11111);
        map.put("b","b");
        map.put("c",new Date());
        map.put("e","new Date()");
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        for(int i = 0 ; i < 3; i++){
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("a",i);
            m.put("b","b"+i);
            m.put("c",new Date());
            m.put("d",i);
            list.add(m);
        }
        map.put("d",list);
        File templateFile = new File(PoiTest.class.getResource("").getPath() + "template.xls");
        File testFile = new File(PoiTest.class.getResource("").getPath() + "test.xls");
        poiTest.writeTemplateExcel(map, templateFile, testFile);

    }

    /**
     *  模板excel内容填充
     *  只支持 ${xxx} 和 ${xxx.yyy}
     *  map中的Object只支持 String, number, List, Boolean, Date, Calendar, LocalDateTime, RichTextString, LocalDate
     *  日期和数字的格式需要在模板中设置
     *  formula只支持list的单行内元素的计算，而且需要在模板中设置
     * @param map
     * @param file
     * @throws IOException
     */
    private void writeTemplateExcel(Map<String, Object> map, File file, File outFile) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        // 遍历sheet
        while (sheetIterator.hasNext()) {
            HSSFSheet hssfSheet = (HSSFSheet) sheetIterator.next();
            Iterator<Row> rowIterator = hssfSheet.iterator();
            // 用于记录增加的行
            Map<String, List<HSSFRow>> temp = new HashMap<String, List<HSSFRow>>();
            // 遍历row
            while (rowIterator.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                Iterator<Cell> cellIterator = hssfRow.iterator();
                // 遍历cell
                while (cellIterator.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) cellIterator.next();
                    int hssfCellIndex = hssfCell.getColumnIndex();
                    CellType cellType = hssfCell.getCellType();
                    //刷新公式单元格的值
                    if(CellType.FORMULA.equals(cellType)){
                        hssfCell.setCellFormula(hssfCell.getCellFormula());
                        continue;
                    }
                    if (!CellType.STRING.equals(cellType)) {
                        continue;
                    }
                    String cellText = hssfCell.getStringCellValue();
                    String matcher = RegularExpression.PLACEHOLDER.matcherFirst(cellText, 1);
                    if(matcher == null) {
                        continue;
                    }
                    if (matcher.indexOf(".") < 0) { // String ,int ,date 等类型
                        Object cellValue = map.get(matcher);
                        setCellValue(cellValue, hssfCell);
                    } else { // List
                        String[] arr = matcher.split("\\.");
                        Object cellValue = map.get(arr[0]);
                        if (cellValue == null) {
                            hssfSheet.removeRow(hssfRow);
                        } else if (cellValue instanceof List) {
                            List list = (List) cellValue;
                            long size = list.size();
                            if (size == 0) {
                                hssfSheet.removeRow(hssfRow);
                            } else {
                                if (temp.get(arr[0]) != null) {
                                    for (int i = 0; i < size; i++) {
                                        HSSFRow tempRow = null;
                                        if (i > 0) {
                                            tempRow =  temp.get(arr[0]).get(i - 1);
                                        }else {
                                            tempRow = hssfRow;
                                        }
                                        HSSFCell tempCell = tempRow.getCell(hssfCellIndex);
                                        Map<String, Object> objectMap = (Map<String, Object>) list.get(i);
                                        setCellValue(objectMap.get(arr[1]), tempCell);
                                    }
                                } else {
                                    int rowNum = hssfRow.getRowNum();
                                    List<HSSFRow> hssfRowList = new ArrayList<HSSFRow>();
                                    for (int i = 0; i < size; i++) {
                                        HSSFRow tempRow = null;
                                        if (i > 0) {
                                            tempRow = hssfSheet.createRow(rowNum + i);
                                            if(hssfRow.getRowStyle()!=null){
                                                tempRow.setRowStyle(hssfRow.getRowStyle());
                                            }
                                            for( int j = 0; j < hssfRow.getLastCellNum(); j++){
                                                HSSFCell tempCell = tempRow.createCell(j);
                                                CellType tempCellType = hssfRow.getCell(j).getCellType();
                                                if(CellType.FORMULA.equals(tempCellType)){
                                                    tempCell.setCellFormula(copyCellFormula(rowNum, rowNum+i, hssfRow.getCell(j).getCellFormula()));
                                                }else {
                                                    tempCell.setCellType(tempCellType);
                                                }
                                                tempCell.setCellStyle(hssfRow.getCell(j).getCellStyle());
                                            }
                                            hssfRowList.add(tempRow);
                                        }else {
                                            tempRow = hssfRow;
                                        }
                                        Map<String, Object> objectMap = (Map<String, Object>) list.get(i);
                                        setCellValue(objectMap.get(arr[1]), tempRow.getCell(hssfCellIndex));
                                    }
                                    temp.put(arr[0], hssfRowList);
                                }
                            }
                        } else {
                            hssfSheet.removeRow(hssfRow);
                        }
                    }
                }
            }
        }
        FileOutputStream xlsStream = new FileOutputStream(outFile);
        workbook.write(xlsStream);
        workbook.close();
    }

    public void writeExcel() throws IOException {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");

        for (int row = 0; row < 10; row++) {
            HSSFRow rows = sheet.createRow(row);
            for (int col = 0; col < 10; col++) {
                // 向工作表中添加数据
                rows.createCell(col).setCellValue("data" + row + col);
            }
        }
        File xlsFile = new File(PoiTest.class.getResource("").getPath() + "poi.xls");
        FileOutputStream xlsStream = new FileOutputStream(xlsFile);
        workbook.write(xlsStream);
        workbook.close();
    }

    public void readExcel() throws IOException {
        File xlsFile = new File(PoiTest.class.getResource("").getPath() + "poi.xls");
        // 获得工作簿
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        // 遍历工作表
        for (int i = 0; i < sheetCount; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            // 获得行数
            int rows = sheet.getLastRowNum() + 1;
            // 获得列数，先获得一行，在得到改行列数
            Row tmp = sheet.getRow(0);
            if (tmp == null) {
                continue;
            }
            int cols = tmp.getPhysicalNumberOfCells();
            // 读取数据
            for (int row = 0; row < rows; row++) {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cols; col++) {
                    System.out.printf("%10s", r.getCell(col).getStringCellValue());
                }
                System.out.println();
            }
        }
        workbook.close();
    }

    public void setCellValue( Object cellValue, HSSFCell hssfCell ){
        if(cellValue == null) {
            hssfCell.setBlank();
        }else if( cellValue instanceof Boolean ){
            hssfCell.setCellValue((Boolean)cellValue);
            hssfCell.setCellType(CellType.BOOLEAN);
        }else if( cellValue instanceof Date){
            hssfCell.setCellValue((Date)cellValue);
            hssfCell.setCellType(CellType.NUMERIC);
        }else if( cellValue instanceof Calendar ){
            hssfCell.setCellValue((Calendar)cellValue);
            hssfCell.setCellType(CellType.NUMERIC);
        }else if( cellValue instanceof LocalDateTime ){
            hssfCell.setCellValue((LocalDateTime)cellValue);
            hssfCell.setCellType(CellType.NUMERIC);
        }else if( cellValue instanceof LocalDate){
            hssfCell.setCellValue((LocalDate)cellValue);
            hssfCell.setCellType(CellType.NUMERIC);
        }else if( cellValue instanceof RichTextString ){
            hssfCell.setCellValue((RichTextString)cellValue);
            hssfCell.setCellType(CellType.STRING);
        }else {
            hssfCell.setCellValue(cellValue.toString());
        }
    }


    /**
     * 要复制基础行的公式，需要将基础行公式中的行号改为当前行的行号
     * 这个方法只支持单行行内计算的公式，涉及到多行的公式情况太复杂，无法复制
     * 公式中的行号 = HSSFRow的行号 +1
     * @param baseRowNum   基础行
     * @param currentRowNum   当前行
     * @param cellFormula   公式
     * @return
     */
    private String copyCellFormula(int baseRowNum, int currentRowNum,  String cellFormula) {

        List<String> columnRowList = RegularExpression.EXCEL_CELL.matcher(cellFormula,0);
        List<String> columnList = RegularExpression.EXCEL_CELL.matcher(cellFormula,1);
        List<String> rowList = RegularExpression.EXCEL_CELL.matcher(cellFormula,2);
        for(int i = 0; i < rowList.size(); i++){
            if(Integer.parseInt(rowList.get(i)) == (baseRowNum+1)){
                cellFormula = cellFormula.replaceAll(columnRowList.get(i),columnList.get(i) + (currentRowNum+1));
            }
        }
        return cellFormula;
    }
}
