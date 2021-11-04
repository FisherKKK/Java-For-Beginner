## 1. Spring5

### 1.1 简洁

* `interface21`是它的雏形

* 2004年3月24 Spring1.0

* 理念:

  * 强大的向后兼容性

  * 解决企业开发的复杂性

  * 本身是个大杂烩, 整合现有的技术框架:

    * `SSH`: `Struct2` + `Spring` + `Hibernate`
    * `SSM`: `SpringMVC` + `Spring` + `Mybatis`(半定制性)

* [官方文档]( https://docs.spring.io/spring-framework/docs/current/reference/html/overview.html#overview)

* [各个版本文档](https://docs.spring.io/spring-framework/docs/)

* [GitHub地址](https://github.com/spring-projects/spring-framework)

* `Maven`

  ```xml
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.2.9.RELEASE</version>
  </dependency>
  // 网页开发要导入上述
  
  
  <dependency>
  	<groupId>junit</groupId>
  	<artifactId>junit</artifactId>
  	<version>4.12</version>
  	<scope>test</scope>
  </dependency>
  // 测试专用
  
  
  
  <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.9.RELEASE</version>
  </dependency>
  // 下面一个是整合所需要的
  ```

* 优点:

  1. 开源免费容器
  2. 轻量级, 非入侵式的框架
  3. 控制反转(IOC), 面向切面编程(AOP)
  4. 支持事务处理, 对框架整合的支持

**总结: `Spring`就是一个轻量级的`IOC`和`AOP`的框架**

### 1.2 组成

![](.\pic\s1.png)

`ORM`, 对象映射关系

### 1.4 扩展

1. 构建一切, SpringBoot
2. 协调一切, Spring Cloud
3. 连接一切, Spring Cloud Data Flow

* `SpringBoot`快速开发脚手架
  * 基于`SpringBoot`可以快速开发单个微服务
  * 约定大于配置!
* `Spring Cloud`
  * 基于`SpringBoot`实现

大多数公司使用`SpringBoot`进行快速开发, 前提是完全`Spring`和`SpringMVC`, 承上启下.

* 弊端: 配置十分繁琐, 人称: "配置地狱"

## 2. IOC理论推导

1. `UserDao`接口
2. `UserDaoImpl`实现类
3. `UserService`业务接口
4. `UserServiceImple`业务实现类

> ```xml
> <properties>
>     <maven.compiler.source>1.8</maven.compiler.source>
>     <maven.compiler.target>1.8</maven.compiler.target>
> </properties>
> // 解决编译器版本问题
> ```

在之前的业务中, 用户的需求可能会影响我们原来的代码, 我们需要根据用户的需求去修改源代码, 修改成本昂贵

我们使用`set`接口实现, 之前程序主动创建对象, 控制权在程序员手中, 使用之后程序不再具有主动性, 成为了被动接受对象. 这种思想从本质上解决了问题, 程序员不用管理对象的创建. 系统耦合性大大降低, 专注在业务实现上. 这是`IOC`原型

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
    https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```





#### `IOC`本质

是一种设计思想, `DI`是实现`IOC`的一种方式, 实际上是获得依赖对象的方式反转了.

采用`xml`配置`Bean`的时候, `Bean`的定义信息和实现是分离的, 而采用注解的方式可以把两者融合为一体, `Bean`的定义信息直接以注解的形式定义在实现类中, 从而达到0配置的目的

**控制反转是一种描述并通过第三方去生产或获取特定对象的方式. 在`Spring`中实现控制反转的`IOC`容器, 其实现方法是依赖注入(`DI`)**

## 3. Hello Spring

* `Hello`是谁创建了

  bean代表一个对象, **id = 变量名, class = new 的对象, property相当于给对象中的属性设置一个值**, 这个过程就是控制反转

* 反转: 程序本身并不创建对象, 而是被动接受对象

* 控制: 谁创建对象谁控制, `Spring`

* 依赖注入: 利用`set`方法进行注入

IOC是一种思想, 由主动编程变成被动接受, 可以通过`Class`那个类进行观察. 现在无需对程序进行改动, 只需要在`xml`文件中进行修改, `IOC`: 对象由`Spring`来创建, 管理, 装配!





## 4. `IOC`创建的方式

1. 使用无参构造函数创建对象bean

2. 如果使用有参构造函数

   1. ```xml
      <!--下标赋值-->
      <bean id="user" class="com.desitate.pojo.User" >
          <constructor-arg index="0" value="hhh"/>
      </bean> 
      ```

   2. ```xml
      <!--通过类型进行创建, 不建议使用-->
      <bean id="user" class="com.desitate.pojo.User">
          <constructor-arg type="java.lang.String" value="hhh" />
      </bean>
      ```
   
   3. ```xml
      <!--直接通过参数名进行创建-->
      <bean id="user" class="com.desitate.pojo.User">
          <constructor-arg name="name" value="FisherKK" />
      </bean>
      ```

> `getBean`之前对象已经被创建, 在配置文件的时候, 容器中管理的对象就已经被初始化



## 5. Spring配置

### 5.1 别名

```xml
<!--如果添加别名, 可以通过别名访问对象-->
<alias name="user" alias="heyha"/>
```

### 5.2 Bean的配置

```xml
<!--name也表示别名, 并且可以写多个名字-->
<bean id="userT" class="com.desitate.pojo.UserT" name="user2, user3, user4">
    <property name="name" value="Hello" />
</bean>
```

### 5.3 `import`

一般用于团队开发, 可以将多个配置文件导入为一个文件

假设项目中有多个人开发, 每个人负责不同类的开发, 每个类注册在不同的类中, 我们可以利用`import`, 将所有人的`beans`合并为一个, 使用的时候直接使用总配置即可

```xml
<import resource="beans.xml"/>
```

内容相同也会合并

## 6. `DI`依赖注入

### 6.1 构造器注入

已经说过

### 6.2 `Set`方式注入

* 依赖注入本质上是`set`注入
  * 依赖: `bean`对象的创建依赖容器
  * 注入: `bean`对象中的所有属性由容器来注入

* 环境搭建

  1. 复杂类型

     ```java
     public class Address {
         private String address;
     
         public String getAddress() {
             return address;
         }
     
         public void setAddress(String address) {
             this.address = address;
         }
     }
     ```

     

  2. 真实测试对象

     ```java
     public class Student {
         private String name;
         private Address address;
         private String[] books;
         private List<String> hobbies;
         private Map<String, String> card;
         private Set<String> game;
         private Properties info;
         private String wife;
     }
     ```

  3. beans.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
         https://www.springframework.org/schema/beans/spring-beans.xsd">
     
         <bean id="address" class="com.desitate.polo.Address">
             <property name="address" value="Shanghai"/>
         </bean>
     
         <bean id="student" class="com.desitate.polo.Student">
     
             <!--普通值的直接注入-->
             <property name="name" value="FisherKK"/>
     
             <!--Bean注入, ref-->
             <property name="address" ref="address"/>
     
             <!--数组-->
             <property name="books">
                 <array>
                     <value>红楼梦</value>
                     <value>西游记</value>
                     <value>三国演义</value>
                     <value>水浒传</value>
                 </array>
             </property>
     
             <!--List-->
             <property name="hobbies">
                 <list>
                     <value>Swimming</value>
                     <value>Programming</value>
                     <value>Playing game</value>
                 </list>
             </property>
     
             <!--Map-->
             <property name="card">
                 <map>
                     <entry key="身份证" value="12244"/>
                     <entry key="银行卡" value="24685"/>
                 </map>
             </property>
     
             <!--Set-->
             <property name="game">
                 <set>
                     <value>King Honor</value>
                     <value>Mario</value>
                 </set>
             </property>
     
             <!--null-->
             <property name="wife">
                 <null/>
             </property>
     
             <!--Properties-->
             <property name="info">
                 <props>
                     <prop key="id">20190525</prop>
                     <prop key="sex">Female</prop>
                     <prop key="name">XiaoMing</prop>
                     <prop key="username">admin</prop>
                     <prop key="password">123</prop>
                 </props>
             </property>
         </bean>
     
     </beans>
     ```

  4. 测试类

     ```java
     public class MyTest {
         public static void main(String[] args) {
             ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
             Student  student = (Student) context.getBean("student");
             System.out.println(student);
         }
     }
     ```

     

### 6.3 拓展方式

我们可以使用`p`和`c`命名空间进行注入, 使用方式官网存在.

配置:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--p命名空间注入可以直接注入属性值-->
    <bean id="user" class="com.desitate.polo.User" p:name="Fisher" p:age="18"/>

    <!--c命名空间注入构造器注入, constructor-arg参数, 必须要有构造器-->
    <bean id="user2" class="com.desitate.polo.User" c:age="18" c:name="余坤"/>
</beans>
```



测试:

```java
@Test
public void test2() {
    ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
    System.out.println(context.getBean("user2", User.class));
}
```

> 注意p和c命名空间不能直接使用, 必须导入相应的约束
>
> ```xml
> xmlns:p="http://www.springframework.org/schema/p"
> xmlns:c="http://www.springframework.org/schema/c"
> ```



### 6.4 `bean`的作用域

![](./pic/s2.png)



1. 单例模式(Spring默认机制)
2. 原型模式: 每次从容器`get`的时候都会产生新的对象
3. 其余的`request`, `session`, `application`只能在`web`中进行使用

## 7. `Bean`的自动配置

* 自动装配是`Spring`满足`bean`依赖的一种方式
* `Spring`会在上下文中自动寻找, 并自动装配`bean`属性

在`Spring`中有三种装配方式:

1. `xml`中显式配置
2. `java`中显式配置
3. 隐式的自动装配[!重要]

### 7.1 测试

环境搭建: 一个人两个动物

### 7.2 `ByName`自动装配

```xml
<!--byName: 会自动在容器上下文中查找， 和自己对象set方法后面的值对应的bean id
-->
<bean id="person" class="com.desitate.pojo.Person" autowire="byName">
    <property name="name" value="FisherKK"/>
</bean>
```

### 7.3 `ByType`自动装配

```xml
<!--byType: 会自动在容器上下文中查找, 和自己对象属性类型相同的bean
-->
<bean id="person" class="com.desitate.pojo.Person" autowire="byType">
    <property name="name" value="FisherKK"/>
</bean>
```

这个类的实例必须唯一, 否则不知道装配哪一个

> Tips:
>
> * `byName`的时候需要保证所有bean的id唯一, 并且这个bean需要和自动注入的属性的`set`方法的值一致
> * `byType`需要保证bean的class唯一, 并且这个bean需要和自动注入的属性的类型一致

### 7.4 使用注解实现自动装配

`JDK1.5`支持的注解, `Spring2.5`就支持注解; 大多时候都是采用注解的方式进行开发, 要比`xml`方式好得多

要使用注解须知:

1. 导入约束, `context`约束

2. 配置注解得支持

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   
       <context:annotation-config/>
   
   </beans>
   ```

#### `@Autowired`

直接在属性(或者set方法)上使用, id和变量名要相同// **先匹配type, type多就匹配name**

> 如此一来可以忽略set方法, 只要get即可, 前提是是这个自动装配的属性在`IOC`容器中存在, 且符合名字`byName`
>
> Tips:
>
> ```xml
> @Nullable 字段标记了这个注解, 说明这个字段可以为null;
> ```
>
> ```java
> public @interface Autowired {
>     boolean required() default true;
> }
> ```

如果`@Autowired`自动装配的环境比较复杂, 自动装配无法通过一个注解完成的时候, 我们还可以使用如下方式指定装配

```java
@Autowired
@Qualifier(value = "dog000")
private Dog dog; 
/**
* 配合使用, 指定唯一的bean
*/
```

小结:

`@Autowired`和`@Resource`的区别:

* 都是自动装配的, 都可以放在属性字段上
* 前面一个优先通过`byType`, 后面一个是通过`byName`



## 8. 使用注解开发

在`Spring4`之后使用注解开发, 必须要保证`AOP`的保存在, 使用注解需要导入`context`依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
    <!--指定要扫描的包, 这个包下的注解就会生效-->
    <context:component-scan base-package="com.desitate"/>

</beans>
```



1. `Bean`

2. 属性如何注入

   ```java
   @Component
   public class User {
   
       // 相当于bean中的属性<property />
   	// @Value("FisherKK")
       public String name;
   
       public String getName() {
           return name;
       }
   
       @Value("Fisher")
       public void setName(String name) {
           this.name = name;
       }
   }
   ```

   

3. 衍生的注解

   `@Component`有几个衍生注解, 我们在`web`开发中会按照`MVC`三层架构分层

   * dao[`@Repository`], 功能和`@Component`是一样的, 只是实现采用如此
   * service[`@Service`]
   * controller[`@Controller`]

   这四个注解功能一样, 都是代表将某个类注册到`Spring`中, 装配`Bean`

4. 自动装配

   上面已经说过了

5. 作用域

   ```java
   @Scope("prototype")
   ```

6. 小结

   `xml`和注解:

   * `xml`更加万能, 适合于任何场景, 维护更加简单方便
   * 注解不是自己的类无法使用, 维护相对复杂

   最佳实践:

   `xml`用来管理`bean`, 注解管理注入. 在使用过程中只需要注意我们需要开启注解支持:

   * `Context`依赖
   * `注解`扫描



## 9. 完全使用`Java`方式配置`Spring`

我们现在完全不使用`xml`配置, 全权交给`Java`

`JavaConfig`是`Spring`的一个子项目, 它是`Spring4`之后的核心功能

实体类:

```java
// 这个注解的意思就是说明这个类被Spring接管了, 注册到了容器了
@Component
public class User {
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Value("FisherKK") // 注入值
    public void setName(String name) {
        this.name = name;
    }
}
```



配置类:

```java
@Configuration
@ComponentScan("com.desitate.pojo")
@Import(Config.class)
public class Config {

    // 注册一个bean, 就相当于之前写的一个bean标签
    // id为方法名, class为返回值
    @Bean
    public User user() {
        return new User(); // 要注入到bean的对象
    }
}
```



测试类:

```java
public class MyTest {
    public static void main(String[] args) {
        // 如果完全使用了配置类的方式做, 我们只能通过AnnotationConfigApplicationContext
        // 获取容器, 通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println(context.getBean("user", User.class));
    }
}
```



这种纯`java`的配置方式, 在`SpringBoot`中随处可见

## 10. 代理模式

why? `Spring`AOP的底层实现 [SpringMVC和Spring APO必考]

代理模式分类:

* 静态代理
* 动态代理

### 10.1 静态代理

角色分析:

* 抽象角色: 一般使用接口和抽象类
* 真实角色: 被代理的角色
* 代理角色: 代理真实角色, 代理真实角色后, 我们会做一些附属操作
* 客户: 访问代理对象的人

代码步骤:

1. 接口

   ```java
   // 租房
   public interface Rents {
   
       public void rent();
   
   }
   ```

   

2. 真实角色

   ```java
   // 房东
   public class Host implements Rents{
   
       @Override
       public void rent() {
           System.out.println("房东出租房子");
       }
   }
   ```

   

3. 代理角色

   ```java
   public class Proxy implements Rents{
       private Host host;
   
       public Proxy() {
       }
   
       public Proxy(Host host) {
           this.host = host;
       }
   
   
       @Override
       public void rent() {
           host.rent();
       }
   
       public void seeHouse() {
           System.out.println("中介看房");
       }
   
       public void fee() {
           System.out.println("收中介费");
       }
   
       public void rule() {
           System.out.println("签租赁合同");
       }
   }
   ```

   

4. 客户端访问代理角色

   ```java
   
   public static void main(String[] args) {
           Host host = new Host();
           Proxy proxy = new Proxy(host);
           proxy.rent();
       }
   }
   ```

   

代理模式好处:

* 可以使真实角色的操作更加纯粹, 不用去关注公共的业务
* 公共业务交给了代理角色, 实现了业务分工
* 公共业务发生扩展的时候, 方便集中管理

缺点:

* 一个真实角色就会产生一个代理角色, 代码量翻倍, 开发效率变低

### 10.2 深入理解

AOP就是采用代理横向添加新的业务逻辑和功能

### 10.3 动态代理

* 动态代理和静态代理角色一样
* 动态代理类是动态生成的, 不是直接写好的
* 动态代理分为: 基于接口动态代理, 基于类的动态代理
  * 基于接口, `JDK`动态代理
  * 基于类: `cglib`
  * `java`字节码, `javasist`

需要了解两个类: Proxy: 代理, 提供了创建动态代理类和实例的静态方法; InvocationHandler: 调用处理程序, 由代理实例调用处理程序实现的接口. 每个代理实例都有一个关联的调用处理程序

```java
// InvocationHandler
public class ProxyInvocationHandler implements InvocationHandler {

    private Object object; // 被代理的接口

    // 设置代理接口
    public void setObject(Object object) {
        this.object = object;
    }

    // 生成代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                object.getClass().getInterfaces(), this);
    }

    // 处理代理实例, 并返回结果, 调用代理需要执行的方法
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        log(method.getName());
        Object invoke = method.invoke(object, objects); // method就是我们执行的方法
        // 更多需要执行的方法
        return invoke;
    }

    // 我们可以增加新的功能
    public void log(String m) {
        System.out.println("执行了" + m + "方法");
    }

}
```

```java
// Client
public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl(); // 真实角色

        // 代理处理程序
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();

        // 设置要代理的对象
        proxyInvocationHandler.setObject(userService);

        UserService proxy = (UserService) proxyInvocationHandler.getProxy();
        proxy.delete();
    }
}
```

动态代理的好处:

* 所有静态代理的好处
* 一个动态代理类代理的是一个接口, 一般就是对应一类业务
* 一个动态代理类可以代理多个类, 只要这个类实现了对应的接口

## 11. `AOP`

### 11.1 什么是`AOP`

面向切面编程, 通过预编译方式和运行期动态代理实现程序功能统一维护的一种技术. `AOP`是`OOP`的延续, 是函数式编程的一种衍生范式. 利用`AOP`可以对业务逻辑的各个部分进行隔离, 从而使得业务逻辑各部分之间的耦合度降低, 提高程序的可用性, 同时提高了开发效率

### 11.2 `AOP`在Spring中的作用

**提供声明式事务; 允许用户自定义切面**

* 横切关注点: 跨越应用程序多个模块的方法或功能, 我们想要关注的地方
* 切面: 是一个类
* 通知: 类中的方法
* 目标: 接口或者方法
* 代理: 生成的代理对象
* 切入点: 执行地点
* 连接点

### 11.3 使用`Spring`实现`AOP`

**重点** 使用`AOP`织入, 需要导入一个依赖包

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```

