<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: shanchu
  Date: 11/24/21
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //获取cookie
    Cookie[] cookies = request.getCookies();

    boolean flag = false;
    //b遍历cookie
    if(cookies!=null){
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            String value = cookie.getValue();


            if(name.equals("lastTime")){
                flag = true;
                value = URLDecoder.decode(value,"utf-8");
                response.getWriter().write("<h1>欢迎回来，您上次访问时间 "+value+"</h1>");
                break;
            }
        }
    }



    if(!flag){
        response.getWriter().write("<h1>您好，欢迎您首次访问!");
    }


    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    String strDate = sdf.format(date);
    Cookie cookie = new Cookie("lastTime", URLEncoder.encode(strDate,"utf-8"));
    cookie.setMaxAge(60*60*24*30);
    response.addCookie(cookie);
%>
</body>
</html>
