# jpml
Java pattern matching library base on lambdas.

Many languages support pattern matching at the language level. <br/>
Java language does not support at the moment pattern matching  <br/>
but there is hope that in the future everything will be change.

Using Java 8 features, we can emulate some of the features pattern matching.<br/>
In general, patterns can be divided into three types: constant, type, var.<br/>
The library supports both statement and expression.<br/>

*Constant pattern* allow test for equality with constants.

```Java
   switch (data) {
      case new Person("man")    -> System.out.println("man");
      case new Person("woman")  -> System.out.println("woman");
      case new Person("child") 	-> System.out.println("child");        
      case null                 -> System.out.println("Null value ");
      default                   -> System.out.println("Default value: " + data);
   };
```

Using this library developer can write in the following way.

```Java

   import org.kl.state.Default;
   import org.kl.state.Null;
   import static org.kl.handle.ConstantPattern.matches;

   matches(data,
      new Person("man"),    () ->  System.out.println("man");
      new Person("woman"),  () ->  System.out.println("woman");
      new Person("child"),  () ->  System.out.println("child");        
      Null.class,           () -> { System.out.println("Null value "); },
      Default.class,        () -> { System.out.println("Default value: " + data); }
   );
```

*Tuple pattern* allow test for equality multiple pieces with constants.

```Java
   switch (side, width) {
      case "top",    25 -> System.out.println("top");
      case "bottom", 30 -> System.out.println("bottom");
      case "left",   15 -> System.out.println("left");        
      case "right",  15 -> System.out.println("right"); 
      case null         -> System.out.println("Null value ");
      default           -> System.out.println("Default value ");
   };
```
Using this library developer can write in the following way.

```Java

   import org.kl.state.Default;
   import org.kl.state.Null;
   import static org.kl.handle.TuplePattern.matches;

   matches(side, width,
      "top",    25,  () -> System.out.println("top");
      "bottom", 30,  () -> System.out.println("bottom");
      "left",   15,  () -> System.out.println("left");        
      "right",  15,  () -> System.out.println("right");         
      Null.class,    () -> { System.out.println("Null value"); },
      Default.class, () -> { System.out.println("Default value"); }
   );
```

*Type test pattern* allow match type and then extract value.

```Java
    switch (data) {
      case Integer i  -> System.out.println(i * i);
      case Byte    b  -> System.out.println(b * b);
      case Long    l  -> System.out.println(l * l);        
      case String  s  -> System.out.println(s * s);
      case null       -> System.out.println("Null value ");
      default         -> System.out.println("Default value: " + data);
   };
```

Using this library developer can write in the following way.

```Java

   import org.kl.state.Default;
   import org.kl.state.Null;
   import static org.kl.handle.VerifyPattern.matches;

   matches(data,
      Integer.class, i  -> { System.out.println(i * i); },
      Byte.class,    b  -> { System.out.println(b * b); },
      Long.class,    l  -> { System.out.println(l * l); },
      String.class,  s  -> { System.out.println(s * s); },
      Null.class,    () -> { System.out.println("Null value "); },
      Default.class, () -> { System.out.println("Default value: " + data); }
   );
```
*Guard pattern* allow match type and check condition for the truth at one time.

```Java
    switch (data) {
      case Integer i && i != 0     -> System.out.println(i * i);
      case Byte    b && b > -1     -> System.out.println(b * b);
      case Long    l && l < 5      -> System.out.println(l * l);
      case String  s && !s.empty() -> System.out.println(s * s);
      case null                    -> System.out.println("Null value ");
      default                      -> System.out.println("Default value: " + data);
   };
```

Using this library developer can write in the following way.

```Java

   import org.kl.state.Default;
   import org.kl.state.Null;
   import static org.kl.handle.GuardPattern.matches;

   matches(data,           
      Integer.class, i  -> i != 0,  i  -> { System.out.println(i * i); },
      Byte.class,    b  -> b > -1,  b  -> { System.out.println(b * b); },
      Long.class,    l  -> l == 5,  l  -> { System.out.println(l * l); },
      Null.class,    () -> { System.out.println("Null value "); },
      Default.class, () -> { System.out.println("Default value: " + data); }
   );
```

For simplify writing a condition, you can use such functions to compare: <br/>
lessThan/lt, greaterThan/gt, lessThanOrEqual/le, greaterThanOrEqual/ge,  <br/>
equal/eq, notEqual/ne.

```Java

   import org.kl.state.Default;
   import org.kl.state.Null;
   import static org.kl.handle.GuardPattern.*;

   matches(data,           
      Integer.class, ne(0),  i  -> { System.out.println(i * i); },
      Byte.class,    gt(-1), b  -> { System.out.println(b * b); },
      Long.class,    eq(5),  l  -> { System.out.println(l * l); },
      Null.class,    () -> { System.out.println("Null value "); },
      Default.class, () -> { System.out.println("Default value: " + data); }
   );
```

*Deconstruction pattern* allow match type and deconstruct object at the parts.

```Java
   Figure figure = new Rectangle();
 
   switch (figure) {
      case Rectangle(int w, int h) -> System.out.println("square: " + (w * h));
      case Circle   (int r)        -> System.out.println("square: " + (2 * Math.PI * r));
      default                      -> System.out.println("Default square: " + 0);
   };
   
   for ((int w, int h) :  listRectangles) {
      System.out.println("square: " + (w * h));
   }
```

