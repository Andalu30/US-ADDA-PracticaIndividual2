package andalu30.PracticaIndividual2.pd;

import java.util.List;
import java.util.stream.Collectors;

import andalu30.PracticaIndividual2.common.ProblemaAlimentos;
import andalu30.PracticaIndividual2.common.SolucionAlimentos;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;

public class TestAlimentosPD {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		String path = "ficheros/alimentos.txt";
		
		System.out.println("Generando problema a partir del archivo 'alimentos.txt'");
		ProblemaAlimentos pa = ProblemaAlimentos.create(path);
		
		
		List<Double> minimos = pa.getNutrientesProblema().stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList());
		
		ProblemaAlimentosPD2 p = ProblemaAlimentosPD2.create(0, pa.getIngredientesActivosProblema(), pa.getNutrientesProblema(),minimos);								
		
		AlgoritmoPD.calculaMetricas();
		AlgoritmoPD.isRandomize = false;
		AlgoritmoPD.conFiltro = false;

		System.out.println("Generando AlgoritmoBT a partir del problema");
		AlgoritmoPD<SolucionAlimentos, Integer> a = Algoritmos.createPD(p);
		
		if (AlgoritmoPD.conFiltro==true) {
			System.out.println("Ejecutando el algoritmo con cota: Por favor, espere.");
		}else {
			System.out.println("Ejecutando el algoritmo: Por favor, espere.");			
		}
		
		a.ejecuta();
		
		//a.showAllGraph("ficheros/pruebaAlimentosSinFiltro.gv", "Alimentos", p);
	
		List<Integer> solucionAlgoritmo = a.getSolucion(p).getGramos();
		System.out.println("\n----------------------------------\nSolucion algoritmo: "+solucionAlgoritmo);
		
		System.out.println("\nSolución:");
		for (int i = 0; i < solucionAlgoritmo.size(); i++) {
			System.out.println("Alimento #"+i+": "+solucionAlgoritmo.get(i)+" gramos.");
		}
		System.out.println("----------------------------------");

	}
}
