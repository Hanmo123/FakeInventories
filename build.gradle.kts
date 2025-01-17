plugins {
    id("java")
    id("maven-publish")
}

group = "me.iwareq.fakeinventories"
version = "1.1.8-MOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.opencollab.dev/maven-releases")
    maven("https://repo.opencollab.dev/maven-snapshots")
}

dependencies {
    implementation(files("${project.rootDir}/../libs/Nukkit-MOT-SNAPSHOT.jar"))

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.IWareQ"

            from(components["java"])
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
}

tasks.withType<ProcessResources> {
    filteringCharset = Charsets.UTF_8.name()
    filesMatching("plugin.yml") {
        expand("version" to version)
    }
}
