package org.entity;

import java.util.Objects;

public class Calatori {
private Integer id;
private String nume;

public Integer getId() {
	return id;
}
public void setIdCalator(Integer id) {
	this.id = id;
}
public String getNume() {
	return nume;
}
public void setNume(String nume) {
	this.nume = nume;
}
public Calatori(Integer id, String nume) {
	super();
	this.id = id;
	this.nume = nume;
}
public Calatori() {
	super();
}
@Override
public int hashCode() {
	return Objects.hash(id, nume);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Calatori other = (Calatori) obj;
	return Objects.equals(id, other.id) && Objects.equals(nume, other.nume);
}
@Override
public String toString() {
	return "Calatori [id=" + id + ", nume=" + nume + "]";
}


}
