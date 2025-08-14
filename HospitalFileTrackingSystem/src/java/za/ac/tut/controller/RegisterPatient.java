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
import java.util.List;
import java.util.Random;
import za.ac.tut.entities.Patient;
import za.ac.tut.entities.PatientFile;
import za.ac.tut.entities.bl.PatientFacadeLocal;

/**
 *
 * @author nhlak
 */
public class RegisterPatient extends HttpServlet {

    @EJB
    PatientFacadeLocal facadeLocal;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String sa_id = request.getParameter("sa_id");
        String address = request.getParameter("address");
        if (sa_id == null || sa_id.length() != 13 || !sa_id.matches("\\d{13}")) {
            request.setAttribute("message", "Invalid SA ID format. Must be 13 numeric digits.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Check Luhn algorithm
        if (!isValidLuhn(sa_id)) {
            request.setAttribute("message", "Invalid SA ID. Failed Luhn check.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        //
        Random rand = new Random();
        long min = 10000L;
        long max = 99999L;

        long id = min + (long) (rand.nextDouble() * ((max - min) + 1));

        List<PatientFile> patientFile = null; 
        //
        Patient patient = new Patient(id, name, surname, sa_id, address, patientFile);
        facadeLocal.create(patient);
        HttpSession session = request.getSession(false);
        session.setAttribute("patient", patient);
        RequestDispatcher disp = request.getRequestDispatcher("menu.jsp");
        disp.forward(request, response);
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
