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

   import org.kl.state.Else;
   import org.kl.state.Null;
   import static org.kl.pattern.ConstantPattern.matches;

   matches(data,
      new Person("man"),    () ->  System.out.println("man");
      new Person("woman"),  () ->  System.out.println("woman");
      new Person("child"),  () ->  System.out.println("child");        
      Null.class,           () ->  System.out.println("Null value "),
      Else.class,           () ->  System.out.println("Default value: " + data)
   );
```

For work with range values could use such functions: in/or. Also could apply with another form. 

```Java
   import static org.kl.pattern.ConstantPattern.or;
   import static org.kl.pattern.ConstantPattern.in; 
   
   matches(data).as(
      or(1, 2),    () ->  System.out.println("1 or 2");
      in(3, 6),    () ->  System.out.println("between 3 and 6");
      7,           () ->  System.out.println("7");        
      Null.class,  () ->  System.out.println("Null value "),
      Else.class,  () ->  System.out.println("Default value: " + data)
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

   import org.kl.state.Else;
   import org.kl.state.Null;
   import static org.kl.pattern.TuplePattern.matches;

   matches(side, width,
      "top",    25,  () -> System.out.println("top");
      "bottom", 30,  () -> System.out.println("bottom");
      "left",   15,  () -> System.out.println("left");        
      "right",  15,  () -> System.out.println("right");         
      Null.class,    () -> System.out.println("Null value"),
      Else.class,    () -> System.out.println("Default value")
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

   import org.kl.state.Else;
   import org.kl.state.Null;
   import static org.kl.pattern.VerifyPattern.matches;

   matches(data,
      Integer.class, i  -> { System.out.println(i * i); },
      Byte.class,    b  -> { System.out.println(b * b); },
      Long.class,    l  -> { System.out.println(l * l); },
      String.class,  s  -> { System.out.println(s * s); },
      Null.class,    () -> { System.out.println("Null value "); },
      Else.class,    () -> { System.out.println("Default value: " + data); }
   );
```

This pattern give simplify work with union types. Also could apply with another form. 
	
```Java
   import java.util.Union;
   
   Union value = Union.of(1, "one");
   
   matches(value).as(
      Integer.class, i -> out.println("number: " + i),
      String.class,  s -> out.println("string: " + s)
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

   import org.kl.state.Else;
   import org.kl.state.Null;
   import static org.kl.pattern.GuardPattern.matches;

   matches(data,           
      Integer.class, i  -> i != 0,  i  -> { System.out.println(i * i); },
      Byte.class,    b  -> b > -1,  b  -> { System.out.println(b * b); },
      Long.class,    l  -> l == 5,  l  -> { System.out.println(l * l); },
      Null.class,    () -> { System.out.println("Null value "); },
      Else.class,    () -> { System.out.println("Default value: " + data); }
   );
```

For simplify writing a condition, developer can use such functions to compare: <br/>
lessThan/lt, greaterThan/gt, lessThanOrEqual/le, greaterThanOrEqual/ge,  <br/>
equal/eq, notEqual/ne. Also for omit condition could use such functions:<br/>
always/yes, never/no. Also could apply with another form. 

```Java
   matches(data).as(           
      Integer.class, ne(0),  i  -> { System.out.println(i * i); },
      Byte.class,    gt(-1), b  -> { System.out.println(b * b); },
      Long.class,    eq(5),  l  -> { System.out.println(l * l); },
      Null.class,    () -> { System.out.println("Null value "); },
      Else.class,    () -> { System.out.println("Default value: " + data); }
   );
```

*Deconstruction pattern* allow match type and deconstruct object at the parts.

```Java
   Figure figure = new Rectangle();
 
   let (int w, int h) = figure;
 
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
   import org.kl.state.Else;
   import static org.kl.pattern.DeconstructPattern.matches;
   import static org.kl.pattern.DeconstructPattern.foreach;
   import static org.kl.pattern.DeconstructPattern.let;

   Figure figure = new Rectangle();

   let(figure, (int w, int h) -> {
      System.out.println("border: " + w + " " + h));
   });

   matches(figure,
      Rectangle.class, (int w, int h) -> System.out.println("square: " + (w * h)),
      Circle.class,    (int r)        -> System.out.println("square: " + (2 * Math.PI * r)),
      Else.class,      ()             -> System.out.println("Default square: " + 0)
   );
   
   foreach(listRectangles, (int w, int h) -> {
      System.out.println("square: " + (w * h));
   });
```

Using Java 11 feature, we can deduce types deconstruct parameters. Also could apply with another form. 

```Java
   Figure figure = new Rectangle();
   
   let(figure, (var w, var h) -> {
      System.out.println("border: " + w + " " + h));
   });

   matches(figure).as(
      Rectangle.class, (var w, var h) -> System.out.println("square: " + (w * h)),
      Circle.class,    (var r)        -> System.out.println("square: " + (2 * Math.PI * r)),
      Else.class,      ()             -> System.out.println("Default square: " + 0)
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
    
    let (w: int w, h:int h) = figure;
 
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
   import org.kl.state.Else;
   import static org.kl.pattern.PropertyPattern.matches;
   import static org.kl.pattern.PropertyPattern.foreach;
   import static org.kl.pattern.PropertyPattern.let;
   import static org.kl.pattern.PropertyPattern.of;   

   Figure figure = new Rectangle();

   let(figure, of("w", "h"), (int w, int h) -> {
      System.out.println("border: " + w + " " + h));
   });

   matches(figure,
      Rect.class,    of("w", 5,  "h", 10), (int w, int h) -> System.out.println("sqr: " + (w * h)),
      Rect.class,    of("w", 10, "h", 15), (int w, int h) -> System.out.println("sqr: " + (w * h)),
      Circle.class,  of("r"), (int r)  -> System.out.println("sqr: " + (2 * Math.PI * r)),
      Else.class,    ()                -> System.out.println("Default sqr: " + 0)
   );
   
   foreach(listRectangles, of("x", "y"), (int w, int h) -> {
      System.out.println("square: " + (w * h));
   });
```

For simplify naming parameters could use another way. Using Java 11 feature, we can deduce types
property parameters. Also could apply with another form. 

```Java

   Figure figure = new Rect();

   let(figure, Rect::w, Rect::h, (var w, var h) -> {
      System.out.println("border: " + w + " " + h));
   });

   matches(figure).as(
      Rect.class,    Rect::w, Rect::h, (var w, var h) -> System.out.println("sqr: " + (w * h)),
      Circle.class,  Circle::r, (var r)  -> System.out.println("sqr: " + (2 * Math.PI * r)),
      Else.class,    ()                  -> System.out.println("Default sqr: " + 0)
   );
   
   foreach(listRectangles, Rect::w, Rect::h, (var w, var h) -> {
      System.out.println("square: " + (w * h));
   });
