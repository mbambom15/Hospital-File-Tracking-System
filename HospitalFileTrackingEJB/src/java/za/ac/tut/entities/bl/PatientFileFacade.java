/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.entities.bl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import za.ac.tut.entities.Patient;
import za.ac.tut.entities.PatientFile;

/**
 *
 * @author nhlak
 */
@Stateless
public class PatientFileFacade extends AbstractFacade<PatientFile> implements PatientFileFacadeLocal {

    @PersistenceContext(unitName = "HospitalFileTrackingEJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientFileFacade() {
        super(PatientFile.class);
    }

    @Override
    public List<PatientFile> findByPatient(Patient patient) {
        return em.createQuery("SELECT f FROM PatientFile f WHERE f.patient = :patient", PatientFile.class)
                .setParameter("patient", patient)
                .getResultList();
    }

}
