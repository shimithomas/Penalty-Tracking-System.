package com.penalty.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.penalty.bean.Penalty;

@Service
public class RecordPenaltyService {

	@Value("${penalty.file.path}")
	private String filePath;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ArrayList<Penalty> readPenaltyDetails(){

		ArrayList<Penalty> penaltyList = new ArrayList<Penalty>();

		try(FileInputStream file = new FileInputStream(new File(filePath));Workbook workbook = new XSSFWorkbook(file))
		{
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Penalty newPenalty = new Penalty();
				Row nextRow = iterator.next();

				String vin = nextRow.getCell(0).getStringCellValue();
				String ownerName  =  nextRow.getCell(1).getStringCellValue();
				Double penaltyAmount = nextRow.getCell(2).getNumericCellValue();
				Date violationDate =    nextRow.getCell(3).getDateCellValue();
				String violationType =    nextRow.getCell(4).getStringCellValue();

				logger.info("Values ---  "+vin+" ** "+ownerName+" ** "+penaltyAmount+" ** "+violationDate+" ** "+ violationType);
				newPenalty =  new Penalty(vin, ownerName, penaltyAmount, violationDate, violationType);

				penaltyList.add(newPenalty);
			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return penaltyList;
	}
}
