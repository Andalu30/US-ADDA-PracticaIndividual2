package andalu30.PracticaIndividual2.common;

import java.util.List;

import andalu30.PracticaIndividual2.common.IngredienteActivo;
import us.lsi.bt.SolucionBT;

public class SolucionAlimentos implements SolucionBT{
	//Propiedades
	private List<Integer> gramos;
	private List<IngredienteActivo> ingredientes;

	//Constructor
	public static SolucionAlimentos create(List<Integer> gramos, List<IngredienteActivo> lsIngredientes) {
		return new SolucionAlimentos(gramos,lsIngredientes);
	}
	
	private SolucionAlimentos(List<Integer> gramos,List<IngredienteActivo> ingr) {
		super();
		this.gramos = gramos;
		this.ingredientes = ingr;
	}
	
	//Getters
	public List<Integer> getGramos(){
		return this.gramos;
	}

	
	@Override
	public Double getObjetivo() {
		Double res = 0.;
		//El objetivo es el coste de los ingredientes por cada cantidad de ingredientes.
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
