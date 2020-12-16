package planner5d.parser

import com.google.gson.Gson
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import planner5d.ProjectDenormalizer
import planner5d.entity.Project

@Component
class ProjectDetailsParser(private val projectDenormalizer: ProjectDenormalizer) {

    fun parseHtml(html: Document, project: Project, baseUrl: String): Project {

        project.aboutContents = html.select(".is-7-tablet-portrait > p:nth-child(12)").text()
        val key = parseProjectKey(html)
        project.hash = key

        val url = "$baseUrl/api/project/$key"
        val data = java.net.URL(url).readText()

        val parsedProject = projectDenormalizer.denormalize(
            Gson().fromJson(data, ProjectData::class.java),
            project
        )

        // do other stuff here with parsedProject
        return parsedProject
    }

    private fun parseProjectKey(html: Document): String {

        val href = html.select("a.button.is-fullwidth.is-primary").attr("href")
        val re = Regex("key=(.+)&")

        val result = re.find(href) ?: throw Exception("Project key not found!")

        return result.groupValues[1]
    }

}


data class ProjectData(
    val storage: String = "",
    val copyright: String = "",
    val items: List<Items>,
)

data class Items(
    val name: String?,
    val hash: String?,
    val data: Item?
)

data class Item(
    val className: String?,
    val items: List<Item>?,
)
