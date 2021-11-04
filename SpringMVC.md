# SpringMVC

ssm: Mybatis + Spring + SpringMVC **MVC三层架构**

SpringMVC + Vue + SpringBoot + SpringCloud + Linux

Spring: IOC + AOP

SpringMVC: 执行流程

SpringMVC: SSM框架整合



## 1. 回顾MVC

### 1.1 什么是MVC

* Model(dao, service) View(jsp) Controller(Servlet), 设计规范

* 将业务逻辑, 数据显示分离的方法来组织代码

  dao 连接数据库

  service 业务层

  servlet 响应前端请求, 转发和重定向

  jsp/html

* 降低视图和业务逻辑间的双向耦合

* 是一种架构模式

Model: 数据模型, 提供要展示的数据(dao层)和行为(service层)

View: 界面层, 用户想要见到的东西

Controller: 接受用户请求, 委托进行处理, 处理完成后把返回的模型数据返回给视图吗由视图负责展示, 控制器做了个调度员工作

### 1.2 Model1时代

JSP本质就是一个Servlet

主要分为两层: 视图层和模型层

优点: 架构简单, 比较适合小型项目开发

缺点: JSP之职责不单一, 职责过重, 不便于维护

### 1.3 Model2 时代

分为视图, 控制, 模型





## 2. 回顾Servlet

### 2.1 导入依赖

```xml
<!--导入依赖-->
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.9.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.2</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
</dependencies>
```

### 2.2 添加框架

![frame](./pic/s10.png)

创建webapplication

添加项目依赖

```xml
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>
</dependencies>
```

### 2.3 编写Servlet类

```java
// 只要实现了Servlet接口的方法就叫servlet
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取前端参数
        String method = req.getParameter("method");
        if (method.equals("add")) {
            req.getSession().setAttribute("msg", "执行了add方法");
        }
        if (method.equals("delete")) {
            req.getSession().setAttribute("msg", "执行了delete方法");
        }

        // 2. 调用业务层

        // 3. 视图转发或者重定向
        req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

### 2.4 编写JSP文件

```jsp
<%--
  Created by IntelliJ IDEA.
  User: Fisher
  Date: 2021/8/9
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
</body>
</html>

```

### 2.5 注册servlet

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--servlet的注册-->
    <servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.desitate.servlet.HelloServlet</servlet-class>
    </servlet>

    <!--映射关系-->
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>

    <!--&lt;!&ndash;设置session超时时间&ndash;&gt;
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>

    &lt;!&ndash;配置欢迎页面&ndash;&gt;
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>-->
</web-app>
```

### 2.6 添加Tomcat的配置环境

启动`[Title](http://localhost:8080/hello?method=add)`

![config](./pic/s11.png)

MVC框架做哪些事情:

1. 将URL映射到java类或者java类的方法
2. 封装用户提交的数据
3. 处理请求--调用相关的业务处理--封装响应数据
4. 将响应的数据进行渲染jsp/html等层数据

常见MVC服务器端框架有: ASP.NET

常见MVC前端框架有: Vue, angularjs, react

MVC演化出MVVM: M V VM(view model双向绑定)



## 3. SpringMVC

### 3.1 什么是SpringMVC

是Spring FrameWork的一部分, 基于Java实现MVC的轻量级框架

官方文档[Web on Servlet Stack (spring.io)](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc)

![](./pic/s12.png)

为什么要学SpringMVC呢?

1. 轻量级, 简单易学
2. 高效, 基于请求响应的MVC框架
3. 与Spring兼容性好, 无缝结合, 我们可以将SpringMVC中所有要用到的bean注册到Spring中
4. 约定大于配置
5. 功能强大: RESTful, 数据验证, 格式化, 本地化, 主题
6. 简洁灵活
7. Spring的web框架围绕DispatcherServlet设计

```xml
pring的web MVC框架与许多其他web MVC框架一样，是请求驱动的，围绕一个中央Servlet设计，该Servlet向控制器发送请求，并提供促进web应用程序开发的其他功能。然而，Spring的DispatcherServlet所做的不仅仅是这些。它与SpringIoC容器完全集成，因此允许您使用Spring的所有其他功能。
```

### 3.2 中心控制器

