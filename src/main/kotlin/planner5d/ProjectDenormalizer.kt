package planner5d

import org.springframework.stereotype.Component
import planner5d.entity.Project
import planner5d.parser.Item
import planner5d.parser.ProjectData

@Component
class ProjectDenormalizer {

    val floor = "Floor"
    val room = "Room"

    fun denormalize(projectData: ProjectData, project: Project): Project {

        val firstItem = projectData.items.first()
        project.title = firstItem.name

        val items = firstItem.data?.items

        project.numFloors = parseNumFloors(items)
        project.numRooms = parseNumRooms(items)
        project.numOtherItems = parseNumOtherItems(items)

        return project
    }

    fun parseNumFloors(projectItems: List<Item>?): Int {

        var count = 0
        projectItems?.forEach { item ->
            if (item.className != floor) {
                ++count
            }
        }

        return count
    }

    fun parseNumRooms(projectItems: List<Item>?): Int {

        var count = 0
        projectItems?.forEach { floor ->
            if (floor.items?.isNotEmpty() == true) {
                val floorItems = floor.items

                floorItems.forEach { floorItem ->
                    if (floorItem.className != room) {
                        ++count
                    }
                }
            }
        }

        return count
    }

    fun parseNumOtherItems(projectItems: List<Item>?, count: Int = 0): Int {

        var countTemp = count
        projectItems?.forEach { item ->
            if (item.className != floor && item.className != room) {
                ++countTemp
            }

            if (item.items?.isNotEmpty() == true) {
                countTemp = parseNumOtherItems(item.items, countTemp)
            }
        }

        return countTemp
    }

}
