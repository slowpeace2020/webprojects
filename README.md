# webprojects

java web基础
黑盒测试不写代码，只看输入输出
白盒测试写程序，关注代码执行流程

junit白盒测试
@Before在执行测试之前执行
@After在测试方法执行之后执行

反射：框架设计的灵魂，将类的组成部分封装为其他对象
好处：
在程序运行中，操作对象
解耦，提高程序的可扩展性


获取Class对象的三种方式：
source源代码阶段 1. Class.forName("全类名")：将字节码文件加载进内存，返回class对象
多用于配置文件，将类定义在配置文件中，读取文件，加载类
Class类对象阶段  2.类名.class:通过类名的class属性来获取
多用于参数传递
Runtime运行时3. 对象.getClass(): getClass()在Object类中定义，也就是所有类都有此方法
多用于对象获取字节码的方式

同一个字节码文件（.class）在一次程序运行中只被加载一次，不论通过哪种方式获取的Class对象都是同一个

day1/06

Class对象功能
获取功能
1.获取成员变量 getFields()[获取public 成员变量] getField(fieldname) getDeclaredFields()[获取所有成员变量]
//忽略访问权限修饰符的安全检查
age.setAccessible(true);

2.获取构造方法（根据参数类型区分）
创建对象
使用空参创建对象，可以用class.newInstance（）
3.获取成员方法getMethods 和成员变量类似
执行方法 method.invoke(参数列表)
4.获取类名

案例
需求：写一个框架，不能改变该类的代码，可以帮我们创建任意对象，执行对象的任意方法
实现：
1. 配置文件
2. 反射
   步骤；
   1.将要创建的对象的全类名和需要执行的方法定义在配置文件中
   2.在程序中读取配置文件
   3.使用反射加载类文件

day1/11

注解：注释说明

jdk中预定义的注解
@Override
@Deprecated  该注解标注的内容表示已过时
@SupressWarnings 压制警告，一般传递参数“all”

自定义注解
格式：public @interface 注解名称{}
本质：注解本质上就是一个接口，继承Annotation接口
属性：接口中的抽象方法
要求：
1. 属性的返回值类型：
基本数据类型，String，枚举，注解，以上类型的数组
2.定义了属性，使用注解时需要赋值
如果定义属性时，使用default，设置默认值
如果只有一个属性需要赋值，并且属性名称是value，value可以省略

元注解：用于描述注解的注解
@Target：描述注解能够作用的位置
ElementType取值：
TYPE：可以作用于类
METHOD：作用于方法
FILED：可以作用于属性
@Retention: 描述注解被保留的阶段
@Retention(RetentionPolicy.RUNTIME) 当前被描述的注解，会保留到class字节码文件中，并被JVM读取到
@Documented：描述注解是否被抽取到API文档中
@Inherited：描述注解是否被子类继承


在程序中使用注解


Tomcat
端口冲突，可以修改config/server.xml的配置端口号

brew services start tomcat
brew services stop tomcat


day13 05

配置
部署项目的方式：
1. 直接将项目放到webapps目录下
   /hello：项目访问路径-->虚拟目录
   简化部署：将项目打成war包，把war包放到webapps目录下，会自动解压缩
2. 配置conf/server.xml文件
   在<Host>标签体中配置
   <Context docBase="/Users/prpjectpath" path="/hehe" />
   docBase为项目存放路径，path为虚拟目录
3. conf/Catalina/localhost创建任意名称的XML文件
   内容：<Context docBase="/Users/prpjectpath" />
   文件名称就是虚拟路径的名称

目录结构
Java动态项目的目录结构：
项目根目录：
WEB-INF目录
web.xml: web项目的核心配置文件
classes目录：放置字节码文件
lib目录；放置依赖的jar包

创建java EE项目
热部署：Edit Configuration
On 'Update' action 选择 UPdate resources
On frame deactivation  选择 UPdate resources


Servelet
概念：serve applet 运行在服务器端的小程序
Servlet就是一个接口，定义了Java类被浏览器访问（Tomcat识别）到的规则
我们定义一个类实现servlet接口，复写方法