```

*Position pattern* allow match type and check value fields class in order of declaration.

```Java
    switch (data) {
      case Circle(5)   -> System.out.println("small circle");
      case Circle(15)  -> System.out.println("middle circle");
      case null        -> System.out.println("Null value ");
      default          -> System.out.println("Default value: " + data);
   };
```

Using this library developer can write in the following way.

```Java
   import org.kl.state.Else;
   import org.kl.state.Null;
   import static org.kl.pattern.PositionPattern.matches;
   import static org.kl.pattern.PositionPattern.of;

   matches(data,           
      Circle.class,  of(5),  () -> { System.out.println("small circle"); },
      Circle.class,  of(15), () -> { System.out.println("middle circle"); },
      Null.class,            () -> { System.out.println("Null value "); },
      Else.class,            () -> { System.out.println("Default value: " + data); }
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

*Static pattern* allow match type and deconstruct object using factory methods.

```Java
   
   switch (some) {
      case Rect.as(int w, int h) -> System.out.println("square: " + (w * h));
      case Circle.as(int r)      -> System.out.println("square: " + (2 * Math.PI * r));
      default                    -> System.out.println("Default square: " + 0);
   };
```

Using this library developer can write in the following way.

```Java
   import static org.kl.pattern.StaticPattern.matches;
   import static org.kl.pattern.StaticPattern.of;

   matches(figure,
      Rect.class,   of("unapply"), (int w, int h) -> out.println("square: " + (w * h)),
      Circle.class, of("unapply"), (int r)        -> out.println("square: " + (2 * Math.PI * r)),
      Else.class, ()                              -> System.out.println("Default square: " + 0)
   );    
```

For simplify naming deconstruct method could use another way. Also could apply with another form. 

```Java
   matches(figure).as(
      Rect.class,   Rect::unapply, (int w, int h) -> out.println("square: " + (w * h)),
      Circle.class, Circle::unapply, (int r)      -> out.println("square: " + (2 * Math.PI * r)),
      Else.class,   ()                            -> System.out.println("Default square: " + 0)
   );
```

Also this pattern give simplify work with Optional<<V>>, Expected<T, E>.
	
```Java
   import org.kl.util.Expected;
   import java.util.Optional;

   matches(value).as(
      Optional::empty, () -> out.println("empty"),
      Optional::get,    v -> out.println("value: " + v)
   );
   
   matches(value).as(
      Optional::error, e -> out.println("get error: " + e),
      Optional::value, v -> out.println("get value: " + v)
   );
```	

*Sequence pattern* allow processing on data sequence.

```Java
   List<Integer> list = List.of(1, 2, 3);
  
   switch (list) {
      case empty()       -> System.out.println("Empty value");
      case head(var h)   -> System.out.println("list head: " + h);
      case middle(var m) -> out.println("middle list:" + m),
      case tail(var t)   -> out.println("tail list:  " + t),
      case at(1, var i)  -> out.println("at list:    " + i),
      case edges(var f, var l) -> out.println("edges: " + f + " - " + l)      
      default            -> System.out.println("Default value");
   };
```

Using this library developer can write in the following way.

```Java
   import org.kl.state.Else;
   import static org.kl.pattern.SequencePattern.matches;

   List<Integer> list = List.of(1, 2, 3);

   matches(figure,
      empty()   () -> System.out.println("Empty value"),
      head(),   (int h) -> System.out.println("list head: " + h),
      middle(), (int m) -> out.println("middle list:" + m),
      tail(),   (int t) -> out.println("tail list:  " + t),
      at(1),    (int i) -> out.println("at list:    " + i),
      edges(),  (int f, int l) -> out.println("edges: " + f + " - " + l)
      Else.class, () -> System.out.println("Default value")
   );   
```

Using Java 11 feature, we can deduce types property parameters. 
Also could apply with another form.

```Java
   List<Integer> list = ...;

   matches(figure).as(
      empty()   () -> System.out.println("Empty value"),
      head(),   (var h) -> System.out.println("list head: " + h),
      middle(), (var m) -> out.println("middle list:" + m),
      tail(),   (var t) -> out.println("tail list:  " + t),
      at(1),    (var i) -> out.println("at list:    " + i),
      edges(),  (var f, var l) -> out.println("edges: " + f + " - " + l)
      Else.class, () -> System.out.println("Default value")
   );   
```

*Common pattern* contains general constructions which could be useful.

```Java 11
    lazy var rect = new Rectangle();
    var result = rect ?: new Rectangle();
	
    with(rect) {
        setWidth(5);
        setHeight(10);
    }

    when {
        side == Side.LEFT  -> System.out.println("left  value"),
        side == Side.RIGHT -> System.out.println("right value")
    }
```

Using this library developer can write in the following way.

```Java
   import static org.kl.pattern.CommonPattern.with;
   import static org.kl.pattern.CommonPattern.when;
   import static org.kl.pattern.CommonPattern.lazy;
   import static org.kl.pattern.CommonPattern.elvis;

   var rect = lazy(Rectangle::new);
   var result = elvis(rect.get(), new Rectangle());
   
   with(rect, it -> {
       it.setWidth(5);
       it.setHeight(10);
   });
   
   when(
       side == Side.LEFT,  () -> System.out.println("left  value"),
       side == Side.RIGHT, () -> System.out.println("right value")
   );
```

Requirements:<br/>
JDK: Java 8, 11


