package org.entity;

import java.util.Objects;

public class Soferi {
private Integer  idSofer;
private String nume;

public Integer getIdSofer() {
	return idSofer;
}
public void setIdSofer(Integer idSofer) {
	this.idSofer = idSofer;
}
public String getNume() {
	return nume;
}
public void setNume(String nume) {
	this.nume = nume;
}
public Soferi(Integer idSofer, String nume) {
	super();
	this.idSofer = idSofer;
	this.nume = nume;
}
public Soferi() {
	super();
}
@Override
public int hashCode() {
	return Objects.hash(idSofer, nume);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Soferi other = (Soferi) obj;
	return Objects.equals(idSofer, other.idSofer) && Objects.equals(nume, other.nume);
}
@Override
public String toString() {
	return "Soferi [idSofer=" + idSofer + ", nume=" + nume + "]";
}

}
