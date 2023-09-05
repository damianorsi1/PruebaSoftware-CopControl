package avion;

import avion.AvionSimple;
import copControl.Dificultad;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;

import org.junit.Before;
import org.junit.Test;
import avion.Avion;
import pista.Pista;
import pista.PistaSimple;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class AvionesTest {
    Avion avionUno,avionDos,avionTres;
    Mapa mapa;
    Pista pista;
    Nivel nivel;
    Dificultad dificultad;


    @Before
    public void setUp() throws Exception {
        Posicion posicionPista = new Posicion(0,0);

        Posicion posicionAvion1 = new Posicion(0,3);
        Posicion posicionAvion2 = new Posicion(0,24);
        Posicion posicionAvion3 = new Posicion(0,30);

        List<Pista> pistas = new ArrayList<Pista>();
        pista = new PistaSimple(posicionPista);
        pistas.add(pista);
        mapa = new Mapa(pistas);
        avionUno = new AvionSimple(posicionAvion1, posicionPista, mapa);
        avionDos = new AvionSimple(posicionAvion2, posicionPista, mapa);
        avionTres = new AvionSimple(posicionAvion3, posicionPista, mapa);

        dificultad = new Dificultad(4,10,0);
        nivel = new Nivel(mapa, dificultad);
    }
    
    @Test
    public void colocaAvionEnAire() {
        mapa.colocarAvionEnAire(avionUno);
        assertEquals(avionUno,nivel.getAvionesVolando().get(0));
    }

    @Test
    public void chocanAvionDosyTres() {
        mapa.colocarAvionEnAire(avionUno);
        mapa.colocarAvionEnAire(avionDos);
        assertFalse(nivel.huboChoque());
        mapa.colocarAvionEnAire(avionTres);
        assertTrue(nivel.huboChoque());
    }

    @Test
    public void aterrizanUnAvionEnPosicion() {
        mapa.colocarAvionEnAire(avionUno);
        mapa.colocarAvionEnAire(avionTres);
        int cantidadAvionesAterrizados = nivel.aterrizarAviones();
        assertTrue(cantidadAvionesAterrizados == 1);
    }
}