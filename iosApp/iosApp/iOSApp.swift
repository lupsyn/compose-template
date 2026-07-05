import SwiftUI
import ComposeTemplate

@main
struct iOSApp: App {
    init() {
        // Kotlin/Native exports functions whose names begin with "init" with a
        // "do" prefix to avoid clashing with the Objective-C init family, so the
        // Kotlin `initKoin()` is called here as `doInitKoin()`.
        MainViewControllerKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
