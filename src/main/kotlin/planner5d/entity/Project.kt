package planner5d.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Entity
class Project(

    @Id
    @GeneratedValue
    var id: Long? = null,

    var link: String?,
    var hash: String?,
    var title: String?,
    var aboutContents: String,

    var numFloors: Int,
    var numRooms: Int,
    var numOtherItems: Int,

    var hits: Int,

    /*@OneToOne
    var thumbnail: ProjectThumbnail,

    @OneToOne
    var stats: ProjectStats,*/
) {

    override fun toString(): String {
        return """link=${this.link}, hash=${this.hash}, aboutContents=${this.aboutContents}"""
    }
}


