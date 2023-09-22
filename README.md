An API to create Minecraft launchers ```4.4.1```
---

- This projects needs Java 8 or up.
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
        <version>4.4.1</version>
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
    implementation 'com.github.danieldieeins:OpenLauncherAPI:4.4.1'
    //Other dependencies...
}
```
