package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ModificarLibro {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try( Scanner sc = new Scanner(System.in)) {
		
			
			System.out.println("Dime el ID del libro a modificar:");
			int id = Integer.parseInt(sc.nextLine());
			
			System.out.println("Dime el numero de paginas:");
			int num = Integer.parseInt(sc.nextLine());
			
			System.out.println("Dime el nuevo titulo:");
			String titulo = sc.nextLine();
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gravity_books", "root", "root");
			String sql = " UPDATE book SET title = ? , num_pages = ? WHERE book_id = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			// sustituit ? de la SQL por los valores solicitados con el Scanner
			pst.setString(1, titulo); 
			pst.setInt(2, num); 
			pst.setInt(3, id); 
			
			int filasCambiadas = pst.executeUpdate(); 
			
			if ( filasCambiadas == 1) {
				System.out.println("Libro modificado con exito");
			}else {
				System.out.println("Lo sentimos, no hay libros con el ID = " + id);
			}
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

