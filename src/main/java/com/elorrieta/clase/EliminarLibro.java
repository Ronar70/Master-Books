package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * No se puede eliminar un Libro porque existe en la tabla `book_author` una CONSTRAINT `fk_ba_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`))
 * Vamos a cambiar por DELETE CASCADE ejecutando el siguiente script:
 * 
 * ALTER TABLE `book_author` DROP FOREIGN KEY `fk_ba_book`; ALTER TABLE `book_author` ADD CONSTRAINT `fk_ba_book` FOREIGN KEY (`book_id`) REFERENCES `book`(`book_id`) ON DELETE CASCADE ON UPDATE RESTRICT;
 *
 */
public class EliminarLibro {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try( Scanner sc = new Scanner(System.in)) {
		
			
			System.out.println("Dime el ID del libro eliminar:");
			int id = Integer.parseInt(sc.nextLine());
			
						
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gravity_books", "root", "root");
			String sql = " DELETE FROM book WHERE book_id = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			// sustituit ? de la SQL por los valores solicitados con el Scanner
			pst.setInt(1, id); 
			
			int filasCambiadas = pst.executeUpdate(); 
			
			if ( filasCambiadas == 1) {
				System.out.println("Libro eliminado con exito");
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

