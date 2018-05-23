package andalu30.PracticaIndividual2;

import java.util.Comparator;
import java.util.List;

import us.lsi.bt.SolucionBT;

public class SolucionAlimentos implements SolucionBT{

	public static SolucionAlimentos create(List<Integer> gramos) {
		return new SolucionAlimentos(gramos);
	}

	private List<Integer> gramos;


	private SolucionAlimentos(List<Integer> gramos) {
		super();
		this.gramos = gramos;
	}

	public List<Integer> getGramos(){
		return this.gramos;
	}


	@Override
	//Objetivo aleatorio en verdad, no veo en que afecta
	public Double getObjetivo() {
		return new Double(this.gramos.stream().max(Comparator.naturalOrder()).orElse(0));
	}


	@Override
	public String toString() {
		return "SolucionAlimentos [gramos=" + gramos + "]";
	}



}
