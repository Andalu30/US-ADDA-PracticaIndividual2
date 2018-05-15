package andalu30.PracticaIndividual2;

import java.lang.ref.PhantomReference;
import java.util.List;

public class ProblemaAlimentosPD implements ProblemaPDR< List<Integer> ,Integer>{

    private static ProblemaAlimentosPD problemaInicial;

	private static List<Nutriente> nutrientes;
	private static List<IngredienteActivo> ingredientesActivos;


    private int index;
    private Double costeAcumulado;




    public static ProblemaAlimentosPD create(String path){
        ProblemaAlimentos pa = ProblemaAlimentos.create(path);
        this.nutrientes = pa.getNutrientesProblema();
        this.ingredientesActivos = pa.getIngredientesActivosProblema();


        
    }
    public static ProblemaAlimentosPD create(ProblemaAlimentosPD p, Integer a){
        return new ProblemaAlimentosPD(p,a);
    }



    private ProblemaAlimentosPD(ProblemaAlimentosPD p, Integer a){
        this.index= p.index+1;
        this.costeAcumulado = a*p.get
    }



    private Boolean constraints(Integer a){
        //TODO:
    }



    public Tipo getTipo(){
        return Tipo.Min;
    }

    public int size(){
        //TODO:
        return null;
    }

    public List<Integer> getAlternativas(){
        
        //TODO
        return null;
    }


    public boolean esCasoBase(){
        //TODO:
        return this.index == ProblemaMochilaPD.numeroIngredientes();
    }

    public Sp<Integer> getSolucionParticalCasoBase(){
        //TODO
        return Sp.create(null, 0.);
    }

    public Sp<Integer> getSolucionParcialporAlternativa(Integer a, Sp<Integer> r){
        //TODO
        Double valor = a*//Peso de la alternativa
        return Sp.create(a, valor);
    }

    public Sp<Integer> getsolucionParcial(List<Sp<Integer>> ls){
        //TODO
        return ls.stream().filter(x -> x.propiedad != null).max(Comparator.naturalOrder()).orElse(null);
    }

    //TODO:
    public getSolucionReconstruidaCasoBase(Sp<Integer> sp){
        
    }

    public double getObjetivoEstimado(Integer a){
        //TODO
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
