package Ejercicios;

import org.junit.Test;

public class AddSongBySinatra extends ParentTestSinatra {
	
	@Test
	public void autenticacionPagina() {
		logIn(usuario,pwd);
	}
	
	@Test
	public void addSong() {
		logIn(usuario,pwd);
		agregarCancion(titulo,duracion,fecha,letra);
		existeCancion(titulo,duracion,fecha,letra);
	}
	
	@Test
	public void likeSong() {
		buscarCancion(titulo);
		darLike();
	}
		
	@Test
	public void editSong() {
		logIn(usuario,pwd);
		buscarCancion(titulo);
		editarCancion(tituloEditado);
		
	}
	
	
	@Test
	public void deleteSong() {
		logIn(usuario,pwd);
		buscarCancion(titulo);
		eliminarCancion(titulo);
				
	}

	


		
		
}















