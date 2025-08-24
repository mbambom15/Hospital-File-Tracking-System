/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.entities.bl;

import jakarta.ejb.Local;
import java.util.List;
import za.ac.tut.entities.Patient;
import za.ac.tut.entities.PatientFile;

/**
 *
 * @author nhlak
 */
@Local
public interface PatientFileFacadeLocal {

    void create(PatientFile patientFile);

    void edit(PatientFile patientFile);

    void remove(PatientFile patientFile);

    PatientFile find(Object id);

    List<PatientFile> findAll();

    List<PatientFile> findRange(int[] range);

    int count();
    
    public List<PatientFile> findByPatient(Patient patient);

    
}
