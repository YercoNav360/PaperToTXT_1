package com.google.android.gms.samples.vision.ocrreader;

/**
 * Created by Yerco_M_ (Android) on 02-08-2017.
 */

public class Nodo {
    public OcrGraphic graphic;
    public Nodo siguiente;
    public Nodo anterior;

    public Nodo() {
        graphic = null;
        siguiente = null;
        anterior = null;
    }

    public Nodo(OcrGraphic graphic, Nodo siguiente, Nodo anterior) {
        this.graphic = graphic;
        this.siguiente = siguiente;
        this.anterior= anterior;
    }
}
