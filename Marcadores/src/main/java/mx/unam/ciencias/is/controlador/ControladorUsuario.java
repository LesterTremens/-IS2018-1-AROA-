/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.controlador;

import javax.servlet.http.HttpServletRequest;
import mx.unam.ciencias.is.mapeobd.Usuario;
import mx.unam.ciencias.is.modelo.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author orlando
 */
@Controller
public class ControladorUsuario {

    @Autowired
    private UsuarioDAO usuario_db;
    
    @RequestMapping(value="/guardaUsuario", method = RequestMethod.GET)
    public String guardaUsuario(HttpServletRequest request){
        String nickname = request.getParameter("nickname");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        Usuario u = usuario_db.getUsuario(correo);
        if(u == null){
           u = new Usuario();
           u.setNickname(nickname);
           u.setCorreo(correo);
           u.setContrasenia(contrasenia);
           usuario_db.guardar(u);
        }
        return "redirect:/";
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String NuevoInicio(HttpServletRequest request){
      
        return "NuevoInicio";
    }
    
    
    
    
}
