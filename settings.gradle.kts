pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PruebaTecnica"
include(":app")
include(":feature_home")
include(":feature_home:data")
include(":feature_home:domain")
include(":feature_home:presentation")
include(":feature_genres")
include(":feature_genres:data")
include(":feature_genres:domain")
include(":feature_genres:presentation")
include(":feature_search")
include(":feature_search:data")
include(":feature_search:domain")
include(":feature_search:presentation")
include(":shared")
include(":shared:presentation")
include(":shared:domain")
include(":shared:data")
