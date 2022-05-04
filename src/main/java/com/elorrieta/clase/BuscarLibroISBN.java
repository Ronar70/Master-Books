package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BuscarLibroISBN {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try( Scanner sc = new Scanner(System.in)) {
		
			// 8987059752 es el ISBN del libro con id = 1
			System.out.println("Dime el ISBN del libro a buscar:");
			String isbn = sc.nextLine();
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gravity_books", "root", "root");
			String sql = " SELECT book_id, title" +
					     " FROM book " +  
						 " WHERE isbn13 = ? " + 
			             " ORDER BY book_id DESC LIMIT 100;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			
			pst.setString(1, isbn); // sustituir 1? de la SQL por el isbn leido por pantalla
			
			ResultSet rs = pst.executeQuery(); 
			
			boolean encontrado = false;
			
			while( rs.next() ) {				
				int id = rs.getInt("book_id");
				String titulo = rs.getString("title");						
				System.out.printf(" %5s %-25s  \n", id , titulo);	
				encontrado = true;
				
			}// while
			
			if (!encontrado) {
				System.out.println("Lo sentimos, no hay libros con el ISBN = " + isbn);
			}
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

