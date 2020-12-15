package planner5d

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import planner5d.entity.Project
import planner5d.parser.ProjectDetailsParser
import planner5d.parser.ProjectsParser

@Component
class ProjectsImporter(
    private val projectsParser: ProjectsParser,
    private val priProjectDetailsParser: ProjectDetailsParser
) {

    private val baseUrl = "https://planner5d.com"

    fun import(page: Int = 1, maxPages: Int = 5): Boolean {

        val url = this.baseUrl + "/gallery/floorplans?page=" + page
        val html = Jsoup.connect(url).get()
        val parsedProjects = projectsParser.parseHtml(html)

        runBlocking {
            val projects = parsedProjects.map { parsedProject: Project ->

                val url = baseUrl + parsedProject.link
                println(url)

                GlobalScope.async {
                    val projectDetailsHtml: Document = Jsoup.connect(url).get()
                    priProjectDetailsParser.parseHtml(projectDetailsHtml, parsedProject, baseUrl)
                }
            }

            projects.map { it.await() }.forEach {
                println(it)
            }
        }

        return true
    }
}
