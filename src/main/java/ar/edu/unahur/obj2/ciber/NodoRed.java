package ar.edu.unahur.obj2.ciber;

public class NodoRed {
    private Integer idNodo;
    private Integer mbps;
    private Integer limite = -50_000;

    public NodoRed(Integer id, Integer mbps) {
        this.idNodo = id;
        this.mbps = mbps;
    }

    public Integer getID() {
        return idNodo;
    }

    public void ampliarMbps(Integer mbpsDado) {

        this.mbps += mbpsDado;
    }

    public void restringirMbps(Integer mbpsDado) {
        if (mbps - mbpsDado < limite) {

        }
        this.mbps -= mbpsDado;
    }
}
