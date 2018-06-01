package andalu30.PracticaIndividual2.bt;

import java.util.List;

import andalu30.PracticaIndividual2.common.ProblemaAlimentos;
import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestAlimentosBT {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		AlgoritmoBT.calculaMetricas();
		AlgoritmoBT.numeroDeSoluciones = 1;
		
		AlgoritmoBT.conFiltro = false;
		AlgoritmoBT.isRandomize = false;
		
		
		//Creacion de problemas y algoritmo
		System.out.println("Generando problema a partir del archivo 'alimentos.txt'");
		ProblemaAlimentos pa = ProblemaAlimentos.create("./ficheros/alimentos.txt");
		
		System.out.println("Generando AlgoritmoBT a partir del problema");
		ProblemaAlimentosBT p = ProblemaAlimentosBT.create(pa);
		AlgoritmoBT<SolucionAlimentos, Integer> a = Algoritmos.createBT(p);
		
		if (AlgoritmoBT.conFiltro==true) {
			System.out.println("Ejecutando el algoritmo con cota: Por favor, espere.");
		}else {
			System.out.println("Ejecutando el algoritmo: Por favor, espere.");			
		}
		a.ejecuta();

		
		//Solucion
		List<Integer> solucionAlgoritmo = a.getSolucion().getGramos();
		System.out.println("\n----------------------------------\nSolucion algoritmo: "+solucionAlgoritmo);
		
		System.out.println("\nSolución:");
		for (int i = 0; i < solucionAlgoritmo.size(); i++) {
			System.out.println("Alimento #"+i+": "+solucionAlgoritmo.get(i)+" gramos.");
		}
		System.out.println("----------------------------------");
	}

}
