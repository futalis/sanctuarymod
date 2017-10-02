package com.futalis.sanctuary.tiles;

public class SanctuaryBlockReference {

    public int i, j, k, dim;
    public SanctuaryBlockReference(int i, int j, int k, int dim){
        this.i = i;
        this.j = j;
        this.k = k;
        this.dim = dim;
    }

    @Override
    public boolean equals(Object o) {
        boolean ret = false;
        if(o !=null && o instanceof SanctuaryBlockReference){
            SanctuaryBlockReference ref = (SanctuaryBlockReference) o;
            ret = ref.i ==i && ref.j ==j && ref.k==k && ref.dim==dim;
        }
        return ret;
    }

    @Override
    public String toString() {
        return ("("+this.i+", "+this.j+", "+this.k+"@"+this.dim+")");
    }
}
