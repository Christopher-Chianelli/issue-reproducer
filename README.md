# convertStrings=True cut Java returned Strings with NULL bytes short

When `jpype.startJVM(convertStrings=True)` is used, returned Strings with NULL bytes are cut short.
This behavior is not observed when `jpype.startJVM(convertStrings=False)` is used.

# Build and Run

```
mvn clean install
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
python jpype-convert-issue.py
```

# Expected Behavior

MyClass.getArgument('String\u0000with\u0000NULL\u0000bytes') == 'String\u0000with\u0000NULL\u0000bytes'

# Actual Behavior

MyClass.getArgument('String\u0000with\u0000NULL\u0000bytes') == 'String'

