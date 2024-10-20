import java.util.Arrays;
import java.util.Comparator;

public class Prioridad {
    public static void main(String[] args) {

        String[] trabajos = {"A", "B", "C", "D", "E"};
        int[] rafagaCPU = {3, 1, 3, 4, 2};
        int[] tiempoLlegada = {2, 4, 0, 1, 3};
        int[] prioridad = {2, 3, 1, 3, 4};

        Job[] jobArray = new Job[trabajos.length];
        for (int i = 0; i < trabajos.length; i++) {
            jobArray[i] = new Job(trabajos[i], tiempoLlegada[i], rafagaCPU[i], prioridad[i]);
        }

        Arrays.sort(jobArray, Comparator.comparingInt(j -> j.tiempoLlegada));

        int tiempoActual = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[trabajos.length];
        int[] tiempoRetorno = new int[trabajos.length];
        int[] tiempoEspera = new int[trabajos.length];


        while (completed < trabajos.length) {

            int idx = -1;
            int minPrioridad = Integer.MAX_VALUE;

            for (int i = 0; i < trabajos.length; i++) {
                if (jobArray[i].tiempoLlegada <= tiempoActual && !isCompleted[i] && jobArray[i].prioridad < minPrioridad) {
                    minPrioridad = jobArray[i].prioridad;
                    idx = i;
                }
            }

            if (idx != -1) {

                tiempoActual += jobArray[idx].rafaga;
                tiempoRetorno[idx] = tiempoActual;
                tiempoEspera[idx] = tiempoRetorno[idx] - jobArray[idx].tiempoLlegada - jobArray[idx].rafaga; // Tiempo de espera
                isCompleted[idx] = true;
                completed++;
            } else {
                tiempoActual++;
            }
        }
        double tiempoMedioEspera = 0;
        double tiempoMedioRetorno = 0;
        for (int i = 0; i < trabajos.length; i++) {
            tiempoMedioEspera += tiempoEspera[i];
            tiempoMedioRetorno += tiempoRetorno[i];
        }
        tiempoMedioEspera /= trabajos.length;
        tiempoMedioRetorno /= trabajos.length;

        System.out.println("Trabajo\t\tTiempo de Llegada\tRáfaga CPU\tPrioridad\tTiempo de Espera\tTiempo de Retorno");
        for (int i = 0; i < trabajos.length; i++) {
            System.out.println(jobArray[i].nombre + "\t\t\t\t" + jobArray[i].tiempoLlegada + "\t\t\t\t" + jobArray[i].rafaga + "\t\t\t\t" + jobArray[i].prioridad + "\t\t\t\t" + tiempoEspera[i] + "\t\t\t\t" + tiempoRetorno[i]);
        }
        System.out.println("Tiempo Medio de Espera: " + tiempoMedioEspera);
        System.out.println("Tiempo Medio de Retorno: " + tiempoMedioRetorno);
    }
}