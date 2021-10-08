# JConversion does not work for java.lang.Class

When looking for overloads for methods, java.lang.Class conversions are ignored.

# Build and Run

```
mvn clean install
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
python jpype-convert-issue.py
```

# Expected Behavior

Either an error on the JConversion that says conversions for 'java.lang.Class' are not
possible or no assert error being raised

# Actual Behavior

TypeError: No matching overloads found for *static* org.acme.MyClass.getClassSimpleName(ProxyClassInstance), options are:
	public static java.lang.String org.acme.MyClass.getClassSimpleName(java.lang.Class)
