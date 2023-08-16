An API to create Minecraft launchers ```4.1.0```
-

Including following projects
- FlowMultitools: https://github.com/FlowArg/FlowMultitools (v1.4.0)
- FlowUpdater: https://github.com/FlowArg/FlowUpdater (v1.8.0)
- OpenLauncherLib: https://github.com/FlowArg/OpenLauncherLib (v3.2.6)
- OpenAuth: https://github.com/FlowArg/OpenAuth (1.1.5)

---

- This projects needs Java 17 or up.
- This project will likely be updated with new features and custom and; or modified code.
- Take a look to check the dependencies: https://github.com/danieldieeins/OpenLauncherAPI/blob/master/pom.xml

---

- ``NEW: SimpleMicrosoftAuth | NEW: AESUtil``

How to implement (Maven/Gradle)
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
        <version>VERSION</version>
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
    implementation 'com.github.danieldieeins:OpenLauncherAPI:VERSION'
    //Other dependencies...
}
```
