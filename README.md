# Java-For-Beginner
[toc]

## Java明天

* `Java` 1995年诞生, 出生`Sun`公司

* 一次编写到处运行, 兼容不同操作系统和CPU

  1. 解释型语言, `Python`

     吃火锅类型, 边解释边运行, 比较慢

  2. 编译型语言, `c`

     炒菜类型

  3. 半编译语言, `Java`

     干锅型, 编译一部分, 运行又是一部分

* `Java`是一种面向对象语言

  1. 将源代码编译成字节码`bytecode`, 平台无关
  2. 依赖虚拟机进行解释执行
  3. 一次编写, 到处运行

* 一些术语

  1. `Java specification`规定了`Java`语法和功能
  2. `JCP`, 掌管`Java`规范
  3. `JDK`实现规范, 又称为工具包
     * Sun JDK(默认)
     * OpenJDK
     * IBM JDK

* 版本问题

  1. `JDK1.2`-`JDK1.4`称为`Java 2`

     > J2EE: Java 2 企业版本
     >
     > J2SE: 桌面程序
     >
     > J2ME 移动设备
     >
     > 1. Java SE
     > 2. Java EE
     > 3. Java ME
  
   
  
  2. `JDK1.5`又被称为`Java5` 停止更新, 不再发布补丁包
  3. `JDK1.8`又被称为`Java8`, 为互联网企业常用版本
  4. 一般稳定版为**8**和**11**, 但是**8U202**之前才是免费

* 编译过程

  * `.java`文件
  * (添加`javac.exe`), 形成` .class`文件字节码(平台无关)
  * (添加`java.exe`), 进行运行(平台相关)



## Java今天

### 学习路径

* 内核
* `Eclipes` 日食

### 三大分支

* SE, PC级别应用开发
* EE, 面向企业级开发
* ME, 面向嵌入式设备



## Java基础

#### 组成

* `main`函数的`PSVM`
*  `public`类必须要和文件同名, 一个文件仅一个`public`类
* 由一个个类组成, 所有成员都需要放在类里面
* 由**属性**和**方法**构成
* 一个`class`最多只有一个`main`函数, 没有`main`就无法主动执行, 但可以被别人调用
* 严格来说`main`函数不属于这个类所拥有, 它知识寄居其中, 是`Java`程序的入口
* **类**是`Java`最小的独立单元
* `main`是`java`程序入口

#### 类型

* `boolean`默认初始化为`false`
* `char`是一个单一的16位`unicode`, 最小值位`\u0000`, 最大值位`\uffff`

### 函数

* 函数必须放在类的范围
* `public/static` 返回值 `(int/void)`, 函数名(`形参列表`)
* 我们建议方法为`public`
* 尽量一个`class`一个文件
* 重载函数, 名字相同但是函数特征标不同`overload`
* 重写(覆写)函数, 子类重写父类同名函数`override`



## 面向对象

* 对象 = 属性 + 方法(成员变量/字段 + 成员方法)

* 对象规范 = 属性定义 + 方法定义 (行为准则, 菜谱)
* 对象是一个变量, 类是类型, 从对象中提取共性, 类规定了对象应该有的属性内容和方法
* 子类可以继承父类所有内容



## 类和对象

* `A obj = new A()`
* 内存空间, 不同的对象在内存中有不同的存放地址, 因此没有两个对象是完全一样.
* `obj`在`Java`中管理内存的句柄, 实际是`引用`, 或者说是`指针`
* 对象赋值时引用赋值, 基本类型时值拷贝
* 类成员变量自动初始化, 局部变量使用前需要自行初始化
* 一般`new`出一个对象, 基本类型默认值为`0`, 但是仍然有一些对象使用**克隆和反射生成.**
* 构造函数必须和类的名字一样, 灭有返回值
* `Java`没有析构函数, 清除函数的过程
* 每个变量都有生命周期
* `Java`内存自动回收机制, 回收效率依赖于`GC`垃圾回收

### 必考哦

* `Java`都必须有构造函数
* 构造函数名称必须和类名一样, 且没有返回值 
* 没有显示构造Java自动产生无参数构造函数, 如果有了显式, 编译器不会自动生成
* 每个子类的构造函数第一句话都默认调用父类无参数构造函数`super()` , 如果显式调用编译器不会再次越俎代庖, `super`语句必须放在第一句话, 因此只能调用一次
* 一个类可以有多个构造函数



