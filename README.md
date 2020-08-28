# Multiple Synthetic Import Lint

A lint check to ensure each file contains at most 1 kotlinx.android.synthetic import.

# The problem
It is fairly easy to import synthetic extensions for the wrong layout file,
thus causing NullPointerExceptions.

This is a common source of bugs, and is arguably the biggest motivation to use ViewBinding over Synthetics.

However, Synthetics are still widely prefered over ViewBinding, [for many reasons](https://stackoverflow.com/a/58465306/6007104).
With this lint check, the biggest advantage of ViewBinding over Synthetics becomes moot.

# Rationale
You should only have 1 synthetic import in each file.
Having more than 1 synthetic import in a file is usually an mistake.
If not a mistake, it is in any case confusing and therefore discouraged.

# Exceptions
What if you really, really, really need to import multiple synthetic imports in a single file?

First of all, consider reworking the code - prefer to encapsulate logic related to each XML layout in its own [Custom or Compound View](https://developer.android.com/guide/topics/ui/custom-components).

If you still need to import multiple synthetic imports in a single file, you can suppress the lint check by adding this line as the very first line in your file:

`@file:Suppress("MultipleSyntheticImport")`

# Usage
Use via Jitpack:
https://jitpack.io/#Innovattic/multiple-synthetic-import-lint
