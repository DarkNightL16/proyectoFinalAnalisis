package org.example.model;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.List;

/**
 * La clase ChartGenerator proporciona un método estático para generar un gráfico de barras.
 */
public class ChartGenerator {
    /**
     * Genera un gráfico de barras y lo guarda como una imagen PNG.
     *
     * @param categories las categorías en el eje x
     * @param values los valores para cada categoría
     * @param chartTitle el título del gráfico
     * @param xAxisTitle el título del eje x
     * @param yAxisTitle el título del eje y
     */
    public static void generateBarChart(List<String> categories, List<Double> values, String chartTitle, String xAxisTitle, String yAxisTitle, int i) {
        // Crea un nuevo gráfico de barras
        CategoryChart chart = new CategoryChartBuilder().width(1280).height(720).title(chartTitle).xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();

        // Configura el estilo del gráfico
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);

        // Ajusta el tamaño de las barras
        chart.getStyler().setAvailableSpaceFill(0.5); // Ajusta este valor según tus necesidades

        // Añade la rotación de las etiquetas del eje X
        chart.getStyler().setXAxisLabelRotation(15); // Ajusta este valor según tus necesidades
        // Añade la serie de datos al gráfico
        chart.addSeries("Rendimiento", categories, values);

        // Intenta guardar el gráfico como una imagen PNG
        try {
            String filename = "./src/main/java/org/example/images/Gráfico" + i + "x" + i + ".png"; // Añade ".png" al nombre del archivo
            BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            // Imprime la traza de pila para cualquier IOException
            e.printStackTrace();
        }
    }
}
