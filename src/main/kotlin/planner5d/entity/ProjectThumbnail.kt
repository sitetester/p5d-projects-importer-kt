package planner5d.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Entity
class ProjectThumbnail(

    @Id
    @GeneratedValue
    var id: Long? = null,

    var src: String,
    var alt: String,
)


