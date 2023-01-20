package spring.mvc.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
http://localhost:8080/request-param?username=hyebin&age=26
 */
@WebServlet(name = "requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "="+ request.getParameter(paramName)));
        System.out.println("전체 파라미터 조회 끝");
        System.out.println();
        System.out.println("단일 파라미터 조회");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username 단일 파라미터 조회: "+username);
        System.out.println("age 단일 파라미터 조회: "+age);
        System.out.println();

        /*
        http://localhost:8080/request-param?username=hyebin&age=26&username=test
        복수로 조회하는 경우 username의 값에 해당하는 것들이 전부 배열에 담긴다
        단일 조회시에는 가장 먼저 입력된 쿼리 파라미터만 조회된다
         */
        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] usernames = request.getParameterValues("username");
        for(String name: usernames) System.out.println("username = "+name);

    }
}
