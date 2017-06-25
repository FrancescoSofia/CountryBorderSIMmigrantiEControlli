package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Confine;
import it.polito.tdp.borders.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BordersDAO {
	
	public List<Country> loadAllCountries(Map<Integer, Country> mapStati) {
		
		String sql = 
				"SELECT ccode,StateAbb,StateNme " +
				"FROM country " +
				"ORDER BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
				mapStati.put(c.getcCode(), c);
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	
public List<Confine> getConfini(Map<Integer, Country> mapStati,int anno,Map<String,Confine> mapConfini) {
		
		String sql = "SELECT state1no,state2no,year,conttype FROM contiguity WHERE year <= ? and conttype = 1" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery() ;
			
			List<Confine> list = new LinkedList<Confine>() ;
			
			while( rs.next() ) {
				Confine c = mapConfini.get(rs.getString("state1no")+rs.getString("state2no"));
				if(c ==null){
				 c = new Confine(mapStati.get(rs.getInt("state1no")),mapStati.get(rs.getInt("state2no")),rs.getInt("year"),rs.getInt("conttype"));
				}
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
		
	
	
}
