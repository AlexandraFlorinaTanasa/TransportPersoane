package org.entity;

import java.util.Objects;

public class Masina {
private Integer codMasina;
private  String tipMasina;
private Integer nrlocuri;
public Integer getCodMasina() {
	return codMasina;
}
public void setCodMasina(Integer codMasina) {
	this.codMasina = codMasina;
}
public String getTipMasina() {
	return tipMasina;
}
public void setTipMasina(String tipMasina) {
	this.tipMasina = tipMasina;
}
public Integer getNrlocuri() {
	return nrlocuri;
}
public void setNrlocuri(Integer nrlocuri) {
	this.nrlocuri = nrlocuri;
}
public Masina(Integer codMasina, String tipMasina, Integer nrlocuri) {
	super();
	this.codMasina = codMasina;
	this.tipMasina = tipMasina;
	this.nrlocuri = nrlocuri;
}
public Masina() {
	super();
}
@Override
public int hashCode() {
	return Objects.hash(codMasina, nrlocuri, tipMasina);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Masina other = (Masina) obj;
	return Objects.equals(codMasina, other.codMasina) && Objects.equals(nrlocuri, other.nrlocuri)
			&& Objects.equals(tipMasina, other.tipMasina);
}
@Override
public String toString() {
	return "Masina [codMasina=" + codMasina + ", tipMasina=" + tipMasina + ", nrlocuri=" + nrlocuri + "]";
}

}
