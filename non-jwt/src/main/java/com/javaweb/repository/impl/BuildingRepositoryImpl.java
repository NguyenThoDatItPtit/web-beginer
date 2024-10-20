package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	
	String url = "jdbc:mysql://localhost:3306/estate";
    String uname = "root";
    String pass = "12345";

	@Override
	public List<BuildingEntity> findAll(String name, Long districtid) {
		String sql = "select * from building where name like '%" + name + "%'";
    	List<BuildingEntity> result = new ArrayList<>();
    	try(Connection conn = DriverManager.getConnection(url, uname, pass);
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery(sql);){
    		while(rs.next()) {
    			BuildingEntity building = new BuildingEntity();
    			building.setName(rs.getString("name"));
    			building.setStreet(rs.getString("street"));
    			building.setWard(rs.getString("ward"));
    			result.add(building);
    		}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("connected database failed.....");
    	}
    	return result;
	}
	
}