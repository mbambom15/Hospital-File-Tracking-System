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
import java.util.ArrayList;
import java.util.List;
import za.ac.tut.entities.Patient;
import za.ac.tut.entities.bl.PatientFacadeLocal;

/**
 *
 * @author nhlak
 */
public class LookUpServlet extends HttpServlet {

    @EJB
    PatientFacadeLocal patientLocal;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String idStr = request.getParameter("id");
        String sa_id = request.getParameter("SA_ID");

        // SA ID must be present
        if (sa_id == null || sa_id.length() != 13 || !sa_id.matches("\\d{13}")) {
            request.setAttribute("message", "Invalid SA ID format. Must be 13 numeric digits.");
            request.getRequestDispatcher("PateientLookUp.jsp").forward(request, response);
            return;
        }

        // Check Luhn
        if (!isValidLuhn(sa_id)) {
            request.setAttribute("message", "Invalid SA ID. Failed Luhn check.");
            request.getRequestDispatcher("PateientLookUp.jsp").forward(request, response);
            return;
        }

        List<Patient> allPatients = patientLocal.findAll();
        Patient patient = null;
        Patient idMatch = null;
        Patient saIdMatch = null;

        // Parse file ID if provided
        Long id = null;
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                id = Long.parseLong(idStr);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "File ID must be numeric.");
                request.getRequestDispatcher("PateientLookUp.jsp").forward(request, response);
                return;
            }
        }

        // Look up patients
        for (Patient p : allPatients) {
            if (id != null && p.getId().equals(id)) {
                idMatch = p;
                if (p.getSA_ID().equals(sa_id)) {
                    patient = p; // Perfect match
                    break;
                }
            }
            if (p.getSA_ID().equals(sa_id)) {
                saIdMatch = p;
            }
        }

        // Handle results
        if (patient != null) {
            session.setAttribute("patient", patient);
            request.getRequestDispatcher("menu.jsp").forward(request, response);

        } else if (idMatch != null) {
            request.setAttribute("message", "This File ID exists, but SA ID does not match.");
            request.getRequestDispatcher("PateientLookUp.jsp").forward(request, response);

        } else if (saIdMatch != null) {
            session.setAttribute("patient", saIdMatch);
            request.getRequestDispatcher("menu.jsp").forward(request, response);

        } else {
            request.setAttribute("message", "No record found. Please register new patient.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    private boolean isValidLuhn(String id) {
        int sum = 0;
        boolean alternate = false;

        for (int i = id.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(id.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }
}
