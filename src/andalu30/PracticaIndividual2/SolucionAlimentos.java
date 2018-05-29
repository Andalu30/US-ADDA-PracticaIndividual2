package andalu30.PracticaIndividual2;

import java.util.Comparator;
import java.util.List;

import us.lsi.bt.SolucionBT;

public class SolucionAlimentos implements SolucionBT{

	public static SolucionAlimentos create(List<Integer> gramos, List<IngredienteActivo> lsIngredientes) {
		return new SolucionAlimentos(gramos,lsIngredientes);
	}
	
	private List<Integer> gramos;
	private List<IngredienteActivo> ingredientes;
	
	
	private SolucionAlimentos(List<Integer> gramos,List<IngredienteActivo> ingr) {
		super();
		this.gramos = gramos;
		this.ingredientes = ingr;
	}
	
	public List<Integer> getGramos(){

		return this.gramos;
	}

	
	@Override
	public Double getObjetivo() {
		Double res = 0.;
		
		for (int i = 0; i < this.gramos.size(); i++) {
			res += this.gramos.get(i)*this.ingredientes.get(i).getCoste();
		}
		
		return res;
	}
	
	
	@Override
	public String toString() {
		return "SolucionAlimentos [gramos=" + gramos + "]";
	}
	
}
