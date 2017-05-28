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

TODO: Cover lightly laws that define categories.
TODO: Say that I've skipped a lot here, but this is really a light touch.

TODO: REF
http://nikgrozev.com/2016/03/14/functional-programming-and-category-theory-part-1-categories-and-functors/

TODO: Describe the exercise.

## Further Reading
####1-Lambda Notes
See [here](https://medium.com/javascript-scene/the-rise-and-fall-and-rise-of-functional-programming-composable-software-c2d91b424c8c) as a starting point for more detail on the relationship between Lambda Calculus and programming languages. Few interesting nuggets to whet your appetite:
* The 'calculus' in Lambda Calculus has nothing to do with integration and differentiation that we all know and love. Rather, it refers to the more general meaning of calculus, which defines a 'method or system for calculation or reasoning'.
* Lisp, that we also all know and love, was heavily influenced by lambda calculus. [Lisp dates from 1958 and is the second oldest prograaming language still in widespread use](https://en.wikipedia.org/wiki/Lisp_(programming_language)), only Fortran edges it out by a year.

####2-General Category Theory
I've tried to inline links to the general concepts that the dojo covers as I've gone along. If you're interested in a more complete discussion of parts of category theory that apply to programming though, then I'd single out [this for special mention](https://bartoszmilewski.com/2014/10/28/category-theory-for-programmers-the-preface/).

If you're after a more accessible, but less thorough, take on most of the material that the dojo covers, then I'd recommend [this](http://adit.io/posts/2013-04-17-functors,_applicatives,_and_monads_in_pictures.html).

Finally, if you're after a balance between the two [then this is good](http://www.cakesolutions.net/teamblogs/category-theory-patterns-in-scala).