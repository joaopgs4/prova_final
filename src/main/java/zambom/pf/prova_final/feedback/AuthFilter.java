package zambom.pf.prova_final.feedback;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zambom.pf.prova_final.usuario.ReturnUsuarioDTO;
import zambom.pf.prova_final.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Autowired 
    UsuarioService usuarioService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String token = request.getHeader("Authorization");

        ResponseEntity<ReturnUsuarioDTO> resp = usuarioService.validateUser(token);

        if (resp == null || resp.getBody() == null) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Unauthorized: Token is missing or invalid.");
            return;
        }

        ReturnUsuarioDTO user = resp.getBody();
        String role = user.getPapel();
        String requestMethod = request.getMethod();

        if ("ADMIN".equals(role)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }        
        else if ("DEVELOPER".equals(role)) {
            if (request.getServletPath().startsWith("/feedback") && "GET".equals(requestMethod)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("Unauthorized: No acces to these routes.");
            }
        }
    }
}