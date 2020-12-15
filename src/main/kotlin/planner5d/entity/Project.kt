package planner5d.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Entity
class Project(

    @Id
    @GeneratedValue
    var id: Long? = null,

    var link: String?,
    var hash: String?,
    @OneToOne
    var thumbnail: ProjectThumbnail,

    @OneToOne
    var stats: ProjectStats,

    var aboutContents: String
) {

    override fun toString(): String {
        return """link=${this.link}, hash=${this.hash}, aboutContents=${this.aboutContents}"""
    }
}


