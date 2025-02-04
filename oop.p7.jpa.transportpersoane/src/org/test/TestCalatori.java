package org.test;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

import org.entity.Calatori;

import org.entity.Calatori;

public class TestCalatori {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

			
				List<Calatori> calatori=new ArrayList<Calatori>();

				calatori.add(new Calatori(1,"Tanase Carmen"));
				calatori.add(new Calatori(2,"Ignat Ioana"));
				calatori.add(new Calatori(3,"Popovici Maria"));
				calatori.add(new Calatori(4,"Minculescu Alexandru"));
				calatori.add(new Calatori(5,"Albu Iulia"));
				calatori.add(new Calatori(6,"Balmos Andreea"));
				calatori.add(new Calatori(7,"Panaite Robert"));
				calatori.add(new Calatori(8,"Prodan Andrei"));
				calatori.add(new Calatori(9,"Atanasiu Florina"));
				calatori.add(new Calatori(10,"Mera Elena"));
				
				EntityManagerFactory emf=Persistence.createEntityManagerFactory("TransportPersoaneJPA");
				EntityManager em=emf.createEntityManager();
				
				//Clean-up clienti
				em.getTransaction().begin();
				em.createQuery("Delete From Calatori c").executeUpdate();
				em.getTransaction().commit();
				
				//Create
				em.persist(calatori.get(0));
				em.persist(calatori.get(1));
				em.persist(calatori.get(2));
				em.persist(calatori.get(3));
				em.persist(calatori.get(4));
				em.persist(calatori.get(5));
				em.persist(calatori.get(6));
				em.persist(calatori.get(7));
				em.persist(calatori.get(8));
				em.persist(calatori.get(9));
				
				em.getTransaction().begin();
				em.getTransaction().commit();
				em.clear();
				
				//Read after create
				List<Calatori> CalatoriPersistenti=em.
						createQuery("Select c From Calatori c",Calatori.class).getResultList();
				
				System.out.println("Lista calatori persistenti/salvati in baza de date");
				for(Calatori c: CalatoriPersistenti)
					System.out.println("Id: "+c.getId()+", nume: "+c.getNume());
				
				//Update/Remove
				em.getTransaction().begin();
				Calatori c13=em.find(Calatori.class, 13);
				if(c13 != null) {
					c13.setNume("TIMI SRL Update");
					
				}
				//Read/Remove
				
				//Calatori c11=(Calatori)em.createQuery("Select c From Calatori o where c.id=11").getSingleResult();
				Calatori c11=em.find(Calatori.class, 11);
				if(c11 !=null) em.remove(c11);
				
				//Realizare tranzactie
				em.getTransaction().commit();
				em.clear();
				
				CalatoriPersistenti=em.
						createQuery("Select c From Calatori c",Calatori.class).getResultList();
				System.out.println("Lista finala calatori persistenti (salvati in baza de date):");
				for(Calatori c:CalatoriPersistenti)
					System.out.println("Id: "+c.getId()+", nume: "+c.getNume());
				

			}











			

		}

	


