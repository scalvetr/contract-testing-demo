import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.cloud.contract") version "4.0.0-M2"
    // https://github.com/spring-cloud/spring-cloud-contract/blob/v3.1.2/pom.xml
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
    id("org.springframework.experimental.aot") version "0.11.4"
}

group = "com.scalvetr"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    maven { url = uri("https://repo.spring.io/release") }
    maven { url = uri("https://repo.spring.io/milestone") }
    mavenCentral()
    mavenLocal()
}

extra["springCloudVersion"] = "2022.0.0-M2"

dependencyManagement {
    dependencies {
        dependency("io.rest-assured:rest-assured:5.0.0")
        dependency("io.rest-assured:json-path:5.0.0")
        dependency("io.rest-assured:xml-path:5.0.0")
        dependency("org.apache.groovy:groovy:4.0.1")
        dependency("org.apache.groovy:groovy-xml:4.0.1")
        dependency("org.apache.groovy:groovy-json:4.0.1")
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.cloud:spring-cloud-starter")
    // spring doc
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    // https://github.com/pact-foundation/pact-jvm/issues/1529
    testImplementation("org.springframework.cloud:spring-cloud-contract-spec-kotlin")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}

contracts {
    testFramework.set(org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5)
    packageWithBaseClasses.set("com.scalvetr.demoproducer")
}

tasks.withType<Delete> {
    doFirst {
        delete("~/.m2/repository/com/scalvetr/demo-producer")
    }
}


tasks {
    contractTest {
        useJUnitPlatform()
        systemProperty("spring.profiles.active", "gradle")
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
        afterSuite(KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
            if (desc.parent == null) {
                if (result.testCount == 0L) {
                    throw IllegalStateException("No tests were found. Failing the build")
                } else {
                    println("Results: (${result.testCount} tests, ${result.successfulTestCount} successes, ${result.failedTestCount} failures, ${result.skippedTestCount} skipped)")
                }
            } else { /* Nothing to do here */
            }
        }))
    }
}
