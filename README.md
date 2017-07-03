# Category Theory in Functional Programming 101 Dojo
This is a crash course in the basics.

## Introduction
Functional programming (FP) has been around for a long time. Recent findings have revealed that the T-Rex probably programmed in Lisp.
During this time, the set of core concepts associated with FP has remained reasonably constant. These concepts are strongly rooted in [lambda calculus](https://en.wikipedia.org/wiki/Lambda_calculus).
Lambda calculus is primarily concerned with the application of functions - their composition; higher-order functions; currying etc. [[1]](####1-Lambda Notes). Essentially lambda calculus *is functional programming*.

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

###1. Categories
Unsurprisingly, at the heart of category theory, are categories. A category is a simple algebraic data structure that consists of two main collections:
1. **Objects -** the 'things' within the category. These can be thought of as the actual data. They're commonly represented using capital letters e.g. *A*, *B*, *C* etc.
2. **Morphisms -** the relationships within the category. These are mappings go from one source object (A) to another target object (B) and are usually represented using arrows e.g. A → B.

<aside class="notice">
I've included this section for completeness: it seemed remiss to talk about CT concepts without first talking about a category is.
That said, understanding them in detail isn't actually **that** important to then be able to grasp the subsequent concepts.
Read this section and complete the exercises, but if you don't understand it all, don't get hung up on it. Just concentrate on the take-home.
</aside>

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

Additional restrictions apply to the collection of objects and arrows for them to qualify as a true mathematical category, but we'll skip over these. See [[2]](####2-General Category Theory) for additional information if you want to round out your understanding.

#### Exercise
Open CodemonSpec and un-ignore and make pass the tests.

Here we'll establish a basic object and morphism that we'll use within subsequent exercises.

Additional notes:
* Note the basic Codemon trait. It defines an example object. Feel free to add more Codemon instances, but subsequent tests just assume the presence of these three.
* The identity function is a required component of a category. It just returns whatever is passed to it. This could be a method on the Codemon objects, but I've instead chosen to leave it as a freestanding function in the companion object. I've followed this convention throughout the exercise as a whole; it's a slightly arbitrary decision, but I feel it represents a more idiomatic approach that highlights the distinction between data and functions. In real-world coding though, I'd tend towards putting functions within the instances themselves as I feel it makes for simpler code and aids discoverability.
* Evolve is a basic morphism we'll add to our category. Create the function as guided by the spec and depicted by the diagram. Note the simplifying assumption that a RaabyChu evolves to itself - this is a little weird, but simplifies subsequent exercises.
* The evolve morphism is a special type of morphism where the domain and codomain are the same. This is called an endomorphism.

####Take Home
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
We'll gloss over this and concentrate on their application in functional programming (see [[3]](####3-Functors) for more detail if you're interested).

Normally, when you perform a function on a value, say +2 on the integer 3 the behaviour is fixed.
In order to better understand functors, it's convenient to extend this to the idea of a value within an associated context.
Commonly, this is depicted as the idea of a value within a 'box' that defines the context.
Now, depending on the context, the behaviour of +2 will change. For example in the case of a List context, +2 would be applied to every element in the list, or in the case of a Promise (or Future) +2 would only be applied once the value had been evaluated.
The key is that only the context itself understands how to contextually take a function and apply it to it's value(s).

#### Exercise
Open CodeballSpec and un-ignore and make pass the tests.

Here we establish a basic endofunctor (functor that only maps between instances of the same type).

Additional Notes:
* Note that this has no meaning in the empty codeball and so we have no choice but to throw an exception. This is a bit of wrinkle in pure functional terms and shows a little of Scala's OO/imperative roots. It's actually what Scala option does too, so I think there's no way around it. In super functional languages like Erlang, I believe that's not possible to actually get at the contained object so this wouldn't be an issue (TOTO: Check the avlitiy of this statement and clean up language apropos category theory). 
* Being able to map across a collection that may contain either something or nothing without having to differentiate is a very powerful pattern that allows for the streamlining of programming to single logical pipes that don't feature continuous branching - so-called railway-orientated programming.
* Functor is very similar to morphisms as we described them before (functions). The major difference is that it is a morphism between categories (sometimes called a structure preserving map) instead of objects (lifted from http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala)

#### Functors in Type Constructors
The Codeball that we created in the previous exercise served to help explain what a functor is, since it's an endofunctor it cannot convert between types which severely limits its scope.
We'll soon deal with that, but firt, we need to ensure that we're familiar with the idea of a *type constructor*.
This is a generic type definition that takes a specific type as its parameter.
For example, in Scala Option[T], List[T] and Future[T] are type constructors. So Option[Boolean] is a type, but Option itself is not.

With all of this in hand, we are now in a position to better define what a functor is in practical terms:
> trait Functor[T[_]] {
>   def apply[A](value: A): T[A]
>   def map[A, B](x: T[A])(f: A => B): T[B]
> }

We can see that:
* It is a type constructor that is defined for a generic type.
* It features a way of taking a value and turning it into a functor - the apply method.
* It features a method that applies a function to a wrapped value and produces a new functor of the resultant type. This is usually called 'map'.

#### Exercise
Open AdvancedCodeballSpec and un-ignore and make pass the tests.

Here we establish a fully-fledged functor!

Additional notes:
* What we're creating here is the simplest possible implementation of what is effectively the Option data type. Normally, this would not be a type unto itself, but would be a *type constructor* i.e. you would give it a type argument in order to turn it into a type, like Option[Int] or Option[Boolean]. 

####Take Home
* Functor is a value in context that provides a method - usually called 'map' - that allows a function to be applied to the value.
* Mapping with the identity function has no effect.
* Familiar examples include List and Option.
* Less familiar examples include functions, where you can map over the result type.

## Further Reading
####1-Lambda Notes
See [here](https://medium.com/javascript-scene/the-rise-and-fall-and-rise-of-functional-programming-composable-software-c2d91b424c8c) as a starting point for more detail on the relationship between Lambda Calculus and programming languages. Few interesting nuggets to whet your appetite:
* The 'calculus' in Lambda Calculus has nothing to do with integration and differentiation that we all know and love. Rather, it refers to the more general meaning of calculus, which defines a 'method or system for calculation or reasoning'.
* Lisp, that we also all know and love, was heavily influenced by lambda calculus. [Lisp dates from 1958 and is the second oldest prograaming language still in widespread use](https://en.wikipedia.org/wiki/Lisp_(programming_language)), only Fortran edges it out by a year.



####2-General Category Theory
I've tried to inline links to the general concepts that the dojo covers as I've gone along. If you're interested in a more complete discussion of parts of category theory that apply to programming though, then I'd single out [this for special mention](https://bartoszmilewski.com/2014/10/28/category-theory-for-programmers-the-preface/).

If you're after a more accessible, but less thorough, take on most of the material that the dojo covers, then I'd recommend [this](http://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html).

Finally, if you're after a balance between the two [then this is good](http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala). As is [this](http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/).

####3-Functors
[This](http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/) provides a good explanation of functors from basic concepts without going into too much detail.

A more heavyweight discussion can be found [here](https://hackernoon.com/functors-and-applicatives-b9af535b1440). Or if you want just the bare bones, then look no further than [here](https://tpolecat.github.io/2014/03/21/functor.html).