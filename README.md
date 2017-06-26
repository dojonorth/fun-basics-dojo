# Functional Basics Dojo
Putting the 'fun' in functional since 2017™

## Disclaimer
I'm currently doing my best to learn these concepts myself, and whilst I've done my best to ensure the correctness of everything I've written, it's entirely possible that I've made mistakes - especially in the nuances of the underlying category theory. I've included references to the majority of concepts I've covered, so if something seems off, you should be able to independently verify it. If you do find anything that's wrong, then give me a shout and I'll correct it.

## Goals
Functional programming languages - and additionally many elements of non-functional programming - have their foundations rooted in [lambda calculus](https://en.wikipedia.org/wiki/Lambda_calculus). Lambda calculus is primarily concerned with the application of functions - their composition; higher-order functions; currying etc. [[1]](####1-Lambda Notes). Whilst lambda calculus provides much of the basic framework of functional programming - in essence it *is* functional programming - recently ideas from another branch of mathematics have been increasingly leveraged by functional programmers. This branch is [Category Theory](https://en.wikipedia.org/wiki/Category_theory) and is what this dojo focuses on.
   
Category theory is a vast and crushingly abstract field of mathematics and I'm not going to come close to covering all of it, because:
1. It would take far too long.
2. A complete understanding isn't needed to apply these concepts. From my experience, it's fine to just understand the general concepts.
3. Most importantly, I only understand the tip of the iceberg myself.

Instead, this dojo is going to be whistlestop tour through a few of the core concepts that have become fashionable in the functional domain. Hopefully, by the end of the dojo, you'll have a good understanding of these concepts and where they're applicable. If nothing else, you'll have a fighting chance if a future interviewer / pair-programming partner attempts to blind you with science by talking about semigroups, free monads and the like... (although if you really want the tools to be able to take them on see [[2]](####2-General Category Theory)).

## The exercises
The dojo takes a workshop-type format. Generally, for each concept, this document provides a little prose, giving some background to the concept and then there'll be a few exercises to complete based around it. These exercises take the format of code that needs writing to make the provided failing tests pass. Initially, all of the failing tests are set to be ignored, so you'll want to un-ignore them as you go along.
 
The exercises build on each other and share a narrative thread. You could possibly skip the prose and just do the exercises or vice-versa, but I'd recommend going through it in order. Similarly, it's strongly advisable to ensure they pass before moving on to the next. If needs be, I've included the answers, so if you get really stuck then just copy the answer in and move on.

##1. Categories
Unsurprisingly, at the heart of category theory, are categories. A category is a simple algebraic data structure that consists of two main collections:
1. **Objects -** the 'things' within the category. These can be thought of as the actual data. For example '2', '3' and '345234' within Integers. They're commonly represented using capital letters e.g. *A*, *B*, *C* etc.
2. **Morphisms -** the relationships within the category. These are mappings go from one source object (A) to another target object (B) and are usually represented using arrows e.g. A → B. An example of a morphisms on Integers would be the '+ 2' operation.

Categories lend themselves well to being represented pictorially as objects and arrows between them. The exercise focuses on magical creatures called Codemon (objects) that can evolve (morphisms), we can depict this as:

[TODO: INSERT PICTURE 1]

There are a few things to take from the diagram:
* *It's missing identity morphisms:* Category theory states that each object should feature a morphism that goes from itself to itself. I haven't bothered to include these on the diagram. 
* *At the core of categories is the concept of composition:* If we have A → B and B → C then there must be a corresponding A → C. I've shown this on the diagram using the composition operator '∘'.

TODO: Not sure this next bit is true now - think I've covered it with the identity and associativity stuff now (see http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala) 
Additional restrictions apply to the collection of objects and arrows for them to qualify as a true mathematical category, but we'll skip over these. See [[2]](####2-General Category Theory) for additional information if you want to round out your understanding.

####Exercise
Open CodemonSpec and un-ignore and make pass the tests.

Here we establish a basic category that we will work with in subsequent exercises.

Additional notes:
* Note the basic Codemon trait. It contains three Codemon provided represent the objects within our new category. Feel free to add more, but subsequent tests just assume the presence of these three.
* The identity function is a required component of a category. It just returns whatever is passed to it. This could be a method on the Codemon objects, but I've instead chosen to leave it as a freestanding function in the companion object. I've followed this convention throughout the exercise as a whole; it's a slightly arbitrary decision, but I feel it represents a more idiomatic approach that highlights the distinction between data and functions. In real-world coding though, I'd tend towards putting functions within the instances themselves as I feel it makes for simpler code and aids discoverability.
* Evolve is a basic morphism we'll add to our category. Create the function as guided by the spec and depicted by the diagram. Note the simplifying assumption that a RaabyChu evolves to itself - this is a little weird, but simplifies subsequent exercises. 

##2. Functors
A functor F is a transformation between two categories X and Y. F must map every object and morphism from A to B. We'll gloss over this and concentrate on their application in functional programming (see [[3]](####3-Functors) for more detail if you're interested).