Using this library developer can write in the following way.

```Java
   import org.kl.state.Default;
   import static org.kl.handle.DeconstructPattern.matches;
   import static org.kl.handle.DeconstructPattern.foreach;

   Figure figure = new Rectangle();

   matches(figure,
      Rectangle.class, (int w, int h) -> { System.out.println("square: " + (w * h)); },
      Circle.class,    (int r)        -> { System.out.println("square: " + (2 * Math.PI * r)); },
      Default.class,   ()             -> { System.out.println("Default square: " + 0); }
   );
   
   foreach(listRectangles, (int w, int h) -> {
      System.out.println("square: " + (w * h));
   });
```

Using Java 11 feature, we can deduce types deconstruct parameters.

```Java
   import org.kl.state.Default;
   import static org.kl.handle.DeconstructPattern.matches;
   import static org.kl.handle.DeconstructPattern.foreach;

   Figure figure = new Rectangle();

   matches(figure,
      Rectangle.class, (var w, var h) -> { System.out.println("square: " + (w * h)); },
      Circle.class,    (var r)        -> { System.out.println("square: " + (2 * Math.PI * r)); },
      Default.class,   ()             -> { System.out.println("Default square: " + 0); }
   );
   
   foreach(listRectangles, (var w, var h) -> {
      System.out.println("square: " + (w * h));
   });
```

Deconstruct classes must to have one or more extract method(s). <br/>
They must to be marked annotation @Extract. Parameters must to be <br/>
output. Since primitive and wrappers for primitive types can't to be <br/>
pass by reference we must to use wrappers such IntRef, FloatRef and etc.

```Java
   @Extract
   public void deconstruct(IntRef width, IntRef height) {
      width.set(this.width);
      height.set(this.height);
   }
```
*Property pattern* allow match type and access to fields class.


```Java
    Figure figure = new Rectangle();
 
    switch (figure) {
      case Rectangle(w: int w == 5,  h: int h == 10) -> System.out.println("sqr: " + (w * h));
      case Rectangle(w: int w == 10, h: int h == 15) -> System.out.println("sqr: " + (w * h));
      case Circle   (r: int r) -> System.out.println("sqr: " + (2 * Math.PI * r));
      default                  -> System.out.println("Default sqr: " + 0);
   };
   
   for ((w: int w, h: int h) :  listRectangles) {
      System.out.println("square: " + (w * h));
   }
```

Using this library developer can write in the following way.

```Java
   import org.kl.state.Default;
   import static org.kl.handle.PropertyPattern.matches;
   import static org.kl.handle.PropertyPattern.foreach;
   import static org.kl.handle.PropertyPattern.of;

   Figure figure = new Rectangle();

   matches(figure,
      Rectangle.class, of("w", 5,  "h", 10), (int w, int h) -> { System.out.println("sqr: " + (w * h)); },
      Rectangle.class, of("w", 10, "h", 15), (int w, int h) -> { System.out.println("sqr: " + (w * h)); },
      Circle.class,    of("r"), (int r)  -> { System.out.println("sqr: " + (2 * Math.PI * r)); },
      Default.class,   ()                -> { System.out.println("Default sqr: " + 0); }
   );
   
   foreach(listRectangles, of("x", "y"), (int w, int h) -> {
      System.out.println("square: " + (w * h));
   });
```
Using Java 11 feature, we can deduce types property parameters.

```Java
   import org.kl.state.Default;
   import static org.kl.handle.PropertyPattern.matches;
   import static org.kl.handle.PropertyPattern.foreach;
   import static org.kl.handle.PropertyPattern.of;

   Figure figure = new Rectangle();

   matches(figure,
      Rectangle.class, of("w", 5,  "h", 10), (var w, var h) -> { System.out.println("sqr: " + (w * h)); },
      Rectangle.class, of("w", 10, "h", 15), (var w, var h) -> { System.out.println("sqr: " + (w * h)); },
      Circle.class,    of("r"), (var r)  -> { System.out.println("sqr: " + (2 * Math.PI * r)); },
      Default.class,   ()                -> { System.out.println("Default sqr: " + 0); }
   );
   
   foreach(listRectangles, of("x", "y"), (var w, var h) -> {
      System.out.println("square: " + (w * h));
   });
```

*Position pattern* allow match type and check value fields class in order of declaration.

```Java
    switch (data) {
      case Circle(5, 5)   -> System.out.println("small circle");
      case Circle(15, 15) -> System.out.println("middle circle");
      case null           -> System.out.println("Null value ");
      default             -> System.out.println("Default value: " + data);
   };
```

Using this library developer can write in the following way.

```Java
   import org.kl.state.Default;
   import org.kl.state.Null;
   import static org.kl.handle.PositionPattern.matches;
   import static org.kl.handle.PositionPattern.of;

   matches(data,           
      Circle.class,  of(5),   () -> { System.out.println("small circle"); },
      Circle.class,  of(15),  () -> { System.out.println("middle circle"); },
      Null.class,    ()       () -> { System.out.println("Null value "); },
      Default.class, ()       () -> { System.out.println("Default value: " + data); }
   );
```

If developer does not want check some fields class that fields must <br/>
to be marked with annotation @Exclude. Excluded fields must to be declared last.

```Java
   class Circle {
      private int radius;
      	  
      @Exclude
      private int temp;
   }
```

Requirements:<br/>
JDK: Java 8
