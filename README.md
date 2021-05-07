= Reproducer

When only scoreManager is used, it works correctly (score -1).
If you inject solverManager, however, it fails (score 0). You
don't even need to use solverManager; injection is enough.

The only thing inject does is create a SolverManager
instance (which in turn create a Solver instance),
so it seems the act of creating a Solver modifies the
Score Director.
