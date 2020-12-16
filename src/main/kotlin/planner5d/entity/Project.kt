package planner5d.entity

import javax.persistence.*

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

    // `cascade` option must be set in order to save this relationship data successfully
    @OneToOne(cascade = [CascadeType.ALL])
    var thumbnail: ProjectThumbnail,

    // `cascade` option must be set in order to save this relationship data successfully
    @OneToOne(cascade = [CascadeType.ALL])
    var stats: ProjectStats,
) {

    override fun toString(): String {
        return """link=${this.link}, hash=${this.hash}, aboutContents=${this.aboutContents}"""
    }
}


