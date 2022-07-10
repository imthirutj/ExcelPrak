package com.tjexcel.proj.DAO;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjexcel.proj.Entity.ExcelEntity;
import com.tjexcel.proj.Repo.ExcelRepo;

@Service
public class ExcelService {

		@Autowired
		private ExcelRepo excelRepo;
	public void savez(ExcelEntity e) {
//		ExcelEntity e = new ExcelEntity("tOTALCOST","cLAIMNUMBER",  "vENDORs",  "cHECKNO", new Date(1/11/2022),
//				 "iNDICATOR",  "fILENAME");
		
		excelRepo.savez(e);
	}
}
