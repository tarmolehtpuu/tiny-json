plugins {
    java
    jacoco
}

group = "ee.moo"
version = rootProject.file("VERSION")
    .readText()
    .replace("\r", "")
    .replace("\n", "")
    .trim()

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:6.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:6.0.3")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.3")
}

tasks.jacocoTestReport {
    dependsOn(tasks.withType<Test>())
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.withType<JavaCompile> {
    options.release.set(17)
}

tasks.jar {
    archiveBaseName.set(project.name)
    archiveVersion.set(project.version.toString())

    from(project.rootDir) {
        include("LICENSE")
        include("NOTICE")
        into("META-INF")
    }

    from(sourceSets.main.get().output)

    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Sealed" to true,
            "Bundle-Copyright" to "Copyright 2026 Tarmo Lehtpuu",
            "Bundle-License" to "http://www.apache.org/licenses/LICENSE-2.0"
        )
    }
}
