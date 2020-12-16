package planner5d

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class AppStartupRunner(private val projectsImporter: ProjectsImporter) {

    @PostConstruct
    fun init() {
        projectsImporter.import()
    }
}