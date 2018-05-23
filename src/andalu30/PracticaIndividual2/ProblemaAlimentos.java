package andalu30.PracticaIndividual2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ProblemaAlimentos {
	private String path;
	private static List<Nutriente> nutrientes;
	private static List<IngredienteActivo> ingredientesActivos;
	private static Double coste;
	private static Integer index;

	//Constructores como en ProblemaMochila
	public static ProblemaAlimentos create(String pathDatos, Double d, Integer index){
		return new ProblemaAlimentos(pathDatos, d, index);
	}

	public static ProblemaAlimentos create(String pathDatos){
		return new ProblemaAlimentos(pathDatos);
	}


	protected ProblemaAlimentos(String pathDatos){
		super();
		this.path = pathDatos;
		this.nutrientes = generaDatosInicialesNutrientes(pathDatos);
		this.ingredientesActivos = generaDatosInicialesIngredientesActivos(pathDatos);
		this.coste = 0;
		this.index = 0;
	}
	protected ProblemaAlimentos(String pathDatos, Double cost,  Integer i){
		super();
		this.path = pathDatos;
		this.nutrientes = generaDatosInicialesNutrientes(pathDatos);
		this.ingredientesActivos = generaDatosInicialesIngredientesActivos(pathDatos);
		this.coste = cost;
		this.index = i;
	}


	public static Integer getCoste(){
		return coste;
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


	//Subproblema: Coste+a*costeIngrediente(index)
	public ProblemaAlimentos getSubProblema(Integer a){
		int index = this.getIndex();
		return ProblemaAlimentos.create(this.path, this.coste+this.getIngredientesActivosProblema().get(index).getCoste()*a ,index+1);
	}

	public IntStream getAlternativas(){
		IntStream r;

		if(this.isFinal()){
			r = IntStream.empty();
		}else{
			//TODO: Mejorar
			r = IntStream.rangeClosed(0, 1000); //Se coje o 0 o un kilogramo.
		}
		return r;
	}

	public Integer getAlternativa(ProblemaMochila p) {
		//TODO: WTF is this??? :'(
		
		Preconditions.checkArgument(esSubproblema(p));
		int index1 = this.index;
		int index2 = p.index;
		Preconditions.checkArgument(index2-index1 == 1);
			
		return null;
	}
	

	public boolean esSubproblema(ProblemaMochila p) {
		int index1 = this.index;
		int index2 = p.index;
		if(index2-index1 != 1){
			return false;
		}else{
		return true;
		}
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
