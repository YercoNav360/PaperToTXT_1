package com.google.android.gms.samples.vision.ocrreader;

import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Yerco_M_ (Android) on 02-08-2017.
 */

public class ListaEnlazadaDoble {
    private Nodo first,last,lista;
    private int size;

    public ListaEnlazadaDoble() {

        Nodo n = new Nodo();

        first = n;
        last = n;
        lista = n;
        //crea lista vacia

    }

    public Nodo getFirst()
    {
        return first;
    }

    public Nodo getLast()
    {
        return last;
    }

    public boolean add(OcrGraphic graphic){
        try{
            if(size > 0) {
                Nodo n = new Nodo();
                n.graphic = graphic;

                lista.siguiente = n;
                n.anterior = lista;
                n.siguiente = null;
                lista = n;
                size++;
                last = n;
            }else{
                lista.graphic = graphic;
                size++;
            }


            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public boolean addSetToList(Set<OcrGraphic> set){

        for (OcrGraphic g:
             set) {
            add(g);
        }

        return true;
    }

    public boolean sendDataToArrayList(ArrayList<OcrGraphic> list){
        Nodo n=first;
        int cont=-1;

        if(n!=null) {
            do {
                list.add(n.graphic);
            } while ((n = n.siguiente) != null);

            return true;
        }else{
            return false;
        }
    }

    public boolean isOrderPosition()
    {
        Nodo n1 = first;
        Nodo n2;
        int cont = 0;

        do {

            n2 = n1.siguiente;
            if(n2 != null && (n1.graphic.getPosition()[0] < n2.graphic.getPosition()[0] ||
                    n1.graphic.getPosition()[1] < n2.graphic.getPosition()[1])){
                cont++;
            }

        } while ((n1 = n1.siguiente) != null);

        if(cont == size)
        {
            return true;
        }

        return false;
    }

    public void orderByPosition()
    {
        Nodo n1 = getFirst();
        Nodo n2;
        Nodo n3;

        //imprimir();

        for(int i = 0; i < size; i++ ) {

            for (int j = 0; j < (size - 1); j++){

                n3 = getNodoByIndex(j);
                n2 = n3.siguiente;


                if(n3 != n2 && ((n3.graphic.getPosition()[0] > n2.graphic.getPosition()[0])||
                        (n3.graphic.getPosition()[1] > n2.graphic.getPosition()[1]))){

                    //Log.i("coparacion","x = "+n3.graphic.getPosition()[0]+">"+n2.graphic.getPosition()[0]);
                    //Log.i("coparacion","y = "+n3.graphic.getPosition()[1]+">"+n2.graphic.getPosition()[1]);

                    change(n3, n2);
                }

                //imprimir();

                if(isOrderPosition())
                {
                    break;
                }
            }

        }
    }

    public void change(Nodo n1, Nodo n2)
    {
        //Log.i("cambio","Cambiando n1 = " + n1.graphic.getTextBlock().getValue() + "/n2 = "+n2.graphic.getTextBlock().getValue());

        Nodo nd1;
        Nodo nd2;

        if(getIndex(n1) > getIndex(n2)){
            nd1 = new Nodo(n2.graphic,n2.siguiente,n2.anterior);
            nd2 = new Nodo(n1.graphic,n1.siguiente,n1.anterior);

        }else{
            nd1 = new Nodo(n1.graphic,n1.siguiente,n1.anterior);
            nd2 = new Nodo(n2.graphic,n2.siguiente,n2.anterior);
        }

        if(n1.anterior != null){
            n1.anterior.siguiente = nd2;
            nd2.anterior = n1.anterior;

        }else{
            nd2.anterior = null;
            first = nd2;

        }

        nd2.siguiente=n1.siguiente;
        n1.siguiente.anterior=nd2;

        n2.anterior.siguiente=nd1;
        nd1.siguiente=n2.siguiente;
        nd1.anterior=n2.anterior;

        if(n2.siguiente!=null){
            n2.siguiente.anterior=nd1;

        }else if(n2.siguiente==null){
            last=nd1;

        }

    }

    public Nodo getNodoByIndex(int index)
    {
        if(index>-1){
            Nodo n = first;
            int cont = -1;
            do {
                cont++;
                if(index == cont){
                    return n;
                }
            } while (( n = n.siguiente) != null);

            return null;
        }else{
            return null;
        }
    }

    public int getIndex(Nodo nodo){
        Nodo n = first;
        int cont =- 1;

        do {
            cont++;
            if(nodo == n){
                return cont;
            }
        } while ((n = n.siguiente)!= null);

        return cont;
    }

    public boolean existsNodo(Nodo nodo)
    {
        Nodo n= first;

        do {
            if(nodo==n){
                return true;
            }
        } while ((n=n.siguiente)!=null);

        return false;
    }

    public boolean deleteNodo(Nodo nodo)
    {
        if(existsNodo(nodo))
        {
            int index=getIndex(nodo);

            if(nodo!=first){
                nodo.anterior.siguiente=nodo.siguiente;
            }else{
                first=nodo.siguiente;
            }

            if(index!=(size-1))
            {
                nodo.siguiente.anterior=nodo.anterior;
            }else{
                last=nodo.anterior;
            }

            nodo=null;
            size--;

            return true;
        }else{
            return false;
        }
    }

    public void imprimir()
    {
        Nodo n = first;
        int cont = 0;

        Log.i("imprimir","Imprimiendo Graficos");
        Log.i("imprimir","***-----------------***");

        if(n != null){
            do {

                if((n.anterior)!= null){
                    Log.i("imprimir",n.anterior.graphic.getTextBlock().getValue()+"<-");
                }else{
                    Log.i("imprimir","Anterior: No Tiene<-");
                }

                Log.i("imprimir",n .graphic.getTextBlock().getValue());

                if((n.siguiente) != null){
                    Log.i("imprimir","->"+n.siguiente.graphic.getTextBlock().getValue());
                }else{
                    Log.i("imprimir","->Siguiente: No Tiene");
                }

                Log.i("imprimir","--");
            } while ((n=n.siguiente)!=null);

            Log.i("imprimir","***-----------------***");
        }else{
            Log.i("imprimir","No hay registros para imprimir");
        }
    }
}
