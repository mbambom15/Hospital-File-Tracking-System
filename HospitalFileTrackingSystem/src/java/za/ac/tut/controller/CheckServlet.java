/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.ac.tut.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import za.ac.tut.entities.Patient;
import za.ac.tut.entities.PatientFile;
import za.ac.tut.entities.bl.PatientFileFacadeLocal;

/**
 *
 * @author nhlak
 */
public class CheckServlet extends HttpServlet {

    @EJB
    PatientFileFacadeLocal pflFacadeLocal;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Patient patient = (Patient) session.getAttribute("patient");
        //get the date and notes of last visit

        if (patient == null) {
            request.setAttribute("message", "No patient selected. Please look up a patient first.");
            RequestDispatcher disp = request.getRequestDispatcher("PateientLookUp.jsp");
            disp.forward(request, response);
        }

        List<PatientFile> files = pflFacadeLocal.findByPatient(patient);

        if (files == null || files.isEmpty()) {
            // No file history at all
            request.setAttribute("message", "No files found, See Doctor!");
            RequestDispatcher disp = request.getRequestDispatcher("generalDoc.jsp");
            disp.forward(request, response);
        } else {
            // Safely get the latest file
            PatientFile latest = files.get(files.size() - 1);

            Date appointmentDate = latest.getNextAppointment();

            if (appointmentDate == null) {
                // File exists, but no appointment set
                request.setAttribute("message", "No Appointment active, new patient!");
                RequestDispatcher disp = request.getRequestDispatcher("menu.jsp");
                disp.forward(request, response);
            } else {
                // Appointment exists – decide what to do with it
                request.setAttribute("message", "Next Appointment: " + appointmentDate.toString());
                RequestDispatcher disp = request.getRequestDispatcher("menu.jsp");
                disp.forward(request, response);
            }
        }

    }

}