快速入门：
1. 创建Java EE项目，
2. 定义一个类实现servlet接口
3. 实现接口中的抽象方法
4. 配置Servlet
在web.xml中配置
<servlet>
<servlet-name>demo1</servlet-name>
<servlet-class>com.study.web.ServletDemo1</servlet-class>
</servlet>
<servlet-mapping>
<servlet-name>demo1</servlet-name>
<url-pattern>/demo1</url-pattern>
</servlet-mapping>


/usr/local/opt/tomcat@9

/usr/local/Cellar/tomcat/10.0.12/libexec
/usr/local/Cellar/tomcat/10.0.12/libexec

/usr/local/Cellar/tomcat@9/9.0.54/libexec/bin


Servlet执行原理
1. 浏览器访问路径
2. 根据访问路径查找web.xml的servlet配置，根据path找name,根据name找对应的java类
3. tomcat将全类名对应的字节码文件加载进内存
4. 创建对象 class.newInstance()
5. 调用service方法


Servlet的生命周期(方法)
init 在Servlet被创建时执行，只被执行一次
service 提供服务，每次访问时执行
destroy 服务器正常关闭时执行

1. 被创建 init方法
   什么时候创建？
   默认情况下，第一次被访问时
   可以指定创建时机
   <servlet>
   <servlet-name>demo2</servlet-name>
   <servlet-class>com.example.daytest.ServletDemo2</servlet-class>
<!--    指定Servlet创建时机 默认为-1，正数则在服务启动时创建-->
    <load-on-startup>5</load-on-startup>
  </servlet>

      Servlet是单例的，多用户访问可能存在安全问题
        尽量不在Servlet中定义成员变量，即使定义了，也不要赋值修改值

2. 提供服务 service
3. 被销毁 destroy
   服务器正常关闭时执行


Servlet3.0支持注解配置，可以不用web.xml配置
在类上使用WebService注解,值为资源路径
@WebServlet("/demo2")

IDEA与tomcat的相关配置
1. IDEA会为每一个tomcat部署的项目单独建立一个配置文件
   查看控制台输出，可以知道路径
   CATALINA_BASE:         /Users/shanchu/Library/Caches/JetBrains/IntelliJIdea2021.2/tomcat/12fcd3a9-498f-4928-b002-7f15e1cdc602

2. 工作空间项目 和 tomcat部署的web项目（tomcat访问）
   tomcat部署的web项目对应着 工作空间项目的web目录下的所有资源
   WEB- INF目录下的资源不能被浏览器直接访问


Servlet体系结构
Servlet实现类：
GEnericServlet, 对接口中其他方法做了默认空实现，只将service（）方法作为抽象

HttpServlet继承GEnericServlet，
复写doGet/doPost

day14/02 9:34


Servlet相关配置
1. urlpattern: Servlet访问路径
   一个Servlet可以定义多个访问路径 @WebServlet({"/d4","/demo4","/dd4"})
2. 路径定义规则
   1./xxxx
   2./xx/xxx 多层路径,支持通配符
    3. *.do


HTTP 客户端和服务端的通信协议的消息格式
特点：
1. 基于TCP/IP的高级协议
2. 默认端口号是80
3. 基于请求响应模型，请求和响应一一对应
4. 无状态的：每次请求相互独立，不能交互数据
历史版本：
1.0 每次请求建立新的连接
1.1 复用连接


