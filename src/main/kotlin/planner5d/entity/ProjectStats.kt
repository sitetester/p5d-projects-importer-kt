package planner5d.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Entity
class ProjectStats(

    @Id
    @GeneratedValue
    var id: Long? = null,

    var numViews: Int,
    var numLikes: Int,
    var numComments: Int
)


