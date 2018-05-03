package practicas.practica15;

import java.util.*;
import us.lsi.graphs.GraphView;
import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;

public class ProblemaBicicletasPD<V, E> implements ProblemaPD<String, ProblemaBicicletasPD.Alternativa> {

	public enum Alternativa{Yes, No};
	
	private int i;
	private int j;
	private int k;
	private GraphView<V,E> grafo;
	
	private ProblemaBicicletasPD(int i, int j, int k, GraphView<V, E> grafo) {
		super();
		this.i = i;
		this.j = j;
		this.k = k;
		this.grafo = grafo;
	}
	
	public static <V, E> ProblemaBicicletasPD<V, E> create(int i, int j, GraphView<V, E> grafo) {
		return new ProblemaBicicletasPD<V, E>(i, j, 0, grafo);
	}
	
	public static <V, E> ProblemaBicicletasPD<V, E> create(int i, int j, int k, GraphView<V, E> grafo) {
		return new ProblemaBicicletasPD<V, E>(i, j, k, grafo);
	}
	
	@Override
	public ProblemaPD.Tipo getTipo() {
		//TODO
	}
	
	@Override
	public boolean esCasoBase() {
		//TODO
	}

	@Override
	public Sp<Alternativa> getSolucionParcialCasoBase() {
		//TODO
	}
	
	@Override
	public List<Alternativa> getAlternativas() {
		//TODO
	}
	
	@Override
	public int getNumeroSubProblemas(Alternativa a) {
		//TODO
	}
	
	@Override
	public ProblemaPD<String, Alternativa> getSubProblema(Alternativa a, int np) {
		//TODO
	}
	
	@Override
	public Sp<Alternativa> getSolucionParcialPorAlternativa(Alternativa a, List<Sp<Alternativa>> ls) {
		//TODO
	}
	
	@Override
	public Sp<Alternativa> getSolucionParcial(List<Sp<Alternativa>> ls) {
		//TODO
	}
	
	@Override
	public String getSolucionReconstruidaCasoBase(Sp<Alternativa> sp) {
		return grafo.getVertice(i) + "<" + sp.propiedad + ">" + grafo.getVertice(j);
	}
		
	@Override
	public String getSolucionReconstruidaCasoRecursivo(Sp<Alternativa> sp, List<String> ls) {
		String r = null;
		switch(sp.alternativa){
		case No: r = ls.get(0); break;
		case Yes: r = ls.get(0) + ", " + ls.get(1); break;	
		}		
		return r;
	}
	
	@Override
	public Double getObjetivoEstimado(Alternativa a) {
		return Double.MIN_VALUE;
	}

	@Override
	public Double getObjetivo() {
		return Double.MAX_VALUE;
	}

	@Override
	public int size() {
		return grafo.getNumVertices()-k;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		result = prime * result + k;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProblemaBicicletasPD))
			return false;
		ProblemaBicicletasPD<?,?> other = (ProblemaBicicletasPD<?,?>) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		if (k != other.k)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + i + "," + j + "," + k + ")";
	}	
}