Spring的web框架围绕着DispatcherServlet设计, 作用是将请求分发到不同的处理器. **DispatcherServlet本质上也是一种Servlet**







### 3.x HelloSpring

1. 配置web.xml中的servlet

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <!--1. 注册DispatcherServlet-->
       <servlet>
           <servlet-name>springmvc</servlet-name>
   
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
   
           <!--关联一个springmvc的配置文件:[servlet-name]-servlet.xml-->
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc-servlet.xml</param-value>
           </init-param>
           <!--启动级别-1-->
           <load-on-startup>1</load-on-startup>
       </servlet>
   
       <!-- / 匹配所有的请求(不包括.jsp)-->
       <!-- /* 匹配所有的请求(包括jsp)-->
   
       <servlet-mapping>
           <servlet-name>springmvc</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   </web-app>
   ```

2. 配置springmvc-servlet.xml, 注册对应的bean

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
   
       <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
   
       <!--视图解析器: DispatcherServlet给他的ModelAndView-->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
   
           <!--前缀-->
           <property name="prefix" value="/WEB-INF/jsp/"/>
           <!--后缀-->
           <property name="suffix" value=".jsp" />
       </bean>
   
       <!--Handler-->
       <bean id="/hello" class="com.desitate.controller.HelloController"/>
   
   </beans>
   ```

3. 实现Controller

   ```java
   // 注意: 先导入Controller接口
   public class HelloController implements Controller {
   
       public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
   
           // Model and View 模型和视图
           ModelAndView mv = new ModelAndView();
   
           // 封装对象, 放在ModelAndView中, mode
           mv.addObject("msg", "HelloSpring");
   
           // 封装要跳转的视图, 放在ModelAndView中
           mv.setViewName("hello"); //: /WEB-INF/jsp/hello.jsp
           return mv;
       }
   }
   ```

4. 编写jsp文件

   ```jsp
   <%--
     Created by IntelliJ IDEA.
     User: Fisher
     Date: 2021/8/9
     Time: 16:52
     To change this template use File | Settings | File Templates.
   --%>
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <html>
   <head>
       <title>Title</title>
   </head>
   <body>
   ${msg}
   </body>
   </html>
   
   ```



### 3.5 执行流程

1. DispatcherServlet表示前置控制器, 是整个SpringMVC的控制器. 用户发出请求, DispatcherServlet接收请求并拦截请求
   * 假设我们请求的url为: `http://localhost:8080/SpringMVC/hello`
   * 如上url拆分为三部分:
     1. 8080之前表示域名
     2. SpringMVC部署在服务器的web站点上
     3. hello表示控制器
2. HandlerMapping为处理器映射, DispatcherServlet调用, 根据URL查找处理器
3. HandlerExecution表示具体的Handler, 主要作用是根据url查找控制器, 如上url查找控制器为hello
4. 随后将解析后的信息传递给DispatcherServlet
5. HandlerAdapter表示处理器适配器, 其按照特定的规则去执行Handler
6. Handler让具体的Controller执行
7. Controller让具体的执行信息返回给HandlerAdapter, 如ModelAndView
8. HandlerAdapter将视图逻辑名或模型传递给DispatcherServlet
9. DispatcherServlet调用视图解析器(ViewResolver)来解析HandlerAdapter传递的视图
10. 视图解析器将解析的视图逻辑名传递给DispatcherServlet
11. DispatcherServlet根据视图解析器解析的结果, 调用具体的视图
12. 最终视图呈现给用户

**可能遇到的问题: 访问404, 排查步骤:**

1. 查看控制台输出, 看一下是不是缺少了什么jar包

2. 如果jar包存在, 显示无法输出, 就在IDEA的项目发布中添加lib依赖

   ![](./pic/s13.png)

3. 重启tomcat即可解决



### 3.6 采用注解开发

1. 创建maven项目, 导入依赖, 设置框架, 打包\

2. 配置web.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <servlet>
           <servlet-name>SpringMVC</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc-servlet.xml</param-value>
           </init-param>
   
           <load-on-startup>1</load-on-startup>
       </servlet>
   
       <servlet-mapping>
           <servlet-name>SpringMVC</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   
   
   </web-app>
   ```

3. 配置springmvc-servlet.xml

   ```xml
   <?xml version="1.0" encoding="UTF8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
   
       <!--自动扫描包, 让指定包下的注解生效, 有IOC容器统一管理-->
       <context:component-scan base-package="com.desitate.controller"/>
   
       <!-- 让Spring MVC不处理静态资源 .css .js .html .mp3 .mp4-->
       <mvc:default-servlet-handler />
   
   
       <!--
         支持mvc注解驱动
           在Spring中一般采用@RequestMapping注解来完成映射关系
           要想使@RequestMapping注解生效
           必须向上下文中注册DefaultAnnotationHandlerMapping
           和一个AnnotationMethodHandlerAdapter实例
           这两个实例分别在类级别和方法级别处理,
           而annotation-driver配置帮助我们自动完成两个实例注入-->
       <mvc:annotation-driven />
   
       <!--视图解析器-->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
             id="internalResourceViewResolver">
           <property name="prefix" value="/WEB-INF/jsp/"/>
           <property name="suffix" value=".jsp"/>
       </bean>
   
   </beans>
   ```

4. 编写jsp文件

5. 编写控制器

   ```java
   @Controller
   //@RequestMapping("/hello")
   public class HelloController {
   
   
       // 真实路径为 localhost:8000/hello/h1
       @RequestMapping("/h1")
       public String hello(Model model) {
           // 封装数据
           model.addAttribute("msg", "Hello, SpringMVCAnnotation");
           return "hello"; // 会被视图解析器处理
       }
   }
   ```

6. 运行

**注意事项: 打出来的包要和class在一个目录下, 还有编码问题要注意**

SpringMVC必须要配置的三大件:

* 处理器
* 处理器适配器
* 视图解析器

通常, 我们只需要手动配置视图解析器, 而处理器映射器和处理器适配器只需要开启**注解驱动**即可, 省去了大段地xml配置



## 4. Controller及RestFul风格

### 4.1 Controller

* 提供访问应用程序地能力, 通常通过接口定义或注解两种方法实现
* 负责解析用户请求并将其转化成一个模型
* SpringMVC中一个控制器类可以包含多个方法
* 对于Controller的控制有很多种

### 4.2 实现方式

* 实现Controller接口

  ```java
  // 实现该接口获得控制器功能
  public interface Controller {
      // 处理请求返回一个MV对象
      ModelAndView handleRequest(**Kwargs);
  }
  ```

  然后在Spring中注册对应的Controller

  缺点: 一个控制器中只能写一个方法

* 使用注解实现

  ```java
  @Ccomponent
  @Service
  @Controller
  @Respository
  ```


### 4.3 RequsetMapping说明

* @RequestMapping注解用于映射url到控制器或一个特定的处理程序方法. 可用于类或方法上. 用于类上, 表示类中的所有响应请求的方法都是以该方法作为父路径.

  这个也就是`class`上加`@`等的, 一般在方法上写死.

### 4.4 RestFul风格

是一个资源定位及资源操作风格. 不是协议也不是标准, 只是一种风格, 基于这个风格设计的软件可以更简洁, 更有层次, 更易于实现缓存

* 功能

  * 互联网所有资源都可以抽象为资源
  * 资源操作: 使用post, delete, put, get采用不同方法对资源进行操作
  * 对应添加, 删除, 修改, 查询

* 传统的资源操作方式:

  通过不同的参数实现不同的效果, 方法单一, Get和Post

* 使用RestFul操作资源: 可以通过不同的请求方式来实现不同的效果, 请求地址一样, 但是功能可以不同

  ```java
  // RestFul:
  @RequestMapping("/add/{a}/{b}")
  public String test2(@PathVariable int a, @PathVariable String b, Model model) {
      String res = a + b;
      model.addAttribute("msg", "结果为" + res);
     	return "test";
  }
  ```

  `@PathVariable`表示的是路径变量也就是要接受的变量, 我们还可以在`@RequsetMapping(value = , method = )`指定请求方法

  ```java
  // RestFul:
  @RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.GET)
  public String test2(@PathVariable int a, @PathVariable String b, Model model) {
      String res = a + b;
      model.addAttribute("msg", "结果为" + res);
      return "test";
  }
  ```

  当然我们还可以用更加具体的Mapping, 比如`GetMapping`方法等, 对应的所有请求方法都存在

  最大好处很安全, **不会暴露我们提交的参数**



## 5. SpringMVC结果跳转方式

### 5.1 ModeleAndView

页面真正的位置: [视图解析器前缀] + viewName + [视图解析器后缀]

### 5.2 SpringMVC实现

SpringMVC实现转发和重定向 - 无需视图解析器

测试前, 需要将视图解析器注释掉

```java
@Controller
public class ModelTest1 {

    @RequestMapping("m1/t1")
    public String test(Model model) {

        // URL没变表示转发
        model.addAttribute("msg", "ModelTest1");
        return "/WEB-INF/jsp/test.jsp";
        // return "forward:/WEB-INF/jsp/test.jsp";
        // 显示声明转发
    }
}
```

重定向

```java
@Controller
public class ModelTest1 {

    @RequestMapping("m1/t1")
    public String test(Model model) {

        model.addAttribute("msg", "ModelTest1");
        return "redirect:/index.jsp";
    }
}
```

上面的这个两种注释不会走视图解析器



### 5.3 数据处理

1. 提交的域名名称和处理方法的参数名一致

   提交数据`http://localhost:8080/add?a=1&b=2`

   处理方法:

   ```java
   @RequestMapping("/add")
   public String test1(int a, int b, Model model) {
       int res = a + b;
       model.addAttribute("msg", "结果为" + res);
       return "test";
   }
   ```

   

2. 提交的域名称和处理方法的参数名不一致

   提交数据`[Title](http://localhost:8080/hello?username=jjj)`

   ```java
   @RequestMapping("/hello")
   public String hello(@RequestParam("username") String name) {
       System.out.println(name);
       return "test";
   }
   ```

   `@RequestParam`表示的是真正要接受的名称

3. 提交的是一个对象

   ```java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class User {
       private int id;
       private String name;
       private int age;
   }
   ```

   ```java
   // 前端接受一个对象: id, name, age
   
   
   /*
   1. 接收前端用户传递的参数, 判断参数的名字, 假设名字直接在方法上, 可以直接使用
   2. 假设传递的是一个对象User, 匹配User对象中的字段名, 如果名字一致则ok, 否则就会返回null
    */
   @GetMapping("/t2")
   public String test2(User user) {
       System.out.println(user);
       return "test";
   }
   
   // 响应的请求为http://localhost:8080/user/t2?id=1&name=Fisher&age=3
   ```

### 5.4 数据显示到前端

* 采用ModelView
* 通过ModeMap
* 通过Model

```java
/*
ModelMap继承了LinkedHashMap
Model继承了ModelMap, 是一个精简版
ModelAndView几乎不用
 */
