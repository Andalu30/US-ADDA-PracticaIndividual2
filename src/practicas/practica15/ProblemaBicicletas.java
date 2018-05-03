package practicas.practica15;


import us.lsi.graphs.GraphsReader;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class ProblemaBicicletas {

	
	public static ProblemaBicicletas create(String fichero) {
		return new ProblemaBicicletas(fichero);
	}

	private Graph<Estacion,Calle> grafo;		
	
	private ProblemaBicicletas(String fichero) {
		super();
		leeDatos(fichero);		
	}

	public void leeDatos(String fichero){
		this.grafo = new SimpleDirectedWeightedGraph<Estacion,Calle>(Calle::create);
		this.grafo = (Graph<Estacion, Calle>) GraphsReader.newGraph(fichero, Estacion::create, Calle::create, grafo, Calle::getWeight);			
		for(Calle c: grafo.edgeSet()){
			grafo.setEdgeWeight(c, c.getMinutos());
		}		
	}

	public Graph<Estacion, Calle> getGrafo() {
		return grafo;
	}	
	
}
