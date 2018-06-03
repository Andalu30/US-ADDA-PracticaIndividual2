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
		
		
		ProblemaAlimentos pa = ProblemaAlimentos.create(path);
		System.out.println("Problema Alimentos: "+pa);
		
		
		List<Double> minimos = pa.getNutrientesProblema().stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList());
		
		ProblemaAlimentosPD2 p = ProblemaAlimentosPD2.create(0, pa.getIngredientesActivosProblema(), pa.getNutrientesProblema(),minimos);								
		System.out.println("ProblemaAlimentosPD2: "+p);
		
		AlgoritmoPD.calculaMetricas();
		AlgoritmoPD.isRandomize = false;
		AlgoritmoPD.conFiltro = false;

		
		AlgoritmoPD<SolucionAlimentos, Integer> a = Algoritmos.createPD(p);
		System.out.println("Algoritmo PD: "+a+"\n");
		
		a.ejecuta();
		
		System.out.println("Algoritmo ejecutado");
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
