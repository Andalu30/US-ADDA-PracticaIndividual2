package andalu30.PracticaIndividual2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ProblemaAlimentos {
	private static List<Nutriente> nutrientes;
	private static List<IngredienteActivo> ingredientesActivos;
	private static Integer index;
	


	public static ProblemaAlimentos create(String pathDatos){
		return new ProblemaAlimentos(pathDatos);
	}
	protected ProblemaAlimentos(String pathDatos){
		super();
		this.nutrientes = generaDatosInicialesNutrientes(pathDatos);
		this.ingredientesActivos = generaDatosInicialesIngredientesActivos(pathDatos);
		this.index = 0;
	}


	public static Integer getIndex(){
		return index;
	}

	public static List<Nutriente> getNutrientesProblema(){
		return nutrientes;
	}

	public static List<IngredienteActivo> getIngredientesActivosProblema(){
		return ingredientesActivos;
	}


	public static void generadatosIniciales(String path){
		generaDatosInicialesNutrientes(path);
		generaDatosInicialesIngredientesActivos(path);

		System.out.println("\tDEBUG:"+nutrientes);
		System.out.println("\tDEBUG:"+ingredientesActivos);


		if (nutrientes.size()!=ingredientesActivos.get(0).getCantidadNutrientes().size()) {
			System.err.println("Se ha producido un error al cargar los datos iniciales. O faltan/sobran nutrientes o la cantidadNutrientes de los ingredientes no es correcta");
			System.exit(-1);
		}
	}
	
	
	private static List<Nutriente> generaDatosInicialesNutrientes(String path){
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
	
	private static List<IngredienteActivo> generaDatosInicialesIngredientesActivos(String path){
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
