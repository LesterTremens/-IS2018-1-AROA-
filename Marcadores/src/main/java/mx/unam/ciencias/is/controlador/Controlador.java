/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.controlador;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import mx.unam.ciencias.is.mapeobd.Marcador;
import mx.unam.ciencias.is.modelo.MarcadorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jonathan
 */
@Controller //Notación para spring
public class Controlador {
    /*Injectamos el modelo del marcador */
    @Autowired
    MarcadorDAO marcador_db;
    
    /**
     * Regresa la pagina principal con los marcadores de la base de datos
     * @param model 
     * @return regresa el modelo 
     */
    //Notación para una petición, value es la petición y method es el tipo de petición
    @RequestMapping(value="/", method = RequestMethod.GET) 
    public ModelAndView marcadores(ModelMap model){
        List<Marcador> mar = marcador_db.getMarcadores();
          
        model.addAttribute("marcadores", mar); //"marcadores" es el que voy a ver en JSP
        
        return new ModelAndView("inicio",model);
    
    }
    
   
    /**
     * Regresa el nombre de el jsp agrega marcador
     * @return 
     */
    @RequestMapping(value="/agregaMarcador")
    public String agregaMarcador(){
        return "agregaMarcador";
    }
    
    /**
     * Guarda un nuevo marcador 
     * @param request los atributos del marcador.
     * @return 
     */
    @RequestMapping(value="/guardaMarcador", method = RequestMethod.GET)
    public String guardaMarcador(HttpServletRequest request){
        Double latitud = Double.parseDouble(request.getParameter("latitud"));
        Double longitud = Double.parseDouble(request.getParameter("longitud"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Marcador ma = marcador_db.getMarcador(latitud, longitud);
        if(ma==null){
            Marcador m  = new Marcador();
            m.setLatitud(latitud);
            m.setLongitud(longitud);
            m.setNombre(nombre);
            m.setDescripcion(descripcion);
            marcador_db.guardar(m);    
        }
        return "redirect:/";
    }
    
    /**
     * Actualiza el marcador
     * Redirige al marcador que queremos actualizar
     * usamos la longitud y latitud para buscar el marcador
     * En el modelo enviarselo a la página que si lo va a actualizar
     * @param model
     * @param request
     * @return un objeto ModelAndView
     */ 
    @RequestMapping(value="/actualizaM", method = RequestMethod.GET)
    public ModelAndView actualizaM(ModelMap model,HttpServletRequest request){
        //Aqui va tu codigo
        Double latitud = Double.parseDouble(request.getParameter("latitud"));
        Double longitud = Double.parseDouble(request.getParameter("longitud"));
        Marcador m = marcador_db.getMarcador(latitud, longitud);
        model.addAttribute("marcador", m);
        //"actualiza"jsp al que le vas a inyenctar el modelo
        return new ModelAndView("actualizaM", model);
    }
    
    /**
     * Este método maneja la petición eliminar marcador
     * @param request recibimos los datos de la petición eliminar
     * @return nos regresa a la página principal donde se refleja la acción
     */
    @RequestMapping(value="/eliminaMarcador", method = RequestMethod.GET)
    public String eliminaMarcador(HttpServletRequest request){
        //Aqui va tu codigo
        Double latitud = Double.parseDouble(request.getParameter("latitud"));
        Double longitud = Double.parseDouble(request.getParameter("longitud"));
        Marcador m = marcador_db.getMarcador(latitud, longitud);
        marcador_db.eliminar(m);
        return "redirect:/";
    }
    
    /**
     * Permite modificar los datos de un marcador, primero 
     * se hace la petición y se especifica el tipo, luego
     * leemos los campos.
     * @param request
     * @return nos regresa a la página principal
     */
    @RequestMapping(value= "/actualizar", method = RequestMethod.POST)
    public String actualizar(HttpServletRequest request){
        //Aqui va tu codigo
        String latitud = request.getParameter("latitud");
        String longitud = request.getParameter("longitud");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("desc");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Marcador m = marcador_db.getMarcadorId(id);
        //Debemos verificar cuales son los campos que el ususario necesita actualizar
        if (!nombre.isEmpty())
            m.setNombre(nombre);
        if (!latitud.isEmpty() && !longitud.isEmpty()) {
            m.setLatitud(Double.parseDouble(latitud));
            m.setLongitud(Double.parseDouble(longitud));
        }
        if (!descripcion.isEmpty())
            m.setDescripcion(descripcion);
        //persistencia en la base de datos
        //se actualiza directo en la base de datos
        marcador_db.actualizar(m);
        return "redirect:/";
    }
}