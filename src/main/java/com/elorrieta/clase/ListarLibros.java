package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListarLibros {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try {
		
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gravity_books", "root", "root");
			String sql = " SELECT b.book_id, title, author_name " +
					     " FROM book b, book_author ba, author a " +  
						 " WHERE b.book_id = ba.book_id AND ba.author_id = a.author_id " + 
			             " ORDER BY b.book_id DESC LIMIT 100;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(); // ejecuto como si pulsara el rayo en workbench y recibo unos resultados "rs"
			while( rs.next() ) {
				
				int id = rs.getInt("book_id");
				String titulo = rs.getString("title");		
				String autor = rs.getString("author_name");
				System.out.printf(" %5s [%s] %-25s  \n", id , autor, titulo);				
				
			}// while
			
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

