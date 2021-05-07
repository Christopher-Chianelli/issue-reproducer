= Reproducer

`mvn quarkus:dev` should print "James Bond detected." if a variant of `drools.propertySpecific=ALLOWED` is specified in application.properties (to configure default behavior), but currently all variants of `drools.propertySpecific` is ignored.

Class annotation `@ClassReactive` works, but if you want the default to be `@ClassReactive`, you would need to annotate every class.