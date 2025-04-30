# toString returning NULL cause segmentation fault

If toString returns null (due to programmer error or bad design),
a segmentation fault occurs when calculating the `str` of the Object in JPype

## Build and Run

```
mvn clean install
python -m venv .venv
source venv/bin/activate
pip install -r requirements.txt
python reproducer.py
```

## Expected Behavior

```
Java: null
Python: null
```

## Actual Behavior

```
Java: null
#
# A fatal error has been detected by the Java Runtime Environment:
#
#  SIGSEGV (0xb) at pc=0x00007f78b748ee70, pid=80377, tid=80377
#
# JRE version: OpenJDK Runtime Environment Temurin-17.0.8+7 (17.0.8+7) (build 17.0.8+7)
# Java VM: OpenJDK 64-Bit Server VM Temurin-17.0.8+7 (17.0.8+7, mixed mode, sharing, tiered, compressed oops, compressed class ptrs, g1 gc, linux-amd64)
# Problematic frame:
# V  [libjvm.so+0x28ee70]  AccessInternal::PostRuntimeDispatch<G1BarrierSet::AccessBarrier<548964ul, G1BarrierSet>, (AccessInternal::BarrierType)2, 548964ul>::oop_access_barrier(void*)+0x0
#
# Core dump will be written. Default location: Core dumps may be processed with "/usr/lib/systemd/systemd-coredump %P %u %g %s %t %c %h" (...)
#
# An error report file with more information is saved as: ...
#
# If you would like to submit a bug report, please visit:
#   https://github.com/adoptium/adoptium-support/issues
#
Aborted (core dumped)
```

