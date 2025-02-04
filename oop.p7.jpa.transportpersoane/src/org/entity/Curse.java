package org.entity;

import java.util.Objects;

public class Curse {
private Integer idCursa;
private String tipCursa; //de zi, de noapte

public Integer getIdCursa() {
	return idCursa;
}
public void setIdCursa(Integer idCursa) {
	this.idCursa = idCursa;
}
public String getTipCursa() {
	return tipCursa;
}
public void setTipCursa(String tipCursa) {
	this.tipCursa = tipCursa;
}
public Curse(Integer idCursa, String tipCursa) {
	super();
	this.idCursa = idCursa;
	this.tipCursa = tipCursa;
}
public Curse() {
	super();
}
@Override
public int hashCode() {
	return Objects.hash(idCursa, tipCursa);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Curse other = (Curse) obj;
	return Objects.equals(idCursa, other.idCursa) && Objects.equals(tipCursa, other.tipCursa);
}
@Override
public String toString() {
	return "Curse [idCursa=" + idCursa + ", tipCursa=" + tipCursa + "]";
}


}
