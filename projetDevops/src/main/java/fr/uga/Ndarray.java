package fr.uga;

public class Ndarray{
    private int ndim;
    private int[] nshape;
    private int nsize;
    private float[][] array;

    private Ndarray(int[] shape, float nombre ){ // pour avoir des fonctions de type zero
        if(shape.length>2){
            throw new java.lang.Error("dimension greater than 2");
        }else if (shape.length == 1){
            nsize = shape[0];
            array = new float[1][shape[0]];
        }else{
            nsize = shape[0]*shape[1];
            array = new float[shape[0]][shape[1]];
        }
        ndim = shape.length;
        nshape = shape;
        if(shape.length == 1){
            for(int i=0; i<shape[0]; i++){
                array[0][i] = nombre;
            }
        }else{
            for(int i=0; i<shape[0]; i++){
                for(int j=0; j<shape[1]; j++){
                    array[i][j] = nombre;
                }
            }
        }
    }

    private Ndarray(float[][] a){
        nsize = a[0].length;
        nshape = new int[] {nsize};
        ndim = 1;
        array = a;
    }

    public int getdim(){return ndim;}

    public int[] getnshape(){return nshape;}

    public int getsize(){return nsize;}

    public Ndarray zero(int[] shape, float nombre){
        return new Ndarray(shape, nombre);
    }

    public Ndarray array(float[] tab){
        float[][]arr = new float[][] {tab};
        return new Ndarray(arr);
    }

    public Ndarray arange(float from, float to, float step){
        float [][] arr = new float[1][(int) ((to - from)/step)];
        int i = 0;
        float value = from;
        while(value<to){
            arr[0][i]=value;
            value = value + step;
            i++;
        }

        return new Ndarray(arr);
    }
}