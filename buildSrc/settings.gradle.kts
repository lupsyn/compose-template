dependencyResolutionManagement {
    versionCatalogs {
        create("baseLibs") {
            from(files("libs.versions.toml"))
        }
    }
}