1. 使用`Spring`的`API`接口[主要是spring接口实现]

   接口:

   ```java
   public interface UserService {
       public void add();
       public void delete();
       public void select();
       public void update();
   }
   ```

   实现:

   ```java
   public class UserServiceImpl implements UserService{
   
       @Override
       public void add() {
           System.out.println("增加了一个用户");
       }
       
      
   
       @Override
       public void delete() {
           System.out.println("删除了一个用户");
       }
   
       @Override
       public void select() {
           System.out.println("挑选了一个用户");
       }
   
       @Override
       public void update() {
           System.out.println("更新了一个用户");
       }
   }
   ```

   前置环绕(接口之前执行):

   ```java
   public class Log implements MethodBeforeAdvice {
   
       // method：要执行的目标对象方法
       // objects: 参数
       // o: 目标对象
       @Override
       public void before(Method method, Object[] objects, Object o) throws Throwable {
           System.out.println(o.getClass().getName() + "的" + method.getName() + "被执行了");
       }
   }
   ```

   后置环绕(接口之后执行):

   ```java
   public class AfterLog implements AfterReturningAdvice {
   
       // returnValue返回值
       @Override
       public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
           System.out.println("执行了" + method.getName() + "方法, 返回结果为: " + returnValue);
       }
   }
   ```

   配置文件(首先我们需要在类里面进行注册bean, 然后采用aop配置切入点, 也就是环绕执行的地点, 最后指定我们环绕执行的方法):

   ```java
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
   
       <bean id="userService" class="com.desitate.service.UserServiceImpl"/>
       <bean id="log" class="com.desitate.log.Log"/>
       <bean id="afterLog" class="com.desitate.log.AfterLog"/>
   
       <!--使用原生的Spring API接口-->
       <!--配置AOP文件, 需要导入aop约束-->
       <aop:config>
   
           <!--切入点, 执行的地点, expression要执行的位置-->
           <aop:pointcut id="point" expression="execution(* com.desitate.service.UserServiceImpl.*(..))"/>
   
   
           <!--执行环绕增加-->
           <aop:advisor advice-ref="log" pointcut-ref="point"/>
           <aop:advisor advice-ref="afterLog" pointcut-ref="point"/>
       </aop:config>
   
   </beans>
   ```

   测试(一定要注意, 代理针对的是对接口的代理):

   ```java
   public class MyTest {
       public static void main(String[] args) {
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
   
           // 动态代理代理的是接口, 而不是具体的实现类
           UserService userService = (UserService) context.getBean("userService");
           userService.add();
       }
   }
   ```

