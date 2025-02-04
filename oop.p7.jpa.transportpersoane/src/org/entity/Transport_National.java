package org.entity;

import java.util.Objects;

public class Transport_National extends Transport {
 private String tipTrasport; //interurban, interegional
 private String plecare;
 private String destinatie;
private Integer durata; // in minute
private Integer nrKm;

public String getTipTrasport() {
	return tipTrasport;
}
public void setTipTrasport(String tipTrasport) {
	this.tipTrasport = tipTrasport;
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
public Transport_National(Integer idTransport, String nume, Double pretTransport, String tipTrasport, String plecare,
		String destinatie, Integer durata, Integer nrKm) {
	super(idTransport, nume, pretTransport);
	this.tipTrasport = tipTrasport;
	this.plecare = plecare;
	this.destinatie = destinatie;
	this.durata = durata;
	this.nrKm = nrKm;
}
public Transport_National(String tipTrasport, String plecare, String destinatie, Integer durata, Integer nrKm) {
	super();
	this.tipTrasport = tipTrasport;
	this.plecare = plecare;
	this.destinatie = destinatie;
	this.durata = durata;
	this.nrKm = nrKm;
}
public Transport_National(Integer idTransport, String nume, Double pretTransport) {
	super(idTransport, nume, pretTransport);
}
public Transport_National() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(destinatie, durata, nrKm, plecare, tipTrasport);
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
	Transport_National other = (Transport_National) obj;
	return Objects.equals(destinatie, other.destinatie) && Objects.equals(durata, other.durata)
			&& Objects.equals(nrKm, other.nrKm) && Objects.equals(plecare, other.plecare)
			&& Objects.equals(tipTrasport, other.tipTrasport);
}
@Override
public String toString() {
	return "Transport_National [tipTrasport=" + tipTrasport + ", plecare=" + plecare + ", destinatie=" + destinatie
			+ ", durata=" + durata + ", nrKm=" + nrKm + "]";
}

}