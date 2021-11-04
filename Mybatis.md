# Mybatis

环境:

* JDK1.8
* MySQL 5.7
* maven 3.6.1(0)
* IDEA

回顾:

* JDBC
* MySQL
* Java基础
* Maven
* Junit

框架: 配置文件, 最好的方式: 看[mybatis – MyBatis 3 | 简介](https://mybatis.org/mybatis-3/zh/index.html)

## 1. 简介

## 1.1 什么是Mybatis

* MyBatis 是一款优秀的**持久层框架**
* 它支持自定义 SQL、存储过程以及高级映射。
* MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。
* MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
* 存在GitHub上 [mybatis/mybatis-3: MyBatis SQL mapper framework for Java (github.com)](https://github.com/mybatis/mybatis-3)

如何获得MyBatis?

* maven仓库

  ```xml
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.6</version>
  </dependency>
  
  ```

  

* Github地址: [Releases · mybatis/mybatis-3 (github.com)](https://github.com/mybatis/mybatis-3/releases)

### 1.2 持久化

数据持久化: 

* 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
* 内存: 易失性存储
* 数据库(jdbc), io文件持久化
* 生活: 冷藏, 罐头

为什么需要持久化:

* 有一些对象不能让他丢掉

* 内存很贵

### 1.3 持久层

Dao层, Service层, Controller层

* 完成持久化工作的代码块
* 层界限十分明显

### 1.4 为什么需要Mybatis

* 方便
* 传统的JDBC代码太复杂, 简化成框架, 自动化
* 帮助程序员将程序存入到数据库
* 不用Mybatis也可以. 更容易上手. 但是**技术没有高低之分**
* 优点:
  * 简单易学
  * 灵活
  * sql和代码分离, 提高维护性
  * 提供映射标签, 支持对象和数据库`orm`字段的关系映射
  * 提供对象关系映射标签, 支持对象关系组建维护
  * 提供xml标签, 支持动态编写sql
* 使用的人很多



## 2. 第一个Mybatis程序

思路: 搭建环境 --> 导入Mybatis --> 编写代码 --> 测试

### 2.1 搭建环境

* 搭建数据库

* 创建maven项目, 删除src目录

* 导入maven依赖

  ```xml
  <!--导入依赖-->
  <dependencies>
      <!--mysql驱动-->
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>5.1.47</version>
      </dependency>
      <!--mybatis-->
      <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>3.5.6</version>
      </dependency>
      <!--junit-->
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>test</scope>
      </dependency>
  </dependencies>
  ```

### 2.2 创建模块

* 编写mybatis核心配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <!--核心配置文件-->
  <configuration>
  
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                  <property name="username" value="root"/>
                  <property name="password" value="123456"/>
              </dataSource>
          </environment>
      </environments>
  </configuration>
  ```

* 编写mybatis工具类

  ```java
  package com.desitate.utils;
  
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  
  import java.io.IOException;
  import java.io.InputStream;
  
  // sqlSessionFactory构建sqlSession
  public class MybatisUtils {
  
      private static SqlSessionFactory sqlSessionFactory;
      // 使用mybatis第一步, 获取sqlSessionFactory对象
      static {
          try {
              String resource = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      
      // 既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
      // SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
      // 你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
      
      public static SqlSession getSqlSession() {
          return sqlSessionFactory.openSession();
      }
  }
  
  ```

  

### 2.3 编写代码

* 实体类

  ```java
  public class User {
      private int id;
      private String name;
      private String pwd;
  
      public User() {
      }
  
      public User(int id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      public int getId() {
          return id;
      }
  
      public String getName() {
          return name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  }
  
  ```

* Dao接口

  ```java
  public interface UserDao {
      List<User> getUserList();
  }
  ```

* 接口实现类, 由UserImpl转化为Mapper配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <!--namespace绑定对应的Dao/Mapper接口/-->
  <mapper namespace="com.desitate.dao.UserDao">
      
      
      <!--查询语句, id相当于重写方法的名字, 整体相当于一个实现类-->
      <select id="getUserList" resultType="com.desitate.pojo.User">
          select * from mybatis.user;
      </select>
  
  </mapper>
  ```

  在其中指定返回结果的类型, 虽然返回的是一个`List`, 但是我们的`resultType`应当填其中的泛型类型

### 2.4 测试

核心注册文件中注册mappers

可能遇到的问题:

* 配置文件没有注册
* 绑定接口错误
* 方法名不对
* 返回类型不对
* Maven导出资源



* junit 测试

  ```java
  public class UserDaoTest {
  
      @Test
      public void test() {
          // 第一步获取sqlSession对象
          SqlSession sqlSession = MybatisUtils.getSqlSession();
  
          // 第二步执行SQL
  
          // 方式一: getMapper
          UserDao mapper = sqlSession.getMapper(UserDao.class);
          List<User> userList = mapper.getUserList();
          for (User user: userList) {
              System.out.println(user);
          }
          sqlSession.close(); // 关闭sqlSession
          // 方式二:
  
      }
  
  }
  ```

  

> Tips:
>
> * `Type interface com.desitate.dao.UserDao is not known to the MapperRegistry.`
>
>   表示`Mapper.xml`没有在核心配置文件中注册
>
> * `The error may exist in com/desitate/dao/UserMapper.xml`
>
> * ```xml
>   <build>
>       <resources>
>           <resource>
>               <directory>src/main/resources</directory>
>               <includes>
>                   <include>**/*.properties</include>
>                   <include>**/*.xml</include>
>               </includes>
>               <filtering>true</filtering>
>           </resource>
>                       
>           <resource>
>               <directory>src/main/java</directory>
>               <includes>
>                   <include>**/*.properties</include>
>                   <include>**/*.xml</include>
>               </includes>
>               <filtering>true</filtering>
>           </resource>
>       </resources>
>   </build>
>                       
>   // 防止自己写的xml无法导入
>   ```
>
> * **注意一个很重要的一点`Mapper.xml`中不能有中文注释**

总结:

工具类和核心配置文件 --> 编写实体类 --> 编写接口 --> 编写接口实现的mapper.xml --> 测试

## 3. 增删改查CRUD

### 3.1 namespace

`namespace`中的包名要和Dao/mapper接口的包名一致

### 3.2 select

查询语句

* id: 就是对应的namespace中的方法名
* resultType: sql语句执行的返回值, 除了Class就是内部类型
* parameterType: 参数类型



1. 编写接口

   ```java
   User getUserById(int id);
   ```

2. 编写对应mapper中的sql语句

   ```xml
   <select id="getUserById" parameterType="int" resultType="com.desitate.pojo.User">
       select * from mybatis.user where id = #{id}
   </select>
   ```

   

3. 测试

   ```java
   @Test
   public void getUserById() {
       try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           User user = mapper.getUserById(2);
           System.out.println(user);
       }
   }
   ```

### 3.3 insert

```xml
<!--对象中的属性, 可以直接取出来-->
<insert id="addUser" parameterType="com.desitate.pojo.User">
    insert into mybatis.user (id, name, pwd) value (#{id}, #{name}, #{pwd})
</insert>
```

### 3.4 update

```xml
<update id="updateUser" parameterType="com.desitate.pojo.User">
    update mybatis.user
    set name = #{name}, pwd = #{pwd}
    where id = #{id};
</update>
```

### 3.5 delete

```xml
<delete id="deleteUser" parameterType="int">
    delete from mybatis.user where id = #{id};
</delete>
```

注意点: 

* 增删改需要提交事务

### 3.6 错误分析

* 标签不要匹配错误
* resource要采用路径的方式
* 程序配置文件必须符合规范
* 程序空指针异常, 没有注册到上级
* 输出的xml中存在中文乱码问题
* maven资源没有导出的问题

### 3.7 万能的Map

假设我们的实体类, 或者数据库中的表, 字段或者参数过多, 我们应当使用Map!

```java
// 万能的map
int addUser2(Map<String, Object> map);
```

```xml
<!--对象中的属性, 可以直接取出来, 传递map中的key-->
<insert id="addUser2" parameterType="map">
    insert into mybatis.user (id, name, pwd)
    values (#{userId}, #{userName}, #{password});
</insert>
```

```java
@Test
public void addUser2() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", 5);
        map.put("userName", "Hello");
        map.put("password", "233232");
        int res = mapper.addUser2(map);
        if (res > 0) {
            sqlSession.commit();
        }
        // 提交事务

    }
}
```

Map传递参数, 直接在sql中取出key即可 parameterType = map

对象传递参数, 直接在sql中取对象属性即可 Obj

只有一个基本类型参数的情况下, 可以直接在sql中取到 [省略填写]

多个参数用map或者注解

### 3.8 模糊查询

模糊查询怎么写:

1. Java代码执行的时候, 传递通配符`% %`

   ```java
   List<User> users = mapper.getUserLike("%张%");
   ```

2. 在sql拼接中使用通配符

   `select * from mybatis.user where name like "%"#{value}"%";`

## 4. 配置解析

### 4.1 核心配置文件

* mybatis-config.xml

* MyBatis的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息

  ```xml
  configuration（配置）
  properties（属性）
  settings（设置）
  typeAliases（类型别名）
  typeHandlers（类型处理器）
  objectFactory（对象工厂）
  plugins（插件）
  environments（环境配置）
  environment（环境变量）
  transactionManager（事务管理器）
  dataSource（数据源）
  databaseIdProvider（数据库厂商标识）
  mappers（映射器
  ```

### 4.2 环境变量(environments)

MyBatis 可以配置成适应多种环境, **不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

学会使用多套运行环境

Mybatis默认的事务管理是**JDBC**(还有MANAGED), 连接池:**POOLED**(还有UNPOOLED等)

池: 用完可以回收

### 4.3 属性(properties)

我们可以通过properties属性来实现引用配置文件

这些属性可以在外部进行配置，并可以进行动态替换。你既可以在典型的 Java 属性文件中配置这些属性，也可以在 properties 元素的子元素中设置[db.properties ]

编写一个配置文件

db.properties

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&useUnicode=true&characterEncoding=UTF-8
username=root
password=123456
```

在核心配置文件中进行引入的时候一定要注意标签的顺序

在核心配置文件中引入

```xml
<!--引入外部配置文件-->
<properties resource="db.properties">
    <property name="username" value="root"/>
    <property name="password" value="123456"/>
</properties>
```

* 可以直接引入外部文件
* 可以在其中增加一些属性
* 如果两个名字冲突, 优先使用外部文件

### 4.4 类型别名（typeAliases）

* 类型别名可为 Java 类型设置一个缩写名字
* 它仅用于 XML 配置，意在降低冗余的全限定类名书写

第一种方式:

```xml
<!--可以给实体类写别名-->
<typeAliases>
    <typeAlias type="com.desitate.pojo.User" alias="User"/>
</typeAliases>
```

第二种方式, 也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean

扫描实体类的包, 它的别名就为这个类的类名, 首字母小写(指定扫描包的名字)

```xml
<typeAliases>
    <package name="com.desitate.pojo"/>
</typeAliases>
```

在实体类比较少的时候使用第一种, 多的时候使用第二个, 但是第一种方式可以DIY, 第二种方式一般不可以, 但是可以通过注解进行使用

```java
@Alias
public class 
```

下面是常见的Java内建的类型别名, 一般直接小写即可, 基本类型前缀`_`

### 4.5 设置(setting)

```xml
logImpl
指定 MyBatis 所用日志的具体实现，未指定时将自动查找。	SLF4J | LOG4J | LOG4J2 | JDK_LOGGING | COMMONS_LOGGING | STDOUT_LOGGING | NO_LOGGING	未设置

cacheEnabled	
全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。	true | false	true

lazyLoadingEnabled	
延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 fetchType 属性来覆盖该项的开关状态。	true | false	false
```

### 4.6 其他配置(不重要)

插件:

* mybatis-generator-core
* mybatis-plus
* 通用mapper

### 4.7 映射器

MapperRegistery: 注册绑定我们的Mapper文件;

* 使用资源路径引用[推荐]

  ```xml
  <mappers>
      <mapper resource="com/desitate/dao/UserMapper.xml"/>
  </mappers>
  ```

* 使用映射器接口实现类的完全限定名

  ```xml
  <mappers>
       <mapper class="com.desitate.dao.UserMapper"/>
  </mappers>
  ```

  注意点: 

  * 接口和它的Mapper配置文件必须同名
  * 接口和它的Mapper配置文件必须在同一个包下

* 使用包扫描

  ```xml
  <mappers>
       <package name="com.desitate.dao"/>
  </mappers>
  ```

  注意点: 

  * 接口和它的Mapper配置文件必须同名
  * 接口和它的Mapper配置文件必须在同一个包下

总结:

* 将数据库配置文件外部引用
* 实体类别名
* 保证UserMapper接口和UserMapper.xml改为一致, 并且放在同一个包下



### 4.8 生命周期和作用域

不同作用域和生命周期类别是至关重要的，因为错误的使用会导致非常严重的**并发问题**。



**SqlSessionFactoryBuilder**:

* 一旦创建了 SqlSessionFactory，就不再需要它了
* 局部变量

**SqlSessionFactory**:

* 可以想象成数据库连接池
* 一旦被创建就应该在应用的运行期间一直存在, **没有任何理由丢弃它或重新创建另一个实例**
* 因此 SqlSessionFactory 的最佳作用域是应用作用域
* 最简单的就是使用**单例模式**或者静态单例模式

**SqlSession**:

* 连接到连接池的请求
* SqlSession 的实例不是线程安全的，因此是不能被共享的, 所以它的最佳的作用域是请求或方法作用域
* 用完之后赶紧关闭, 否则资源被占用

这里的每一个mapp而代表一个具体的业务.



## 5. ResultMap 解决属性名和字段名不一致的问题

### 5.1 问题

数据库中的字段

![database](./pic/s3.png)

新建一个项目, 拷贝之前的, 测试实体类不一致的情况

```java
public class User {
    private int id;
    private String name;
    private String password;
}
```

**测试出现问题**:

会出现为null的情况

**解决方法**:

* 起别名

  ```xml
  <select id="getUserById" parameterType="int" resultType="com.desitate.pojo.User">
      select id, name, pwd as password from mybatis.user where id = #{id}
  </select>
  ```

### 5.2 resultMap

结果集映射

```
id name pwd
id name password
```

```xml
<!--结果集映射-->
<resultMap id="UserMap" type="user">
    <!--column: 数据库中的字段, property: 实体类中的属性-->
    <result column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="pwd" property="password"/>
</resultMap>

<select id="getUserById" parameterType="int" resultMap="UserMap">
    select id, name, pwd as password from mybatis.user where id = #{id}
</select>
```



* `resultMap` 元素是 MyBatis 中最重要最强大的元素
* ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。
* 你会发现上面的例子没有一个需要显式配置 `ResultMap`，这就是 `ResultMap` 的优秀之处——你完全可以不用显式地配置它们

## 6. 日志

### 6.1 日志工厂

如果一个数据操作出现异常, 我们需要排错, 日志就是最好的帮手.

曾经: sout, debug

现在: 日志工厂

![log](./pic/s4.png)

* SLF4J
* LOG4J[掌握]
* LOG4J2
* JDK_LOGGING
* COMMONS_LOGGING
* STDOUT_LOGGING[掌握]
* NO_LOGGING

在Mybatis具体使用哪一个日志实现, 在设置中设定

**STDOUT_LOGGING标准日志输出**

在mybatis核心配置文件中, 配置我们的日志

```xml
<settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

![log](./pic/s5.png)

### 6.2 Log4j

什么是log4j:

* Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626), 文件, [GUI](https://baike.baidu.com/item/GUI)组件，甚至是套接口服务器、[NT](https://baike.baidu.com/item/NT/3443842)的事件记录器、[UNIX](https://baike.baidu.com/item/UNIX) [Syslog](https://baike.baidu.com/item/Syslog)[守护进程](https://baike.baidu.com/item/守护进程/966835)等

* 我们也可以控制每一条日志的输出格式

* 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程

* 这些可以通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。

  

1. 先导入包

   ```xml
   <!-- https://mvnrepository.com/artifact/log4j/log4j -->
   <dependency>
       <groupId>log4j</groupId>
       <artifactId>log4j</artifactId>
       <version>1.2.17</version>
   </dependency>
   
   ```

   

2. log4j.properties

   ```properties
   # 将等级为DEBUG的日志信息输出到console和file两个目的地, console和file的定义在下面的代码
   log4j.rootLogger = DEBUG, console, file
   
   ### 控制台输出相关设置 ###
   log4j.appender.console = org.apache.log4j.ConsoleAppender
   log4j.appender.stdout.Target = System.out
   log4j.appender.stdout.Threshold = DEBUG
   log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
   log4j.appender.stdout.layout.ConversionPattern = [%c]-%m%n
   
   
   ### 文件输出相关配置 ###
   log4j.appender.file = org.apache.log4j.RollingFileAppender
   log4j.appender.file.File = ./log/desitate.log
   log4j.appender.file.MaxFileSize = 10MB
   log4j.appender.file.Threshold = DEBUG
   log4j.appender.file.layout = org.apache.log4j.PatternLayout
   log4j.appender.file.layout.ConversionPattern = [%p][%d{yy-MM-dd}][%c]%m%n
   
   
   # 日志输出级别
   log4j.logger.org.mybatis=DEBUG
   log4j.logger.java.sql=DEBUG
   log4j.logger.java.sql.Statement=DEBUG
   log4j.logger.java.sql.ResultSet=DEBUG
   log4j.logger.java.sql.PreparedStatement=DEBUG
   ```

3. 配置log4j为日志的实现

   ```xml
   <setting name="logImpl" value="LOG4J"/>
   ```

4. log4j的使用, 直接测试之前的查询

   ![log4j](./pic/s6.png)

**简单使用**

1. 在要使用log4j的类中导入相应的包

2. 日志对象, 参数为当前类的class

   ```java
   static Logger logger = Logger.getLogger(UserDaoTest.class);
   ```

3. 日志级别

   ```java
   logger.info("info: 进入了testLog4j");
   logger.debug("debug: 进入了testlog4j");
   logger.error("error: 进入了testlog4j");
   ```



## 7. 分页

**为什么要分页?**

* 减少数据处理量



### 7.1 使用`Limit`分页

```sql
select * from user limit startIndex, pageSize;
select * from user limit 3; #[ 0, 3)
```

使用mybatis实现分页, 核心SQL

1. 接口

   ```java
   // 分页
   List<User> getUserByLimit(Map<String, Integer> map);
   ```

2. Mapper.xml

   ```xml
   <!--分页-->
   <select id="getUserByLimit" parameterType="map" resultType="User">
       select * from mybatis.user limit #{startIndex}, #{pageSize}
   </select>
   ```

3. 测试

   ```java
   @Test
   public void getUserByLimit() {
       try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           HashMap<String, Integer> map = new HashMap<>();
           map.put("startIndex", 1);
           map.put("pageSize", 2);
           List<User> userList = mapper.getUserByLimit(map);
           for (User user: userList) {
               System.out.println(user);
           }
       }
   }
   ```

   

### 7.2 RowBounds分页

不再使用SQL实现分页

1. 接口

   ```java
   List<User> getUserByRowBounds();
   ```

2. Mapper.xml

   ```xml
   <!--分页-->
   <select id="getUserByRowBounds" resultType="User">
       select * from mybatis.user
   </select>
   ```

3. 测试

   ```java
   @Test
   public void getUserListByRowBounds() {
       try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           // RowBounds实现
           RowBounds rowBounds = new RowBounds(1, 2);
   
           // 通过Java代码层面实现分页
           List<User> userList = sqlSession.selectList("com.desitate.dao.UserMapper.getUserByRowBounds", null, rowBounds);
   
           for (User user: userList) {
               System.out.println(user);
           }
       }
   }
   ```

### 7.3 分页插件

![page](./pic/s7.png)

了解即可

## 8. 使用注解开发

### 8.1 面向接口编程

1. 注解在接口上实现

   ```java
   @Select("select * from user")
   public List<User> getUsers();
   ```

2. 需要在核心配置文件中绑定接口

   ```xml
   <!--绑定接口-->
   <mappers>
       <mapper class="com.desitate.dao.UserMapper"/>
   </mappers>
   ```

3. 测试(底层动态代理)

   ```java
   @Test
   public void test() {
       try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
   
           // 底层主要应用反射
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
   
           List<User> users = mapper.getUsers();
           System.out.println(users);
       }
   }
   ```

Mybatis详细执行流程

![exe](./pic/s8.png)

### 8.3 注解CRUD

我么可以在工具类创建的时候实现自动提交事务

```java
public static SqlSession getSqlSession() {
    return sqlSessionFactory.openSession(true);
}
```

编写接口, 增加注解

```java
// 方法存在多个参数, 所有的参数前面必须要加上@Param注解
@Select("select * from user where id = #{id}")
public User getUserById(@Param("id") int id);

@Insert("insert into user(id, name, pwd) values(#{id}, #{name}, #{password})")
int addUser(User user);

@Update("update user set name=#{name}, pwd=#{password} where id=#{id}")
int updateUser(User user);

@Delete("delete from user where id=#{uid}")
int deleteUser(@Param("uid") int id);
```

测试类

```java
我们必须要将接口文件绑定到配置文件中
```

**关于`@Param`**

* 基本类型参数或者Stirng类型, 需要加上
* 引用类型不需要
* 如果只有一个基本类型的话, 基本可以忽略, 但是建议大家加上
* 我们在SQL中引用的就是这里的@Param中的设定的属性名

`#{}`可以防止sql注入



## 9. Lombok

* java库
* 插件
* 构建工具
* 只需要加注解免去getter和setter

```xml
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger, @CustomLog
@Data
@Builder
@SuperBuilder
@Singular
@Delegate
@Value
@Accessors
@Wither
@With
@SneakyThrows
@val
@var
```

使用步骤:

* 在IDE中安装插件

* 在项目中导入lombok的jar包

  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.12</version>
  </dependency>
  ```

  

* `@Data`: 无参构造, getter, setter, hashCode, toString, euqals
* `@AllArgsConstructor`, `@NoArgsConstructor`有参和无参构造器
* 按照名字使用即可, 可在字段上添加



## 10. 多对一处理

* 多个学生对应一个老师
* 对于学生这边而言, 关联: 多个学生关联一个老师
* 对于老师而言, 一对多, 集合, 一个老师有很多学生

![1](./pic/s9.png)

### 10.1 测试环境搭建

1. 导入lombok
2. 新建实体类
3. 建立Mapper接口
4. 建立Mapper.xml文件
5. 在核心配置文件中绑定我们的Mapper接口或者文件[方式多]
6. 测试查询是否能够成功

### 10.2 按照查询嵌套处理

也就是子查询

```xml
<!--
思路:
    1. 查询所有的学生信息
    2. 根据查询出来学生的tid, 寻找对应的老师, 子查询
-->
<select id="getStudent" resultMap="StudentTeacher">
    select * from student
</select>

<resultMap id="StudentTeacher" type="Student">
    <!--复杂属性需要单独处理
        对象使用association
        集合使用collection-->
    <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
</resultMap>

<select id="getTeacher" resultType="Teacher">
    select * from teacher where id = #{id}
</select>
```

### 10.3 按照结果嵌套处理

联表查询

```xml
<!--按照结果嵌套处理-->
<select id="getStudent" resultMap="StudentTeacher">
    select s.id sid, s.name sname, t.name tname, t.id tid
    from student s, teacher t
    where s.tid = t.id;
</select>

<resultMap id="StudentTeacher" type="Student">
    <result property="id" column="sid"/>
    <result property="name" column="sname"/>
    <association property="teacher" javaType="Teacher">
        <result property="name" column="tname"/>
        <result property="id" column="tid"/>
    </association>
</resultMap>
```

回顾MySql多对一方式:

* 子查询
* 联表查询

## 11. 一对多处理

比如一个老师拥有多个学生

对于老师而言, 就是一对多的关系

### 11.1 环境搭建, 和刚才一样

```java
@Data
public class Teacher {
    private int id;
    private String name;
    // 一个老师拥有多个学生
    private List<Student> students;
}
```

```java
@Data
public class Student {
    private int id;
    private String name;
    int tid;
}
```

```java
public interface TeacherMapper {
    // 获取老师
    List<Teacher> getTeachers();

    // 获取指定老师下所有的学生及老师信息

    Teacher getTeacher(@Param("tid") int id);


    Teacher getTea(@Param("tid") int id);
}
```



### 11.2 按照结果嵌套处理

```xml
<select id="getTeachers" resultType="teacher">
    select * from mybatis.teacher
</select>

<!--按照结果嵌套-->
<select id="getTeacher" resultMap="TeacherStudent">
    select s.id sid, s.name sname, t.name tname, t.id tid
    from student s, teacher t
    where s.tid = t.id and t.id = #{tid}
</select>

<resultMap id="TeacherStudent" type="teacher">
    <result property="id" column="tid"/>
    <result property="name" column="tname"/>
    <!--集合用collection
        javaType=""指定属性的类型
        集合中的泛型使用ofType获取-->
    <collection property="students" ofType="student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <result property="tid" column="tid"/>
    </collection>
</resultMap>
```



### 11.3 按照查询嵌套处理

```xml
<select id="getTea" resultMap="TeaStudent">
    select * from mybatis.teacher where id = #{tid}
</select>

<resultMap id="TeaStudent" type="teacher">
    <collection property="students" javaType="ArrayList" ofType="student" select="getStudentByTeacherId" column="id"/>
</resultMap>

<select id="getStudentByTeacherId" resultType="student">
    select * from student where tid = #{tid}
</select>
```



小结:

1. 关联-association[多对一]
2. 集合-collection [一对多]
3. javaType & ofType
   * javaType用来指定实体类中属性的类型
   * ofType用来指定映射到集合中的pojo类型, 泛型约束类型

注意点:

* 保证SQL的可读性, 尽量保证通俗易懂
* 注意一对多和多对一, 属性名和字段问题
* 如果问题不好排查, 建议使用log4j



面试高频:

* MySql引擎
* InnoDB底层原理
* 索引
* 索引优化

## 12. 动态SQL

**什么是动态SQL: 动态SQL就是指根据不同的条件生成不同的SQL语句**

如果你之前用过 JSTL 或任何基于类 XML 语言的文本处理器，你对动态 SQL 元素可能会感觉似曾相识。在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。

* if
* choose (when, otherwise)
* trim (where, set)
* foreach

### 12.1 搭建环境

```sql
CREATE TABLE `mybatis`.`blog`  (
  `id` varchar(50) NOT NULL COMMENT '博客id',
  `title` varchar(100) NOT NULL COMMENT '博客标题',
  `author` varchar(30) NOT NULL COMMENT '博客作者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `views` int(30) NOT NULL COMMENT '浏览量',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8;
```

### 12.2 创建基础工程

1. 导包

2. 编写配置文件

3. 编写实体类

   ```java
   @Data
   public class Blog {
       private int id;
       private String title;
       private String author;
       private Date currentTime;
       private int views;
   }
   ```

4. 编写实体类对应的Mapper接口和Mapper.xml



### 12.3 IF

```java
// 查询博客
List<Blog> queryBlogIf(Map map);
```

```xml
<select id="queryBlogIf" parameterType="map" resultType="blog">
    select * from mybatis.blog where 1 = 1
    <if test="title != null">
        and title = #{title}
    </if>
    <if test="author != null">
        and author = #{author}
    </if>
</select>
```

### 12.4 choose、when、otherwise

```xml
<select id="queryBlogIf" parameterType="map" resultType="blog">
    select * from mybatis.blog
    <where>
        <if test="title != null">
            title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </where>

</select>
```

`<where>`标签可以防止`and`或者`or`的退化, 没有子句的话消除`where`

```xml
<select id="queryBlogChoose" parameterType="map" resultType="blog">
    select * from mybatis.blog
    <where>
        <choose>

            <when test="title != null">
                title = #{title}
            </when>

            <when test="author != null">
                and author = #{author}
            </when>

            <otherwise>
                and views = #{views}
            </otherwise>
        </choose>
    </where>
</select>
```

只会执行其中的一个

### 12.5 trim、where、set

```xml
<update id="updateBlog" parameterType="map">
    update mybatis.blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>

        <if test="author != null">
            author = #{author}
        </if>
    </set>

    where id = #{id}
</update>
```

```xml
<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```

```xml
<trim prefix="WHERE" prefixOverrides="AND |OR ">
  ...
</trim>
```

**所谓的动态SQL, 本质还是SQL语句, 只是我们还可以再SQL层面, 去执行一个逻辑代码**

### 12.6 foreach

```sql
select * from user when 1 = 2 and (id = 1 or id = 2 or id = 3);

```



```xml
<select id="selectPostIn" resultType="domain.blog.Post">
  SELECT *
  FROM POST P
  WHERE ID in
  <foreach item="item" index="index" collection="list"
      open="(" separator="," close=")">
        #{item}
  </foreach>
</select>
```



Mapper.xml

```xml
<!-- select * from mybatis.blog where 1 = 1 and (id = 1 or id = 2 or id = 3)
    我们现在传递一个万能的map, 这个map可以存在一个集合
-->
<select id="queryBlogForeach" parameterType="map" resultType="blog">

    select * from mybatis.blog
    <where>
        <foreach collection="ids" item="id" open="(" close=")" separator="or">
            id = #{id}
        </foreach>
    </where>
</select>
```

```java
// 查询第1-2-3号记录的博客
List<Blog> queryBlogForeach(Map map);
```

```java
// Test
@Test
public void queryBlogForeach() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap hashMap = new HashMap();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(44);
        ids.add(5);
        hashMap.put("ids", ids);
        List<Blog> blogs = mapper.queryBlogForeach(hashMap);
        System.out.println(blogs);
    }
}
```





### 12.7 SQL片段

有的时候, 我们可能将一些功能的部分抽取出来, 方便使用

1. 使用SQL标签抽取公共的部分

   ```xml
   <sql id="if-title-author">
       <if test="title != null">
           title = #{title}
       </if>
       <if test="author != null">
           and author = #{author}
       </if>
   </sql>
   ```

2. 在需要的使用的部分使用`include`标签引用即可

   ```xml
   <select id="queryBlogIf" parameterType="map" resultType="blog">
       select * from mybatis.blog
       <where>
           <include refid="if-title-author"/>
       </where>
   
   </select>
   ```

注意事项:

* 最好基于单表来定义SQL片段
* 片段中最好不要存在`where`标签



**动态SQL就是再拼接SQL语句, 我们只要保证SQL的正确性, 按照SQL格式, 去排列组合就可以了**

建议:

* 现在MySql中写出完整的SQL, 再对应的修改为动态SQL

## 13. 缓存

### 13.1 什么是缓存

1. 什么是缓存
   * 存在内存中的临时数据
   * 将用户经常查询的数据放在内存中, 用户去查询数据就不用从磁盘上查询, 从缓存中查询, 从而提高查询效率, 解决了高并发系统的性能问题
2. 为什么使用缓存
   * 减少和数据库的交互次数, 减少系统开销, 提高系统效率
3. 什么样的数据能够使用缓存?
   * 经常查询且不经常改变的数据



### 13.2 Mybatis缓存

MyBatis 内置了一个强大的事务性查询缓存机制，它可以非常方便地配置和定制.

Mybatis系统中默认定义了两级缓存: **一级缓存**和**二级缓存**:

* 默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存(SqlSession级别的缓存)
* 要启用全局的二级缓存需要手动配置开启, 基于namespace级别的缓存
* 为了提高扩展性, Mybatis定义了缓存接口Cache, 我们可以通过实现Cache接口来自定义二级缓存



### 13.3 一级缓存

* 一级缓存也叫本地缓存:
  * 与数据库同一次会话期间查询到的数据会放在本地缓存中
  * 以后如果需要获取相同的数据, 直接从缓存中拿, 没必要去查询数据库



测试步骤:

1. 开启日志

   ```xml
   <settings>
       <!--标准日志工厂-->
       <!--是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn-->
       <setting name="logImpl" value="STDOUT_LOGGING"/>
   </settings>
   ```

2. 测试在一个Session中查询两次相同的记录

3. 查看日志输出

   Sql只生成了一次

   ```shell
   Opening JDBC Connection
   Created connection 1750498848.
   ==>  Preparing: select * from user where id = ?
   ==> Parameters: 1(Integer)
   <==    Columns: id, name, pwd
   <==        Row: 1, FisherKK, 123456
   <==      Total: 1
   User(id=1, name=FisherKK, pwd=123456)
   ==================
   User(id=1, name=FisherKK, pwd=123456)
   true
   Closing JDBC Connection [com.mysql.jdbc.JDBC4Connection@68567e20]
   Returned connection 1750498848 to pool.
   
   ```

缓存失效的原因:

1. 增删改操作可能会改变原来的数据, 所以必定会刷新缓存
2. 查询不同的东西
3. 查询不同的Mapper
4. 手动清理缓存

```java
public void test() {
    try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);

        System.out.println("==================");
        sqlSession.clearCache(); // 手动清理缓存
//            mapper.updateUer(new User(3, "HHH", "PPPP"));
        System.out.println("==================");


        User user1 = mapper.queryUserById(1);
        System.out.println(user1);

        System.out.println(user == user1);
    }
}
```

小结: 一级缓存是默认开启的, 只在一次SqlSession中有效, 也就是拿到连接和关闭连接这一个区间, 一级缓存其实就是一个map



### 13.4 二级缓存

* 二级缓存也叫全局缓存, 一级缓存作用域太低了, 所以诞生了二级缓存
* 基于namespace级别的缓存, 一个名称空间, 对应一个二级缓存
* 工作机制
  * 一个会话查询一条数据, 这个数据会被放在当前会话的一级缓存中
  * **如果当前会话关闭了, 这个会话对应的一级缓存就没了; 但是我们想要的是, 会话关闭了, 一级缓存中的数据被保存到二级缓存中**
  * 新的会话查询信息, 就可以从二级缓存中获取内容
  * 不同的mapper查出的数据会放在自己对应的缓存中map



步骤:

1. 开启全局缓存

   ```xml
   <!--显示开启全局缓存-->
   <setting name="cacheEnabled" value="true"/>
   ```

2. 在要使用二级缓存的Mapper中开启

   ```xml
   <!--在当前的Mapper.xml中开启二级缓存-->
   <cache eviction="FIFO"
           flushInterval="60000"
           size="512"
           readOnly="true"/>
   ```

   也可以自定义参数

3. 测试

   ```java
   @Test
   public void test() {
       SqlSession sqlSession = MybatisUtils.getSqlSession();
       SqlSession sqlSession1 = MybatisUtils.getSqlSession();
   
       UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
       User user = mapper.queryUserById(1);
       System.out.println(user);
       sqlSession.close();
   
       User user1 = mapper1.queryUserById(1);
       System.out.println(user1);
   
       System.out.println(user == user1);
   
       sqlSession1.close();
   
   }
   ```

   只能针对一级缓存死掉之后生效

   问题:

   1. 我们需要将实体类序列化, 否则就会报错

      ```java
      java.io.NotSerializableException
      ```

      ```java
      @Data
      @AllArgsConstructor
      public class User implements Serializable {
          private int id;
          private String name;
          private String pwd;
      }
      ```

   小结:

   * 只要开启了二级缓存, 在同一个Mapper下就有效
   * 所有的数据都会放在一级缓存中
   * 只有当会话提交, 或者关闭的时候, 才会提交到二级缓存中



### 13.5 缓存原理

1. 先看二级缓存
2. 再看一级缓存
3. 查询数据库



### 13.6 自定义缓存-ehcache

Ehcache是一种广泛使用的开源Java分布式缓存, 主要面向通用缓存

要在程序使用, 先要导包

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.1.0</version>
</dependency>
```

Redis数据库来做缓存, K-V键值对

在Mapper指定使用我们的缓存

```xml
<!--Ehcache的cache-->
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```



