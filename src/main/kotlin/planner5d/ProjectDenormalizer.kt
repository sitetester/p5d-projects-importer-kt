package planner5d

import org.springframework.stereotype.Component
import planner5d.entity.Project
import planner5d.parser.ProjectData

@Component
class ProjectDenormalizer {

    fun denormalize(projectData: ProjectData, project: Project): Project {

        // TODO: parse floors, rooms and other info
        return project
    }
}
