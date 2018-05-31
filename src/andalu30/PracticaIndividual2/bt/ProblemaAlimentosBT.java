package andalu30.PracticaIndividual2.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import andalu30.PracticaIndividual2.common.IngredienteActivo;
import andalu30.PracticaIndividual2.common.Nutriente;
import andalu30.PracticaIndividual2.common.ProblemaAlimentos;
import us.lsi.bt.EstadoBT;

public class ProblemaAlimentosBT implements EstadoBT<SolucionAlimentos,Integer>{

	private Integer index; //El alimento por el que vamos.
	
	//Propiedades
	private List<IngredienteActivo> listIngredientesActivos;
	private List<Nutriente> listNutriente;
	private Integer numeroIngredientes; //Derivada
	private List<Integer> memoriaAlternativas; //Lista con las alternativas que hemos cogido (Los gramos).
	private List<Double> minimos; //Memoria que se inicializa con los minimos que hay que cumplir y se va restando. Si todo es <0 se han cumplido los minimos

	
	
	//Constructores
	public static ProblemaAlimentosBT create(Integer index, List<IngredienteActivo> listIngredientesActivos,
			List<Nutriente> listNutriente) {
		return new ProblemaAlimentosBT(index,
									   listIngredientesActivos,
									   listNutriente,
									   listNutriente.stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList()));
	}
	
	public static ProblemaAlimentosBT create(ProblemaAlimentos pa) {
		return new ProblemaAlimentosBT(0,ProblemaAlimentos.getIngredientesActivosProblema(),
										 ProblemaAlimentos.getNutrientesProblema(),
										 ProblemaAlimentos.getNutrientesProblema().stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList()));
	}
	
	private ProblemaAlimentosBT(Integer index, List<IngredienteActivo> listIngredientesActivos, List<Nutriente> listNutriente, List<Double> minimos) {
		super();		
		this.index = index;
		this.listIngredientesActivos = listIngredientesActivos;
		this.listNutriente = listNutriente;
		this.numeroIngredientes = listIngredientesActivos.size();
		
		this.memoriaAlternativas = new ArrayList<>();
		this.minimos = minimos;
	}

	
	
	//Metodos de BT
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
				
		//Añadimos la alternativa a la memoria de las alternativas
		this.memoriaAlternativas.add(a);
		
		//Reducimos los mimimos
		for (int i = 0; i < this.listNutriente.size(); i++) {
			this.minimos.set(i, this.minimos.get(i)-a*this.listIngredientesActivos.get(index).getCantidadNutrientes().get(i));
		}
						
		this.index++;
		return this;
	}
	
	@Override
	public ProblemaAlimentosBT retrocede(Integer a) {

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
		//Solucion nula si no se cumplen las caracteristicas
		if (this.minimos.stream().anyMatch(x -> x>0.)) {
			return null;
		}else {			
			return SolucionAlimentos.create(ls, this.listIngredientesActivos);
		}
	}
	
	
	@Override
	public String toString() {
		return "ProblemaAlimentosBT [index=" + index + ", listIngredientesActivos=" + listIngredientesActivos
				+ ", listNutriente=" + listNutriente + "]";
	}	
}