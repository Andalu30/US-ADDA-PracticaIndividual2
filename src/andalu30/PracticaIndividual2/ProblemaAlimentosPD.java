package andalu30.PracticaIndividual2;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

import us.lsi.pd.AlgoritmoPD.Sp;
import us.lsi.pd.ProblemaPD;
import us.lsi.pd.ProblemaPDR;

public class ProblemaAlimentosPD implements ProblemaPDR< List<Integer> ,Integer>{

    private static ProblemaAlimentosPD problemaInicial;

	private static List<Nutriente> nutrientes;
	private static List<IngredienteActivo> ingredientesActivos;
	
    private int index;
    private Double costeAcumulado;
    private List<Double> memoriaNutrientes;



    public static ProblemaAlimentosPD create(String path){
    	ProblemaAlimentos pa = ProblemaAlimentos.create(path);
                
    }
    
    public static ProblemaAlimentosPD create(ProblemaAlimentosPD p, Integer a){
        return new ProblemaAlimentosPD(p,a);
    }

    private ProblemaAlimentosPD(ProblemaAlimentosPD p, Integer a){
        
    	
    }







    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    
    
    
    
   
}
