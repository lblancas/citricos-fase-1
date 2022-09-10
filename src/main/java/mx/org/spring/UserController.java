package mx.org.spring;
import mx.org.citicos.controller.UsuarioController;
import mx.org.citricos.entity.Usuario;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
public class UserController
{
    @RequestMapping("/users/{name}/{pass}")
    public User getUsuario(@PathVariable("name") String name,@PathVariable("pass") String pass)
    {
    	System.out.println(">>> {"+name+"} y pass {"+pass+"}");
        UsuarioController controller = new UsuarioController();
        Usuario user = controller.findControl(name, pass);
        if(user.getId()!=null)
        {
        	
	        return new User(user.getId(),user.getLogin(),user.getNombre()+" "+user.getApellidos(),"Editar",user.getPerfilstr());
    	}
        System.out.println(">>> problemas --- ");
    	return new User(0,"Problemas con usuario / password");
    	
    }
    @PostMapping(path= "/users")
    public User usuario(@RequestParam(name="username") String username,@RequestParam(name="password") String password)
    {
    	UsuarioController controller = new UsuarioController();
        Usuario user = controller.findControl(username, password);
        if(user.getId()!=null)
        {
	    	return new User(user.getId(),user.getLogin(),user.getNombre()+" "+user.getApellidos(),"Editar",user.getPerfilstr());
	    }
	    return new User(0,"Problemas con usuario / password");
    }
    @RequestMapping(path= "/users-all")
    public List<Usuario> getUsuarios()
    {
    	System.out.println("Se inicia consulta de usuarios");
    	UsuarioController controller = new UsuarioController();
    	return controller.getll();
    }
   
   
}
