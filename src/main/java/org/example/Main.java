package org.example;

import org.example.model.GeneradorMatrices;
import org.example.model.TiempoEjecucion;

public class Main {
    public static void main(String[] args) {

        //GeneradorMatrices.generarMatricesA();

        int[] sizes = {64, 128, 256, 512};


        for (int size : sizes) {
            TiempoEjecucion.calcularTiempos(size);
          
        }

        System.out.println("¡Tiempos de ejecución calculados exitosamente!");   
    }
}
