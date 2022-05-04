package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BuscarLibroAutor {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try( Scanner sc = new Scanner(System.in)) {
		
			// A. Merritt es autor con id = 3
			System.out.println("Dime el nombre del autor a buscar:");
			String autor = sc.nextLine();
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gravity_books", "root", "root");
			String sql = " SELECT b.book_id, title, author_name " +
					     " FROM book b, book_author ba, author a " +  
						 " WHERE b.book_id = ba.book_id AND ba.author_id = a.author_id " + 
					     " AND author_name = ? " +
			             " ORDER BY b.book_id DESC LIMIT 100;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			
			pst.setString(1, autor); // sustituir 1? de la SQL por el autor leido por pantalla
			
			ResultSet rs = pst.executeQuery(); 
			
			boolean encontrado = false;
			
			while( rs.next() ) {				
				int id = rs.getInt("book_id");
				String titulo = rs.getString("title");						
				System.out.printf(" %5s %-25s  \n", id , titulo);	
				encontrado = true;
				
			}// while
			
			if (!encontrado) {
				System.out.println("Lo sentimos, no hay libros escritos por " + autor);
			}
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

