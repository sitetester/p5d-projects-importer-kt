package planner5d.repository

import org.springframework.data.repository.CrudRepository
import planner5d.entity.Project

interface ProjectsRepository : CrudRepository<Project, Long>