# jpml
Java pattern matching library base on lambdas.

Many languages support pattern matching at the language level. <br/>
Java language does not support at the moment pattern matching  <br/>
but there is hope that in the future everything will change.

Using Java 8 features, we can emulate some of the features pattern matching.<br/>
Type test pattern allow match type and then extract value.

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
   import static org.kl.handle.TypeVerifyPattern.matches;

   matches(data,
           Integer.class, i  -> { System.out.println(i * i); },
           Byte.class,    b  -> { System.out.println(b * b); },
           Long.class,    l  -> { System.out.println(l * l); },
           String.class,  s  -> { System.out.println(s * s); },
           Null.class,    () -> { System.out.println("Null value "); },
           Default.class, () -> { System.out.println("Default value: " + data); }
   );
```

Deconstruction pattern allow match type and deconstruct object at the parts.


```Java
    Figure figure = new Rectangle();
 
    switch (figure) {
        case Rectangle(int w, int h) -> System.out.println("square: " + (w * h));
        case Circle   (int r)        -> System.out.println("square: " + (2 * Math.PI * r));
        default                      -> System.out.println("Default square: " + 0);
   };
```

Using this library developer can write in the following way.

```Java
   import org.kl.state.Default;
   import static org.kl.handle.DeconstructPattern.matches;

   Figure figure = new Rectangle();

   matches(figure,
           Rectangle.class, (Integer w, Integer h) -> { System.out.println("square: " + (w * h)); },
           Circle.class,    (Integer r)            -> { System.out.println("square: " + (2 * Math.PI * r)); },
           Default.class,   ()                     -> { System.out.println("Default square: " + 0); }
   );
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

Guard pattern allow match type and check condition for truthfulness at one time.

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
   import static org.kl.handle.TypeGuardPattern.matches;

   matches(data,           
           Integer.class, i  -> i != 0,
                          i  -> { System.out.println(i * i); },
           Byte.class,    b  -> b > - 1,
                          b  -> { System.out.println(b * b); },
           Long.class,    l  -> l < 5,
                          l  -> { System.out.println(l * l); },
           String.class,  s  -> !s.empty(),
                          s  -> { System.out.println(s * s); },
           Null.class,    () -> { System.out.println("Null value "); },
           Default.class, () -> { System.out.println("Default value: " + data); }
   );
```

Requirements:<br/>
JDK: Java 8

