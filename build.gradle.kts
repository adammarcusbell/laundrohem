import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
	id ("java-test-fixtures")
	id ("java-library")
}

group = "com.laundrohem"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

sourceSets {
	create("integrationTest") {
		compileClasspath += sourceSets.main.get().output
		runtimeClasspath += sourceSets.main.get().output
	}
}

val integrationTestImplementation by configurations.getting {
	extendsFrom(configurations.implementation.get())
}

configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.data:spring-data-r2dbc")
	implementation("org.springframework.data:spring-data-jdbc")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springdoc:springdoc-openapi-webflux-ui:1.5.6")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.5.6")

	implementation("io.r2dbc:r2dbc-postgresql")
	implementation("org.liquibase:liquibase-core:3.8.0")

	runtimeOnly("org.postgresql:postgresql:42.2.19")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")

	integrationTestImplementation("junit:junit:4.13")
	integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")
	integrationTestImplementation("io.projectreactor:reactor-test")
	integrationTestImplementation(testFixtures(project))
}

val integrationTest = task<Test>("integrationTests") {
	description = "The Integration Tests"
	group = "verification"

	testClassesDirs = sourceSets["integrationTest"].output.classesDirs
	classpath = sourceSets["integrationTest"].runtimeClasspath
	shouldRunAfter("test")
}

tasks.check { dependsOn(integrationTest) }

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

springBoot {
	buildInfo()
}