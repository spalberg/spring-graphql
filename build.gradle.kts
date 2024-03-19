plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.netflix.dgs.codegen") version "6.0.3"
	id("org.graalvm.buildtools.native") version "0.9.28"
}

group = "de.palberg"
version = "0.0.1-SNAPSHOT"

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
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.micrometer:micrometer-tracing-bridge-brave")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")
}

val enablePreview = "--enable-preview"
tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
	options.compilerArgs.add(enablePreview)
	options.compilerArgs.add("-parameters")
}

tasks.withType<JavaExec> {
	jvmArgs(enablePreview)
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs(listOf(enablePreview))
}

tasks.generateJava {
	generateDataTypes = true
	schemaPaths = mutableListOf(
			"${projectDir}/src/main/resources/graphql",
			"${projectDir}/src/main/resources/graphql-client",
	)
	packageName = "de.palberg.graphql.codegen"
	generateClientv2 = true
	generateDataTypes = true
}
