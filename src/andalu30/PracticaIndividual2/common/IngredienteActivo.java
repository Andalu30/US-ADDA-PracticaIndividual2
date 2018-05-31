package andalu30.PracticaIndividual2.common;

import java.util.List;

public class IngredienteActivo {
	private List<Double> cantidadNutrientes;
	private Double coste;
	
	public IngredienteActivo(List<Double> cantidadNutrientes, Double coste) {
		super();
		this.cantidadNutrientes = cantidadNutrientes;
		this.coste = coste;
	}

	public List<Double> getCantidadNutrientes() {
		return cantidadNutrientes;
	}

	public void setCantidadNutrientes(List<Double> cantidadNutrientes) {
		this.cantidadNutrientes = cantidadNutrientes;
	}

	public Double getCoste() {
		return coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadNutrientes == null) ? 0 : cantidadNutrientes.hashCode());
		result = prime * result + ((coste == null) ? 0 : coste.hashCode());
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
		IngredienteActivo other = (IngredienteActivo) obj;
		if (cantidadNutrientes == null) {
			if (other.cantidadNutrientes != null)
				return false;
		} else if (!cantidadNutrientes.equals(other.cantidadNutrientes))
			return false;
		if (coste == null) {
			if (other.coste != null)
				return false;
		} else if (!coste.equals(other.coste))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IngredienteActivo [cantidadNutrientes=" + cantidadNutrientes + ", coste=" + coste + "]";
	}
	
	
	
}