## 信息隐藏

类的成员属性是私有的`private`

类的方法是`public`共有的, `getter`和`setter`方法

**`this`指针指向当前类的变量, 还可以代替本类的构造函数和对象本身.  同样只能出现一次, 并且在第一行**



## 继承

* 父类, 基类, 超类

* 子类, 派生类(`derived class`)

* `extends`表示继承

* 类名要大写

* 子类会继承所有父类(祖先)的所有的属性和方法， 但是无法访问私有成员

* `Object`单根继承原则, 只能继承一个父亲, 默认继承`Object`

* `Object`默认有`clone`, `hashCode`, `toString`等方法

* 每个子类的构造函数的第一句都默认调用父类的无参数构造函数`super`, 如果想要自己加的话, 编译器不会自动添加, 但是需要将`super`放在第一句, 并且不能写多个`super`, **必须在第一句话!!!**

  > 注意:
  >
  > 子类构造函数一定要调用父类的构造函数, 如果无法调用就会导致报错, 调用的方式有两种:
  >
  > * 默认调用父类的无参数构造函数`super`, 即使你不写, 编译器也会自动加, 除非你显式地调用了`super`函数
  > * 倘若父类没有无参数的构造函数, 如果采用默认方式就会报错, 因此需要进行显式的调用, 但是你也可以通过`this`调用其它构造函数, 从而实现`super`
  >
  > 总而言之, 子类不管显式还是隐式, 都要调用父类的构造函数(或者采用`this`的方式), 从而进入基础状态, 如果不能就会直接编译期间出错

  

## 抽象类

* 类: 属性 + 方法

* 一个完整的类: 所有的方法都要实现(方法体)

* 一个完整类才可以被实例化

* 一个类有方法没有实现被称为抽象类`abstract`

  类要进行`abstract`声明, 没有实现的函数需要`abstract`声明

  满足契约制设计

* 抽象类也是类, 子类一定要实现父类的所有抽象方法, 如果没有完全实现, 也必须声明为抽象类



## 接口

如果类中的所有方法都没有实现, 那么这个类为`interface`

接口不算类， 或者说是特殊的类

类只能继承一个类, 但可以实现多个接口, 继承和实现可以同时使用

接口可以继承继承多个接口, 没有实现的方法将会叠加

类实现接口, 就必须实现所有未实现的方法, 倘若没有, 那么会成为抽象类

接口里面可以定义变量, 一般是常量

类实现接口, 就必须实现所有未实现的方法, 如果没有完全实现, 那么只能成为抽象类

**考:**

* 抽象类和接口相同点: 两者都不能被实例化, 不能`new`
* 不同点:
  1. 抽象类`abstract`, 接口`interface`
  2. **抽象类可以有部分方法实现, 接口所有方法都不能有实现(以前)**
  3. 一个类只能继承一个抽象类, 实现多个接口
  4. 接口可以继承多个接口
  5. 抽象类有构造函数, 接口没有
  6. 抽象类可以有`main`, 也能运行, 接口没有`main`函数
  7. 抽象类方法可以有`private/public`, 接口方法都是`public`



## 类转型

* 变量可以转化
* 子类可以转化为父类(自动), 父类无法转化为子类(除非强转, 父类从子类转回来)
* 子类可以自动向上转型为父类 
* 子类重新定义一个和父类一样的方法, 这就是重写, 子类方法的优先级高于父类
* **动态绑定**默认都是动态绑定
* 多态的作用以统一的接口操控某一类中不同对象的动态行为, 可以进行对象之间的解耦
* 可以基于接口的多态
* **注意陷阱， 匿名对象**



## Static

类共有成员  

* 静态对象

* 静态方法

* 静态块, 只执行一次

* 实例初始化块, 生成一次执行一次

* 变量内存中只有一个`copy`, 方法可以不通过对象调用

* 只依赖类存在， 不依赖对象实例， 所有对象共享栈空间

* 静态方法只能使用静态变量和方法 , 不需要对象也可以引用

* 静态块

  只在类加载的时候执行一次

  执行顺序: 静态初始化, 实例初始化, 构造器初始化

## 单例模式 (考试不考)

`Singleton`, 限定某一个在整个程序允许的对象仅允许存在一个

* 采用`static`来共享对象实例
* 采用`private`构造函数, 防止外界`new`

