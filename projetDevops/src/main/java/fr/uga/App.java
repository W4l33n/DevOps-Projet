package fr.uga;


public class App {
    public static void main(String[] args) {
        System.out.println("\n-------Démonstration de notre bibliothèque Ndarray-------\n");

        //Méthodes de fabrication
        Ndarray t0=Ndarray.zeros(2,3);
        Ndarray t1=Ndarray.ones(2,3);
        System.out.println("---Tableau de zéros---\n"+t0.toString()+"\n");
        System.out.println("Tableau de uns:\n"+t1.toString()+"\n");

        //Arange
        Ndarray t2=Ndarray.arange(2,6,2);
        System.out.println("---Tableau de 2 à 6 avec des pas de 2---\n"+t2.toString()+"\n");


        //Addition
        Ndarray add=t1.add(t1);
        System.out.println("---Addition tableau uns et uns---\n"+add.toString()+"\n");

        //Soustraction
        System.out.println("---Soustraction---\n");
        Ndarray t3=Ndarray.ones(3);
        System.out.println(t2.toString()+"\n"+t3.toString()+"\n");
        Ndarray sub=t2.sub(t3);
        System.out.println("----Resultat soustraction---\n"+sub.toString()+"\n");

        // Multiplication
        System.out.println("---Multiplication---\n");
        System.out.println(t2.toString()+"\n"+t3.toString()+"\n");
        Ndarray mul=t2.mul(t3);
        System.out.println("---Résultat multiplication---\n"+mul.toString()+"\n");


        //Reshape
        Ndarray t4=Ndarray.arange(2, 12, 2);
        Ndarray reshape=t4.reshape(2,3);
        System.out.println("---Reshape du tableau de 2 à 12 avec pas de 2 à 1D vers (2x3)---\n"+reshape.toString()+"\n");

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
