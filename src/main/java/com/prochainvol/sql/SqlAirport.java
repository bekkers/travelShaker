package com.prochainvol.sql;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import com.prochainvol.json.ProchainvolObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "aeroports")
public class SqlAirport extends ProchainvolObject implements Serializable {

	private static final Logger logger = Logger.getLogger(SqlAirport.class.getName());


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;

	protected int openflightsid=0;
	protected String iata="";
	protected String icao="";
	protected String city="";
	protected String country="";

	@Column(name = "titre")
	protected String name="";
	@Temporal(TemporalType.DATE)
	protected Date maj = new Date();
	/**
	 * Hours offset from UTC. Fractional hours are expressed as decimals, eg.
	 * India is 5.5.
	 */
	protected Float timezone=0f;
	/**
	 * Daylight savings time. One of E (Europe), A (US/Canada), S (South
	 * America), O (Australia), Z (New Zealand), N (None) or U (Unknown). See
	 * also: Help: Time
	 */
	protected String dst="";
	/**
	 * database time zone Timezone in "tz" (Olson) format, eg.
	 * "America/Los_Angeles".
	 */
	protected String tz="";

	protected Float latitude=0f;
	protected Float longitude=0f;

	protected Float altitude = 0f;
	protected Integer active = 1;
	protected Integer size=0;
	protected Integer multi=0;
	protected String titrefr="";
	protected String titrefull="";
	protected String titrefullfr="";
	protected String iataz="";
	protected String countryfr="";
	protected String cityfr="";

	protected String image="";

	protected String slug="";

	public SqlAirport() {
		super();
	}

	public SqlAirport(String iata) {
		super();
		this.iata = iata;
	}

	public SqlAirport(String nom, String ville, String etat, String iata) {
		this(iata);
		this.name = nom;
		this.city = ville;
		this.country = etat;
	}

	public SqlAirport(String nom, String ville, String etat, String iata, String icao) {
		this(nom, ville, etat, iata);
		this.icao = icao;
	}

	public int getActive() {
		return active;
	}

	public int getAirportId() {
		return openflightsid;
	}

	public Float getAltitude() {
		return altitude;
	}

	public String getCity() {
		return city;
	}

	public String getCityfr() {
		return cityfr;
	}

	public String getCountry() {
		return country;
	}

	public String getCountryfr() {
		return countryfr;
	}

	public String getDst() {
		return dst;
	}

	public String getFullName() {
		return String.format("%s, %s, %s", getName(), getCity(), country);
	}

	public String getFullNameWithIata() {
		return String.format("(%s) %s, %s, %s", getIata(), getName(), getCity(), country);
	}

	public String getIata() {
		return iata;
	}

	public String getIataz() {
		return iataz;
	}

	public String getIcao() {
		return icao;
	}

	public int getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public Date getMaj() {
		return maj;
	}

	public int getMulti() {
		return multi;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public String getSlug() {
		return slug;
	}

	public float getTimezone() {
		return timezone;
	}

	public String getTitrefr() {
		return titrefr;
	}

	public String getTitrefull() {
		return titrefull;
	}

	public String getTitrefullfr() {
		return titrefullfr;
	}

	public String getTz() {
		return tz;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setAirportId(int airportId) {
		this.openflightsid = airportId;
	}

	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCityfr(String cityfr) {
		this.cityfr = cityfr;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountryfr(String countryfr) {
		this.countryfr = countryfr;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public void setIataz(String iataz) {
		this.iataz = iataz;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public void setMaj(Date maj) {
		this.maj = maj;
	}

	public void setMulti(int multi) {
		this.multi = multi;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public void setTimezone(float timezone) {
		this.timezone = timezone;
	}

	public void setTitrefr(String titrefr) {
		this.titrefr = titrefr;
	}

	public void setTitrefull(String titrefull) {
		this.titrefull = titrefull;
	}

	public void setTitrefullfr(String titrefullfr) {
		this.titrefullfr = titrefullfr;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public int getOpenflightsid() {
		return openflightsid;
	}

	public void setOpenflightsid(int openflightsid) {
		this.openflightsid = openflightsid;
	}

	public void setTimezone(Float timezone) {
		this.timezone = timezone;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setMulti(Integer multi) {
		this.multi = multi;
	}

}
