# Category Theory in Functional Programming 101 Dojo
This is a crash course in the basics.

#Introduction
Functional programming (FP) has been around for a long time. Recent findings have revealed that the T-Rex probably programmed in Lisp.
During this time, the set of core concepts associated with FP has remained reasonably constant. These concepts are strongly rooted in [lambda calculus](https://en.wikipedia.org/wiki/Lambda_calculus).
Lambda calculus is primarily concerned with the application of functions - their composition; higher-order functions; currying etc. [[1]](####1-Lambda Notes). Essentially lambda calculus *is functional programming*.

Recently though, concepts from another area of mathematics have increasingly become prevalent in the FP-scence: Category Theory (CT).
We work in an industry that is subject to subject to swings in what's fashionable and CT concepts are most certainly bang on-trend at the moment, to the point where CT's sometimes overstated is if it's an integral part of what defines the FP paradigm.
I don't think that's the case. FP is still at it's core about functions and their interactions, just as it's always been. CT concepts provides us with a number approaches that build on top of normal FP concepts and allow for many common problems to be solved in really slick ways.
My favourite description of the relationship between CT and FP that I've read is that CT gives us the 'Design Patterns' for the functional domain.

Category Theory itself is a vast and crushingly abstract field of mathematics. I only understand - or at least have managed to convince myself that I understand - a very small portion of it.
That's fine though, as my goal here isn't to provide you with a complete exposition into category theory, replete with the required mathematical rigour, but instead to concentrate on the application of a few core concepts, without getting too bogged down.
The goal is that I'll try and present a simplified view of the underlying mathematics: there will be some omissions, but what's there should be 99% correct, with the 1% having been sacrificed for brevity and not impinging on the application of the concepts.  

**Disclaimer:** I'm *far* from a CT expert. I learnt about it myself for the dojo, so if you notice any inconsitencies or errors, then flag me up and gladly correct it.

## Further Reading
####1-Lambda Notes
See [here](https://medium.com/javascript-scene/the-rise-and-fall-and-rise-of-functional-programming-composable-software-c2d91b424c8c) as a starting point for more detail on the relationship between Lambda Calculus and programming languages. Few interesting nuggets to whet your appetite:
* The 'calculus' in Lambda Calculus has nothing to do with integration and differentiation that we all know and love. Rather, it refers to the more general meaning of calculus, which defines a 'method or system for calculation or reasoning'.
* Lisp, that we also all know and love, was heavily influenced by lambda calculus. [Lisp dates from 1958 and is the second oldest prograaming language still in widespread use](https://en.wikipedia.org/wiki/Lisp_(programming_language)), only Fortran edges it out by a year.