plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":controller"))
    implementation(project(":dao"))
    implementation(project(":service"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    testImplementation("org.mockito:mockito-core:4.8.0")
}

tasks.test {
    useJUnitPlatform()
}