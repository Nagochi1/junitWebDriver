package Ejercicios;

import org.junit.Test;

public class ImdbJUnit extends ParentTest {	
		
	@Test
	public void testMovie() {
		
		searchMovie(MOVIE); //Buscar pelicula
		validateMovie(MOVIE, YEAR); //Validar existencia de la pelicula
		openMovie(MOVIE, YEAR); //Abrir película buscada
		playTrailer(); //Reproducir trailer de la pelicula encontrada		
		
	}
}