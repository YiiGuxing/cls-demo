import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "1.6.0"
    id("org.jetbrains.intellij") version "1.3.0"
}

group = "me.yiiguxing.demo.cls"
version = "0.1"

repositories {
    mavenLocal()
    maven(url = "https://maven.aliyun.com/repository/public")
    maven(url = "https://maven-central.storage-download.googleapis.com/repos/central/data/")
    maven(url = "https://repo.eclipse.org/content/groups/releases/")
    maven(url = "https://www.jetbrains.com/intellij-repository/releases")
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test", "1.6.0"))
}

sourceSets.main {
    java.srcDirs("src/main/gen")
}

intellij {
    pluginName.set("Simple HTML Support")
    version.set("2020.3.4")
    type.set("IU")
    plugins.set(listOf("PsiViewer:203-SNAPSHOT"))
}

tasks {

    patchPluginXml {
        version.set("0.1")
        sinceBuild.set("203")
        untilBuild.set("")
    }

    buildSearchableOptions {
        enabled = false
    }

    test {
        useJUnitPlatform()
    }

    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}