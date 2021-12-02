<%--
  Created by IntelliJ IDEA.
  User: shanchu
  Date: 11/24/21
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<script>
  //点击链接或者图片换一张
  //给超链接和图片绑定单击事件
  //重新设置图片的src属性
  window.onload = function () {
    //获取图片对象
    var img = document.getElementById("checkCode");
    //绑定单击事件
    img.onclick = function () {
      //加时间戳，刷新
      var date = new Date().getTime();
      img.src = "/test/checkCodeServlet?"+date;
    }
  }
</script>
<body>
<form action="/day16/checkCodeServlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>

        <tr>
            <td>验证码</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="checkCode" src="/day16/checkCodeServlet"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
    </table>

</form>
</body>
</html>
