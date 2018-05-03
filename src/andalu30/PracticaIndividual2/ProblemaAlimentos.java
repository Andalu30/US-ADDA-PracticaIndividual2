package andalu30.PracticaIndividual2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ProblemaAlimentos {

	public static void main(String[] args) {
		
		String path = "./ficheros/alimentos.txt"; //Path del archivo con los datos iniciales del problema
		
		List<Nutriente> nutrientes = generaDatosInicialesNutrientes(path);
		List<IngredienteActivo> ingredientesActivos = generaDatosInicialesIngredientesActivos(path);
		
		System.out.println("\tDEBUG:"+nutrientes);
		System.out.println("\tDEBUG:"+ingredientesActivos);

		//Check correlacion de los datos iniciales
		if (nutrientes.size()!=ingredientesActivos.get(0).getCantidadNutrientes().size()) {
			System.err.println("Se ha producido un error al cargar los datos iniciales. O faltan/sobran nutrientes o la cantidadNutrientes de los ingredientes no es correcta");
			System.exit(-1);
		}
		
	
	}
	
	
	
	
	
	public static List<Nutriente> generaDatosInicialesNutrientes(String path){
		List<Nutriente> res = new ArrayList<>();
		
		try {
			File archivo = new File(path);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);

	        String linea;
	        String[] div = null;
			while((linea=br.readLine())!=null) {
	        	if (linea.startsWith("Nutrientes,")) {
					div = linea.split(",");
					for (int i = 1; i < div.length; i++) {
						Nutriente n = new Nutriente(new Integer(div[i]));
						res.add(n);
					}   	
	        	}	        	
			}
			fr.close();
			
		} catch (Exception e) {
			System.err.println("Se ha producido un error al inicializar los datos de los nutrientes: "+e.getMessage());
		}
		return res;
	}
	
	public static List<IngredienteActivo> generaDatosInicialesIngredientesActivos(String path){
		List<IngredienteActivo> res = new ArrayList<>();
				
			try {
				File archivo = new File(path);
				FileReader fr = new FileReader(archivo);
				BufferedReader br = new BufferedReader(fr);
	
		        String linea;
		        String[] div = null;
				while((linea=br.readLine())!=null) {
		        	if (linea.startsWith("IngredienteActivo,")) {
						div = linea.split(",");
						String[] nut = div[1].split("-");
						
						List<Double> cantidadNutrientes = new ArrayList<>();
						for (String s : nut) {
							cantidadNutrientes.add(new Double(s));
						}
					
						IngredienteActivo ia = new IngredienteActivo(cantidadNutrientes, new Double(div[2]));
						res.add(ia);
		        	}	        	
				}
				fr.close();
			} catch (Exception e) {
				System.err.println("Se ha producido un error al inicializar los datos de los ingredientes activos: "+e.getMessage());
			}
			return res;	
		}
	
	
	
	
	
}
