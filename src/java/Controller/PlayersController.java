
package Controller;

import Modell.Players;
import Service.PlayersService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class PlayersController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            //ADD PLAYER
            if (request.getParameter("task").equals("addPlayer")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("name").isEmpty() && !request.getParameter("position").isEmpty() && !request.getParameter("birth").isEmpty() && !request.getParameter("teamId").isEmpty() && !request.getParameter("status").isEmpty()) {
                    SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
                    
                    String name = request.getParameter("name");
                    String position = request.getParameter("position");
                    Date birth = dateformat.parse(request.getParameter("birth"));
                    Integer teamId = Integer.parseInt(request.getParameter("teamId"));
                    Integer status = Integer.parseInt(request.getParameter("status"));
                    
                    Players p = new Players(0, name, position, birth, teamId, status);
                    
                    String result = PlayersService.addNewPlayer(p);
                    returnValue.put("result", result);
                }
                
                else{
                    returnValue.put("result", "Nem megfelelően kitöltött mezők");
                }
                out.print(returnValue.toString());
            }
            
               //LIST
        if (request.getParameter("task").equals("getAllActivePlayers")) {
                JSONArray returnValue = new JSONArray();
                List<Players> players = PlayersService.getAllActivePlayers();
                
                if (players.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("result", "Nincs aktív játékos");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                }
                else{
                    for (Players p : players){
                        returnValue.put(p.toJSON());
                        out.print(returnValue.toString());
                    }
                    
                }
                
            }
        
            //logical delete
            if (request.getParameter("task").equals("logdeletePlayer")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("id").isEmpty()) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    Players p = Players.getPlayerbyId(id);
                    String result = PlayersService.logdeletePlayer(p);
                    returnValue.put("result", result);
                }
                else{
                
                    returnValue.put("result", "Id mező nincs megfelelően kitöltve");
                }
                out.print(returnValue.toString());
            }
            
            //update
            if (request.getParameter("task").equals("updatePlayer")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("position").isEmpty() ) {
                    Integer id = Integer.parseInt( request.getParameter("Id"));
                    
                    String position = request.getParameter("position");
                    
                    
                    
                    Players p = new Players(id, null, position, null, 0, 0);
                    
                    String result = PlayersService.updatePlayer(p);
                    returnValue.put("result", result);
                }
                
                else{
                    returnValue.put("result", "Nem megfelelően kitöltött mezők");
                }
                out.print(returnValue.toString());
            }
            
            if (request.getParameter("task").equals("changePlayerTeam")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("teamId").isEmpty() ) {
                    Integer id = Integer.parseInt( request.getParameter("Id"));
                    
                    
                    Integer teamid = Integer.parseInt(request.getParameter("teamId"));
                    
                    
                    
                    Players p = new Players(id, null, null, null, teamid, 0);
                    
                    String result = PlayersService.changePlayerTeam(p);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PlayersController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
