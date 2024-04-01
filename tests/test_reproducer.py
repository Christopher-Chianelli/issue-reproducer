def test_throwing():
    import jpype
    import jpype.imports
    jpype.startJVM(classpath=[
        'target/issue-reproducer-1.0.0-SNAPSHOT.jar',
        'target/dependency/asm-9.7.jar'
    ])
    from org.acme import MyClass
    consumer = MyClass.getConsumer()
    consumer.accept('My Error')

def test_b():
    pass