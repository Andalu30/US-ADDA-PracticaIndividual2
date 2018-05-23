package andalu30.PracticaIndividual2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import us.lsi.bt.EstadoBT;

public class ProblemaAlimentosBT implements EstadoBT<SolucionAlimentos,Integer>{

	public static ProblemaAlimentosBT create(Integer index, List<IngredienteActivo> listIngredientesActivos,
			List<Nutriente> listNutriente) {
		return new ProblemaAlimentosBT(index,listIngredientesActivos,listNutriente);
	}
	
	public static ProblemaAlimentosBT create() {
		return new ProblemaAlimentosBT();
	}
	
	private Integer index;
	private List<IngredienteActivo> listIngredientesActivos;
	private List<Nutriente> listNutriente;
	private Integer numeroIngredientes;
	private Integer alternativaSeleccionada;

		
	
	
	private ProblemaAlimentosBT(Integer index, List<IngredienteActivo> listIngredientesActivos, List<Nutriente> listNutriente) {
		super();
		this.index = index;
		this.listIngredientesActivos = listIngredientesActivos;
		this.listNutriente = listNutriente;
		this.numeroIngredientes = listIngredientesActivos.size();
	}
	
	private ProblemaAlimentosBT() {
		super();
		this.index = 0;
		this.listIngredientesActivos = new ArrayList<>();
		this.listNutriente = new ArrayList<>();
		this.numeroIngredientes = 0;
	}

	
	
	
	
	@Override
	public EstadoBT<SolucionAlimentos, Integer> getEstadoInicial() {
		return ProblemaAlimentosBT.create();
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
	public EstadoBT<SolucionAlimentos, Integer> avanza(Integer a) {
		//TODO: mas?
		this.index = this.index+1;
		this.alternativaSeleccionada = a;
		
		return this;
	}

	@Override
	public EstadoBT<SolucionAlimentos, Integer> retrocede(Integer a) {
		//TODO: mas?
		this.index= this.index-1;
		return this;
	}

	@Override
	public List<Integer> getAlternativas() {
		Stream<Integer> s = IntStream.range(0, 1001).boxed();
		return s.collect(Collectors.toList());
	}

	@Override
	public boolean esCasoBase() {
		return this.index==numeroIngredientes;
	}
	


	@Override
	public SolucionAlimentos getSolucion() {
		List<Integer> ls = new ArrayList<>();
		
		for (int i = 0; i < numeroIngredientes; i++) {
			//TODO: algo
			ls.add(this.alternativaSeleccionada);
		}
		
		return SolucionAlimentos.create(ls);
	}

	@Override
	public String toString() {
		return "ProblemaAlimentosBT [index=" + index + ", listIngredientesActivos=" + listIngredientesActivos
				+ ", listNutriente=" + listNutriente + "]";
	}
	
	
}