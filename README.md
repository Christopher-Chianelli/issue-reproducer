# PyTest failure in Python 3.11 and above

When a generated class is called from python and throws an exception, PyTest get an internal error

# Build and Run

```
mvn clean install
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
pytest
```

# Expected Behavior

All tests being run and the exception being reported

# Actual Behavior

An internal error ending with
```text
INTERNALERROR>   File ".../venv/lib64/python3.12/site-packages/_pytest/_code/code.py", line 212, in lineno
INTERNALERROR>     return self._rawentry.tb_lineno - 1
INTERNALERROR>            ~~~~~~~~~~~~~~~~~~~~~~~~~^~~
INTERNALERROR> TypeError: unsupported operand type(s) for -: 'NoneType' and 'int'
```

