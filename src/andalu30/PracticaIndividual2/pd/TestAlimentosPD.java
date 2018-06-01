package andalu30.PracticaIndividual2.pd;

import java.util.List;

import andalu30.PracticaIndividual2.common.ProblemaAlimentos;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.pd.AlgoritmoPD;

public class TestAlimentosPD {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		String path = "ficheros/alimentos.txt";
		andalu30.PracticaIndividual2.pd.ProblemaAlimentos pa = andalu30.PracticaIndividual2.pd.ProblemaAlimentos.create(path);
		
		ProblemaAlimentosPD p = ProblemaAlimentosPD.create(pa.getIngredientesActivosProblema(), pa.getNutrientesProblema());
		System.out.println("ProblemaAlimentosPD: "+p);
		
		AlgoritmoPD.isRandomize = false;
		AlgoritmoPD.conFiltro = false;
		AlgoritmoPD.calculaMetricas();

		
		AlgoritmoPD<List<Integer>, Integer> a = Algoritmos.createPD(p);
		System.out.println("AlgoritmoPD: "+a);
		
		a.ejecuta();
		
//		a.showAllGraph("ficheros/pruebaAlimentosSinFiltro.gv", "Alimentos", p);
//		
//		
		System.out.println("\n----------------------------------\nSolucion algoritmo: "+a.getSolucion(p));
		System.out.println("----------------------------------");

	}
}
