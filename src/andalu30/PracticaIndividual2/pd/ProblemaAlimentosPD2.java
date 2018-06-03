package andalu30.PracticaIndividual2.pd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import andalu30.PracticaIndividual2.common.IngredienteActivo;
import andalu30.PracticaIndividual2.common.Nutriente;
import andalu30.PracticaIndividual2.common.SolucionAlimentos;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;
import us.lsi.pd.ProblemaPDR;

public class ProblemaAlimentosPD2  implements ProblemaPDR<SolucionAlimentos, Integer> {
	
	private int indexingrediente;
	private List<IngredienteActivo> ingredientesActivos;	
	private List<Nutriente> nutrientes;	
	private List<Double> minimos;
	private List<Integer> memoriaAlternativas;
	private Double costeAcumulado;

	
	public static ProblemaAlimentosPD2 create(Integer index, List<IngredienteActivo> ling, List<Nutriente> nuts, List<Double> minimos, List<Integer> memoriaAlternativas) {
		return new ProblemaAlimentosPD2(index, ling, nuts, minimos, memoriaAlternativas);
	}
	
	public static ProblemaAlimentosPD2 create(Integer index, List<IngredienteActivo> ling, List<Nutriente> nuts, List<Double> minimos) {
		List<Integer> memoriaAlternativas = new ArrayList<>();
		for (int i = 0; i < ling.size(); i++) {
			memoriaAlternativas.add(0);
		}
		return new ProblemaAlimentosPD2(index, ling, nuts, minimos, memoriaAlternativas);
	}
	
	private ProblemaAlimentosPD2(int indexAlimento, List<IngredienteActivo> ling, List<Nutriente> nuts, List<Double> minimos,List<Integer> memoriaalternativas) {
		this.indexingrediente = indexAlimento;
		this.ingredientesActivos = ling;
		this.nutrientes = nuts;
		this.minimos = minimos;
		this.memoriaAlternativas = memoriaalternativas;
		this.costeAcumulado = 0.;
	}
	

	
	
	@Override
	public Tipo getTipo() {
		return Tipo.Min;
	}

	@Override
	public int size() {
		return this.ingredientesActivos.size()-this.indexingrediente;
	}

	@Override
	public boolean esCasoBase() {
									System.out.println("Caso base: "+(this.ingredientesActivos.size()==this.indexingrediente));
		return this.ingredientesActivos.size()==this.indexingrediente;
	}

	@Override
	public Sp<Integer> getSolucionParcialCasoBase() {
		return Sp.create(null, 0.);
	}

	@Override
	public Sp<Integer> getSolucionParcial(List<Sp<Integer>> ls) {
		Sp<Integer> s = Collections.min(ls);
		return s;
	}

	@Override
	public List<Integer> getAlternativas() {
		List<Integer> ret=IntStream.range(1, 1000)
				.boxed()
				.collect(Collectors.toList());		
		return ret;
	}


	@Override
	public ProblemaPD<SolucionAlimentos, Integer> getSubProblema(Integer a) {
		
		//Memoria alternativas
		this.memoriaAlternativas.set(indexingrediente, a);
										System.out.println(this.memoriaAlternativas);
										
		return ProblemaAlimentosPD2.create(this.indexingrediente+1,this.ingredientesActivos,this.nutrientes,this.minimos,this.memoriaAlternativas);
	}
	

	@Override
	public Sp<Integer> getSolucionParcialPorAlternativa(Integer a, Sp<Integer> sp) {	
		Sp<Integer> res = Sp.create(a, sp.propiedad+a*this.ingredientesActivos.get(this.indexingrediente).getCoste());
		return res;
	}
	
	@Override
	public SolucionAlimentos getSolucionReconstruidaCasoBase(Sp<Integer> sp) {
		System.out.println("SolucionReconstruidaCasoBase");
		return SolucionAlimentos.create(new ArrayList<>(),this.ingredientesActivos);
	}

	@Override
	public SolucionAlimentos getSolucionReconstruidaCasoRecursivo(Sp<Integer> sp, SolucionAlimentos ls) {
		System.out.println("SolucionReconstruidaCasoRecursivo");

		List<Integer> gramos = this.memoriaAlternativas;
		gramos.set(indexingrediente, sp.alternativa);
		
		
		return SolucionAlimentos.create(gramos, this.ingredientesActivos) ;
	}
	
	
	
	
	
	
	
	
	
	
	//---Hashcode equals toString---

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costeAcumulado == null) ? 0 : costeAcumulado.hashCode());
		result = prime * result + indexingrediente;
		result = prime * result + ((ingredientesActivos == null) ? 0 : ingredientesActivos.hashCode());
		result = prime * result + ((memoriaAlternativas == null) ? 0 : memoriaAlternativas.hashCode());
		result = prime * result + ((minimos == null) ? 0 : minimos.hashCode());
		result = prime * result + ((nutrientes == null) ? 0 : nutrientes.hashCode());
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
		ProblemaAlimentosPD2 other = (ProblemaAlimentosPD2) obj;
		if (costeAcumulado == null) {
			if (other.costeAcumulado != null)
				return false;
		} else if (!costeAcumulado.equals(other.costeAcumulado))
			return false;
		if (indexingrediente != other.indexingrediente)
			return false;
		if (ingredientesActivos == null) {
			if (other.ingredientesActivos != null)
				return false;
		} else if (!ingredientesActivos.equals(other.ingredientesActivos))
			return false;
		if (memoriaAlternativas == null) {
			if (other.memoriaAlternativas != null)
				return false;
		} else if (!memoriaAlternativas.equals(other.memoriaAlternativas))
			return false;
		if (minimos == null) {
			if (other.minimos != null)
				return false;
		} else if (!minimos.equals(other.minimos))
			return false;
		if (nutrientes == null) {
			if (other.nutrientes != null)
				return false;
		} else if (!nutrientes.equals(other.nutrientes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProblemaAlimentosPD2 [indexingrediente=" + indexingrediente + ", ingredientesActivos="
				+ ingredientesActivos + ", nutrientes=" + nutrientes + ", minimos=" + minimos + ", memoriaAlternativas="
				+ memoriaAlternativas + ", costeAcumulado=" + costeAcumulado + "]";
	}

	
	
}

