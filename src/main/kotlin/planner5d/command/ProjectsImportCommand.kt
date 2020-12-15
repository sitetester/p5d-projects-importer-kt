package planner5d.command

import planner5d.ProjectsImportManager

// @ShellComponent
class ProjectsImportCommand(private val projectsImportManager: ProjectsImportManager) {

    //@ShellMethod("Planner5d import projects command")
    fun import(): Unit {
        projectsImportManager.manageImport()
    }
}