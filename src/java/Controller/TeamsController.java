/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.Teams;
import Service.TeamsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class TeamsController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            //ADD
            if (request.getParameter("task").equals("add")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("name").isEmpty() && !request.getParameter("state").isEmpty() && !request.getParameter("owner").isEmpty() && !request.getParameter("status").isEmpty()) {
                    String name = request.getParameter("name");
                    String state = request.getParameter("state");
                    String owner = request.getParameter("owner");
                    Integer status = Integer.parseInt(request.getParameter("status"));
                    
                    
                    Teams t = new Teams(0, name, state, owner, status);
                    
                    String result = TeamsService.addNewTeam(t);
                    returnValue.put("result", result);
                }
                
                else{
                    returnValue.put("result", "Nem megfelelően kitöltött mezők");
                }
                out.print(returnValue.toString());
            }
            
            //LIST
            
            if (request.getParameter("task").equals("getAllActiveTeams")) {
                JSONArray returnValue = new JSONArray();
                List<Teams> teams = TeamsService.getAllActiveTeams();
                
                if (teams.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("result", "Nincs aktív csapat");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                }
                else{
                    for (Teams t : teams){
                        returnValue.put(t.toJSON());
                        out.print(returnValue.toString());
                    }
                    
                }
                
            }
            
            //logical delete
            if (request.getParameter("task").equals("logdeleteTeam")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("id").isEmpty()) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    Teams t = Teams.getTeambyId(id);
                    String result = TeamsService.logdeleteTeam(t);
                    returnValue.put("result", result);
                }
                else{
                
                    returnValue.put("result", "Id mező nincs megfelelően kitöltve");
                }
                out.print(returnValue.toString());
            }
            
            
            //update
            if (request.getParameter("task").equals("updateTeam")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("owner").isEmpty() ) {
                    Integer id = Integer.parseInt( request.getParameter("Id"));
                    
                    String owner = request.getParameter("owner");
                    
                    
                    
                    Teams t = new Teams(id, null, null, owner, 0);
                    
                    String result = TeamsService.updateTeam(t);
                    returnValue.put("result", result);
                }
                
                else{
                    returnValue.put("result", "Nem megfelelően kitöltött mezők");
                }
                out.print(returnValue.toString());
            }
            //cahngename
            if (request.getParameter("task").equals("changeTeamName")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("name").isEmpty() ) {
                    Integer id = Integer.parseInt( request.getParameter("Id"));
                    
                    String name = request.getParameter("name");
                    
                    
                    
                    Teams t = new Teams(id, name, null, null, 0);
                    
                    String result = TeamsService.changeTeamName(t);
                    returnValue.put("result", result);
                }
                
                else{
                    returnValue.put("result", "Nem megfelelően kitöltött mezők");
                }
                out.print(returnValue.toString());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