```java

class Singleton {
    private static Singleton obj = new Singleton();
    private Singleton() {
        
    }
    
    public static Singleton getInstance() {
        return obj;
    }
}
```



### 设计模式(要知道什么是设计模式)

* 解决方案
* 在软件开发过程中, 经过验证, 用于解决在特定环境下, 重复出现的特定问题的解决方案  



## `final`

修饰:

* 类, 无法继承
* 方法, 子类无法更改
* 字段, 
* 变量
  1. 基本类型是无法修改
  2. 对象/对象, 可以修改对象中的内容, 但是无法移动它, 也就是无法挪窝



## 常量池

一种不会修改的变量

* 不能修改final
* 只读,只要一份, static
* 为了访问`public`
* `public static final`, 大写采用下划线连接
* 接口中变量默认常量

##### 常量池

* `Java`为很多基本类型的包装类/字符串建立常量池
* 常量池相同的值存储一份, 公用
* 包装类, **小数没有缓存**
* 数字只缓存了-128-127;
* 字面量赋值, 放在栈存储区, 会被共享, 因此会指向同一片内存空间
* `new`放在堆存储区, 不被共享
* 创建方式
  1. 常量式, 字面量赋值创建, 放在栈内存, 被常量化
  2. `new`对象创建, 放在堆内存, 不会常量化
* 如果不涉及变量, 字符串那么基本都是存在常量池中
* **`==`对于对象来讲比较的是地址**, `==`值的话比较具体值
* 如果其中涉及到变量, 会重新`new`, 只要涉及动态结果都不一样
* `Java`为常量字符串都建立常量池缓存机制
* 基本类型的包装类比较, 包装类自动拆包; `+`也会自动拆箱
* **包装类型加减会导致拆箱**

## 不可变对象

八个基本类型的包装类对象和Stirng以及大整数(`Immutable Object`), 内在成员变量无法被修改

* immutable不可变, 有改变请`clone/new`一个对象

* 所有的属性都是`final`和`private`

* 不提供`setter`

* 类是`final`的, 或者所有的方法都是`final`

* 类中包含`mutable`对象, 那么返回拷贝需要深度`clone`

* 不可变对象优点:

  1. 只读
  2. 并发读
  3. 可以重复使用

  缺点:

  1. 制造垃圾
  2. 浪费空间

* 最常用的不可变是`String`

  1. 加法会新产生一个`String`
  2. 建议使用`StringBuffer`和`StringBuilder`, 前一个同步线程安全, 后一个不同步线程不安全



## `package`

和`C++`命名空间很像

`Java`类文件第一句话给出包名, 包上的点代表目录, 包名和目录名一致

全称要加上类名

包名尽量唯一, 域名逆序作为包名

具体类要带上包名

可采用`import`关键字引入类, 如果在同一个`package`下可以省略

`import`必须要放在`package`之后, 类定义之前, 依赖也要说

编译的时候要添加全目录， 运行的时候使用完整名字



## `jar`

`jar`文件, 一种扩展名为`jar`的文件, 是`Java`所特有的一种文件格式, 用于可执行程序文件的传播.

`jar`文件实际上是一组`class`文件的压缩包

`jar`文件可以包括多个`class`, 比多层目录更加简洁实用

`jar`文件只包括`class`, 而没有包含`java`文件



## `package`和`import`命令行

包名: 类存放的目录

类的完整名字: 包名 + 类名

包具体在什么位置不重要, 编译和允许时候通过`classpath`指定

`javac`编译, 指定依赖和绝对路径

`java`运行, 指定依赖和完整类名

路径内有空格需要将整体加`""`





## `classpath`

包的位置通过编译命令解决, 编译采用绝对路径

运行的时候采用` java -classpath .;` 目录 完成类名

## 访问权限

* private, 仅自己类
* default, 默认, 同一个包内可以访问
* protected, 仅子类可用和包访问权限
* public, 公开

![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\11.PNG)

## `Java`常用类

* `Java8`很多包, 主要是`java8`, `javax`, `org`三个包
* 文档原本书程序中的注释, 采用`JavaDoc`技术, 将注释抽取出来, 组织成`HTML`表现`API`
*   `Java`类库
  1. 以`java`开始 的包是`Java`核心包
  2. `javax`是`java`扩展包

### 数字相关类

`Java`数字类

