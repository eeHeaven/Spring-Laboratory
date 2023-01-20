package spring.mvc.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        //header 세팅
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-header","hello");
        //혹은 이렇게도 가능
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //쿠기 관련: 객체 사용하기
        Cookie cookie = new Cookie("myCookie","good");
        cookie.setMaxAge(600);//600초
        response.addCookie(cookie);

        //redirect 관련
        /*
        response.setStatus(HttpServletResponse.SC_FOUND); 302
        reponse.setHeader("Location","/basic/hello-form.html");
         */
        response.sendRedirect("/basic/hello-form.html");

        //메세지 바디에 값 넣기
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }
}