Normally, when you perform a function on a value, say +2 on the integer 3 the behaviour is fixed. In order to better understand functors, it's convenient to extend this to the idea of a value within an associated context. Commonly, this is depicted as the idea of a value with a 'box' that defines the context. Now, depending on the context, the behaviour of +2 will change. For example in the case of a List context, +2 would be applied to every element in the list, or in the case of a Promise (or Future) +2 would only be applied once the value had been evaluated.

A functor is a typeclass with a method that defines how functions are applied to it. Usually the method is called 'map' (sometimes 'fmap'). 

TODO: Write more about what it is.

####Exercise - Part 1
Open CodeballSpec and un-ignore and make pass the tests.

Here, we introduce the Codeball - a type that can have a Codemon inside it or not. When there is a Codemon inside, then the map method will be applied to it, whereas when the context is that it's empty, then the map method will do nothing.

Additional notes:
* What we're creating here is the simplest possible implementation of what is effectively the Option data type. Normally, this would not be a type unto itself, but would be a *type constructor* i.e. you would give it a type argument in order to turn it into a type, like Option[Int] or Option[Boolean]. 
* As I alluded to earlier, a functor normally maps between categories. This makes sense in the case of a type constructor like Option, where we could introduce a function isOdd: Int -> Boolean that would then convert the type of our Option from Option[Int] to Option[Boolean]. We will see this in a subsequent exercise. Codeball is a special case known as an 'endofunctor' that always stays within the same category.
* Note that this has no meaning in the empty codeball and so we have no choice but to throw an exception. This is a bit of wrinkle in pure functional terms and shows a little of Scala's OO/imperative roots. It's actually what Scala option does too, so I think there's no way around it. In super functional languages like Erlang, I believe that's not possible to actually get at the contained object so this wouldn't be an issue (TOTO: Check the avlitiy of this statement and clean up language apropos category theory). 
* Being able to map across a collection that may contain either something or nothing without having to differentiate is a very powerful pattern that allows for the streamlining of programming to single logical pipes that don't feature continuous branching - so-called railway-orientated programming.

####Exercise - Part 2
Open CodemonCentreSpec and un-ignore and make pass the tests.

Now, we create a full-blown type constructor that we make into a functor: The codemon centre can contain multiple entries for any given type and so the corresponding map functor capability can convert between types. 

