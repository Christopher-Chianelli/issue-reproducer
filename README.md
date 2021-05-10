= Reproducer

`mvn clean install` followed by `mvn exec:java` should print "James Bond detected.". Calling KieBaseUpdaterANC.generateAndSetInMemoryANC twice causes rules not to fire. This is a particular problem in Quarkus since the KieBases are cached.
