package quality

val ktlint: Configuration by configurations.creating
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    ktlint(libs.findLibrary("ktlint").get())
}

tasks {
    register<JavaExec>("ktlint") {
        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args(
            "src/**/*.kt",
            "--reporter=plain",
            "--reporter=checkstyle, output=${layout.buildDirectory}/reports/ktlint.xml"
        )
    }

    register<JavaExec>("ktlintFormat") {
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args("-F", "src/**/*.kt")
    }
}

tasks.getByName("check") {
    setDependsOn(listOf(tasks.getByName("ktlint")))
}
