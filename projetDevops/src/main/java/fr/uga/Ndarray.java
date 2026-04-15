package fr.uga;

public class Ndarray<E>{
    private int ndim;
    private int[] nshape;
    private int nsize;
    private E[][] array;

    private Ndarray(int[] shape, E nombre ){ // pour avoir des fonctions de type zero
        if(shape.length>2){
            throw new java.lang.Error("dimension greater than 2");
        }else if (shape.length == 1){
            nsize = shape[0];
            array = (E[][]) new Object[1][shape[0]];
        }else{
            nsize = shape[0]*shape[1];
            array = (E[][]) new Object[shape[0]][shape[1]];
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

    public int getdim(){return ndim;}

    public int[] getnshape(){return nshape;}

    public int getsize(){return nsize;}

    public Ndarray<E> zero(int[] shape, E nombre){
        return new Ndarray<E>(shape, nombre);
    }
}