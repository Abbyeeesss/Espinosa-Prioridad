public class Job {
    String nombre;
    int tiempoLlegada;
    int rafaga;
    int prioridad;

    Job(String nombre, int tiempoLlegada, int rafaga, int prioridad) {
        this.nombre = nombre;
        this.tiempoLlegada = tiempoLlegada;
        this.rafaga = rafaga;
        this.prioridad = prioridad;
    }
}
