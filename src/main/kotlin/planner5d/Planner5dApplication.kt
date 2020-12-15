package planner5d

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Planner5dApplication

fun main(args: Array<String>) {

    runApplication<Planner5dApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}