Request
1. request对象和response对象的原理
    1. tomcat服务器根据URL中的资源路径，创建对应的Servlet对象
    2. tomcat服务器，创建request和response对象，request对象中封装请求消息数据
    3. tomcat将request和response两个对象传递给service方法，并调用service方法
    4. 程序员可以通过  request对象获取消息数据，通过response对象设置响应消息数据
    5. 服务器给浏览器返回响应消息之前会从response对象中拿程序猿设置的响应消息数据
       2.request获取消息
       1. 获取请求行
       GET /day14/demo1?name=luren HTTP/1.1
       方法：
        1. 获取请求方式： GET
           String getMethod()
        2. 获取虚拟目录： /day14
           String getContextPath()
        3. 获取Servlet路径 /demo1
           String getServletPath()
        4. 获取get方式请求参数：name=luren
           String getQueryString()
        5. 获取请求URI
           String getRequestURI(): /day14/demo1
           String getRequestURL: http://locahost:8080/day14/demo1

    2. 获取请求头
         1. String getHeader(String name), getHeaderNames();
    3. 获取请求体
       Post才有
       1. 获取流对象
          BufferedReader getReader() 获取字符输入流， 只操作字符数据
          ServletInputStream getInputStream() 获取字节输入流， 可以操作所有类型数据

       2. 其他功能
          获取请求参数通用方式
           String getParameter(String name)
           String[] getParameterValues(String name)
           getParameterNames()
           中文乱码：在获取参数前设置request编码 request.setCharacterEncoding("utf-8")
          
          请求转发
            一种在服务器内部的资源跳转方式
            1. 步骤：
             1. 通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(STring path)
             2. 通过RequestDispatcher对象进行转发：forward(ServletRequest request, ServletResponse response)
            2. 特点
             1. 浏览器地址栏路径不发生变化
             2. 只能转发到当前服务器内部资源中
             3. 是一次请求，不算新请求

          共享数据
             域对象：一个有作用范围的对象，可以在范围内共享数据
             request域：一次请求，一般用于请求转发多个资源中共享数据
               方法：
                 void setAttribute(String name,Object obj) 存储数据
                 Object getAttribute(String name) 获取数据
                 removeAttribute(String name)移除键值对


          获取ServletCpntext
          request.getServletCpntext()

3.request继承体系结构

day14/13未开始


案例：用户登录

response
1. 设置响应行
   setStatus(int sc)
2. 设置响应头
   setHeader(String name,value)
3. 设置响应体
   步骤：
    1. 获取输出流
       字符输出流 getWriter()
       字节输出流  ServletoutputStream getOutputStream()
    2. 使用输出流

功能：
1. 重定向
告诉浏览器重定向
告诉浏览器B资源的路径，
响应头location：资源B的路径
//设置状态码302
//    resp.setStatus(302);
//设置响应头location
//    resp.setHeader("location","/rsponseDemo2");
简单重定向
resp.sendRedirect("/rsponseDemo2");

        redirect 重定向特点：
            地址栏变化
            可以访问其他服务器资源
            两次请求，不能使用request对象共享数据

        forward转发：req.getRequestDispatcher
          转发地址栏不变
          只能访问当前服务器的资源
          一次请求，可以使用request对象共享数据

    2. 路径
       1. 分类
          1. 相对路径：不能确定唯一资源，以.开头， ./index.html
          ../向上退一级
              找到访问当前资源和目标资源之间的相对位置关系
          2. 绝对路径：可以确定唯一资源
              可以简化域名，以/开头的路径

              看请求是谁发出的
              给客户端浏览器使用需要加虚拟目录[项目的访问路径](example: 重定向)
                  动态获取虚拟目录
                  request.egtContextpath()
              给服务器使用不需要(example:转发)

        推荐使用绝对路径， 


获取字符输出流
乱码原因：编码使用的字符集不一致
//获取流对象之前，设置流的默认编码
//    resp.setCharacterEncoding("utf-8");
//告诉浏览器，服务器发送的消息使用的编码方式，建议浏览器使用该编码解码
//    resp.setHeader("content-type","text/html;charset=utf-8");
//简单方式
resp.setContentType("text/html;charset=utf-8");

获取字节输出流
//简单方式
//    resp.setContentType("text/html;charset=utf-8");

      //获取字节输出流
      ServletOutputStream sos = resp.getOutputStream();
      //输出数据
      sos.write("hello你好".getBytes());


验证码



ServletContext对象
1. 概念：代表整个web应用，可以和程序的容器（服务器）来通信
2. 获取：
    1. 通过request对象获取
       request.getServletContext();

    2. 通过HttpServlet获取
       this.getServletContext();

