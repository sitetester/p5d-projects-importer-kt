package planner5d.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import planner5d.repository.ProjectsRepository

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Controller
@RequestMapping("/projects")
class ProjectsController(private val projectsRepository: ProjectsRepository) {

    @GetMapping
    fun index(modelAndView: ModelAndView): ModelAndView {

        modelAndView.addObject("projects", projectsRepository.findAll())
        modelAndView.viewName = "/projects/index"

        return modelAndView
    }
}