package com.example.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		String today = jdbcTemplate.queryForObject("select now()", String.class);
		model.addAttribute("today", today);
		model.addAttribute("name", "한사람");
		
		List<String> tableNames = new ArrayList<>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("show tables");
			if(rs.next()) {
				do {
					tableNames.add(rs.getString(1));
				}while(rs.next());
			}
			rs.close();
			stmt.close();
			model.addAttribute("list", tableNames);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "home";
	}
	
}
