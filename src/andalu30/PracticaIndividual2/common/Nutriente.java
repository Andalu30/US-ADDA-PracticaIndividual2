package andalu30.PracticaIndividual2.common;

public class Nutriente {
	private Integer cantidadMinimaPorKilo;

	
	public Nutriente(Integer cantidadMinimaPorKilo) {
		super();
		this.cantidadMinimaPorKilo = cantidadMinimaPorKilo;
	}

	public Integer getCantidadMinimaPorKilo() {
		return cantidadMinimaPorKilo;
	}

	public void setCantidadMinimaPorKilo(Integer cantidadMinimaPorKilo) {
		this.cantidadMinimaPorKilo = cantidadMinimaPorKilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadMinimaPorKilo == null) ? 0 : cantidadMinimaPorKilo.hashCode());
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
		Nutriente other = (Nutriente) obj;
		if (cantidadMinimaPorKilo == null) {
			if (other.cantidadMinimaPorKilo != null)
				return false;
		} else if (!cantidadMinimaPorKilo.equals(other.cantidadMinimaPorKilo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nutriente [cantidadMinimaPorKilo=" + cantidadMinimaPorKilo + "]";
	}

	
}
