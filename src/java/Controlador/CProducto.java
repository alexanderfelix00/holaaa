package Controlador;

import Beans.Producto;
import Modelo.MProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(name = "CProducto", urlPatterns = {"/ServletProducto"})
public class CProducto extends HttpServlet {

    MProducto model = new MProducto();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String operacion = request.getParameter("op");

            switch (operacion) {
                case "listar":
                    Listar(request, response);
                    break;
                case "listarID":
                    ListarID(request, response);
                    break;
                case "insertar":
                    Insertar(request, response);
                    break;
                case "actualizar":
                    Actualizar(request, response);
                    break;
                case "eliminar":
                    Eliminar(request, response);
                    break;
            }
        } finally {
            out.close();
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

    private void Listar(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            PrintWriter out = response.getWriter();

            JSONArray array = new JSONArray();
            model.Listar().forEach(item -> {
                JSONObject json = new JSONObject();
                json.put("id", item.getId());
                json.put("nombre", item.getNombre());
                json.put("cantidad", item.getCantidad());
                json.put("precio", item.getPrecio());
                array.add(json);
            });

            out.print(array);
        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ListarID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            PrintWriter out = response.getWriter();
            int id = Integer.parseInt(request.getParameter("id"));

            JSONArray array = new JSONArray();
            model.ListarID(id).forEach(item -> {
                JSONObject json = new JSONObject();
                json.put("id", item.getId());
                json.put("nombre", item.getNombre());
                json.put("cantidad", item.getCantidad());
                json.put("precio", item.getPrecio());
                array.add(json);
            });

            out.print(array);
        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Insertar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {

            String nombre = request.getParameter("name");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            
            Producto producto = new Producto(nombre, cantidad, precio);
            
            int filas = model.Insertar(producto);

            if (filas != 0) { //EXITO
                out.print("EXITO");
            } else {
                out.print("ERROR");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }

    private void Actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("name");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precio = Double.parseDouble(request.getParameter("precio"));

            Producto producto = new Producto(id, nombre, cantidad, precio);
            int filas = model.Actualizar(producto);

            if (filas != 0) { //EXITO
                out.print("EXITO");
            } else {
                out.print("ERROR");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }

    private void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {

            int id = Integer.parseInt(request.getParameter("id"));

            int filas = model.Eliminar(id);

            if (filas != 0) { //EXITO
                out.print("EXITO");
            } else {
                out.print("ERROR");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }

}
