package fr.uga;
import java.util.Arrays;

public class Ndarray{
    private final int ndim;
    private final int[] nshape;
    private final int nsize;
    private final float[][] array;

    // Constructeur création de NDArray avec une valeur (zeros,ones,...)
    private Ndarray(int[] shape, float nombre ){ 
        if(shape.length==0||shape.length>2){
            throw new IllegalArgumentException("Dimension 1 et 2 supportés seulement");
        }
        this.ndim = shape.length;
        this.nshape = shape.clone();
        if(shape.length == 1){
            this.nsize = shape[0];
            this.array = new float[1][shape[0]];
            Arrays.fill(this.array[0],nombre);
        }else{
            this.nsize = shape[0]*shape[1];
            this.array = new float[shape[0]][shape[1]];
            for(float[]row:this.array){
                Arrays.fill(row,nombre);
            }
        }
    }

    // NDArray 1D
    public Ndarray(float[] tab){
        this.nsize = tab.length;
        this.nshape = new int[] {nsize};
        this.ndim = 1;
        this.array = new float[1][tab.length];
        System.arraycopy(tab, 0, this.array[0], 0, tab.length);
    }

    // NDArray 2D
    public Ndarray(float[][] tab){
        int rows= tab.length;
        int cols=tab[0].length;
        this.ndim = 2;
        this.nsize = rows*cols;
        this.nshape = new int[] {rows,cols};
        this.array = new float[rows][cols];
        for (int i=0;i<rows;i++){
            System.arraycopy(tab[i], 0, this.array[i], 0, cols);
        }
    }

    //Fonction de création

    public static Ndarray zeros(int... shape){
        return new Ndarray(shape, 0f);
    }
    public static Ndarray ones(int... shape){
        return new Ndarray(shape, 1f);
    }

    public static Ndarray arange(float from, float to, float step){
        if (step==0) throw new IllegalArgumentException("step supérieur à 0");
        int n = (int)((to - from)/step);
        float [] arr = new float[n];
        for (int i=0;i<n;i++){
            arr[i]=from+i*step;
        }
        return new Ndarray(arr);
    }

    //getters
    public int getNdim(){return ndim;}
    public int[] getShape(){return nshape.clone();}
    public int getSize(){return nsize;}

    //Accès éléments
    public float get(int i){
        if (ndim!=1) throw new IllegalStateException("utilisez get(i,j) pour 2D");
            return array[0][i];
    }

    public float get(int i, int j){
        if (ndim!=2) throw new IllegalStateException("utilisez get(i) pour 1D");
            return array[i][j];
    }

    // Affichage

    @Override 
    public String toString(){
        StringBuilder sb= new StringBuilder("array(");
        if (ndim==1){
            sb.append(rowToString(array[0]));
        }else{
            sb.append("[");
            for (int i=0;i<array.length;i++){
                if (i>0) sb.append(",\n         ");
                sb.append(rowToString(array[i]));
            }
            sb.append("]");
        }
        sb.append(")");
        return sb.toString();
    }

    private static String rowToString(float[] row){
        StringBuilder sb=new StringBuilder("[");
        for (int i=0;i<row.length;i++){
            if(i>0) sb.append(", ");
            sb.append(row[i]==(int)row[i] ? (int) row[i]+".":row[i]);
        }
        return sb.append("]").toString();
    }
    
    
}