##3. Monads
Monads have a semi-mythical status in computing. They change your mind in such a way that once you understand monads, you become incapable of explaining monads. Well hopefully I'm just 99% of the way to understanding them as I think there's not actually that much to the concept. As [has been pointed out](https://bartoszmilewski.com/2016/11/21/monads-programmers-definition/) people end to overestimate their complexity, as they confuse the myriad of applications with the concept itself. 

Just like how a functor boiled down to something that has a map operation on it, a monad is a type constructor - i.e. it takes a type parameter and so is written as M[A]  - and has two distinct operations on it:
* **Pure -** a method that takes a value of a plain type and puts it into a monad creating a monadic value. This is effectively the monad constructor. It goes by many aliases across the programming world: return in Haskell, unit in Scala, sometimes pure elsewhere, occasionally zero
* **Bind -** a method that performs as per the functor map operation in that it contextually applies the function to the contents of the monad, but then performs an additional flattening step that will be described in more detail later. Again, this operation goes by other names >>= in Haskell and flatMap in Scala.

I prefer the Scala name for bind, which is flatMap, as it describes what the operation does: it maps over the monad's values and then flattens the result. As with functors, just what this flatten operation does is contextual to the monad in question, but in essence it takes any nested instances within the Monad and then 'flattens' them out into a single monad. For example:
* *The List Monad -* List(List(1,2,3), List(4), List(5,6,7)).flatten() would give List(1,2,3,4,5,6,7)
* *The Option Monad -* Some(Some(x)).flatten would give Some(x), whereas Some(None) gives None

This means that the function that is passed to flatMap, must itself return an instance of the monad, as the exercise will show in depth. We'll go into the benefits of monads later, but even in the two examples listed above, the power of flatMap over map can be seen:
* *The List Monad -* we can now change the number of elements in the list. Using map only allows us to transform the elements, whilst the total number remains the same.
* *The Option Monad -* We can actually change from a Some to a None. Using map only allows for the value within a Some to be altered.

Hence, it should come as no surprise that flatMap is much more powerful than map. This is illustrated by the fact that it's possible to write map in terms of flatMap, but not vice-versa. If the two had a fight, flatMap would win everytime.

[FLATMAP WINS FIGURE]

####Exercise
Open CodeBoxSpec and un-ignore and make pass the tests.

Now we're really upping the ante! We introduce the codeBox a branded box, total distinct from a similar, much cheaper box, that can contain anything! It features both monad and functor operations and so we can vary the number of items in the box and even change their types!

##4. Praxis
TODO: - EXPLANATION:
SEQUENCING


1. Practice applying what we've built int he exciting world of Codemon!

####Exercise

TODO - EXERCISE OUTLINE:
1. Open CodemonWorld
2. Write the throw codeball function. Has a random chance to catch a codemon or return an empty codeball. Want:
 - 10% chance to capture a Rusa
 - 20% chance to capture a Sikachu (run around a lot, so lots of oportunity to capture them)
 - 1% chance to capture a RaabyChu (to clever to capture)
 - 69% chance to capture nothing.
TODO: More explanation on using the stateful random number generator. Need to tidy up the random number generation. Currently, it's too implementation specific tied.
3. Everyone knows that RaabyChus are where the money is! New advanced Codemon technology now allows for instantaneous forced evolution of Codemon! We can make more cash! The downside is that there's a 75% chance of the Codemon dying during the process (in which case we get back an empty Codeball). Still, money is money, right? Write a function that:
  - Takes a Codemon and Returns a Codeball
  - Evolves the Codemon to it's final form. We know the final form is reached as calling evolve on it causes no change.
  - Each time a Codemon is forcibly evolved, there's a 75% chance it dies and we get an empty ball back.
4. Finally, we're putting it all together. We're implementing an entire industrial process that does the following:
  - Captures a number of Codemon
  - Throws out any empty Codeballs
  - Force evolves all of the Codemon toi be Raabychus
  - Discards all of the empty Codeballs (corpses)
  - Returns all of the RabbyChus in Codeballs in CodeBox.
TODO: Tighten this up a bit. Needs a bit more explanation.

"flatMap that shit"
 

TODO: Nice to get a for comprehension in there.


TODO - GENERAL:
TODO: Section on Monoids?
TODO: Maybe take away some function signatures to make it harder
TODO: Emphaise it's possible to race through, but better to understand things. Rally drill home concepts.

## Further Reading
####1-Lambda Notes
See [here](https://medium.com/javascript-scene/the-rise-and-fall-and-rise-of-functional-programming-composable-software-c2d91b424c8c) as a starting point for more detail on the relationship between Lambda Calculus and programming languages. Few interesting nuggets to whet your appetite:
* The 'calculus' in Lambda Calculus has nothing to do with integration and differentiation that we all know and love. Rather, it refers to the more general meaning of calculus, which defines a 'method or system for calculation or reasoning'.
* Lisp, that we also all know and love, was heavily influenced by lambda calculus. [Lisp dates from 1958 and is the second oldest prograaming language still in widespread use](https://en.wikipedia.org/wiki/Lisp_(programming_language)), only Fortran edges it out by a year.
* As I alluded to above, lambda calculus essentially *is functional programming*. The application of category theory concepts is just a recent innovation built on top of it.

####2-General Category Theory
I've tried to inline links to the general concepts that the dojo covers as I've gone along. If you're interested in a more complete discussion of parts of category theory that apply to programming though, then I'd single out [this for special mention](https://bartoszmilewski.com/2014/10/28/category-theory-for-programmers-the-preface/).

If you're after a more accessible, but less thorough, take on most of the material that the dojo covers, then I'd recommend [this](http://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html).

Finally, if you're after a balance between the two [then this is good](http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala). As is [this](http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/).

####3-Functors
http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/ (Functors Section)

Basic narative:
Say how map is really good, but ti can't change the artity. Very limited in that sense.

Exercise Narrative
1. CodemonSpec: Introduce category idea & basic morphism
  TODO: Put traits in the concepts section.

2 - 3. CodeballSpec: Introduce functor lite.
  Say how what we've produced here is a very limit isomorphic construct to pave the way.
  We've put the map function on the codeball to make it more familiar too. From now on, we're going hardcore FP and have moved it off.

4. AdvancedCodeBallSpec: This is the real thing.
  Have moved the map method out for more FP style and put in a trait
  TODO: Uninspired name - the vaneer over this just being an Option is obvious coming off
  Now a category constructor or whatever it's called [TODO: Check]
  Moral: Basically an option.

5. More MasterCodeballSpec - Do real functor stuff
  Functors have other rules, but we don't care about them.
  Now can change type.
  But can never change outside context - as will be exposed.

6 - 9. WildCodemonCaptureSpec - Functor application. Time for some praxis.
  Basically modeling sequencing with a failure model (railway-orientated programming)
  Moral: Note the sequencing - imagine how would / could relate to reading a file then a value from it etc.
  Note how level of nesting at the end is undesirable - super clunky. We need something better.

10 - 11. MasterCodeballSpec - Welcome to party (Monad)
  Haven't extended the AdvancedCodeball. Could have done. Up to you if you want to. Then just pretend it's different.
  They decide what sensible behaviour is (sort of). But I've decided it should be that should be that any EmptyBalls make for an Empty.
  Flatten is not strictly needed, but it simplifies the path to flatMap.
  TODO: Check. Believe that we don't actually need map exposed on interface, but we do need to have map functionality underneath as we map on it. Can make it from the tools we have.
  Pure operation always gives us back a Some - None is an object.
  FlatMap actually lets us now change the outer context.
  Can write what you want, but it should at least make sense. Could have multiple interpretations, but probably not.
  Need a bit more stressing when to use flatMap.

  FINAL - Play thing - do our own map and flatten
  Do chainning like before but now better

12. ImprovedWildCodemonCaptureSpec
  Writing map in terms of flatmap shows how it lords it over it. You effectively have to write map top get flatMap though.
  Compare and contrast the hassle of using map only to sequence operations.
  Note the return type is no longer stupid.
  Talk about the pattern of flatmap, flatmap then map being very common. Leads to for comprehension.

13. Bonus: Write the Codebox.
  Hint - this is basically just a list.
  I've not spent too long on it.

14 - 16. CodeboxSpec
  Effectively list.
  Can piggyback on existing List functionality or do it yourself, depending on your inclination and time.
  Feel free to tinker with the tests
  I've not gone back over it, so mileage may vary.