2. 自定义类[主要是切面的定义]

   `execution(* com.desitate.service.UserServiceImpl.*(..))`

   第一个部分表示返回值的类型, 第二个部分`AOP`所切的服务包名 `..`表示当前包和子包 ()

   `.*(..)`所有函数, 参数任意

   

   创建切面, 就是我们想要添加的功能的类:

   ```java
   public class DiyPointCut {
   
       public void before() {
           System.out.println("方法执行前.");
       }
   
       public void after() {
           System.out.println("方法执行后");
       }
   }
   
   ```

   将类注册到beans.xml中:

   ```xml
   <!--方式二-->
   <bean id="diy" class="com.desitate.diy.DiyPointCut"/>
   <aop:config>
   
       <!--自定义切面, ref是引用的面-->
       <aop:aspect ref="diy">
           <!--切入点-->
           <aop:pointcut id="point" expression="execution(* com.desitate.service.UserServiceImpl.*(..))"/>
           <!--通知-->
           <aop:before method="before" pointcut-ref="point"/>
           <aop:after method="after" pointcut-ref="point"/>
   
       </aop:aspect>
   </aop:config>
   ```

   切入点就是我们要执行的切面的位置, 然后我我们指定前置和后置

3. 使用注解实现

   注解切面:

   ```java
   @Aspect // 标注类是一个切面
   public class AnnotationPointCut {
   
   
       // 环绕之后
       @Before("execution(* com.desitate.service..*.*(..))")
       public void before() {
           System.out.println("=====方法执行前=====");
       }
   
       // 环绕之前
       @After("execution(* com.desitate.service.UserServiceImpl.*(..))")
       public void after() {
           System.out.println("====方法执行后====");
       }
   
       // 环绕, 我们可以给定一个参数, 代表我们要获取切入的点
       @Around("execution(* com.desitate.service.UserServiceImpl.*(..))")
       public void around(ProceedingJoinPoint jp) throws Throwable {
           System.out.println("===环绕前==="); // 最先执行
   
           System.out.println(jp.getSignature()); // 获得签名
           // 执行方法
           Object proceed = jp.proceed();
   
           System.out.println("===环绕后==="); // 执行完后马上执行
       }
       
   }
   ```

   配置beans.xml

   ```xml
   <!--方式三-->
   <bean id="annotationPointCut" class="com.desitate.diy.AnnotationPointCut"/>
   <!--开启注解支持 JDK(proxy-target-class="false"默认)  cglib为true 几乎不用-->
   <aop:aspectj-autoproxy proxy-target-class="false"/>
   ```



