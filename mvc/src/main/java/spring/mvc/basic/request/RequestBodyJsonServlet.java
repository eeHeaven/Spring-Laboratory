package spring.mvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;
import spring.mvc.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
스프링부트는 jackson이라는 기본 라이브러리를 제공해서 json을 파싱
 */
@WebServlet(name="requestBodyJsonServlet",urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("body = "+body);
        HelloData helloData = objectMapper.readValue(body, HelloData.class);
        System.out.println("helloData.username = "+helloData.getUsername());
        System.out.println("helloData.age = "+helloData.getAge());

    }
}
