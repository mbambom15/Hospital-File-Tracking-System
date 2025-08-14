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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nhlak
 */
@Entity
public class PatientFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //
    @Column(name = "timecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "category")
    private String category;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "symptoms")
    private String symptoms;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctors doctorAssigned;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "patient_file_id")  // foreign key in Prescriptions table
    private List<Prescriptions> prescriptions;
    @Column(name = "treatment")
    private String treatment;
    @Column(name = "status")
    private String status;
    @Temporal(TemporalType.DATE)
    private Date nextAppointment;
    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Doctors getDoctorAssigned() {
        return doctorAssigned;
    }

    public void setDoctorAssigned(Doctors doctorAssigned) {
        this.doctorAssigned = doctorAssigned;
    }

    public List<Prescriptions> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescriptions> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(Date nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientFile() {
    }

    public PatientFile(Date timestamp, String category, String diagnosis, String symptoms, Doctors doctorAssigned, List<Prescriptions> prescriptions, String treatment, String status, Date nextAppointment, String notes) {
        this.timestamp = timestamp;
        this.category = category;
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
        this.doctorAssigned = doctorAssigned;
        this.prescriptions = prescriptions;
        this.treatment = treatment;
        this.status = status;
        this.nextAppointment = nextAppointment;
        this.notes = notes;
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
        if (!(object instanceof PatientFile)) {
            return false;
        }
        PatientFile other = (PatientFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.tut.entities.PatientFile[ id=" + id + " ]";
    }

}
