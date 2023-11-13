import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.17"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}


allprojects {
	group = "com.ddn"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

subprojects {

	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-kapt")

	dependencies {
		// implementation("org.springframework.boot:spring-boot-starter-security")

		implementation("org.springframework.boot:spring-boot-starter-web")
		implementation("org.springframework.boot:spring-boot-starter-webflux")

		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

		developmentOnly("org.springframework.boot:spring-boot-devtools")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("io.projectreactor:reactor-test")
		testImplementation("org.springframework.security:spring-security-test")
	}
}