3. 功能：
    1. 获取MIME类型(在互联网通信过程中定义的一种文件数据类型)
       格式： 大类型/小类型  text/html。image/jpeg
       获取： String getMimeType(String file)

    2. 域对象：共享数据
       setAttribute(String name, Object value)
       getAttribute(String name)
       removeAttribute(String name)

       ServletContext对象范围：所有用户的数据

    3. 获取文件的真实（服务器）路径
       方法：getRealPath()
       /b.txt默认是webapp目录下
       src目录下的a.txt是/WEB-INF/a.txt
       WEB-INF目录下的c.txt是/WEB-INF/c.txt

案例：文件下载需求
1. 页面显示超链接
2. 点击链接弹出下载提示框
3. 完成图片下载

day15/20/5:42


day16

会话技术
一次对话包含多次请求和响应
功能：一次会话内共享数据
1. 客户端会话技术 cookie
   将数据保存在客户端

   快速入门
    1. 创建cookie对象，绑定数据
    2. 发送cookie
    3. 获取cookie，拿到数据

   原理


      cookie的细节
        一次可以发送多个cookie

        cookie保存在浏览器内存中，当浏览器关闭，cookie数据被销毁
         持久化存储，setMaxAge(int seconds)
           1. 正数， cookie的存活时间，将cookie写入硬盘的文件中,到期之后自动删除
           2. 负数，默认值
           3。 0 删除cookie
         在tomcat8之前不能直接存储中文数据，需要转吗（采用URL编码），之后支持，特殊字符还是都不支持，建议URl编码 

        一个tomcat服务器的不同web项目之间cookie能否共享？
          默认情况下不共享

          setPath(String path): 设置cookie获取范围，默认情况下为当前虚拟目录
           若要共享则将path设置为“/”

        不同tomcat服务器间cookie共享问题
          setDomain(String path) 如果设置一级域名相同，那么多个服务器之间可以共享

      cookie的特点和应用
       1. 存在于客户端浏览器
       2. 浏览器对于单个cookie大小有限制（4k），数量限制20个

       作用：
         1. cookie用于存储少量不敏感的数据
         2. 不登录的情况下，完成服务器对客户的身份识别

    案例：记住上一次访问时间
       1. 需求分析：
         1. 访问一个servlet，如果是首次访问，则提示：您好，欢迎您首次访问
         2. 非首次，则显示：欢迎回来，您上次访问时间是xxx

       2. 方案：
         1. 可以采用cookie来完成
         2. 在服务器的servlet判断是否有一个名为lastTime的cookie
            1. 有，不是首次访问
              1. 响应数据：欢迎回来，您上次访问时间是xxx
              2. 写回cookie：lastTime=20xx年某月某日
            2. 没有， 是第一次访问
              1. 响应数据：您好，欢迎您首次访问
              2. 写回cookie：lastTime=20xx年某月某日







2. 服务器端会话技术 session
   1. 概念：服务器端会话技术，在一次会话的多次请求间共享数据，将数据保存在服务器端的对象中（HttpSession）
   2. 快速入门：
   HttpSession对象，
   Object getAttribute(String name);
   setAttribute(String name, Object value);
   3. 原理
   session依赖于cookie，最开始没有cookie，响应头创建cookie，带着sessionID,保证一次对话的session是同一个

     4. 细节
       1. 客户端关闭，服务端不关闭，两次获取session是否同一个？
          默认情况下不是，
          若需要，则可创建cookie，键为JSESSIONID,设置最大存活时间，让cookie持久化保存

       2. 客户端不关闭，服务端关闭，两次获取session是否同一个？
          不是同一个，但要确保数据不丢失
           session的钝化：
             在服务器正常关闭之前，将session对象序列化到硬盘上
           session的活化：
             在服务器启动后，将session文件转化为内存中的session对象
       3. session默认的失效时间？什么时候被销毁？
          1. 服务器关闭
          2. session对象调用销毁方法invalidate()
          3. session 默认失效时间是30分钟  
             选择配置修改（web.xml）
              <session-config>
                <session-timeout>30</session-timeout>
              </session-config>

       4. 特点：
           存储一次会话的多次请求数据，存在服务器端
           可以存储任意类型，任意大小数据

