package andalu30.PracticaIndividual2.pd;

import java.util.List;
import java.util.stream.Collectors;

import andalu30.PracticaIndividual2.common.IngredienteActivo;
import andalu30.PracticaIndividual2.common.Nutriente;
import andalu30.PracticaIndividual2.common.ProblemaAlimentos;
import us.lsi.pd.ProblemaPDR;
import us.lsi.pd.mochila.ProblemaMochilaPD;

public class ProblemaAlimentosPD implements ProblemaPDR< List<Integer> ,Integer>{

	//Propiedades
	private static ProblemaAlimentosPD problemaInicial;
	private static List<IngredienteActivo> listaIngredientes;
	private static List<Nutriente> listaNutrientes;
	private List<Double> minimos;
	private int index;

	
	//Constructor
	public static ProblemaAlimentosPD create(String path, Integer c) {
		
	}
	
	public static ProblemaAlimentosPD create(ProblemaAlimentosPD p, Integer a) {
		return new ProblemaAlimentosPD(p,a);
	}
	
	private ProblemaAlimentosPD(int index, List<IngredienteActivo> ling, List<Nutriente> nut) {
		this.index = index;
		this.listaIngredientes = ling;
		this.listaNutrientes = nut;
		this.minimos = this.listaNutrientes.stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList());
	}
	
	private ProblemaAlimentosPD(ProblemaAlimentosPD p, Integer a) {
		this.index++;
		this.listaIngredientes = p.listaIngredientes;
		this.listaNutrientes = p.listaNutrientes;
		this.minimos = this.listaNutrientes.stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList());
	}
	
	
	//Metodos
	private Boolean constraints(Integer a) {
		return this.minimos.stream().allMatch(x -> x<0.);
	}
	
	
	//Hashcode, equals & toString
}
    
    
   
}
