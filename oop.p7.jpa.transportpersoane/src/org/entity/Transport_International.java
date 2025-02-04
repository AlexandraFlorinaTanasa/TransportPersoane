package org.entity;

import java.util.Objects;

public class Transport_International extends Transport {
 private String tipTransport; //intercontinental, de cabotaj
 private String plecare;
 private String destinatie;
private Integer durata; //in minute
private Integer nrKm;
public String getTipTransport() {
	return tipTransport;
}
public void setTipTransport(String tipTransport) {
	this.tipTransport = tipTransport;
}
public String getPlecare() {
	return plecare;
}
public void setPlecare(String plecare) {
	this.plecare = plecare;
}
public String getDestinatie() {
	return destinatie;
}
public void setDestinatie(String destinatie) {
	this.destinatie = destinatie;
}
public Integer getDurata() {
	return durata;
}
public void setDurata(Integer durata) {
	this.durata = durata;
}
public Integer getNrKm() {
	return nrKm;
}
public void setNrKm(Integer nrKm) {
	this.nrKm = nrKm;
}
public Transport_International(Integer idTransport, String nume, Double pretTransport, String tipTransport,
		String plecare, String destinatie, Integer durata, Integer nrKm) {
	super(idTransport, nume, pretTransport);
	this.tipTransport = tipTransport;
	this.plecare = plecare;
	this.destinatie = destinatie;
	this.durata = durata;
	this.nrKm = nrKm;
}
public Transport_International(String tipTransport, String plecare, String destinatie, Integer durata, Integer nrKm) {
	super();
	this.tipTransport = tipTransport;
	this.plecare = plecare;
	this.destinatie = destinatie;
	this.durata = durata;
	this.nrKm = nrKm;
}
public Transport_International(Integer idTransport, String nume, Double pretTransport) {
	super(idTransport, nume, pretTransport);
}
public Transport_International() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(destinatie, durata, nrKm, plecare, tipTransport);
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	Transport_International other = (Transport_International) obj;
	return Objects.equals(destinatie, other.destinatie) && Objects.equals(durata, other.durata)
			&& Objects.equals(nrKm, other.nrKm) && Objects.equals(plecare, other.plecare)
			&& Objects.equals(tipTransport, other.tipTransport);
}
@Override
public String toString() {
	return "Transport_International [tipTransport=" + tipTransport + ", plecare=" + plecare + ", destinatie="
			+ destinatie + ", durata=" + durata + ", nrKm=" + nrKm + "]";
}


}