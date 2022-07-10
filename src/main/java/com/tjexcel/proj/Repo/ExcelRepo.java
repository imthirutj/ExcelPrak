package com.tjexcel.proj.Repo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tjexcel.proj.Entity.ExcelEntity;


@Repository
//public interface ExcelRepo  extends CrudRepository<ExcelEntity, Integer>{
//
//}
public class ExcelRepo{
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	  
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
//	    this.jdbcTemplate = jdbcTemplate;  
//	}  
//	
 
	public void savez(ExcelEntity e) {
		  String query="insert into exceltb values(?,?,?,?,?,?,?)";  
	
		 int r= jdbcTemplate.update(query,e.getTOTALCOST(),e.getCLAIMNUMBER(),e.getVENDOR(),e.getCHECKNO(),e.getCHECKDATEPAID(),e.getINDICATOR(),e.getFILENAME());  
		  System.out.println("Heyyy"+ r);
	}
}