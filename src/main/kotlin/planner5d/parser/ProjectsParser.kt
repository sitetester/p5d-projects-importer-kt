package planner5d.parser

import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import planner5d.entity.Project
import planner5d.entity.ProjectStats
import planner5d.entity.ProjectThumbnail

@Component
class ProjectsParser {

    fun parseHtml(html: Document): List<Project> {

        return html.select("div.card.ideas-card").map { divCardIdeasCard ->
            Project(
                link = divCardIdeasCard.select("a.card-image.image").attr("href"),
                thumbnail = parseThumbnail(divCardIdeasCard),
                stats = parseStats(divCardIdeasCard),
                hash = "",
                aboutContents = ""
            )
        }
    }

    private fun parseThumbnail(divCardIdeasCard: org.jsoup.nodes.Element): ProjectThumbnail {

        val img = divCardIdeasCard.select("a.card-image.image img")

        return ProjectThumbnail(src = img.attr("src"), alt = img.attr("alt"))
    }

    private fun parseStats(divCardIdeasCard: org.jsoup.nodes.Element): ProjectStats {

        val numViews =
            divCardIdeasCard.select("div.level-left div.level-item:nth-child(1) span:nth-child(2)").text()
        val numLikes =
            divCardIdeasCard.select("div.level-left div.level-item:nth-child(2) span:nth-child(2)").text()
        val numComments =
            divCardIdeasCard.select("div.level-right div.level-item:nth-child(1) span:nth-child(2)").text()

        return ProjectStats(numViews = toInt(numViews), numLikes = toInt(numLikes), numComments = toInt(numComments))
    }

    private fun toInt(text: String): Int {
        return try {
            text.toInt()
        } catch (ex: Exception) {
            0
        }
    }

    private fun parseProjects(html: String): Unit {

    }
}