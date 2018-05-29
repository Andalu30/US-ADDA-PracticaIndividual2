package andalu30.PracticaIndividual2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.plaf.synth.SynthSeparatorUI;

import us.lsi.bt.EstadoBT;

public class ProblemaAlimentosBT implements EstadoBT<SolucionAlimentos,Integer>{

	//Constructores
	public static ProblemaAlimentosBT create(Integer index, List<IngredienteActivo> listIngredientesActivos,
			List<Nutriente> listNutriente) {
		return new ProblemaAlimentosBT(index,
									   listIngredientesActivos,
									   listNutriente,
									   listNutriente.stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList()));
	}
	
	public static ProblemaAlimentosBT create() {
		return new ProblemaAlimentosBT();
	}
	
	public static ProblemaAlimentosBT create(ProblemaAlimentos pa) {
		return new ProblemaAlimentosBT(0,ProblemaAlimentos.getIngredientesActivosProblema(),
										 ProblemaAlimentos.getNutrientesProblema(),
										 ProblemaAlimentos.getNutrientesProblema().stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList()));
	}
	
	
	
	private Integer index; //El alimento por el que vamos.
	
	private List<IngredienteActivo> listIngredientesActivos;
	private List<Nutriente> listNutriente;
	private Integer numeroIngredientes;
	
	private List<Integer> memoriaAlternativas; //Lista con las alternativas que hemos cogido (Los gramos).
	//private List<Double> memoriaNutientes; //Cantidad total de nutrientes segun la alternativa del ingrediente que hayamos seleccionado.
	private List<Double> minimos;
	
	
	private ProblemaAlimentosBT(Integer index, List<IngredienteActivo> listIngredientesActivos, List<Nutriente> listNutriente, List<Double> minimos) {
		super();		
		this.index = index;
		this.listIngredientesActivos = listIngredientesActivos;
		this.listNutriente = listNutriente;
		
		//this.minimos = listNutriente.stream().map(x -> (double) x.getCantidadMinimaPorKilo()).collect(Collectors.toList()); 
		//TODO: Quizas haya que cambiarlo
		
		this.numeroIngredientes = listIngredientesActivos.size();
		
		this.memoriaAlternativas = new ArrayList<>();
		this.minimos = minimos;
	}
	
	
	private ProblemaAlimentosBT() {
		super();
		this.index = 0;
		this.listIngredientesActivos = new ArrayList<>();
		this.listNutriente = new ArrayList<>();
		this.numeroIngredientes = 0;

		this.memoriaAlternativas = new ArrayList<>();
		this.minimos = new ArrayList<>();

	}

	
	
	@Override
	public ProblemaAlimentosBT getEstadoInicial() {
		return ProblemaAlimentosBT.create(0,this.listIngredientesActivos,this.listNutriente);
	}

	@Override
	public Tipo getTipo() {
		return Tipo.Min;
	}
	
	@Override
	public int size() {
		return numeroIngredientes-this.index;
	}

	@Override
	public ProblemaAlimentosBT avanza(Integer a) {
		//System.out.println("AVANZA - Alternativa="+a);
				
		//Añadimos la alternativa a la memoria de las alternativas
		this.memoriaAlternativas.add(a);
		
		//Reducimos los mimimos (nutrientes)
		for (int i = 0; i < this.listNutriente.size(); i++) {
			this.minimos.set(i, this.minimos.get(i)-a*this.listIngredientesActivos.get(index).getCantidadNutrientes().get(i));
		}
				
		System.out.println(minimos);
		
		this.index++;
		return this;
	}
	
	@Override
	public ProblemaAlimentosBT retrocede(Integer a) {
		//System.out.println("RETROCEDE");

		//Reducimos el index
		this.index--;
		
		//Quitar la ultima alternativa añadida.
		this.memoriaAlternativas.remove(this.memoriaAlternativas.size()-1);
		
		//aumentamos los mimimos (nutrientes)
		for (int i = 0; i < this.listNutriente.size(); i++) {
			this.minimos.set(i, this.minimos.get(i)+a*this.listIngredientesActivos.get(index).getCantidadNutrientes().get(i));
		}
		
		return this;
	}

	@Override
	public List<Integer> getAlternativas() {
		Stream<Integer> s = IntStream.range(1, 1000).boxed();
		return s.collect(Collectors.toList());
	}

	@Override
	public boolean esCasoBase() {
		return this.index==numeroIngredientes;
	}
	


	@Override
	public SolucionAlimentos getSolucion() {
		List<Integer> ls = new ArrayList<>();
		
		
		for (Integer alt : this.memoriaAlternativas) {
			ls.add(alt);
		}
		//Solucion nula si no se cumplen las caracteristicas (contador.)		
		
		if (this.minimos.stream().anyMatch(x -> x>0.)) {
			return null;
		}else {			
			return SolucionAlimentos.create(ls);
		}
	}

	
	
	
	@Override
	public String toString() {
		return "ProblemaAlimentosBT [index=" + index + ", listIngredientesActivos=" + listIngredientesActivos
				+ ", listNutriente=" + listNutriente + "]";
	}	
}