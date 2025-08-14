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

        Long id = Long.parseLong(request.getParameter("id"));
        String sa_id = request.getParameter("SA_ID");
        //get all records
        if (sa_id == null || sa_id.length() != 13 || !sa_id.matches("\\d{13}")) {
            request.setAttribute("message", "Invalid SA ID format. Must be 13 numeric digits.");
            request.getRequestDispatcher("PateientLookUp.jsp").forward(request, response);
            return;
        }

        // Check Luhn algorithm
        if (!isValidLuhn(sa_id)) {
            request.setAttribute("message", "Invalid SA ID. Failed Luhn check.");
            request.getRequestDispatcher("PateientLookUp.jsp").forward(request, response);
            return;
        }
    
        List<Patient> allPatients = patientLocal.findAll();
        Patient patient = null;
        String message = "";

        for (Patient p : allPatients) {
            if (p.getId().equals(id) && p.getSA_ID().equals(sa_id)) {
                patient = p;
                break;
            }
        }
        if (patient != null) {
            session.setAttribute("patient", patient);
            RequestDispatcher disp = request.getRequestDispatcher("menu.jsp");
            disp.forward(request, response);
        } else {
            request.setAttribute("message", "File not Found, Create");
            RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
            disp.forward(request, response);
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