* 整数, `Short`, `Int`, `Long`
* 浮点数
* 大整数, 大浮点数
* 随机数
* 工具类
* `java.math`包
* 整数类型溢出会直接编译出错
* 整型默认`int`, 浮点数默认`double`
* 随机数的常用操作
  1. `nextInt()`返回`int`
  2. `nextInt(int a)`返回`[0,a)`之间的随机`int`
  3. `nextDouble()`返回一个`[0, 1.0)`之间的`double`
  4. `ints`方法批量返回随机数组
* `Math.random()`返回一个`[0.0, 1.0]`之间的`double`
* `java.lang.Math`
  1. `abs`
  2. `log`
  3. `max`
  4. `pow`
  5. `round`
  6. `floor`
  7. `ceil`

### 字符串相关类

* `String`, 不可变对象, 加减操作性能较差

  * `charAt`
  * `indexOf`
  * `concat`, 相当于会生成一个新的对象
  * `endWith`
  * `equal`实际值是否相等
  * `trim`取除前后的空格, 返回一个新对象
  * `split`, 将字符串按照指定字符分割
  * `replaceAll`, 第一个参数是正则表达式

* 可变字符串

  1. `StringBuffer`, 字符串加减, 同步, 性能好
  2. `StringBuilder`, 字符串加减, 不同步, 性能更好

  方法一样, 区别在同步

  * 初始化的时候可以赋予初始值

  * `append`
  * `length`表示字符串实际长度
  * `capacity`表示实际存储空间
  * `trimTOSize()`取出空隙, 将字符串存储压缩到实际大小
  * 如果有大量`append`, 事先预估大小, 在调用相应的构造函数
  * 一旦`length`大于`capacity`, `capacity`便在前一次的基础上加1后翻倍

### 时间相关类

* `java.sql.Date`和数据库对应的时间类

* `Calendar`是目前程序最常用的, 但是是抽象类

  常用操作:

  **注意`calendar`的月是0-11**

  ```java
  Calendar gc = Calendar.getInstance();
  Calendar gc = new GregorianCalenda();
  get(FILED); // 获取事件中每个属性的值, 注意月份为0-11
  getTime(); // 返回对应的Data对象
  getTimeInMillis(); // 返回子毫秒数
  set(Field); // 设置时间段
  add(field, amount); // 指定字段增加减少时间
  roll(field, amount); // 指定字段增加/减少时间, 但是不影响上一级, 支队指定的域做改变, 其余的域并不变
  ```

* `Java8`推出新的时间`API`, `java.time`, 特点: 多线程不变性, 遵循设计模式

  ```java
  java.time; // 新的日期/时间API基础包
  java.time.chrono; // 非ISO的日历系统定义了广泛的API
  java.time.format; // 格式化和解析日期时间对象的类
  java.time.temporal; // 特定时态对象
  java.time.zone; // 支持不同时区以及相关规则的类
  ```

* `java.time`包主要包括

  * `LocalDate`日期类
  * `LocalTime`时间类
  * `LocalDateTime` 上面两个之和
  * `Instant`时间戳

```java
LocalDate locaDate = LocalDate.now(); // 返回当前时间
locaDate = LocalDate.of(int year, Month.Enum, day);
localDate = LocalDate.ofYearDay(int year, int day);
localDate.isLeapYear(); // 是否是闰年
localDate.plus(); // 事件运算等操作
LocalTime time = LocalTime.now(); // 当前时间 用法基本同上

Period period = today.util(lastDayOfYear); // 计算时间间隔
Instant timestap = Instance.now(); // 定义事件戳
3 3.14 3.141 3 
```



### 格式化相关类

* `NumberFormat`
* `MessageFormat`
* `DateFormat`
* `DateTimeFormatter`

```java
DecimalFormat(); // #表示可以为空, 0表示0填充, 整数有几位填几位
MessageFormat.format(); // 采用"{}" 传入Object[]
DateTimeFormatter.ofPattern(); // yyyy表示年, MM表示月, dd表示日
```



## `Java`数据结构

### 数组

``` 
T[] a; // 声明数组对象引用
a = new T[]; // 绑定引用, 或者采用直接初始化
```



### JCF

  ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\12.png)

* 集合接口为`Collection`, 有`add`, `contain`, `remove`, `size`方法, `iterator`迭代器
* `Iterator`迭代器, `hasNext`, `next`, `remove`



`JCF`主要数据结构:

* 列表
* 集合
* 映射

`JCF`主要算法类:

* `Array`对数组查找排序
* `Collection`, `Collection`及其子类查找排序



