package planner5d

import org.springframework.stereotype.Component
import planner5d.repository.ProjectsRepository

// https://stackoverflow.com/questions/35479631/how-to-use-spring-annotations-like-autowired-in-kotlin
// https://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in
@Component
class ProjectsImportManager(private val repository: ProjectsRepository) {

    // https://dzone.com/articles/waiting-for-coroutines
    fun manageImport() {


        println("\nProjects imported successfully!")
    }
}