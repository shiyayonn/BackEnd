package com.prms.main.exporters;

import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.prms.main.models.Address;
import com.prms.main.models.Patient;

public class ExcelExporter{
	private XSSFWorkbook wb;
	private XSSFSheet ws;

	private List<Patient> records;
	private List<Address> addresses;

	public ExcelExporter(List<Patient> records, List<Address> addresses){
		this.records = records;
		this.addresses = addresses;
		wb = new XSSFWorkbook();
		ws = wb.createSheet("Patient Records");
	}

	private void writeHeader(){
		CellStyle s = wb.createCellStyle();
		XSSFFont f = wb.createFont();
		f.setBold(true);
		f.setFontHeight(14);
		s.setFont(f);

		Row r = ws.createRow(0);

		createCell(r, 0, "ID", s);   
		createCell(r, 1, "STATUS", s);   
		createCell(r, 2, "LAST_NAME", s);   
		createCell(r, 3, "FIRST_NAME", s);   
		createCell(r, 4, "MIDDLE_NAME", s);   
		createCell(r, 5, "BIRTHDATE", s);   
		createCell(r, 6, "GENDER", s);   
		createCell(r, 7, "EMAIL_ADDRESS", s);
		createCell(r, 8, "CONTACT_NUMBER", s);   	
	}

	private void writeData(){
		int rCount = 1;
		CellStyle s = wb.createCellStyle();
		XSSFFont f = wb.createFont();
		f.setFontHeight(11);
		s.setFont(f);

		for(Patient p : records){

			Row r = ws.createRow(rCount);

			createCell(r, 0, "p.getPatientId()", s);   
			createCell(r, 1, "p.getStatus()", s);   
			createCell(r, 2, "p.getLastName()", s);   
			createCell(r, 3, "p.getFirstName()", s);   
			createCell(r, 4, "p.getMiddleName()", s);   
			createCell(r, 5, "p.getBirthDate()", s);   
			createCell(r, 6, "p.getGender()", s);   
			createCell(r, 7, "p.getEmailAddress()", s);
			createCell(r, 8, "p.getContactNumber()", s);    

			for (Address a : addresses){
				int columnNo = 9;
				int aCounter = 1;

				if (a.getPatientId() == p.getPatientId()){
					Cell cell = ws.createRow(1).createCell(columnNo);
					
					if(cell.getStringCellValue() == ""){ 
						CellStyle s2 = wb.createCellStyle();
						XSSFFont f2 = wb.createFont();
						f2.setBold(true);
						f2.setFontHeight(16);
						s2.setFont(f2);
 
						createCell(ws.createRow(1), columnNo, "OTHER_ADDRESS_" + aCounter, s2);
						aCounter++;
					}

					createCell(r, 9, "a.getAddress()", s); 
					columnNo++;
				}	
			}
		}
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        ws.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date){
			cell.setCellValue((Date) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

	public void export(HttpServletResponse response) throws Exception{
		writeHeader();
		writeData();

		ServletOutputStream outputStream = response.getOutputStream();
		wb.write(outputStream);
		wb.close();
		outputStream.close();
	}

}