plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.mobo"
version = "2.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven(url = "https://maven.vaadin.com/vaadin-prereleases")
    maven(url = "https://maven.vaadin.com/vaadin-addons")

}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("com.alibaba:fastjson:2.0.46")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.mapdb:mapdb:3.1.0")
    implementation("com.vaadin:vaadin-spring-boot-starter")

}

dependencyManagement {
    imports {
        mavenBom("com.vaadin:vaadin-bom:24.3.5")
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}