### `List`

* `ArrayList`非同步
* `LinkedList`非同步
* `vector`同步

`LinkedList`双向链表

`Vector`适合在多线程下使用, 非同步不建议使用



`Set`

判断对象是否属于某一个集合

每个元素内容差异, 顺序无关

* `HashSet`, 无序, 不同步, 
* `TreeSet`, 可排序, 不同步, 不可容纳`null`元素
* `LinkedHashSet`, 可排序, 不同步

`Hash`判断元素重复原则:

* 判定`Hashcode`, 不同返回`false`
* 判定`equal`, 不同, 返回`false`

`TreeSet`需要判定重复原则:

* 需要元素继承`Comparable`接口
* `compareTo`函数



### `Map`

* `HashTable`同步, 慢, 数据量小
* `HashMap`, 不同步, 块, 数据量大
* `Propertities`, 同步, 文件形式, 数据量小



1. `HashTable`
   * `K-V`对, 都不为`null`
   * 同步, 线程安全
   * 无需
   * 适合小数据
2. `HashMap`
   * 都允许为`null`
   * 不同步, 线程不安全
   * 无序
3. `LinkedHashMap`
   * 链式`Hash`
4. `TreeMap`
   * 基于红黑树
   * 可以根据`key`的自然排序和`compareTo`方法进行排序输出
5. `Properties`
   * 继承于`Hashtable`
   * 少量配置文件
   * 从文件加载的`load`方法, 写入文件的`store`方法



## `Java`异常

异常: 程序的不正常行为

* 5 / 0
* 数组越界
* 读取文件, 文件不存在



![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\13.png)

异常的类型

* `Throwable`, 所有错误的祖先

* `Error`系统内部错误或者资源耗尽, 不管

* `Exception`程序有关异常

  * `runtimeException`: 程序自身错误

    5/0, 空指针, 数组越界(上述都是`unchecked`异常)

  * 非`runtimeException`: 外界错误

    打开不存在文件

    加载不存在类

* `Unchecked Exception`: 编译器不会辅助检查, 需要程序员自己管, 包括啊`Error`子类和`RuntimeException`的子类; 编译不管

* 非`RuntimeException`的`Exception`子类(编译器会辅助检查异常)

  注意编译器会检查程序是否为`checked exception`配置了处理. 如果没有处理, 会报错

* `Checked Exception`程序员必须处理, 以发生后处理为主, 编译器会辅助检查

* `Unchecked Exception`中的`RuntimeException`子类, 程序必须处理, 以预防为主, 编译器不关心此异常类, 也不会辅助检查

* `Error`子类, 可以不用处理

### 异常处理

**`try`必须有, 剩下的至少有一个**

`try ... catch() ... catch() ... finally`

`try`正常逻辑, `catch`捕捉异常, `finally`必须执行操作

方法可能存在异常, 但是不处理, 那么可以使用`throw`来声明异常

调用带有`throws`异常的方法, 要么处理这些异常, 要么向外传递

一个方法被覆盖, 覆盖它的方法必须抛出相同的异常或者异常子类

如果父类抛出多个异常, 那么重写的子类方法必须抛出哪些异常的子集, 也就是不能抛出新异常

`catch`块小异常写在前面, 大的卸载后面

### 自定义异常

需要继承`Exception`或者其子类

* 继承自`Exception`, 成为`CheckedException`
* 继承`RuntimeException`, 成为`un`

重点在于构造函数, 可以调用父类的`message`构造函数



## 工具类

* 排序搜索
* `Array`
* `Collections`



## 文件读写

* 文件系统由`os`负责管理
* 文件系统和`Java`进程是平行, 是两套系统
* 文件系统由文件夹和文件递归组成
* 文件属性: 名称, 大小, 扩展名, 修改时间

学会这一个即可

1. `java.io.File`, `File`类很重要, `File`类常用方法要知道, 这些方法不涉及文件内容, 只涉及属性
2. 常见操作
3. 创建文件需要文件处理, 创建目录不需要
4. 文件的相关属性



新的可以不用管 (了解以下即可)

* `Path`, `Files`, `Directory`, `FileSystem`, `Files`  // 不会考

`search`所有的文件, 采用`nio`

写一个递归文件, 搜索目录下所有的`java`文件 

```java
// 方式1
File file = new File("Path");


// 方式2
Path path = FileSystems.getDefault().getPath("dir", "file");


// 方式3
Path path = Paths.get("dir", "file");

// 遍历
DirectoryStream<Path> paths = Files.newDirectoryStream(path);
```



