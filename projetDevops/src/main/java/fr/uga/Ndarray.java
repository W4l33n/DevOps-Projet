package fr.uga;

import java.util.Arrays;

/**
 * Classe Ndarray : Bibliothèque de calcul scientifique pour tableaux
 * multidimensionnels.
 * Elle permet de manipuler des tableaux 1D et 2D avec des opérations
 * mathématiques de base.
 * 
 * @author JACOLIN Loa, REMY Marine, VOLF DIANA
 * @version 1.0
 */
public class Ndarray {
    private final int ndim;
    private final int[] nshape;
    private final int nsize;
    private final float[][] array;

    // Constructeur création de NDArray avec une valeur (zeros,ones,...)
    private Ndarray(int[] shape, float nombre) {
        if (shape.length == 0 || shape.length > 2) {
            throw new IllegalArgumentException("Dimension 1 et 2 supportés seulement");
        }
        this.ndim = shape.length;
        this.nshape = shape.clone();
        if (shape.length == 1) {
            this.nsize = shape[0];
            this.array = new float[1][shape[0]];
            Arrays.fill(this.array[0], nombre);
        } else {
            this.nsize = shape[0] * shape[1];
            this.array = new float[shape[0]][shape[1]];
            for (float[] row : this.array) {
                Arrays.fill(row, nombre);
            }
        }
    }

    /**
     * Constructeur pour un tableau à une dimension (1D).
     * 
     * @param tab Le tableau de flottants source.
     */
    public Ndarray(float[] tab) {
        this.nsize = tab.length;
        this.nshape = new int[] { nsize };
        this.ndim = 1;
        this.array = new float[1][tab.length];
        System.arraycopy(tab, 0, this.array[0], 0, tab.length);
    }

