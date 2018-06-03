package andalu30.PracticaIndividual2.pd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import andalu30.PracticaIndividual2.common.IngredienteActivo;
import andalu30.PracticaIndividual2.common.Nutriente;
import andalu30.PracticaIndividual2.common.ProblemaAlimentos;
import andalu30.PracticaIndividual2.common.SolucionAlimentos;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;
import us.lsi.pd.ProblemaPDR;


public class ProblemaAlimentosPD implements ProblemaPDR<SolucionAlimentos ,Integer>{

	//Propiedades
	private int index;
	private List<IngredienteActivo> listaIngredientes;
	private List<Nutriente> listaNutrientes;
	private List<Double> minimos;
	private List<Integer> memoriaAlternativas;
	private Double costeAcumulado;
	

	
	//Constructores (Basados en Afinidad)

	public static ProblemaAlimentosPD create(Integer index, List<IngredienteActivo> lising, List<Nutriente> nuts) {
		return new ProblemaAlimentosPD(index,
									   lising,
									   nuts,
									   nuts.stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList())
									   );
	}

	@SuppressWarnings("static-access")
	public static ProblemaAlimentosPD create(ProblemaAlimentos pa) {
		return new ProblemaAlimentosPD(0,
										pa.getIngredientesActivosProblema(),
										pa.getNutrientesProblema(),
										pa.getNutrientesProblema().stream().map(x -> new Double(x.getCantidadMinimaPorKilo())).collect(Collectors.toList())
										);
	}
	
	
//	public static ProblemaAlimentosPD create(Integer index, List<IngredienteActivo> ling, List<Nutriente> nuts,
//			List<Double> minimos) {
//		return new ProblemaAlimentosPD(index, ling, nuts, minimos);
//	}
	
	private ProblemaAlimentosPD(Integer index, List<IngredienteActivo> ling, List<Nutriente> nuts,
			List<Double> minimos) {
		super();
		this.index = index;
		this.listaIngredientes = ling;
		this.listaNutrientes = nuts;
		this.minimos = minimos;
		this.memoriaAlternativas = new ArrayList<>();
	}
	
	
	
	
	
	
	
	//Metodos
	@Override
	public Tipo getTipo() {
		return Tipo.Min;
	}

	@Override
	public int size() {
		System.out.println(listaNutrientes.size()-this.index);
		return this.listaNutrientes.size()-this.index;
	}
	
	@Override
	public boolean esCasoBase() {
		System.out.println("Es caso base: "+(this.index==this.listaIngredientes.size()));
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
	public ProblemaPD<SolucionAlimentos, Integer> getSubProblema(Integer a) {
		
		//Añadir la alternativa a la memoria
		this.memoriaAlternativas.add(a);
		
		System.out.println("DEBUG - memoriaAlternativas: "+this.memoriaAlternativas);
		
		//Restar de los minimos
		for (int i = 0; i < this.listaNutrientes.size(); i++) {
			this.minimos.set(i, this.minimos.get(i)-a*this.listaIngredientes.get(index).getCantidadNutrientes().get(i));
		}
		System.out.println("Minimos: "+this.minimos);
		this.index++;
		return this;					
	}
	
	@Override
	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> sp) {
		Double valor = sp.propiedad + a*this.listaIngredientes.get(index).getCoste();
		return Sp.create(a, valor);
	}
	
	@Override
	public List<Integer> getAlternativas() {		
		List<Integer> ret=IntStream.range(0, 1000)
				.filter(x -> cumpleRestricciones(x))
				.boxed()
				.collect(Collectors.toList());		
		return ret;
}
	
	private Boolean cumpleRestricciones(Integer a) {
		return this.minimos.stream().allMatch(x -> x<0.);
	}
	
	@Override
	public SolucionAlimentos getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		List<Integer> a = new ArrayList<>();
		return SolucionAlimentos.create(a, this.listaIngredientes);
	}
	
	@Override
	public SolucionAlimentos getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, SolucionAlimentos s) {
		List<Integer> aux = s.getGramos();
		aux.set(index, sp.alternativa);
		SolucionAlimentos res = SolucionAlimentos.create(aux, this.listaIngredientes);		
		return res;
	}

	@Override
	public Double getObjetivoEstimado(Integer a) {
		return this.costeAcumulado+a*this.listaIngredientes.get(index).getCoste();	
	}

	@Override
	public Double getObjetivo() {
		Double r = null;
		if (this.esCasoBase()) {
			r = this.costeAcumulado;
		}
		return r;
	} 
	
		
	
	
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
