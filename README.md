# Category Theory in Functional Programming 101 Dojo
This is a crash course in the basics.

## Introduction
Functional programming (FP) has been around for a long time. Recent findings have revealed that the T-Rex probably programmed in Lisp.
During this time, the set of core concepts associated with FP has remained reasonably constant. These concepts are strongly rooted in [lambda calculus](https://en.wikipedia.org/wiki/Lambda_calculus).
Lambda calculus is primarily concerned with the application of functions - their composition; higher-order functions; currying etc. [[1]](#### 1-Lambda Notes). Essentially lambda calculus *is functional programming*.

Recently though, concepts from another area of mathematics have increasingly become prevalent in the FP-scence: Category Theory (CT).
We work in an industry that is subject to subject to swings in what's fashionable and CT concepts are most certainly bang on-trend at the moment, to the point where CT's sometimes overstated as is if it's an integral part of what defines the FP paradigm.
I don't think that's the case. FP is still at it's core about functions and their interactions, just as it's always been. Instead, CT concepts provide us with a number approaches that build on top of normal FP and allow for the solving of many common problems in really slick ways.
Because of this, my favourite description of the relationship between CT and FP that I've read, is that CT gives us the 'Design Patterns' for the functional domain.

Category Theory itself is a vast and crushingly abstract field of mathematics. I only understand - or at least have managed to convince myself that I understand - a very small portion of it.
That's fine though, as my goal here isn't to provide you with a complete exposition into category theory, replete with the required mathematical rigour, but instead to concentrate on the application of a few core concepts, without getting too bogged down in the underlying theory.
The goal is that I'll try and present a simplified view of the underlying mathematics: there will be omissions, but what's left should be 99% correct, with the remaining 1% largely being a loss of generality in how I've explained things.  

**Disclaimer:** I'm *far* from a CT expert. I learnt about it myself for the dojo, so if you notice any inconsitencies or errors, then flag me up and gladly correct it.

## Dojo Format
The dojo takes a workshop-type format. Generally, for each concept, this document provides a little prose, giving some background to the concept and then there'll be a few exercises to complete based around it.
These exercises take the format of code that needs writing to make the provided failing tests pass. Initially, all of the failing tests are set to be ignored, so you'll want to un-ignore them as you go along.

The exercises build on each other and share a narrative thread. You could possibly skip the prose and just do the exercises or vice-versa, but I'd recommend going through it in order.
Similarly, it's strongly advisable to ensure they pass before moving on to the next. If needs be, I've included the answers, so if you get really stuck then just copy the answer in and move on.

## Setup
TODO: Maybe crib something from my old Dojo https://github.com/dojonorth/planet-survey-akka

## Exercises

### 1. Categories
Unsurprisingly, at the heart of category theory, are categories. A category is a simple algebraic data structure that consists of two main collections:
1. **Objects -** the 'things' within the category. These can be thought of as the actual data. They're commonly represented using capital letters e.g. *A*, *B*, *C* etc.
2. **Morphisms -** the relationships within the category. These are mappings go from one source object (A) to another target object (B) and are usually represented using arrows e.g. A → B.

**Aside - It doesn't really matter...:**
>I've included this section for completeness: it seemed remiss to talk about CT concepts without first talking about a category is.
That said, understanding them in detail isn't actually **that** important to then be able to grasp the subsequent concepts.
Read this section and complete the exercises, but if you don't understand it all, don't get hung up on it. Just concentrate on the take-home.

You'll notice though that these are incredibly general concepts. Normally, at this stage I'd like to give some concrete example to help clear things up, but CT really doesn't make this easy.
In fact, CT sets out to describe things in such extreme generality that other entire fields of mathematics slot into it and it's sometimes called [Abstract Nonsense](https://en.wikipedia.org/wiki/Abstract_nonsense) by other mathematicians.
That said, the example we'll focus on is the *category of finite sets and maps*, though there are [many others](http://eed3si9n.com/learning-scalaz/Examples+of+categories.html).
Within this category an object is a finite set or collection. For example, the set Classic Consoles Russ Owned = {Sega Master System, Sega Megadrive, Super Nintendo} or the set Console Cartridges = {Super Mario World, Alex Kidd in Miracle World, Road Rash II}
Note that you might have expected the objects to be elements within the sets, but if you're developer used to thinking in terms of types and instances of types, then generally speaking category theory operates at the level above you're used to thinking at, as we'll see.

We'll call the set Classic Consoles Russ Owned 'C' and the set Console Cartridges 'G', then we can define a morphism plays: C → G. We can depict this as:

[TODO: Plays diagram]

The important thing to note is that for each dot in the domain of consoles, there is exactly one arrow leaving that runs to the codomain.

There are a few things to take from the diagram:
* *It's missing identity morphisms:* Category theory states that each object should feature a morphism that goes from itself to itself. I haven't bothered to include these on the diagram. 
* *At the core of categories is the concept of composition:* If we have A → B and B → C then there must be a corresponding A → C. I've shown this on the diagram using the composition operator '∘'.

Additional restrictions apply to the collection of objects and arrows for them to qualify as a true mathematical category, but we'll skip over these. See [[2]](#### 2-General Category Theory) for additional information if you want to round out your understanding.

#### Exercise
Open CodemonSpec and un-ignore and make pass the tests.

Here we'll establish a basic object and morphism that we'll use within subsequent exercises.

Additional notes:
* Note the basic Codemon trait. It defines an example object. Feel free to add more Codemon instances, but subsequent tests just assume the presence of these three.
* The identity function is a required component of a category. It just returns whatever is passed to it. This could be a method on the Codemon objects, but I've instead chosen to leave it as a freestanding function in the companion object. I've followed this convention throughout the exercise as a whole; it's a slightly arbitrary decision, but I feel it represents a more idiomatic approach that highlights the distinction between data and functions. In real-world coding though, I'd tend towards putting functions within the instances themselves as I feel it makes for simpler code and aids discoverability.
* Evolve is a basic morphism we'll add to our category. Create the function as guided by the spec and depicted by the diagram. Note the simplifying assumption that a RaabyChu evolves to itself - this is a little weird, but simplifies subsequent exercises.
* The evolve morphism is a special type of morphism where the domain and codomain are the same. This is called an endomorphism.

#### Take Home
Thus far, we've not gone into great depth on what categories are. We're create an example object and morphism with the category of finite sets and maps. How does this relate to programming?
Here's the reveal: when we're developing in a typed language, the class heirarchy forms a category:
* Objects are the types: classes, traits, interfaces etc and the morphisms either
* Morphisms include two main relationships: subtyping and functions between types.

[TODO: Steal Class Heirarchy diagram from http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/]

Why is this important? The thing that really matters is that we can show we're in a category. It doesn't matter what the category is. The fact that we're in a category means that category theory applies!
We have access to hundreds of years of hard work mathematicians have put in understanding and formalising a number of useful concepts that we can now freely pillage and use for our own devices.
The following concepts that we use are a small selection of examples of these.

[TODO: Pillaging diagram]

TODO: Maybe say how normally used to thinking at instances level, but category theory is more concerned with type level. See https://alissapajer.github.io/conferenceslides/craftconf2014/#/4
TODO: Maybe talk a little more on laws - see https://alissapajer.github.io/conferenceslides/craftconf2014/#/11
TODO: Maybe need to establish a thread whereby I say how I've largely skipped over laws throughout.

##2. Functors
In CT, a functor describes a transformation between two categories. It needs to map every object and morphism between the two and must adhere to a number of mathematical laws.
We'll gloss over this and concentrate on their application in functional programming (see [[3]](#### 3-Functors) for more detail if you're interested).

Normally, when you perform a function on a value, say +2 on the integer 3 the behaviour is fixed.
In order to better understand functors, it's convenient to extend this to the idea of a value within an associated context.
Commonly, this is depicted as the idea of a value within a 'box' that defines the context.
Now, depending on the context, how +2 is ultimately applied will change. For example in the case of a List context, +2 would be applied to every element in the list, or in the case of a Promise (or Future) +2 would only be applied once the value had been evaluated.
The key is that only the context itself understands how to contextually take a function and apply it to it's value(s).

#### Exercise
Open CodeballSpec and un-ignore and make pass the tests.

Here we establish a basic endofunctor (functor that only maps between instances of the same type).

Additional Notes:
* Note that this has no meaning in the empty codeball and so we have no choice but to throw an exception. This is a bit of wrinkle in pure functional terms and shows a little of Scala's OO/imperative roots. It's actually what Scala option does too, so I think there's no way around it. In super functional languages like Erlang, I believe that's not possible to actually get at the contained object so this wouldn't be an issue (TOTO: Check the avlitiy of this statement and clean up language apropos category theory). 
* Being able to map across a collection that may contain either something or nothing without having to differentiate is a very powerful pattern that allows for the streamlining of programming to single logical pipes that don't feature continuous branching - so-called railway-orientated programming.
* Functor is very similar to morphisms as we described them before (functions). The major difference is that it is a morphism between categories (sometimes called a structure preserving map) instead of objects (lifted from http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala)
* The map method is defined on the type. I've done this as it's more familiar and is how I'd write it in practice. In future exercises though, I've segregated data and functionality by putting the methods on the respective companion objects

#### Functors in Type Constructors
The Codeball that we created in the previous exercise served to help explain what a functor is, since it's an endofunctor it cannot convert between types which severely limits its scope.
We'll soon deal with that, but first, we need to ensure that we're familiar with the idea of a *type constructor*.
This is a generic type definition that takes a specific type as its parameter.
For example, in Scala Option[T], List[T] and Future[T] are type constructors. So Option[Boolean] is a type, but Option itself is not.

With all of this in hand, we are now in a position to better define what a functor is in practical terms:
> trait Functor[T[_]] {
>   def pure[A](value: A): T[A]
>   def map[A, B](x: T[A])(f: A => B): T[B]
> }

We can see that:
* It is a type constructor that is defined for a generic type.
* It features a way of taking a value and turning it into a functor - the apply method.
* It features a method that applies a function to a wrapped value and produces a new functor of the resultant type. This is usually called 'map'.

**Aside - Language Feature Imports:**
>The term 'higher kinded' is sometimes used to refer to such types that have one or more 'holes' in them into which other types must be inserted.
The use of these is considered an advanced language feature in Scala and so needs to be explicitly enabled, otherwise we'll get compiler warnings.
Hence, in the examples, whenever I've declared a type constructor with T[_] syntax, you'll also see that I've imported the feature to suppress compiler warnings with:
>> import scala.language.higherKinds

#### Exercise
Open AdvancedCodeballSpec and un-ignore and make pass the tests.

Here we establish a fully-fledged functor!

Additional notes:
* What we're creating here is the simplest possible implementation of what is effectively the Option data type. Normally, this would not be a type unto itself, but would be a *type constructor* i.e. you would give it a type argument in order to turn it into a type, like Option[Int] or Option[Boolean]. 

#### Exercise
Open WildCodemonCaptureSpec and un-ignore and make pass the tests.

Here we look at actually applying the functor we've created in anger.

Additional notes:
* As mentioned before, what we've essentially created is the Option data type, albeit with a different name. The Option type allows for the creation of so-called 'walled gardens' where failures are encapsulated within the data type, rather than being a different return type (such as null or an exception).
* The exercise shows how a number of functor calls can be sequenced together to create a single pipeline that also deals with the failure case without the need for any branching logic. In the case of real Options, this might be reading config, where the first function might represent reading from a file, that might fail, followed by using the result of that to determine a URL to read from that might fail etc.
* This style is sometimes called 'railway-orientated programming', whereby there are two 'lines': the good line and the error line that we sometimes switch onto.
* The limitations of map are shown towards the end, where the return type of nested calls becomes increasingly nested and difficult to work with, which is a major limitation.

#### Take Home
* Functor is a value in context that provides a method - usually called 'map' - that allows a function to be applied to the value.
* Mapping with the identity function has no effect.
* Familiar examples include List and Option.
* Less familiar examples include functions, where you can map over the result type.

## 3. Monads
Monads have a semi-mythical status in computing. They change your mind in such a way that once you understand monads, you become incapable of explaining monads.
Well hopefully I'm just 99% of the way to understanding them as I think there's not actually that much to the concept.
As [has been pointed out](https://bartoszmilewski.com/2016/11/21/monads-programmers-definition/) people end to overestimate their complexity, as they confuse the myriad of applications with the concept itself.
A good analogy I read compared them to Duct tape: If you tried to describe duct tape in terms of it's applications then you might say things along the lines of:
* "It covers holes in tents"
* "You can make a [wallets out of it](http://www.wikihow.com/Make-a-Duct-Tape-Wallet)"
* "It can fix ducts"
If you just had it described to you in those terms i.e. what it does, then you'd have no clue what it did. Whereas the underlying description of it is pretty simple: it's a waterproof, resilient tape that binds things together; not unlike monads (well, apart from the waterproof tape part...).
TODO: Include some amusing duct tape uses.

Just like how a functor boiled down to something that has a map operation on it, a monad is a type constructor that has two distinct operations on it:
* **Pure -** a method that takes a value of a plain type and puts it into a monad creating a monadic value. This is effectively the monad constructor. It goes by many aliases across the programming world: return in Haskell, unit in Scala, sometimes pure elsewhere, occasionally zero
* **Bind -** a method that performs as per the functor map operation in that it contextually applies the function to the contents of the monad, but then performs an additional flattening step that will be described in more detail later. Again, this operation goes by other names >>= in Haskell and flatMap in Scala.

I prefer the Scala name for bind, which is flatMap, as it describes what the operation does: it maps over the monad's values and then flattens the result. As with functors, just what this flatten operation does is contextual to the monad in question, but in essence it takes any nested instances within the Monad and then 'flattens' them out into a single monad. For example:
* *The List Monad -* List(List(1,2,3), List(4), List(5,6,7)).flatten() would give List(1,2,3,4,5,6,7)
* *The Option Monad -* Some(Some(x)).flatten would give Some(x), whereas Some(None) gives None

In practical terms, a monad is always also a functor, and features a map method, although this is something of a moot point, since, as we'll see, it's possible to describe map in terms of flatMap and pure.

The type signature of monad is:
> trait Monad[T[_]] {
>  def flatMap[A, B](x: T[A])(f: A => T[B]): T[B]
> }

Looking at flatMap's signature, we can see that the function that is passed to flatMap, must itself return an instance of the monad, as the exercise will show in depth.
We'll go into the benefits of monads later, but even in the two examples listed above, the power of flatMap over map can be seen:
* *The List Monad -* we can now change the number of elements in the list. Using map only allows us to transform the elements, whilst the total number remains the same.
* *The Option Monad -* We can actually change from a Some to a None. Using map only allows for the value within a Some to be altered.

Hence, it should come as no surprise that flatMap is much more powerful than map. This is illustrated by the fact that it's possible to write map in terms of flatMap, but not vice-versa. If the two had a fight, flatMap would win everytime.
[TODO: Insert picture]

As before, be aware that there are additional mathemetical properties that should technically hold true for a monad, but we'll skip them.
You can read about them here [[4]](#### 4-Monads) along with other fun monad facts.

**Aside - Cats:**
>Most (all?) higher-kinded types in Scala feature map and flatMap methods and are effectively Monads (and so also Functors), however, they don't implement any common interface that marks them as such - a la the traits we've created.
The idea of using category-theory-derived methods as a core extension to the Scala language is something that's been done already. Initially by [Scalaz](https://github.com/scalaz/scalaz), that I'll not expand on further, and more recently, and accessibly, by [Cats](https://github.com/typelevel/cats).
Cats is a huge topic unto itself. If you want to learn about it, I recommend [Advanced Scala with Cats](http://underscore.io/books/advanced-scala/) by Underscore.io, which is now available for free.
That said, it's worth pointing out that within Cats, similar traits exist to the ones that we've created for Monad, Functor etc. Since they're used in a wider context though, they're built up slightly differently.
For example, cats Functor only includes map and not pure, which it gets from extending Applicative. You can take a look at their heirrarchy [here](https://github.com/typelevel/cats/tree/master/core/src/main/scala/cats).

#### Exercise
Open MasterCodeballSpec and un-ignore and make pass the tests.

Here we create a fully-fledged monad!

Additional notes:
* Just as a functor is a value wrapped in context, so is a Monad. The key operation is flatten, that needs to be contextually defined for the type.
* As we can see from the type, it only makes sense to flatten instances of the same monadic type.
* We now have the ability to change the outer context. A 'Some' can become a None, which was impossible before with just 'map' - all we could do was change the type of what was inside.
* For any given context, you could potentially define multiple monads (or functors)  , since it's up to the implementor to decide what the 'sensible' implementation is. In practice though, there's usually only one that makes sense.
* Having the flatten operation on the interface isn't a requirement of monad (can you think why?). But it's usually handy to have access to it.

#### Exercise
Open ImprovedWildCodemonCaptureSpec and un-ignore and make pass the tests.

This is a redux of WildCodemonCaptureSpec, where we use the power and the majesty of flatMap to address it's failings.

Additional notes:
* If there was any doubt that map lords it over flatMap, then this should dispel any doubts. Look at flatMap styling on map! It can do everything that it does and more!
* Using flatMap lets us sequence dependent calls as before, except now we're able to keep a handle on the return type, rather than it exploding into a nested mess. 

#### The For Comprehension
This pattern of having multiple flatMap calls, usually follow by a map call to do something with the return type is incredibly common.
So much so, that there exists extra syntatic sugar for it, the 'for-comprehension' - not to be confused with the ubiquitous 'for loop' that it shares a confusing syntactic similarity with.
The for comprehension performs a flatMap call on each of the '<-' arrow statements with the exception of the final one, for which it just calls 'map'. The return type is indicated by the 'yield' statement.
More detail and the source of the following examples can be found [here](http://docs.scala-lang.org/tutorials/FAQ/yield):

For example:
> for(x <- c1; y <- c2; z <- c3) yield {...}

gives:
> c1.flatMap(x => c2.flatMap(y => c3.map(z => {...})))

##### Exercise (OPTIONAL)
ImprovedWildCodemonCaptureSpec and rewrite the series of flatMap calls as a 'for' comprehension.

Additional notes:
* You'll need the map and flatMap methods on the actual class instance instead of in the companion object for this to work. Feel free to just calls the companion object methods.
* The for-comprehension also supportsadditional behaviour such as if statements. If you add this, then you'll need to include additional methods filter or withFilter.
TODO: Write this and do the solution (see example https://stackoverflow.com/questions/35761043/how-to-make-your-own-for-comprehension-compliant-scala-monad).
  
#### Monad Usage
TODO: Write about famous monads

#### Exercise (OPTIONAL)
Open CodeboxSpec and un-ignore and make pass the tests.

This is an optional (read: bit rough) additional exercise to implement a different monad - essentially a list Monad by any other name.

Additional notes:
* Depending on the time you have you could implement a list from scratch or piggyback on top of the existing list type. If you go for the piggyback option, then don't just delegate to the existing list's map/flatMap methods!
* This exercise provides more evidence of the limitations of map as compared to flatMap. Using only map, there is no way to change the number of elements in a List. It is only possible to modify the type or value of the available element,s, but the number of elements will remain the same.

## Further Reading
#### 1-Lambda Notes
See [here](https://medium.com/javascript-scene/the-rise-and-fall-and-rise-of-functional-programming-composable-software-c2d91b424c8c) as a starting point for more detail on the relationship between Lambda Calculus and programming languages. Few interesting nuggets to whet your appetite:
* The 'calculus' in Lambda Calculus has nothing to do with integration and differentiation that we all know and love. Rather, it refers to the more general meaning of calculus, which defines a 'method or system for calculation or reasoning'.
* Lisp, that we also all know and love, was heavily influenced by lambda calculus. [Lisp dates from 1958 and is the second oldest prograaming language still in widespread use](https://en.wikipedia.org/wiki/Lisp_(programming_language)), only Fortran edges it out by a year.

#### 2-General Category Theory
I've tried to inline links to the general concepts that the dojo covers as I've gone along. If you're interested in a more complete discussion of parts of category theory that apply to programming though, then I'd single out [this for special mention](https://bartoszmilewski.com/2014/10/28/category-theory-for-programmers-the-preface/).

If you're after a more accessible, but less thorough, take on most of the material that the dojo covers, then I'd recommend [this](http://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html).

Finally, if you're after a balance between the two [then this is good](http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala). As is [this](http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/).

#### 3-Functors
[This](http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/) provides a good explanation of functors from basic concepts without going into too much detail.

A more heavyweight discussion can be found [here](https://hackernoon.com/functors-and-applicatives-b9af535b1440). Or if you want just the bare bones, then look no further than [here](https://tpolecat.github.io/2014/03/21/functor.html).

#### 4-Monads
TOTO: Write me.
  
  TODO: Have map on monad too. Double-check type
  
  Open Links:
  https://medium.com/@sinisalouc/demistifying-the-monad-in-scala-part-2-a-category-theory-approach-2f0a6d370eff
  https://hackernoon.com/functors-and-applicatives-b9af535b1440
  http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala
  http://eed3si9n.com/herding-cats/Functor.html
  https://www.scala-exercises.org/cats/functor
  https://tpolecat.github.io/2014/03/21/functor.html
  https://en.wikipedia.org/wiki/Monad_(functional_programming)#fmap_and_join
  https://raw.githubusercontent.com/dojonorth/planet-survey-akka/master/README.md
  https://alissapajer.github.io/conferenceslides/craftconf2014/#/40
  https://github.com/lord/slate/wiki/Markdown-Syntax
  http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/