/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nhlak
 */
@Entity
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //this the hospital file Id
    private Long id;
    @Column(name = "p_name")
    private String name;
    @Column(name = "p_surname")
    private String surname;
    @Column(name = "SA_ID", unique = true)
    private String SA_ID;
    private String address;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PatientFile> patientFile;

    public Patient() {
    }

    public Patient(Long id, String name, String surname, String SA_ID, String address, List<PatientFile> patientFile) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.SA_ID = SA_ID;
        this.address = address;
        this.patientFile = patientFile;
    }

    public List<PatientFile> getPatientFile() {
        return patientFile;
    }

    public void setPatientFile(List<PatientFile> patientFile) {
        this.patientFile = patientFile;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSA_ID() {
        return SA_ID;
    }

    public void setSA_ID(String SA_ID) {
        this.SA_ID = SA_ID;
    }

   
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.tut.entities.Patient[ id=" + id + " ]";
    }
    
}
