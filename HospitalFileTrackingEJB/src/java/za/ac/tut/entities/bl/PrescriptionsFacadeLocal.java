/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.entities.bl;

import jakarta.ejb.Local;
import java.util.List;
import za.ac.tut.entities.Prescriptions;

/**
 *
 * @author nhlak
 */
@Local
public interface PrescriptionsFacadeLocal {

    void create(Prescriptions prescriptions);

    void edit(Prescriptions prescriptions);

    void remove(Prescriptions prescriptions);

    Prescriptions find(Object id);

    List<Prescriptions> findAll();

    List<Prescriptions> findRange(int[] range);

    int count();
    
}
