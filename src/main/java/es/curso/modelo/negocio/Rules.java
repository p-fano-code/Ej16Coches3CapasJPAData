package es.curso.modelo.negocio;

import es.curso.modelo.entidad.Coche;

public class Rules {
	
	/**
	 * Método que usaremos para verificar que la matricula de un coche
	 * que intentemos insertar tiene 7 caracteres exactos.
	 * @param coche
	 * @param matricula
	 * @return Devolverá true en caso de que los tenga, false en caso contrario.
	 */

	public boolean verificarMatricula(Coche coche, String matricula) {
		if(matricula.length() == 7) {
			return true;
		}else {
			System.out.println("LA MATRÍCULA DEBE CONTENER 7 CARACTERES");
			return false;
		}
	}
	
	/**
	 * Método que usaremos para verificar que la marca y el modelo de un coche
	 * que intentemos insertar no se encuentra vacío.
	 * @param coche
	 * @param marca
	 * @return Devolverá true en caso de que haya algo escrito, false en caso contrario.
	 */
	
	public boolean verificarMarcaYModelo(Coche coche, String marca) {
		if(marca.length() > 0) {
			return true;
		}else {
			System.out.println("LA MARCA Y EL MODELO NO DEBEN ESTAR VACIOS");
			return false;
		}
	}
}
