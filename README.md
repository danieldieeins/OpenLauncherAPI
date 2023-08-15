This is a pack of FlowArg's three Launcher Tools
-

- FlowMultitools: https://github.com/FlowArg/FlowMultitools
- FlowUpdater: https://github.com/FlowArg/FlowUpdater
- OpenLauncherLib: https://github.com/FlowArg/OpenLauncherLib

This project is compatible with Java 17 because it is not based on Gradle but on Maven.
This project will likely be updated with new features and custom and; or modified code.

How to implement
-
Maven repository:
```
<repositories>
    <repository>
        <id>jitpack</id>
        <url>https://jitpack.io</url>
    </repository>
    <!--Other repositories...-->
</repositories>
```
Maven dependency:
```
<dependencies>
    <dependency>
        <groupId>com.github.danieldieeins</groupId>
        <artifactId>OpenLauncherAPI</artifactId>
        <version>e8f96d9</version>
        <scope>compile</scope>
    </dependency>
    <!--Other dependencies...-->
<dependencies>
```
---
Gradle repository:
```
repositories {
    maven { url 'https://jitpack.io' }
    //Other repositories...
}
```
Gradle dependency:
```
dependencies {
    implementation 'com.github.danieldieeins:OpenLauncherAPI:e8f96d9'
    //Other dependencies...
}
```
