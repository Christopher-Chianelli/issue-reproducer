# Ambiguous overload found for functional interfaces with different argument counts

When finding overloads for methods, a Python function maps to all functional interfaces, including functional interfaces that accept a different argument count

# Build and Run

```
mvn clean install
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
python jpype-ambiguous-overloads-issue.py
```

# Expected Behavior

No error being raised; in particular, the overload for BiFunction will be called if the Python function takes two arguments, the overload for Function will be called if the Python function takes one argument, No overload found otherwise.

# Actual Behavior

TypeError: Ambiguous overloads found for org.acme.MyClass.apply(function) between:
public static java.lang.String org.acme.MyClass.apply(java.util.function.BiFunction)
public static java.lang.String org.acme.MyClass.apply(java.util.function.Function)

