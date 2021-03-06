package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import pois.CGP;
import pois.LocalComercial;
import pois.ParadaDeColectivo;
import pois.SucursalBanco;

public class PersistenciaPOIsTest {
	SoporteDeInstanciasParaTestsBuilder soporteTest;
	ParadaDeColectivo parada114Lugano;
	LocalComercial lcStarbucks;
	SucursalBanco bancoCiudad;
	CGP cgpC5;
	EntityManager entityManager;
	EntityTransaction tx;
	
	
	@Before
	public void init(){
		soporteTest = new SoporteDeInstanciasParaTestsBuilder();
		parada114Lugano = soporteTest.paradaDeColectivo114DeLugano();
		lcStarbucks = soporteTest.starbucksCoronelDiaz1400();
		bancoCiudad = soporteTest.bancoCiudadCabildoYCongreso();
		cgpC5 = soporteTest.cgpComuna5();
		entityManager = PerThreadEntityManagers.getEntityManager();
		tx = entityManager.getTransaction();
		persistirParadaDeColectivo114();	//id = 1
		persistirLocalComercialStarbucks();	//id = 2
		persistirCGP5();					//id = 3
		persistirSucursalBancoCiudad();		//id = 4
	}
	
	@Test
	public void traerParadaColectivo114(){
		ParadaDeColectivo paradaObtenida = entityManager.find(ParadaDeColectivo.class, 1l);
		Assert.assertTrue(parada114Lugano.soyElMismoPOI(paradaObtenida));
	}
	
	@Test
	public void traerLocalComercialStarbucks(){
		LocalComercial localObtenido = entityManager.find(LocalComercial.class, 2l);
		Assert.assertTrue(lcStarbucks.soyElMismoPOI(localObtenido));
	}
	
	@Test
	public void traerCGP5(){
		CGP cgpObtenido = entityManager.find(CGP.class, 3l);
		Assert.assertTrue(cgpC5.soyElMismoPOI(cgpObtenido));
	}
	
	@Test
	public void traerBancoCiudad(){
		SucursalBanco bancoOntenido = entityManager.find(SucursalBanco.class, 4l);
		Assert.assertTrue(bancoCiudad.soyElMismoPOI(bancoOntenido));
	}

	public void persistirParadaDeColectivo114(){
		tx.begin();
		parada114Lugano.setTag("economico");
		entityManager.persist(parada114Lugano.getDireccion());
		entityManager.persist(parada114Lugano);
		tx.commit();
	}
	
	public void persistirLocalComercialStarbucks(){
		tx.begin();
		entityManager.persist(lcStarbucks.getDireccion());
		entityManager.persist(lcStarbucks);
		tx.commit();
	}
	
	public void persistirCGP5(){
		tx.begin();
		entityManager.persist(cgpC5.getDireccion());
		entityManager.persist(cgpC5);
		tx.commit();
	}
	
	public void persistirSucursalBancoCiudad(){
		tx.begin();
		entityManager.persist(bancoCiudad.getDireccion());
		entityManager.persist(bancoCiudad);
		tx.commit();
	}

}
