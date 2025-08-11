/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.entities.bl;

import jakarta.ejb.Local;
import java.util.List;
import za.ac.tut.entities.Doctors;

/**
 *
 * @author nhlak
 */
@Local
public interface DoctorsFacadeLocal {

    void create(Doctors doctors);

    void edit(Doctors doctors);

    void remove(Doctors doctors);

    Doctors find(Object id);

    List<Doctors> findAll();

    List<Doctors> findRange(int[] range);

    int count();
    
}
