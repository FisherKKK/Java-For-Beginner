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

  1. 将源代码编译成字节码`bytecode`
  2. 依赖虚拟机进行解释执行

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



## Java基础

#### 组成

* `main`函数的`PSVM`
*  `public`类必须要和文件同名, 一个文件仅一个`public`类
* 由一个个类组成, 所有成员都需要放在类里面
* 由**属性**和**方法**构成
* 一个`class`最多只有一个`main`函数, 没有`main`就无法主动执行, 但可以被别人调用
* 严格来说`main`函数不属于这个类所拥有, 它知识寄居其中, 是`Java`程序的入口
* **类**是`Java`最小的独立单元

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
* 一般`new`出一个对象, 基本类型默认值为`0`
* 构造函数必须和类的名字一样, 灭有返回值
* `Java`没有析构函数, 清除函数的过程
* 每个变量都有生命周期
* `Java`内存自动回收机制, 回收效率依赖于`GC`垃圾回收

### 必考哦

* `Java`都必须有构造函数
* 没有显示构造Java自动产生无参数构造函数, 如果有了显式, 编译器不会自动生成
* 每个子类的构造函数第一句话都默认调用父类无参数构造函数`super()` ///看!
* 一个类可以有多个构造函数



## 信息隐藏

类的成员属性是私有的`private`

类的方法是`public`共有的, `getter`和`setter`方法

`this`指针指向当前类的变量, 还可以代替本类的构造函数和对象本身.



## 继承

* 父类, 基类, 超类

* 子类, 派生类(`derived class`)

* `extends`表示继承

* 类名要大写

* 子类会继承所有父类(祖先)的所有的属性和方法

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

类只能继承一个类, 但可以实现多个接口, 继承和实现可以同时使用

接口可以继承继承多个接口, 没有实现的方法将会叠加

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
* 子类重新定义一个和父类一样的方法, 这就是重写, 子类方法的优先级高于父类
* **动态绑定**默认都是动态绑定
* 多态的作用以统一的接口操控某一类中不同对象的动态行为, 可以进行对象之间的解耦



## Static

类共有成员  

* 静态对象

* 静态方法

* 静态块, 只执行一次
* 实例初始化块, 生成一次执行一次
* 变量内存中只有一个`copy`, 方法可以不通过对象调用

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
* 为了访问
* `public static final`, 大写采用下划线连接
* 接口中变量默认常量



* `Java`为很多基本类型的包装类/字符串建立常量池
* 常量池相同的值存储一份, 公用
* 包装类, **小数没有缓存**
* 字面量赋值, 放在栈存储区, 会被共享
* `new`放在堆存储区, 不被共享
* 如果其中涉及到变量, 会重新`new`, 只要涉及动态结果都不一样

## 不可变对象

八个基本类型的包装类对象和Stirng以及大整数(`Immutable Object`), 内在成员变量无法被修改

* immutable不可变, 有改变请`clone/new`一个对象
* 所有的属性都是`final`和`private`
* 不提供`setter`
* 类是`final`的, 或者所有的方法都是`final`
* 类中包含`mutable`对象, 那么返回拷贝需要深度`clone`



## `package`

和`C++`命名空间很像

`Java`类文件第一句话给出包名, 包上的点代表目录, 包名和目录名一致

包名尽量唯一, 域名逆序作为包名

具体类要带上包名

`import`必须要放在`package`之后, 依赖也要说

编译的时候要添加全目录， 运行的时候使用完整名字

## `classpath`

包的位置通过编译命令解决, 编译采用绝对路径

运行的时候采用` java -classpath .; 目录 完成类名

## 访问权限

* private, 仅自己类
* default, 默认, 同一个包内可以访问
* protected, 仅子类可用
* public, 公开

![](C:\Users\Fisher\Desktop\holiday\Java-For-Beginner\11.PNG)



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
