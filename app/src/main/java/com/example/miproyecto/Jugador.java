public class Jugador {

    private String nombre;
    private int edad;
    private int peso;
    private String trofeo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getTrofeo() {
        return trofeo;
    }

    public void setTrofeo(String trofeo) {
        this.trofeo = trofeo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", trofeo='" + trofeo + '\'' +
                '}';
    }
}
