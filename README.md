# Functional Basics Dojo
Putting the 'fun' in functional since 2017™

## Disclaimer
I'm currently doing my best to learn these concepts myself, and whilst I've done my best to ensure the correctness of everything I've written here, it's entirely possible that I've made mistakes - especially as we get towards the underlying theory becomes more nuanced. I've included references to the majority of concepts I've covered, so if something seems off, you should be able to independently verify it. If you do find anything that's wrong, then give me a shout and I'll correct it.

TODO: Stress I'll have dropped the ball on some nuances.

## Goals
Functional programming languages - and additionally many elements of non-functional programming - have their foundations rooted in [lambda calculus](https://en.wikipedia.org/wiki/Lambda_calculus). Lambda calculus is primarily concerned with the application of functions - their composition; higher-order functions; currying etc [[1]](####1-Lambda Notes). Whilst lambda calculus provides much of the basic framework of functional programming, more recently ideas from another branch of mathematics have been increasingly leveraged by functional programmers. This branch is [Category Theory](https://en.wikipedia.org/wiki/Category_theory) and is at the centre of what this dojo focuses on.
   
Category theory is a vast and complicated field of mathematics and I'm not going to come close to covering all of it, because:
1. It would take far too long
2. More importantly, I only understand the tip of the iceberg myself.

Instead, this dojo is going to be whistlestop tour through a few of the core concepts, culminating in the monad. Hopefully, by the end of the dojo, you'll have a good understanding of these concepts and where they're applicable. If nothing else, you'll have a fighting chance if a future interviewer / pair-programming partner attempts to blind you with science by talking about applicative functors, free monads and the like... (although if you really want the tools to be able to take them on see [[2]](####2-General Category Theory)).

TODO: Good introduction as to why it's useful here. Include some ideas: http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/


## The exercises
The dojo takes a workshop-type format. Generally, for each concept, this document provides a little prose, giving some background to the concept and then there'll be a few exercises to complete based around it. These exercises take the format of code that needs writing to make the provided failing tests pass. Initially, all of the failing tests are set to be ignored, so you'll want to un-ignore them as you go along.
 
The exercises largely build on each other and share a narrative thread. You could possibly skip the prose and just do the exercises or vice-versa, but I'd recommend going through it in order. Similarly, it's strongly advisable to ensure they pass before moving on to the next. If needs be, I've included the answers, so if you get really stuck then just copy the answer in and move on.

###1. Categories
Unsurprisingly, at the heart of category theory, are categories. A category is a simple algebraic data structure that consists of two main collections:
1. **Objects -** the 'things' within the category. These can be thought of as the actual data. For example '2', '3' and '345234' within Integers. They're commonly represented using capital letters e.g. *A*, *B*, *C* etc.
2. **Morphism -** the relationships within the category. These are mappings go from one source object (A) to another target object (B) and are usually represent using arrows e.g. A → B. An example morphism on Integers would be the '+ 2' operation that for 

TODO - EXPLANATION:
* Cover lightly laws that define categories.
* Say that I've skipped a lot here, but this is really a light touch.
* Read http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/

TODO - EXERCISE OUTLINE: 
1. Open CodemonSpec.
2. Already have the basic Codemon trait. This represents the object part of the category.
3. Have three objects within the category, the three Codemon types.
4. Create a basic identity function. Technically needed. Just returns whatever is passed to it. Could do this on the Codemon, but we'll leave it as a freestanding function in the companion object. More functional that way.
5. Evolve is a basic morphism we'll add to our category. Create the function as guided by the Spec. As before, we choose to put the behaviour on companion object as it emphasises the distinction between function and state. In practice you might have it as a method on the trait.

##2. Functors
TODO - EXPLANATION:
1. Say how functor applies function on state with context and behaviour differs.
2. Functor applies function in situ and value remains in context.
3. We're going to do classic example of having a representation of a value that can be something or nothing. In our case this is represented by the Codeball trait.
4. In nothing case, there's nothing to apply the function to, so it does nothing. Crucialy, it does't throw an exception.
5. In something case, the value is modified but the external context doesn't change.
6. This is opens up the possibility to apply functors across collections of things with different internal contexts, causing different results. This is a very powerful pattern, as we'll see.

TODO - EXERCISE OUTLINE:
1. Open CodeBallSpec.
2. Write a method to extract the codemon from the ball. Note that this has no meaning in the empty codeball and so we have no choice but to throw an exception. This is a bit of wrinkle in pure functional terms and shows a little of Scala's OO/imperative roots. It's actually what Scala option does too, so I think there's no way around it. In super functional languages like Erlang, I believe that's not possible to actually get at the contained object so this wouldn't be an issue (TOTO: Check the avlitiy of this statement and clean up language apropos category theory). 
3. Write the map functor for both. TODO: Say about how for now we're limiting map to sticking within the same category.
4. Open CodemonCentreSpec
5. The Codemon centre can operate across all of the Codeballs within it. Highlights how powerful the functor can be. Implement this new map functionality (depending on how keen you are, you may be able to leverage existing map functionality on the container you use for the CodeBalls - e.g. list - or you may handle the construction of and mapping over a storage class form basic principles (or maybe even come back and remimplement it if you finish everything else!)).

##3. Monads
TODO - EXPLANATION:
1. This is thought of as a very powerful construct. It calls itself 'The Cream!' (not true).
2. Previously functors limited us in that we only change what was already there between types. We couldn't create or destroy things. For example on a list of numbers (1,2,3), map will let us change the numbers, for example add 4 to them all, but we can't remove or create new numbers.
3. Monads are effectively defined by a much more powerful operation - flatMap. The contents we're manipulating is like energy - with map we are like engineers constrained by the first law of thermodynamics, but with flatMap, we are as gods! TODO: Yeah... maybe work on that simile.  
4. TODO: More category theory stuff.

TODO - EXERCISE OUTLINE:
1. Open CodeBoxSpec
2. We're now introducing a new more powerful container. The CodeBox! It has completely unheard of powers - it can contain anything!
3. Implement basic functor functor functionality (the map operation) for the CodeBox. Note now that we're allowing map to change the type it operates on.
4. Implement the flatten operation. TODO: Bit more explanation.
5. Implement flatMap. Behold its majesty. Note that it's possible to implement map in terms flatMap, but not the other way around!

##4. Praxis
TODO: - EXPLANATION:
1. Practice applying what we've built int he exciting world of Codemon!

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

####2-General Category Theory
I've tried to inline links to the general concepts that the dojo covers as I've gone along. If you're interested in a more complete discussion of parts of category theory that apply to programming though, then I'd single out [this for special mention](https://bartoszmilewski.com/2014/10/28/category-theory-for-programmers-the-preface/).

If you're after a more accessible, but less thorough, take on most of the material that the dojo covers, then I'd recommend [this](http://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html).

Finally, if you're after a balance between the two [then this is good](http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala).