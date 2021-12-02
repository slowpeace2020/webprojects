package cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookieDemo2")
public class CookieDemo2 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doPost(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("cookieDemo2...........");
    //获取cookie
    Cookie[] cookies = req.getCookies();
    //b遍历cookie
    for(Cookie cookie:cookies){
      String name = cookie.getName();
      String value = cookie.getValue();
      System.out.println(name+": "+value);
    }

  }
}