### `IO`包

* `Java`读写文件只能以流的形式进行读写

* `IO`包中:

  1. 节点类: 直接对文件进行读写

     `InputStream`, `OutputStream`(字节)

     `Reader`, `Writer`(字符)

  2. 包装类: 

     * 转化类: 字节/字符/数据类型的转化类, 将二进制变成各种数据类型

       `InputStreamReader`, `OutputStreamWriter`(字节转字符)

     * 装饰类: 装饰节点类

       `DataInputStream`(封装数据流)

       `BufferedInputStream`(缓存字节流)

       `BufferedReader`(缓存字符流)
  
* 文件以字节保存

* `Stream`针对字节, `er + stream`转化类, 装饰

```java
// 流的操作顺序, 文本格式数据
// stream -> stream + er + bufferedw


// 二进制格式数据
// 采用字节编码, 非字符编码文件; 广义上来讲一切文件都是二进制文件, 记事本无法阅读
// 读写: 输入数据到文件中, 从文件中读取数据
// stream -> bufferdstream -> Datastr
```

### 读写

* 文本文件, 有若干行字符存储
* 二进制文件, 数据文件dat
* 文件时数据的一个容器
* 文件可以存放大量数据
* **Zip**文件不考



## 考试

**数字格式化**

### `set`添加元素

考试考`HashSet`就**OK**

`Map`添加元素同理

`HashSet`和`Hashtable`以及`HashMap`

* 先判断`hashcode`
* 判断`equal`方法

工具类的实现比较函数:

* 继承`Comparable`接口, 实现`CompareTO`方法

## 练习题

### 基础

* 窄缩会导致语法错误, `flaot f1 = 1.2;`, 整型可以转化为浮点数

* 时刻要注意引用未初始化的情况, 也就是声明了变量名, 但是没有绑定对象

  ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\5.PNG)

* `switch`无法用于浮点数, 因为不准确

* `char`占据两个字节

* `swap`无法直接交换

* `!flag1 == flag2`表示异或关系

* `java`文件可以有多个类但是仅有一个`public`类

* `byte b = 129`由于会溢出, 会导致无法进行直接赋值, 如果没有溢出, 那么可以直接赋值

* `main`方法可以重载, 是`Java`程序入口

* 一次编译到处运行的编译结果是字节码`.class`

* `JDK`(Java开发工具包)包含`JRE`(Java运行环境), 还包括编译部件, `JRE`只包含运行`Java`程序不见, 未含编译部件

* 构造函数和类具有相同的名字, 没有返回类型

* 函数复制的时候, 实际上是值传递, 因此你要注意, 它们传参的时候采用的是值传递

  ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\22p.PNG)

* `this`中的构造函数



### 继承

* 重写的时候, 特征标和限定符也一定要相同

  ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\1.PNG)

* 子类会优先调用父类的无参数构造函数, 如果父类没有就会报错, 编译时报错

  ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\2.PNG)

* 子类调用父类函数需要注意的知识点, 如何调用父类的构造函数

  ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\4.png)

* 动态绑定, 即使在一个函数中调用另一个函数, 仍然会动态绑定

  ```java
  class A {
  
  	public void func1() {
  
  		System.out.println("A func1 is calling");
  
  	}
  
  	public void func2() {
  
  		func1();
  
  	}
  
  }
  
  
  
  class B extends A {
  
  	public void func1() {
  
  		System.out.println("B func1 is calling");
  
  	}
  
  	public void func3() {
  
  		System.out.println("B func3 is calling");
  
  	}
  
  }
  
  
  
  class C {
  
  	public static void main(String[] args) {
  
              A a = new B();
  
              a.func1();
  
              a.func2(); // 会首先调用func2 -> func1(B)中的, 由于动态绑定
  
  
  	}	
  
  }
  ```

* ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\10.png)

  只有在自己运行的时候才能发现内存区没有对应的方法, 编译器无法直接检测出来

  应该说是不是从子类向上转型成的父类, 想要调用子类方法会发生运行时错误




### `Static`和`final`

* 实例变量就是普通的成员变量, 类变量为`static`

* 方法被声明为`final`不能重写, 但可以重载

* `final`的基本类型无论如何都不能修改,  除非第一次初始化

* 对象被`final`, 值可以改, 但是指向无法改

