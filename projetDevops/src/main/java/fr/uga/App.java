package fr.uga;


public class App {
    public static void main(String[] args) {
        System.out.println("\n-------Démonstration de notre bibliothèque Ndarray-------\n");

        //Méthodes de fabrication
        Ndarray t0=Ndarray.zeros(2,3);
        Ndarray t1=Ndarray.ones(2,3);
        System.out.println("---Tableau de zéros---\n"+t0.toString());
        System.out.println("Tableau de uns:\n"+t1.toString()+"\n");

        //Addition
        Ndarray add=t0.add(t1);
        System.out.println("---Addition tableau zéros et uns---\n"+add.toString()+"\n");

        //Arange
        Ndarray t2=Ndarray.arange(0, 25, 5);
        System.out.println("---Tableau de 0 à 20 avec des pas de 5---\n"+t2.toString()+"\n");

        //Reshape
        Ndarray reshape=t2.reshape(2,3);
        System.out.println("---Reshape du tableau de 0 à 20 vers (2x3)---\n"+reshape.toString()+"\n");

        //Accès élément
        System.out.println("---Récupérer des valeurs---");
        System.out.println("---Dimension 1---");
        System.out.println(t2.toString());
        System.out.println("Valeur à [0]: "+t2.get(0)+"\n");
        System.out.println("---Dimension 2---");
        System.out.println(reshape.toString());
        System.out.println("Valeur à [0,0]: "+reshape.get(0,0)+"\n");
    }
}
