package planner5d.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import planner5d.repository.ProjectsRepository

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@RestController
@RequestMapping("/projects")
class ProjectsController(private val projectsRepository: ProjectsRepository) {

    @GetMapping
    fun index() = projectsRepository.findAll()

    companion object {
        private const val template = "Hello, %s!"
    }
}