package quality

//import io.gitlab.arturbosch.detekt.DetektPlugin
//import io.gitlab.arturbosch.detekt.extensions.DetektExtension
//
//apply<DetektPlugin>()
//
//configure<DetektExtension> {
//    config.from(files("$rootDir/config/detekt/detekt.yml"))
//
//    reports {
//        xml {
//            required.set(true)
//            outputLocation.set(project.file("build/reports/detekt/report.xml"))
//        }
//        html {
//            required.set(true)
//            outputLocation.set(project.file("build/reports/detekt/report.html"))
//        }
//    }
//}
//
//tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
//    exclude("**/resources/**,**/build/**")
//}
