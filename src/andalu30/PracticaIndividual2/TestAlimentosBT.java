package andalu30.PracticaIndividual2;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestAlimentosBT {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		AlgoritmoBT.calculaMetricas();
		AlgoritmoBT.numeroDeSoluciones = 1;
		
		AlgoritmoBT.conFiltro = false;
		AlgoritmoBT.isRandomize = false;
		
		System.out.println("Generando problema a partir del archivo 'alimentos.txt'");
		ProblemaAlimentos pa = ProblemaAlimentos.create("./ficheros/alimentos.txt");

		System.out.println("Generando AlgoritmoBT a partir del problema");
		ProblemaAlimentosBT p = ProblemaAlimentosBT.create(pa);
		AlgoritmoBT<SolucionAlimentos, Integer> a = Algoritmos.createBT(p);
		
		System.out.println("Ejecutando el algoritmo: Por favor, espere.");
		a.ejecuta();

		
		List<Integer> solucionAlgoritmo = a.getSolucion().getGramos();
		System.out.println("\n----------------------------------\nSolucion algoritmo: "+solucionAlgoritmo);
		
		System.out.println("\nSolución:");
		for (int i = 0; i < solucionAlgoritmo.size(); i++) {
			System.out.println("Alimento #"+i+": "+solucionAlgoritmo.get(i)+" gramos.");
		}
		System.out.println("----------------------------------");
	}

}
