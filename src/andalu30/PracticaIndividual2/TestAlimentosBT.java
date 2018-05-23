package andalu30.PracticaIndividual2;

import java.util.List;

import us.lsi.algoritmos.Algoritmos;
import us.lsi.bt.AlgoritmoBT;

public class TestAlimentosBT {

	public static void main(String[] args) {
		AlgoritmoBT.calculaMetricas();
		AlgoritmoBT.numeroDeSoluciones = 1;
		
		AlgoritmoBT.conFiltro = false;
		AlgoritmoBT.isRandomize = false;
		
		
		//ProblemaAlimentos.create("/ficheros/alimentos.txt");
		
		ProblemaAlimentosBT p = ProblemaAlimentosBT.create();
		AlgoritmoBT<SolucionAlimentos, Integer> a = Algoritmos.createBT(p);
		a.ejecuta();
		
		List<Integer> solucionAlgoritmo = a.getSolucion().getGramos();
		System.out.println("Solucion algoritmo: "+solucionAlgoritmo);
		
		for (int i = 0; i < solucionAlgoritmo.size(); i++) {
			System.out.println("Alimento #"+i+": "+solucionAlgoritmo.get(i)+" gramos.");
		}
	
	}

}
