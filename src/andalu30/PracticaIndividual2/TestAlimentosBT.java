package andalu30.PracticaIndividual2;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestAlimentosBT {

	public static void main(String[] args) {
		AlgoritmoBT.calculaMetricas();
		AlgoritmoBT.numeroDeSoluciones = 1;
		
		AlgoritmoBT.conFiltro = false;
		AlgoritmoBT.isRandomize = false;
		
		
		ProblemaAlimentos pa = ProblemaAlimentos.create("./ficheros/alimentos.txt");
		System.out.println("DEBUG: "+pa.getNutrientesProblema());
		System.out.println("DEBUG: "+pa.getIngredientesActivosProblema());		
		
		
		
		ProblemaAlimentosBT p = ProblemaAlimentosBT.create(pa);
		//System.out.println("DEBUG: "+p);
		
		
		AlgoritmoBT<SolucionAlimentos, Integer> a = Algoritmos.createBT(p);
		
		a.ejecuta();

		
		List<Integer> solucionAlgoritmo = a.getSolucion().getGramos();
		System.out.println("Solucion algoritmo: "+solucionAlgoritmo);
		System.out.println("Soluciones:" +a.getSoluciones()/*+"\n\nMetricas:\n"+a.metricas*/);
		
		System.out.println("\n\nSolucion:");
		for (int i = 0; i < solucionAlgoritmo.size(); i++) {
			System.out.println("Alimento #"+i+": "+solucionAlgoritmo.get(i)+" gramos.");
		}
	}

}
