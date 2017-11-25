package ohtu;

import org.junit.Test;
import static org.mockito.Mockito.*;
import ohtu.verkkokauppa.*;

public class KauppaTest {

	@Test
	public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
	    // luodaan ensin mock-oliot
	    Pankki pankki = mock(Pankki.class);
	    
	    Viitegeneraattori viite = mock(Viitegeneraattori.class);
	    // määritellään että viitegeneraattori palauttaa viitten 42
	    when(viite.uusi()).thenReturn(42);

	    Varasto varasto = mock(Varasto.class);
	    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
	    when(varasto.saldo(1)).thenReturn(10); 
	    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

	    // sitten testattava kauppa 
	    Kauppa k = new Kauppa(varasto, pankki, viite);              

	    // tehdään ostokset
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	    k.tilimaksu("pekka", "12345");

	    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));   
	    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanKaksiEriTuotetta() {
	    // luodaan ensin mock-oliot
	    Pankki pankki = mock(Pankki.class);
	    
	    Viitegeneraattori viite = mock(Viitegeneraattori.class);
	    // määritellään että viitegeneraattori palauttaa viitten 42
	    when(viite.uusi()).thenReturn(42);

	    Varasto varasto = mock(Varasto.class);
	    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
	    when(varasto.saldo(1)).thenReturn(10); 
	    when(varasto.saldo(2)).thenReturn(10);
	    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 5));

	    // sitten testattava kauppa 
	    Kauppa k = new Kauppa(varasto, pankki, viite);              

	    // tehdään ostokset
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	    k.lisaaKoriin(2);
	    k.tilimaksu("pekka", "12345");

	    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));   
	    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanKaksiSamaaTuotetta() {
	    // luodaan ensin mock-oliot
	    Pankki pankki = mock(Pankki.class);
	    
	    Viitegeneraattori viite = mock(Viitegeneraattori.class);
	    // määritellään että viitegeneraattori palauttaa viitten 42
	    when(viite.uusi()).thenReturn(42);

	    Varasto varasto = mock(Varasto.class);
	    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
	    when(varasto.saldo(1)).thenReturn(10); 
	    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

	    // sitten testattava kauppa 
	    Kauppa k = new Kauppa(varasto, pankki, viite);              

	    // tehdään ostokset
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	    k.lisaaKoriin(1);
	    k.tilimaksu("pekka", "12345");

	    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));   
	    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanKaksiTuotettaToinenLoppu() {
	    // luodaan ensin mock-oliot
	    Pankki pankki = mock(Pankki.class);
	    
	    Viitegeneraattori viite = mock(Viitegeneraattori.class);
	    // määritellään että viitegeneraattori palauttaa viitten 42
	    when(viite.uusi()).thenReturn(42);

	    Varasto varasto = mock(Varasto.class);
	    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
	    when(varasto.saldo(1)).thenReturn(10); 
	    when(varasto.saldo(2)).thenReturn(0);
	    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 5));

	    // sitten testattava kauppa 
	    Kauppa k = new Kauppa(varasto, pankki, viite);              

	    // tehdään ostokset
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	    k.lisaaKoriin(2);
	    k.tilimaksu("pekka", "12345");

	    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));   
	    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void aloitaAsiointiNollaaEdellisenOstoksenTiedot() {
	    // luodaan ensin mock-oliot
	    Pankki pankki = mock(Pankki.class);
	    
	    Viitegeneraattori viite = mock(Viitegeneraattori.class);
	    // määritellään että viitegeneraattori palauttaa viitten 42
	    when(viite.uusi()).thenReturn(42);

	    Varasto varasto = mock(Varasto.class);
	    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
	    when(varasto.saldo(1)).thenReturn(10); 
	    when(varasto.saldo(2)).thenReturn(10);
	    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", 5));

	    // sitten testattava kauppa 
	    Kauppa k = new Kauppa(varasto, pankki, viite);              

	    // tehdään ostokset
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	    k.lisaaKoriin(2);
	    k.tilimaksu("pekka", "12345");
	    k.lisaaKoriin(1);
	    k.tilimaksu("pekka", "12345");

	    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
	    verify(viite, times(2)).uusi();   
	    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

}