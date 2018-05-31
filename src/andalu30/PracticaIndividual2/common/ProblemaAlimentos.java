package andalu30.PracticaIndividual2.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ProblemaAlimentos {
	private static List<Nutriente> nutrientes;
	private static List<IngredienteActivo> ingredientesActivos;

	
	public ProblemaAlimentos() {
		super();
	}
	
	public static ProblemaAlimentos create() {
		return new ProblemaAlimentos();
	}
	
	public static ProblemaAlimentos create(String path) {
		ProblemaAlimentos pa = new ProblemaAlimentos();
		pa.generadatosIniciales(path);
		return pa;
	}
	


	public static List<Nutriente> getNutrientesProblema(){
		return nutrientes;
	}
	public static List<IngredienteActivo> getIngredientesActivosProblema(){
		return ingredientesActivos;
	}






	
	
	
	


	@SuppressWarnings("static-access")
	public void generadatosIniciales(String path){
		this.nutrientes = generaDatosInicialesNutrientes(path);
		this.ingredientesActivos = generaDatosInicialesIngredientesActivos(path);

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



		//Metodos equals toString hashCode
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		@Override
		public String toString() {
			return super.toString();
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

	
	
	
	
}
