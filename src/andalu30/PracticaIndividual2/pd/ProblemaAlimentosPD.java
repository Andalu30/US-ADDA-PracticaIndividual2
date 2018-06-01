package andalu30.PracticaIndividual2.pd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import andalu30.PracticaIndividual2.bt.SolucionAlimentos;
import andalu30.PracticaIndividual2.common.IngredienteActivo;
import andalu30.PracticaIndividual2.common.Nutriente;
import andalu30.PracticaIndividual2.common.ProblemaAlimentos;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;
import us.lsi.pd.ProblemaPDR;
import us.lsi.pd.mochila.ProblemaMochilaPD;

public class ProblemaAlimentosPD implements ProblemaPDR<andalu30.PracticaIndividual2.common.SolucionAlimentos ,Integer>{

	//Propiedades
	private int index;
	private List<IngredienteActivo> listaIngredientes;
	private List<Nutriente> listaNutrientes;
	private List<Double> minimos;
	private Double costeAcumulado;
	

	
	//Constructor	
	
	
	public static ProblemaAlimentosPD create(List<IngredienteActivo> ling, List<Nutriente> nutr) {
		return new ProblemaAlimentosPD(0, ling, nutr);
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
	@Override
	public Tipo getTipo() {
		return Tipo.Min;
	}

	@Override
	public int size() {
		return this.listaNutrientes.size()-this.index;
	}
	
	@Override
	public boolean esCasoBase() {
		System.out.println("Caso base: "+(this.index==this.listaIngredientes.size()));
		return this.index==this.listaIngredientes.size();
	}

	@Override
	public Sp<Integer> getSolucionParcialCasoBase() {
		return Sp.create(null, 0.);
	}
	
	@Override
	public Sp<Integer> getSolucionParcial(List<Sp<Integer>> ls) {
		return ls.stream().filter(x -> x.propiedad != null).min(Comparator.naturalOrder()).orElse(null);
	}
	
	@Override
	public ProblemaPD<andalu30.PracticaIndividual2.common.SolucionAlimentos, Integer> getSubProblema(Integer a) {
		return ProblemaAlimentosPD.create(this, a);
	}
	
	@Override
	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> sp) {
		Double valor = sp.propiedad + a*this.listaIngredientes.get(index).getCoste();
		return Sp.create(a, valor);
	}
	
	@Override
	public List<Integer> getAlternativas() {
		List<Integer> ls = IntStream.rangeClosed(1, 1000)
				.filter(x->this.constraints(x))
				.boxed()
				.collect(Collectors.toList());
		Collections.reverse(ls); //No se porque pero en mochila aparece asi.
		return ls;
	}
	
	private Boolean cumpleRestricciones(Integer a) {
		return this.minimos.stream().allMatch(x -> x<0.);
	}
	
	@Override
	public andalu30.PracticaIndividual2.common.SolucionAlimentos getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		List<Integer> a = new ArrayList<>();
		return andalu30.PracticaIndividual2.common.SolucionAlimentos.create(a, this.listaIngredientes);
	}

	@Override
	public andalu30.PracticaIndividual2.common.SolucionAlimentos getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, SolucionAlimentos s){
	//TODO: Basandome en el ProblemaAfinidad.
	}
	
	
	
	
	//TODO:Por aqui!!


	
		
	
	
	//Hashcode, equals & toString
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((listaIngredientes == null) ? 0 : listaIngredientes.hashCode());
		result = prime * result + ((listaNutrientes == null) ? 0 : listaNutrientes.hashCode());
		result = prime * result + ((minimos == null) ? 0 : minimos.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProblemaAlimentosPD other = (ProblemaAlimentosPD) obj;
		if (index != other.index)
			return false;
		if (listaIngredientes == null) {
			if (other.listaIngredientes != null)
				return false;
		} else if (!listaIngredientes.equals(other.listaIngredientes))
			return false;
		if (listaNutrientes == null) {
			if (other.listaNutrientes != null)
				return false;
		} else if (!listaNutrientes.equals(other.listaNutrientes))
			return false;
		if (minimos == null) {
			if (other.minimos != null)
				return false;
		} else if (!minimos.equals(other.minimos))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProblemaAlimentosPD [listaIngredientes=" + listaIngredientes + ", listaNutrientes=" + listaNutrientes
				+ ", minimos=" + minimos + ", index=" + index + "]";
	}

}