## 12. 整合`Mybatis`

步骤:

1. 导入相关`jar`包

   * junit
   * mybatis
   * MySQL
   * Spring相关
   * AOP织入
   * mybatis-spring[新的包]

   ```xml
   <dependencies>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
           <scope>test</scope>
       </dependency>
   
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.46</version>
       </dependency>
   
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.2</version>
       </dependency>
   
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.1.9.RELEASE</version>
       </dependency>
   
       <!--Spring操作数据库还需要spring-jdbc-->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.1.9.RELEASE</version>
       </dependency>
   
       <dependency>
           <groupId>org.aspectj</groupId>
           <artifactId>aspectjweaver</artifactId>
           <version>1.8.13</version>
       </dependency>
   
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis-spring</artifactId>
           <version>2.0.2</version>
       </dependency>
   
   </dependencies>
   ```

   

2. 编写

3. 测试

### 12.1 回忆mybatis

1. 编写实体类
2. 编写核心配置文件mybatis-config.xml
3. 编写接口
4. 编写Mapper.xml
5. 测试

### 12.2 Mybatis-spring

1. 编写数据源

   spring-dao.xml, 基本上可以不用变

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!--数据源DataSource, 使用Spring的数据源替换Mybatis的配置 c3p0 dbcp
       我们这里使用Spring的jdbc-->
       <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
           <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
           <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
           <property name="username" value="root"/>
           <property name="password" value="123456"/>
       </bean>
   
       <!--sqlSessionFactory-->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="dataSource" />
           <!--绑定Mybatis配置文件-->
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
           <property name="mapperLocations" value="classpath:com/desitate/mapper/*.xml"/>
       </bean>
   
       <!--SqlSessionTemplate就是我们的SqlSession-->
       <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
   
           <!--只能使用构造器构造, 因为没有set方法-->
           <constructor-arg index="0" ref="sqlSessionFactory"/>
   
       </bean>
   
   </beans>
   ```

   applicationContext.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <import resource="spring-dao.xml"/>
   
       <bean id="userMapper" class="com.desitate.mapper.UserMapperImpl">
           <property name="sqlSessionTemplate" ref="sqlSession"/>
       </bean>
   
   </beans>
   ```

   mybatis-config.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <!--核心配置文件-->
   <configuration>
   
       <typeAliases>
           <package name="com.desitate.pojo"/>
       </typeAliases>
       <!--设置放在这里-->
   
   <!--    <settings>-->
   <!--        <setting name="" value=""/>-->
   <!--    </settings>-->
   </configuration>
   ```

   

2. sqlSessionFactory

3. sqlSessionTemplate

4. 接口实现类注册到Spring中

   ```java
   public class UserMapperImpl implements UserMapper {
   
       // 我们所有的操作都是用sqlSession来执行, 现在都是SqlSessionTemplate;
   
       private SqlSessionTemplate sqlSessionTemplate;
   
       public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
           this.sqlSessionTemplate = sqlSessionTemplate;
       }
   
       @Override
       public List<User> selectUser() {
           UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
           return mapper.selectUser();
       }
   }
   
   ```

5. 测试

   ```java
   @Test
   public void test1() {
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
   
   ```

   

采用DaoSupport:

* ```java
  public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
      @Override
      public List<User> selectUser() {
          UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
          return mapper.selectUser();
      }
  }
  ```

* ```xml
  <bean id="userMapper2" class="com.desitate.mapper.UserMapperImpl2">
      <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
  </bean>
  ```

* ```java
  @Test
  public void test1() {
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      UserMapper userMapper = context.getBean("userMapper2", UserMapper.class);
      System.out.println(userMapper.selectUser());
  }
  ```



## 13. 声明式事务

### 13.1 回顾事务

* 把一组业务当成一个业务来做; 要么都成功, 要么都失败
* 事务在项目开发中十分重要, 涉及到数据的一致性问题, 不能马虎
* 确保完整性和一致性

事务ACID原则:

* 原子性, 确保都成功或者都失败

* 一致性, 

* 隔离性

  多个业务可能操作同一个资源, 防止数据损坏

* 持久性

  事务一旦提交, 无论系统发生什么问题, 结果都不会再被影响, 被持久化的写入存储器中



### 13.2 Spring中的事务管理

* 声明式事务: AOP

  spring-dao.xml

  ```xml
  <!--配置声明式事务-->
  <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <constructor-arg ref="dataSource"/>
  </bean>
  
  <!--结合AOP实现事务的织入-->
  
  <!--配置事务的类:-->
  <tx:advice id="txAdvice" transaction-manager="transactionManager">
      <!--给哪些方法配置事务-->
      <!--配置事务的传播特性: new-->
      <tx:attributes>
          <!--指定所有方法-->
          <!--默认传播方式-->
          <tx:method name="add"/>
          <tx:method name="delete"/>
          <tx:method name="query" read-only="true"/>
          <tx:method name="*" propagation="REQUIRED"/>
      </tx:attributes>
  </tx:advice>
  
  <!--配置事务切入-->
  <aop:config>
      <aop:pointcut id="txPoint" expression="execution(* com.desitate.mapper.*.*(..))"/>
      <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"/>
  </aop:config>
  ```

  

* 编程式事务: 需要在代码中进行事务的管理

思考:

为什么需要事务?

* 如果不配置事务, 可能存在数据提交不一致的情况下;
* 如果不在Spring中配置声明式事务, 我们就需要在代码中手动配置事务
* 事务在项目的开发中十分重要, 涉及到数据的一致性和完整性, 不容马虎

## 附录

### 常见注解说明

| `@Autowired` | 自动装配, 类型优先                                   |
| ------------ | ---------------------------------------------------- |
| `@Nullable`  | 字段标记注解表示可空                                 |
| `@Resource`  | 自动装配, 名字优先                                   |
| `@Component` | 组件, 放在类上, 说明这个类被`Spring`管理了, 就是bean |

