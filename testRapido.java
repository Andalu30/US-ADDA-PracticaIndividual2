package andalu30.PracticaIndividual2;

public class testRapido{

    public static void main(String[] args) {


        ProblemaAlimentos pAlimentos = ProblemaAlimentos.create("./ficheros/alimentos.txt");
        System.out.println(">DEBUG: Ingredientes Activos:\t"+pAlimentos.getIngredientesActivosProblema());
        System.out.println(">DEBUG: Nutrientes:\t"+pAlimentos.getNutrientesProblema());
        System.out.println(">DEBUG: Index:\t"+pAlimentos.getIndex());


    }



}