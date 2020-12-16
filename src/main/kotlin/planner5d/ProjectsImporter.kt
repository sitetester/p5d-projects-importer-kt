package planner5d

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.hibernate.SessionFactory
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import planner5d.entity.Project
import planner5d.parser.ProjectDetailsParser
import planner5d.parser.ProjectsParser

@Component
class ProjectsImporter(
    private val sessionFactory: SessionFactory,
    private val projectsParser: ProjectsParser,
    private val priProjectDetailsParser: ProjectDetailsParser
) {

    private val baseUrl = "https://planner5d.com"

    fun import(page: Int = 1, maxPages: Int = 10): Boolean {

        val url = this.baseUrl + "/gallery/floorplans?page=" + page
        val html = Jsoup.connect(url).get()
        val parsedProjects = projectsParser.parseHtml(html)

        runBlocking {
            val projects = parsedProjects.map { parsedProject ->

                val url = baseUrl + parsedProject.link

                GlobalScope.async {
                    val projectDetailsHtml: Document = Jsoup.connect(url).get()
                    priProjectDetailsParser.parseHtml(projectDetailsHtml, parsedProject, baseUrl)
                }
            }

            val resolvedProjects = projects.map { it.await() }
            bulkInsert(resolvedProjects)
        }

        var pageTemp = page + 1
        if (page <= maxPages) {
            import(pageTemp)
        }

        return true
    }

    // https://stackoverflow.com/questions/7349464/bulk-insert-or-update-with-hibernate
    private fun bulkInsert(projects: List<Project>) {

        val session = sessionFactory.openSession()
        val tx = session.beginTransaction()

        projects.forEach { project: Project ->
            session.save(project)
        }

        session.flush()
        session.clear()

        tx.commit()
        session.close()
    }
}