* 执行`main`函数首先会加载`main`函数所在的类, 因此会率先执行它所在类的`静态块`

* 静态块的执行也是父类优先, 因为编译器会先去寻找父类, 所以顺便将之静态块初始化

  ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\6.PNG)

  > 执行main函数时，会首先加载StaticTest（因为main函数就在StaticTest类中）。所以将StaticTest的静态块代码按照顺序执行，输出a .然后执行main函数第一句话，new一个ChildTest对象，那就需要加载ChildTest类，也把它的所有静态块都执行一遍。在执行ChildTest的静态块之前，会将执行ChildTest的父类的静态块代码。注意，任意一个类的一个静态块代码在整个生命周期里面只执行一次。//main1语句再输出c，然后new ChildTest()将调用父类的构造函数和(非静态的)匿名块，再输出b. //main2输出#， //main3将执行StaticTest的所有静态块和(非静态的)匿名块。由于静态块都执行过了，就剩下(非静态的)匿名块，所以输出b。



### 期中练习

* `null`在打印的时候可以生成字符串, 不论在前还是在后. 但是无法调用方法![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\7.PNG)

* ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\8.PNG)

  注意这道题实现了匿名对象, 抽象类已经被实现了

* 注意静态对象也可以被继承, 所以两个人是单独的存储空间, 互不影响

* ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\9.PNG)

  我们通过`new`产生对象, 然后调用方法返回的实际上是父类的数据

* ```java
   Teacher t;
    
    Student s;
    
    // t and s are all non-null.
    
    if (t instanceof Person ){ s=(Student)t; }
  // 两种不同的类型无法强转
  ```

* 返回类型不同也无法进行重写, 而且会导致编译出错



## 异常练习

* `parseInt`产生的异常为`NumberFormatException`

* 发生异常, 程序会立马跳转到`catch`或者`finally`语句, 执行完之后, 执行`try`块的下一句

* 先出错的异常先捕捉, 捕捉完成后就不管了

* ![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\14.PNG)

  B选项错误的原因应该是一次只能捕获一个异常

* ```java
  public void test () {
  
      try
  
      {  
  
              oneMethod ();
  
              System.out.print ( "condition 1");
  
       } 
  
      catch ( Exception e ) {
  
           System.out.print ( "condition 3");
  
      }
  
      catch ( ArithmeticException e ) {
  
          System.out.print ( "condition 2" );
  
      } 
  
       finally
  
       {
  
              System.out.println ("condition 4" );
  
        }
  
   }
  
  // Which will display if oneMethod throw NullPointerException?
  // 这道题是编译的时候发生错误, 因为大异常在前, 小异常在后
  ```

* 即使父类定义了要抛出异常, 子类重写方法抛出的异常只能是它的子集, 或者是更具体地类型, 甚至可以不抛出异常. 但是不能更宽泛, 也不能产生更新的异常

* ```java
  单选(3分)
  Given:
  
  class Mineral{ }
  
  class Gem extends Mineral{ }
  
  class Miner{
  
    static int x = 7;
  
    static String s = null;
  
    public static void getWeight(Mineral m){
  
      int y = 0 / x;
  
      System.out.print(s + " ");
  
    }
  
    public static void main(String[] args){
  
      Mineral[] ma = {new Mineral(), new Gem()};
  
        for(Object o : ma)
  
       getWeight((Mineral) o);
  
    }
  
  }
  
  /*
  
  And the command-line invocation:
  
  java Miner
  
  What is the result?
  
  */
  
  
  /*
  
  A.
  null
  
  B.
  null null
  
  C.
  A ClassCastException is thrown.
  
  D.
  A NullPointerException is thrown.
  
  */
  
  // 程序运行结果的选择B, 第一个转型是可以存在的, `null`可以自行打印
  // 网络异常, 文件异常, SQL异常等等属于IO异常, 也就是外部异常
  // runtimeException可写可不写
  ```

* ```java
  // 比较模板
  class T implements Comparable<T> {
      @Override 
      int compareTo(T t) {
          int result = 0;
          result = ; // 一轮比较
          if (result == 0) {
              // 深层比较
          }
          return result; // 直接返回结果
      }
  }
  
  // 或者Comparator
  
  class TComparator implements Comparator<T> {
      @Override
      int compare(T t1, T t2) {
          
      }
      
      // 显然不好用, 会受到访问权限的限制
  }
  ```

* 