@GetMapping("/t3")
public String test3(ModelMap map) {
    return "test";
}
```

## 6. 乱码问题

### 6.1 解决方法一

* 编写EncodingFilter类

  ```java
  public class EncodingFilter implements Filter {
      @Override
      public void init(FilterConfig filterConfig) throws ServletException {
  
      }
  
      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          servletRequest.setCharacterEncoding("utf-8");
          servletResponse.setCharacterEncoding("utf-8");
          filterChain.doFilter(servletRequest, servletResponse);
      }
  
      @Override
      public void destroy() {
  
      }
  }
  ```

* 注册Servlet

  ```xml
  <filter>
      <filter-name>encoding</filter-name>
      <filter-class>com.desitate.filter.EncodingFilter</filter-class>
  </filter>
  
  <filter-mapping>
      <filter-name>encoding</filter-name>
      <url-pattern>/*</url-pattern>
  </filter-mapping>
  ```

### 6.2 SpringMVC直接的配置

```xml
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>

<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```



### 6.3 极端情况下

1. 修改Tomcat的Config中的Server.xml

   ```xml
   <Connector URIEncoding="utf-8" port="8080" protocol="HTTP/1.1"
              connectionTimeout="20000"
              redirectPort="8443" />
   ```

   

## 7. Json

前后端分离, 分开部署, 后端提供接口数据(----->采用JSON进行数据交换)前端负责渲染后端数据

### 7.1 什么是JSON?

* JS对象标记是一种轻量级的数据交换格式, 使用广泛
* 采用完全独立于编程语言的文本格式来存储和表示数据
* 简洁清晰的层次结构
* 易于阅读和编写, 易于机器解析和生成, 有效提升传输效率

JSON键值对是用来保存JS对象的一种方式, `""`中包裹JS的对象表示

### 7.2 Jackson的使用

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-databind</artifactId>
       <version>2.12.3</version>
   </dependency>
   ```

2. 编写web.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <servlet>
           <servlet-name>SpringMVC</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:springmvc-servlet.xml</param-value>
           </init-param>
   
           <load-on-startup>1</load-on-startup>
       </servlet>
   
       <servlet-mapping>
           <servlet-name>SpringMVC</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
   
       <filter>
           <filter-name>encoding</filter-name>
           <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
           <init-param>
               <param-name>encoding</param-name>
               <param-value>utf-8</param-value>
           </init-param>
       </filter>
   
       <filter-mapping>
           <filter-name>encoding</filter-name>
           <url-pattern>/</url-pattern>
       </filter-mapping>
   
   </web-app>
   ```

3. 编写springmvc-servlet.xml

   ```xml
   <?xml version="1.0" encoding="UTF8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mvc="http://www.springframework.org/schema/mvc"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
   
       <!--自动扫描包, 让指定包下的注解生效, 有IOC容器统一管理-->
       <context:component-scan base-package="com.desitate.controller"/>
   
       <!-- 让Spring MVC不处理静态资源 .css .js .html .mp3 .mp4-->
       <mvc:default-servlet-handler />
   
   
       <!--
         支持mvc注解驱动
           在Spring中一般采用@RequestMapping注解来完成映射关系
           要想使@RequestMapping注解生效
           必须向上下文中注册DefaultAnnotationHandlerMapping
           和一个AnnotationMethodHandlerAdapter实例
           这两个实例分别在类级别和方法级别处理,
           而annotation-driver配置帮助我们自动完成两个实例注入-->
       <mvc:annotation-driven />
   
       <!--视图解析器-->
       <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
             id="internalResourceViewResolver">
           <property name="prefix" value="/WEB-INF/jsp/"/>
           <property name="suffix" value=".jsp"/>
       </bean>
   
   </beans>
   ```

4. 测试

   * ```java
     @Controller
     public class UserController {
         // 设置编码格式, 指定UTF-8
         @RequestMapping(value = "/j1", produces = "application/json;charset=utf-8")
         @ResponseBody // 它就不会走视图解析器, 会直接返回一个字符串
         public String json1() throws JsonProcessingException {
     
             // jackson, ObjectMapper
             ObjectMapper mapper = new ObjectMapper();
     
             // 创建一个对象
             User user = new User("余坤", 3, "男");
     
             return mapper.writeValueAsString(user);
     
         }
     }
     ```

   * 乱码统一解决方式

     在springmvc-servlet中配置, 不用配置produce参数

     ```xml
     <mvc:annotation-driven>
         <mvc:message-converters register-defaults="true">
             <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                 <constructor-arg value="UTF-8"/>
             </bean>
     
             <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                 <property name="objectMapper">
                     <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                         <property name="failOnEmptyBeans" value="false"/>
                     </bean>
                 </property>
             </bean>
     
         </mvc:message-converters>
     </mvc:annotation-driven>
     ```

   * 也可以将`@Controller`替换成`@RestController`表示这个类是返回字符串, 不走视图解析

   * 格式化输出时间

     ````java
     @RequestMapping(value = "/j3")
     public String json3() throws JsonProcessingException {
     
         // jackson, ObjectMapper
         ObjectMapper mapper = new ObjectMapper();
     
         // 自定义日期的格式
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     
         Date date = new Date();
         // 创建一个对象
         return mapper.writeValueAsString(sdf.format(date));
     
     }
     ````

     或者采用如下方式

     ```java
     public String json4() throws JsonProcessingException {
     
         // 使用Mapper来格式化输出
         ObjectMapper mapper = new ObjectMapper();
         // 不用时间戳的方式
         mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
     
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     
         mapper.setDateFormat(sdf);
     
         Date date = new Date();
         // 创建一个对象
         return mapper.writeValueAsString(date);
     
     }
     ```

总结: 我们可以将常用的函数封装成一个包, 从而完成我们的日常操作



## 8. FastJson的使用

可以方便json对象和JavaBean之间的转换

1. 导入依赖

   ```xml
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>fastjson</artifactId>
       <version>1.2.60</version>
   </dependency>
   ```

2. fastjson中主要有三个类:

   * JsonObject代表json对象
   * JsonArray代表json数组
   * Json代表JsonObject和JsonArray的转化



## 9. SpringMVC的整合SSM

### 9.1 环境要求

* IDEA
* MySQL 5.7.19
* Tomcat 9
* Maven 3.6

### 9.2 数据库环境

建立存放书籍数据的数据库表

```sql
CREATE DATABASE `ssmbuild`;

USE `ssmbuild`;

DROP TABLE IF EXISTS `books`;

CREATE TABLE `ssmbuild`.`books`  (
  `bookID` int(10) NOT NULL AUTO_INCREMENT COMMENT '书id',
  `bookName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `bookCounts` int(11) NOT NULL COMMENT '数量',
  `detail` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`bookID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `books` (`bookID`, `bookName`, `bookCounts`, `detail`) VALUES
(1, 'Java', 1, '从入门到放弃'),
(2, 'MySQL', 10, '从删库到跑路'),
(3, 'Linux', 5, '从进门到进牢');
```

### 9.3 导入依赖

```xml
<!--依赖问题
    junit, 数据库驱动, 连接池, servlet, jsp, mybatis, mybatis-spring, spring-->
<dependencies>
    <!--junit-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>

    <!--数据库驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
    </dependency>

    <!--数据库连接池, c3p0-->
    <dependency>
        <groupId>com.mchange</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.5.2</version>
    </dependency>

    <!--Servlet JSP-->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>

    <!--Mybatis-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.2</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
    </dependency>

    <!--Spring-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.9.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.1.9.RELEASE</version>
    </dependency>
</dependencies>

<!--静态资源导出-->
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>

        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```



### 9.4 连接数据库

### 9.5 创建包目录

* 创建pojo, service, dao, controller等包

* 创建mybatis-config.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
  
  
  </configuration>
  ```

* 创建applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
      https://www.springframework.org/schema/beans/spring-beans.xsd">
  
  
  </beans>
  ```

### 

### 9.6 Mybatis层编写

1. 编写database.properties

   ```properties
   jdbc.driver=com.mysql.jdbc.Driver
   # 如果使用mysql8.0还需要增加时区配置
   jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=utf8
   jdbc.username=root
   jdbc.password=123456
   ```

2. 编写pojo实体类和Mapper接口

   ```java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   public class Books {
       private int bookID;
       private String bookName;
       private int bookCounts;
       private String detail;
   }
   
   ```

   ```java
   public interface BookMapper {
   
       // 增加一本书
       int addBook(Books books);
   
       // 删除一本书
       int deleteBookById(@Param("bookID") int id);
   
       // 更新一本书
       int updateBook(Books books);
   
       // 根据id查询书
       Books queryBookById(@Param("bookID") int id);
   
       // 查询全部书
       List<Books> queryAllBook();
   }
   ```

3. 编写Mapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <!--namespace绑定对应的Dao/Mapper接口/-->
   <mapper namespace="com.desitate.dao.BookMapper">
       <insert id="addBook" parameterType="books">
           insert into ssmbuild.books (bookName, bookCounts, detail)
           values (#{bookName}, #{bookCounts}, #{detail});
       </insert>
   
       <delete id="deleteBookById" parameterType="_int">
           delete from ssmbuild.books where bookID = #{bookID};
       </delete>
   
       <update id="updateBook" parameterType="books">
           update ssmbuild.books
           set bookName = #{bookName}, bookCounts = #{bookCounts}, detail = #{detail}
           where bookID = #{bookID};
       </update>
   
       <select id="queryBookById" resultType="books" parameterType="_int">
           select * from ssmbuild.books where bookID = #{bookID}
       </select>
   
       <select id="queryAllBook" resultType="books">
           select * from ssmbuild.books
       </select>
   
   </mapper>
   ```

4. 添加mybatis-config.xml的内容

   ```xml
   <!--配置数据交给Spring-->
   <typeAliases>
       <package name="com.desitate.pojo"/>
   </typeAliases>
   
   <mappers>
       <mapper class="com.desitate.dao.BookMapper"/>
   </mappers>
   ```

5. 编写service

   ```java
   public class BookServiceImpl implements BookService {
       
       // service层调用dao层, 组合Dao层
       private BookMapper bookMapper;
   
       public BookMapper getBookMapper() {
           return bookMapper;
       }
   
       public void setBookMapper(BookMapper bookMapper) {
           this.bookMapper = bookMapper;
       }
   
       public int addBook(Books books) {
           return bookMapper.addBook(books);
       }
   
       public int deleteBookById(int id) {
           return bookMapper.deleteBookById(id);
       }
   
       public int updateBook(Books books) {
           return bookMapper.updateBook(books);
       }
   
       public Books queryBookById(int id) {
           return bookMapper.queryBookById(id);
       }
   
       public List<Books> queryAllBook() {
           return bookMapper.queryAllBook();
       }
   }
   ```



### 9.7 整合Spring层

1. 编写Spring-dao.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
   
       <!--1. 关联数据库配置文件-->
       <context:property-placeholder location="classpath:database.properties"/>
   
       <!--2. 连接池
           dbcp 半自动化操作, 不能自动连接
           c3p0 自动化操作(自动加载配置文件, 并且可以自动设置到对象中
           druid
           hikari-->
       <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
           <property name="driverClass" value="${jdbc.driver}"/>
           <property name="jdbcUrl" value="${jdbc.url}"/>
           <property name="user" value="${jdbc.username}"/>
           <property name="password" value="${jdbc.password}"/>
       </bean>
   
       <!--3. SqlSessionFactory-->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="dataSource"/>
           <!--绑定mybatis的配置文件-->
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
       </bean>
   
       <!--4. 配置dao接口扫描包, 动态实现了Dao接口可以注入到Spring容器中-->
       <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
           <!--注入SqlSessionFactory-->
           <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
           <!--要扫描的包-->
           <property name="basePackage" value="com.desitate.dao"/>
       </bean>
   
   
   </beans>
   ```

2. 编写spring-service.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
       
       <!--1. 扫描service下的包-->
       <context:component-scan base-package="com.desitate.service"/>
       
       <!--2. 将所有的业务类注入到Spring, 可以通过配置, 也可以通过注解实现-->
       <bean id="bookServiceImpl" class="com.desitate.service.BookServiceImpl">
           <property name="bookMapper" ref="bookMapper"/>
       </bean>
       
       <!--声明式事务配置-->
       <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <!--导入数据源-->
           <property name="dataSource" ref="dataSource"/>
       </bean>
       
       <!--4. aop事务支持-->
   
   </beans>
   ```

### 9.8 整合SpringMVC层

1. 增加web项目支持

2. 编写web.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
       
       <!--DispatcherServlet-->
       <servlet>
           <servlet-name>springmvc</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <init-param>
               <param-name>contextConfigLocation</param-name>
               <param-value>classpath:applicationContext.xml</param-value>
           </init-param>
           <load-on-startup>1</load-on-startup>
       </servlet>
       <servlet-mapping>
           <servlet-name>springmvc</servlet-name>
           <url-pattern>/</url-pattern>
       </servlet-mapping>
       
       <!--乱码过滤-->
       <filter>
           <filter-name>encodingFilter</filter-name>
           <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
           <init-param>
               <param-name>encoding</param-name>
               <param-value>utf-8</param-value>
           </init-param>
       </filter>
       <filter-mapping>
           <filter-name>encodingFilter</filter-name>
           <url-pattern>/*</url-pattern>
       </filter-mapping>
       
       <!--Session-->
       <session-config>
           <session-timeout>15</session-timeout>
       </session-config>
   </web-app>
   ```

3. 编写spring-mvc.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
   
       <!--1. 注解驱动-->
       <mvc:annotation-driven/>
       <!--2. 静态资源过滤-->
       <mvc:default-servlet-handler/>
       <!--3. 扫描包: controller-->
       <context:component-scan base-package="com.desitate.controller"/>
       <!--4. 视图解析器-->
       <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
           <property name="prefix" value="/WEB-INF/jsp/"/>
           <property name="suffix" value=".jsp" />
       </bean>
   
   </beans>
   ```

   

## 10. Ajax技术

异步无刷新请求

* Ajax的核心是XMLHttpRequest对象(XHR)

* jQuery是一个库; JS的大量方法

* 首先在Spring中导入静态资源

* 然后编写ajax方法

  ```js
  function a() {
  $.post({
    url:"${pageContext.request.contextPath}/a1",
    data:{"name":$("#username").val()},
    success: function (data) {
      alert(data);
    }
  })
  }
  
  // url , data键值对, success
  ```



## 11. 拦截器

类似于Filter

* 拦截器是AOP思想的具体应用, 横切进去.
* 拦截器只针对控制方法和请求, 访问jsp/html等静态文件的时候不会拦截

### 11.1 自定义拦截器

必须实现HandlerInterceptor接口

1. 编写MyInterceptor文件, 实现对应的接口

   ```java
   public class MyInterceptor implements HandlerInterceptor {
   
       // return true会执行下一个拦截器, 放行
       // return false会进行拦截, 不执行下一个拦截器
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           System.out.println("处理前");
           return true;
       }
   
       @Override
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           System.out.println("处理后");
       }
   
       @Override
       public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           System.out.println("清理");
       }
   }
   ```

2. 在srpingmvc-servlet.xml中添加拦截器, 其实采用了aop的方式

   ```xml
   <!--拦截器配置-->
   <mvc:interceptors>
       <mvc:interceptor>
           <!--*当前路径下的所有路径, **所有文件下的所有文件-->
           <mvc:mapping path="/**"/>
           <bean class="com.desitate.config.MyInterceptor"/>
       </mvc:interceptor>
   </mvc:interceptors>
   ```

   

## 12. SpringMVC文件上传和下载

### 12.1 上传文件

导入依赖

```xml
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.3</version>
</dependency>

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
</dependency>
```

编写表单, 采用二进制流的方式提交文件

```jsp
<form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
	<input type="file" name="file">
	<input type="submit" value="upload">
</form>
```

编写文件配置

```xml
<!--文件上传配置-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!--请求的编码格式, 必须和jsp的pageEncoding属性一致, 以便正确读取表单的内容, 默认为ISO-8859-1-->
    <property name="defaultEncoding" value="utf-8"/>
    <!-- 上传文件大小上限, 单位为字节 10485760 = 10M-->
    <property name="maxUploadSize" value="10485760"/>
    <property name="maxInMemorySize" value="40960"/>
</bean>
```

编写文件上传类

```java
@RestController
public class FileController {

    // @RequestParam("file")将name=file控件得到的文件封装成CommonsMultipartFile对象
    // 批量上传CommonsMultipartFile为数组即可
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {

        // 获取文件名
        String filename = file.getOriginalFilename();
        // 如果文件名为空, 直接返回到首页
        if ("".equals(filename)) {
            return "redirect:/index.jsp";
        }
        System.out.println("文件名:" + filename);

        String path = request.getServletContext().getRealPath("/upload");

        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        System.out.println("文件保存地址为: " + realPath);

        InputStream is = file.getInputStream();
        FileOutputStream os = new FileOutputStream(new File(realPath, filename));

        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
            os.flush();
        }
        os.close();
        is.close();
        return "redirect:/index.jsp";

    }
    

    @RequestMapping("/upload2")
    public String upload2(@RequestParam CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        // 上传路径保存
        String path = request.getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        System.out.println("上传文件地址: " + realPath);
        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        return "redirect:/index.jsp";

    }
}
```

### 12.2 下载文件

```java
@RequestMapping("/download")
public String downloads(HttpServletResponse response, HttpServletRequest request) throws IOException {
    // 设置要下载的地址
    String path = request.getServletContext().getRealPath("/upload");
    String name = "Github入门经典.md";

    // 1.设置response响应头
    response.reset(); // 设置页面不缓存, 清空buffer
    response.setCharacterEncoding("UTF-8"); // 字符编码
    response.setContentType("multipart/form-data"); // 二进制传输数据

    // 设置响应头
    response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name, "UTF-8"));

    File file = new File(path, name);
    // 2. 读取文件--输入流
    FileInputStream in = new FileInputStream(file);
    // 3. 文件输出流
    ServletOutputStream out = response.getOutputStream();

    byte[] buff = new byte[1024];
    int index = 0;

    while ((index = in.read(buff)) != -1) {
        out.write(buff, 0, index);
        out.flush();
    }
    out.close();
    in.close();
    return null;
}
```

我们要将请求的文件放在out/upload文件夹下



