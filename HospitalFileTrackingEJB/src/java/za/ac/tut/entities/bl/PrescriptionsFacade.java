/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.entities.bl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import za.ac.tut.entities.Prescriptions;

/**
 *
 * @author nhlak
 */
@Stateless
public class PrescriptionsFacade extends AbstractFacade<Prescriptions> implements PrescriptionsFacadeLocal {

    @PersistenceContext(unitName = "HospitalFileTrackingEJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrescriptionsFacade() {
        super(Prescriptions.class);
    }
    
}
