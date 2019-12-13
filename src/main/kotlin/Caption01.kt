import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.ColorBuffer
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.draw.tint
import org.openrndr.extra.runway.CaptionRequest
import org.openrndr.extra.runway.CaptionResult
import org.openrndr.extra.runway.fromData
import org.openrndr.extra.runway.runwayQuery
import kotlin.math.cos
import kotlin.math.sin

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        extend {
            val imgd: CaptionResult = runwayQuery("http://localhost:8000/query", CaptionRequest("fish"))
            drawer.image(ColorBuffer.fromData(imgd.result))
        }
    }
}