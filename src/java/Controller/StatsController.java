
package Controller;

import Modell.Stats;
import Service.StatsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class StatsController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //ADD
            if (request.getParameter("task").equals("addStat")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("playerId").isEmpty() && !request.getParameter("point").isEmpty() && !request.getParameter("pass").isEmpty() && !request.getParameter("block").isEmpty() && !request.getParameter("status").isEmpty()) {
                    
                    Integer playerId = Integer.parseInt(request.getParameter("playerId"));
                    Integer point = Integer.parseInt(request.getParameter("point"));
                    Integer pass = Integer.parseInt(request.getParameter("pass"));
                    Integer block = Integer.parseInt(request.getParameter("block"));
                    Integer status = Integer.parseInt(request.getParameter("status"));
                    
                    Stats s = new Stats(0, playerId, point, pass, block, status);
                    
                    String result = StatsService.addNewStat(s);
                    returnValue.put("result", result);
                }
                
                else{
                    returnValue.put("result", "Nem megfelelően kitöltött mezők");
                }
                out.print(returnValue.toString());
            }
            //LIST
            if (request.getParameter("task").equals("getAllActiveStats")) {
                JSONArray returnValue = new JSONArray();
                List<Stats> stats = StatsService.getAllActiveStats();
                
                if (stats.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("result", "Nincs aktív csapat");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                }
                else{
                    for (Stats s : stats){
                        returnValue.put(s.toJSON());
                        out.print(returnValue.toString());
                    }
                    
                }
                
            }
            //logdel
            if (request.getParameter("task").equals("logdeleteStat")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("id").isEmpty()) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    Stats s = Stats.getStatbyId(id);
                    String result = StatsService.logdeleteStat(s);
                    returnValue.put("result", result);
                }
                else{
                
                    returnValue.put("result", "Id mező nincs megfelelően kitöltve");
                }
                out.print(returnValue.toString());
            }
            
            //UPDATE
            if (request.getParameter("task").equals("updateStat")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("point").isEmpty()&& !request.getParameter("pass").isEmpty() && !request.getParameter("block").isEmpty() ) {
                    Integer id = Integer.parseInt( request.getParameter("Id"));
                    
                    Integer point = Integer.parseInt(request.getParameter("point"));
                    Integer pass = Integer.parseInt(request.getParameter("pass"));
                    Integer block = Integer.parseInt(request.getParameter("block"));
                    
                    
                    
                    
                    Stats s = new Stats(id, 0, point, pass ,block, 0);
                    
                    String result = StatsService.updateStat(s);
                    returnValue.put("result", result);
                }
                
                else{
                    returnValue.put("result", "Nem megfelelően kitöltött mezők");
                }
                out.print(returnValue.toString());
            }
            
            if (request.getParameter("task").equals("getscore")) {
                JSONObject returnValue = new JSONObject();
                
                if (!request.getParameter("id").isEmpty()) {
                    Integer id = Integer.parseInt( request.getParameter("id"));
                    
                    
                    Stats s = Stats.getStatbyId(id);
                    if(StatsService.getscore(s)>0){
                    String result = StatsService.getscore(s).toString();
                    returnValue.put("result", result);
                    }
                    else{
                     returnValue.put("result", "Nincs ilyen id");
                    }
                    
                    
                   
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