    /**
     * Constructeur pour un tableau à deux dimensions (2D).
     * 
     * @param tab La matrice de flottants source.
     */
    public Ndarray(float[][] tab) {
        int rows = tab.length;
        int cols = tab[0].length;
        this.ndim = 2;
        this.nsize = rows * cols;
        this.nshape = new int[] { rows, cols };
        this.array = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(tab[i], 0, this.array[i], 0, cols);
        }
    }

    /**
     * Crée un tableau rempli de zéros selon la forme spécifiée.
     * 
     * @param shape Liste d'entiers définissant la forme (ex: 2, 3 pour une matrice
     *              2x3).
     * @return Une nouvelle instance de Ndarray.
     */
    public static Ndarray zeros(int... shape) {
        return new Ndarray(shape, 0f);
    }

    /**
     * Crée un tableau rempli de la valeur 1 selon la forme spécifiée.
     * 
     * @param shape Liste d'entiers définissant la forme.
     * @return Une nouvelle instance de Ndarray.
     */
    public static Ndarray ones(int... shape) {
        return new Ndarray(shape, 1f);
    }

    /**
     * Génère un tableau 1D contenant une séquence de nombres.
     * 
     * @param from Valeur de début (inclusive).
     * @param to   Valeur de fin (exclusive).
     * @param step Pas de progression entre chaque valeur.
     * @return Un Ndarray 1D.
     * @throws IllegalArgumentException Si le pas est égal à zéro.
     */
    public static Ndarray arange(float from, float to, float step) {
        if (step == 0)
            throw new IllegalArgumentException("step supérieur à 0");
        int n = (int) ((to - from) / step);
        float[] arr = new float[n];
        for (int i = 0; i < n; i++) {
            arr[i] = from + i * step;
        }
        return new Ndarray(arr);
    }

    // getters
    public int getNdim() {
        return ndim;
    }

    public int[] getShape() {
        return nshape.clone();
    }

    public int getSize() {
        return nsize;
    }

    // Accès éléments
    public float get(int i) {
        if (ndim != 1)
            throw new IllegalStateException("utilisez get(i,j) pour 2D");
        return array[0][i];
    }

    public float get(int i, int j) {
        if (ndim != 2)
            throw new IllegalStateException("utilisez get(i) pour 1D");
        return array[i][j];
    }

    // Affichage

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("array(");
        if (ndim == 1) {
            sb.append(rowToString(array[0]));
        } else {
            sb.append("[");
            for (int i = 0; i < array.length; i++) {
                if (i > 0)
                    sb.append(",\n         ");
                sb.append(rowToString(array[i]));
            }
            sb.append("]");
        }
        sb.append(")");
        return sb.toString();
    }

    private static String rowToString(float[] row) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < row.length; i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(row[i] == (int) row[i] ? (int) row[i] + "." : row[i]);
        }
        return sb.append("]").toString();
    }

    /**
     * Additionne deux tableaux élément par élément.
     * 
     * @param other Le Ndarray à ajouter à l'instance actuelle.
     * @return Le résultat de l'addition dans un nouveau Ndarray.
     * @throws IllegalArgumentException Si les formes des deux tableaux ne sont pas
     *                                  identiques.
     */
    public Ndarray add(Ndarray other) {
        if (!Arrays.equals(this.nshape, other.nshape)) {
            throw new IllegalArgumentException("Les dimensions sont différentes");
        }
        if (this.ndim == 1) {
            float[] result = new float[this.nsize];
            for (int i = 0; i < this.nsize; i++) {
                result[i] = this.array[0][i] + other.array[0][i];
            }
            return new Ndarray(result);
        } else {
            float[][] result = new float[this.nshape[0]][this.nshape[1]];
            for (int i = 0; i < this.nshape[0]; i++) {
                for (int j = 0; j < this.nshape[1]; j++) {
                    result[i][j] = this.array[i][j] + other.array[i][j];
                }
            }
            return new Ndarray(result);
        }
    }

    /**
     * Soustrait un tableau d'un autre, élément par élément.
     * 
     * @param other Le Ndarray à soustraire de l'instance actuelle.
     * @return Le résultat de la soustraction dans un nouveau Ndarray.
     * @throws IllegalArgumentException Si les formes ne correspondent pas.
     */
    public Ndarray sub(Ndarray other) {
        if (!Arrays.equals(this.nshape, other.nshape)) {
            throw new IllegalArgumentException("Les dimensions sont différentes");
        }
        if (this.ndim == 1) {
            float[] result = new float[this.nsize];
            for (int i = 0; i < this.nsize; i++) {
                result[i] = this.array[0][i] - other.array[0][i];
            }
            return new Ndarray(result);
        } else {
            float[][] result = new float[this.nshape[0]][this.nshape[1]];
            for (int i = 0; i < this.nshape[0]; i++) {
                for (int j = 0; j < this.nshape[1]; j++) {
                    result[i][j] = this.array[i][j] - other.array[i][j];
                }
            }
            return new Ndarray(result);
        }
    }

    /**
     * Multiplie deux tableaux élément par élément.
     * 
     * @param other Le Ndarray à multiplier avec l'instance actuelle.
     * @return Le résultat de la multiplication dans un nouveau Ndarray.
     * @throws IllegalArgumentException Si les formes ne correspondent pas.
     */
    public Ndarray mul(Ndarray other) {
        if (!Arrays.equals(this.nshape, other.nshape)) {
            throw new IllegalArgumentException("Les dimensions sont différentes");
        }
        if (this.ndim == 1) {
            float[] result = new float[this.nsize];
            for (int i = 0; i < this.nsize; i++) {
                result[i] = this.array[0][i] * other.array[0][i];
            }
            return new Ndarray(result);
        } else {
            float[][] result = new float[this.nshape[0]][this.nshape[1]];
            for (int i = 0; i < this.nshape[0]; i++) {
                for (int j = 0; j < this.nshape[1]; j++) {
                    result[i][j] = this.array[i][j] * other.array[i][j];
                }
            }
            return new Ndarray(result);
        }
    }

    /**
     * Redimensionne le tableau vers une nouvelle forme.
     * 
     * @param shape La nouvelle forme désirée.
     * @return Un nouveau Ndarray avec la forme spécifiée mais les mêmes données.
     * @throws IllegalArgumentException Si le nombre total d'éléments ne correspond
     *                                  pas à l'original.
     */
    public Ndarray reshape(int... shape) {
        if (shape.length == 0 || shape.length > 2) {
            throw new IllegalArgumentException("Les dimensions doivent être 1 ou 2");
        }

        int expectedSize = 1;
        for (int dim : shape) {
            expectedSize *= dim;
        }

        if (expectedSize != this.nsize) {
            throw new IllegalArgumentException("La nouvelle forme doit avoir le même nombre d'éléments (" + this.nsize
                    + " attendu, " + expectedSize + " calculé).");
        }

        // Aplatir le tableau dans un tableau 1D
        float[] flat = new float[this.nsize];
        int index = 0;
        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array[i].length; j++) {
                flat[index++] = this.array[i][j];
            }
        }

        // Reconstruire le Ndarray selon la forme
        if (shape.length == 1) {
            return new Ndarray(flat);
        } else {
            float[][] newArray = new float[shape[0]][shape[1]];
            index = 0;
            for (int i = 0; i < shape[0]; i++) {
                for (int j = 0; j < shape[1]; j++) {
                    newArray[i][j] = flat[index++];
                }
            }
            return new Ndarray(newArray);
        }
    }
}