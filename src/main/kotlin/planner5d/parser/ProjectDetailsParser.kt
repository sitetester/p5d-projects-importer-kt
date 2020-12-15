package planner5d.parser

import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import planner5d.entity.Project

@Component
class ProjectDetailsParser {

    fun parseHtml(html: Document, project: Project, baseUrl: String): Project {

        project.aboutContents = html.select(".is-7-tablet-portrait > p:nth-child(12)").text()
        val key = parseProjectKey(html)
        project.hash = key

        // val data = downloadProjectData(key, baseUrl)

        return project
    }

    private fun parseProjectKey(html: Document): String {

        val href = html.select("a.button.is-fullwidth.is-primary").attr("href")
        val re = Regex("key=(.+)&")

        val result = re.find(href) ?: throw Exception("Project key not found!")

        return result.groupValues[1]
    }

    private fun downloadProjectData(key: String, baseUrl: String): String {

        val url = "$baseUrl/api/project/$key"
        return java.net.URL(url).readText()
    }
}


class MineUserEntity {

    data class Items(
        val cdate: String,
        val udate: String,
        val hash: String,
    )
}