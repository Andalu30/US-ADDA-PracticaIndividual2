package andalu30.PracticaIndividual2;

public class testRapido{

    public static void main(String[] args) {

        String pathDatosProblema = "./ficheros/alimentos.txt";

        //TODO
        ProblemaAlimentosPD p = ProblemaAlimentosPD.create()
        AlgoritmoPD.isRandomize = false;
        AlgoritmoPD.conFiltro = true;
        AbstractAlgoritmo.calculaMetricas();

        System.out.println("Datos");
        System.out.println("Problema Inicial = "+p);


        AlgoritmoPD< List<Integer>, Integer> a = Algoritmos.createPD(p);
        a.ejecuta();




        //-------------------------------
        ProblemaAlimentos pAlimentos = ProblemaAlimentos.create(pathDatosProblemas);
        
        System.out.println(">DEBUG: Ingredientes Activos:\t"+pAlimentos.getIngredientesActivosProblema());
        System.out.println(">DEBUG: Nutrientes:\t"+pAlimentos.getNutrientesProblema());
        System.out.println(">DEBUG: Index:\t"+pAlimentos.getIndex());


    }



}