案例：验证码
访问带有验证码的登录页面login.jsp


JSP
1. 概念：
   java server pages:  java服务器端页面
   可以理解为一个特殊的页面，既可以指定html标签，也可以定义Java代码
   用于简化书写
   day16/13

2. 原理
   【 1. 服务器解析请求消息，找是否有index.jsp资源
    2. 若找到了，会将index.jsp转换为.java文件
    3. 编译.java文件，生成.class字节码文件
    4. 由字节码文件提供访问
       】
       本质上是一个Servlet

3. 脚本
   JSP定义Java代码的方式
    1. <%  java代码  %> 这里定义的Java代码，在service方法中，service方法可以定义什么，这里也一样
    2. <%=  java代码  %> 这里定义的Java代码，在jsp转换后的Java类的成员位置，可以定义成员变量，成员方法，用得少
    3. <%！  java代码  %>定义的Java代码，会输出到页面上，输出语句可以定义什么，该脚本就可以定义什么

4. 内置对象
   在jsp页面中不需要创建和获取的对象，可以直接使用的对象，称为内置对象
   总共9个内置对象
   今日内容：
   request,
   response,
   out（字符输出流对象，可以将数据输出到页面，和response.getWriter()类似 ）
   response.getWriter()，的数据缓冲区先，在out之前加载
   统一用out输出



指令
作用：配置jsp页面，导入资源文件
格式：<%@ 指令名称 属性名1=属性值1 属性名2=值2 %>
分类：
1. page     配置jsp
ContentType 设置编码
import 导包


     2. include。页面包含的，导入页面的资源文件
     3. taglib。  导入资源

注释：
   <!-- --> 只能注释html代码片段

<%-- --%> 注释所有

内置对象
在jso页面不需要创建，直接使用的对象
一共9个：                真实类型                  作用
pageContent         PageContent            当前页面共享数据
request         HttpServletRequest        一次请求访问的多个资源（转发）
session         HttpSession                 一次会话的多个请求之间
application     ServletContext             多个用户共享数据
response        HttpServletResponse        响应对象
page            Object                     当前页面的对象 this
out             JspWriter                  输出对象，数据输出到页面
config          servletCofig              servlet配置对象
exception (声明IsError=true才可用)         异常对象




MVC开发模式
MVC：
1. M：model，模型（JavaBean）
   完成具体的业务操作，如查询数据库，封装对象
2. v：view，视图
   展示数据
3. controller，控制器 Servlet
   获取用户的输入
   调用模型
   将数据交给视图展示

优缺点：
耦合性低，方便维护，
重用性高

    使得项目架构更加复杂，对开发人员要求更高


EL表达式
概念：expression language表达式语言
作用：替换和简化Java代码编写
语法：${表达式} 默认支持，isELIgnored="true", 忽略jsp页面的el表达式，加\不转义，原样输出
使用：
运算

       获取值
          1. 只能从域对象获取值
          2. 语法 
            1. ${域名称.键名}
               域名称：
                 1. pageScope
                 2. requestScope
                 3. sessionScope
                 4. applicationScope
            2. ${键名}
               从最小的域中去查找

       获取对象

day17/13


JSTL <% taglib %>


三层架构

浏览器---》 界面层（表示层/web层）----〉业务逻辑层（service层）-----》数据访问层（dao 层）
控制器：servlet             A功能                      数据库表增删改查
视图： JSP                  B功能
(接受用户参数，封装数据，    （组合DAO层的简单方
调用业务逻辑层完成处理，     法形成复杂功能，业务逻辑操作）
转发jsp页面完成显示)

           Springmvc                 Spring                     mybatis



day18 尚未开始

day18/07  尚未开始


day18/14  尚未开始

day18/16  尚未开始
分页：
1. 减轻服务器压力
2. 提升用户体验

day19

filter过滤器
一般用于通用操作，如：登录验证，统一编码，敏感字符过滤