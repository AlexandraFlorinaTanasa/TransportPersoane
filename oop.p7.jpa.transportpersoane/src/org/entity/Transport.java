package org.entity;

import java.util.Objects;

public class Transport {
private Integer idTransport;
private String nume;
private Double pretTransport;

public Integer getIdTransport() {
	return idTransport;
}
public void setIdTransport(Integer idTransport) {
	this.idTransport = idTransport;
}
public String getNume() {
	return nume;
}
public void setNume(String nume) {
	this.nume = nume;
}
public Double getPretTransport() {
	return pretTransport;
}
public void setPretTransport(Double pretTransport) {
	this.pretTransport = pretTransport;
}
public Transport(Integer idTransport, String nume, Double pretTransport) {
	super();
	this.idTransport = idTransport;
	this.nume = nume;
	this.pretTransport = pretTransport;
}
public Transport() {
	super();
}
@Override
public int hashCode() {
	return Objects.hash(idTransport, nume, pretTransport);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Transport other = (Transport) obj;
	return Objects.equals(idTransport, other.idTransport) && Objects.equals(nume, other.nume)
			&& Objects.equals(pretTransport, other.pretTransport);
}
@Override
public String toString() {
	return "Transport [idTransport=" + idTransport + ", nume=" + nume + ", pretTransport=" + pretTransport + "]";
}

}
