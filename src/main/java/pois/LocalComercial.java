package pois;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.uqbar.geodds.Point;

import deApoyo.ExisteHorarioDisponibleEnHorarios;

@Entity
public class LocalComercial extends POI {
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Horario> horarios = new ArrayList<Horario>();
	
	public LocalComercial(){}

	public static LocalComercial nuevoLocalConRubroLibreriaEscolar(String unNombre, Point unaCoordenada,
			List<Horario> unosHorarios, Direccion unaDireccion) {
		return new LocalComercial(unNombre, unaCoordenada, 500, unosHorarios, "LibreriaEscolar", unaDireccion);
	}

	public static LocalComercial nuevoLocalConRubroKioscoDiarios(String unNombre, Point unaCoordenada,
			List<Horario> unosHorarios, Direccion unaDireccion) {
		return new LocalComercial(unNombre, unaCoordenada, 200, unosHorarios, "Kiosco Diarios", unaDireccion);
	}

	public static LocalComercial nuevoLocalConRubroCafeteria(String unNombre, Point unaCoordenada,
			List<Horario> unosHorarios, Direccion unaDireccion) {
		return new LocalComercial(unNombre, unaCoordenada, 50, unosHorarios, "Cafeteria", unaDireccion);
	}

	public static LocalComercial nuevoLocal(String unNombre, Point unaCoordenada, Integer unRadioCercania,
			List<Horario> unosHorarios, String unRubro, Direccion unaDireccion) {
		return new LocalComercial(unNombre, unaCoordenada, unRadioCercania, unosHorarios, unRubro, unaDireccion);
	}

	public LocalComercial(String unNombre, Point unaCoordenada, Integer unRadioCercania, List<Horario> unosHorarios,
			String unRubro, Direccion unaDireccion) {
		this.nombre = unNombre;
		this.setCoordenada(unaCoordenada);
		this.radioCercania = unRadioCercania;
		this.horarios.addAll(unosHorarios);
		this.rubro = unRubro;
		this.direccion = unaDireccion;
	}

	public boolean coincideConAtributo(String unTextoLibre) {
		return this.rubro.equals(unTextoLibre);
	}

	public boolean estaDisponible(LocalDateTime unMomento) {
		return (new ExisteHorarioDisponibleEnHorarios(this.horarios, unMomento)).validar();
	}

	public List<Horario> getHorarios() {
		return this.horarios;
	}

	public void actualizar(POI unPOI) {
		this.actualizarDesdeDatos(unPOI.getCoordenada(), unPOI.getRadioCercania(),
				((LocalComercial) unPOI).getHorarios(), unPOI.getRubro(), unPOI.getDireccion(), unPOI.getTags());
	}

	public void actualizarDesdeDatos(Point unaCoordenada, Integer unRadioCercania, List<Horario> unosHorarios,
			String unRubro, Direccion unaDireccion, List<String> unosTags) {

		super.actualizarDesdeDatos(unaCoordenada, unRadioCercania, unRubro, unaDireccion, unosTags);
		this.horarios = unosHorarios;
	}

}