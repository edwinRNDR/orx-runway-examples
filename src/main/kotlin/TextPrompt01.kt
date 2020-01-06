import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.extra.runway.*
import org.openrndr.ffmpeg.ScreenRecorder
import org.openrndr.shape.Rectangle
import org.openrndr.text.Writer
import java.io.File

/**
 * This demonstrates a prompt to text model. It generates a longer text sequence from a prompt.
 * This example requires a `runway/GPT-2` model to be active in Runway.
 */

fun main() = application {
    configure {
        width = 768
        height = 768
    }

    program {

        val runwayHost = "http://localhost:8000/query"

        val nouns = File("data/dictionary/nouns.txt").readLines()
        val prepositions = File("data/dictionary/prepositions.txt").readLines()
        val adjectives = File("data/dictionary/adjectives.txt").readLines()
        val font = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 36.0)

        val promptFont = loadFont("data/fonts/IBMPlexMono-Regular.ttf", 24.0)

        extend(ScreenRecorder()) {
            frameRate = 1
            maximumDuration = 15.0
            quitAfterMaximum = true
        }
        extend {
            val prompt = "a ${adjectives.random()} ${nouns.random()} ${prepositions.random()} a ${adjectives.random()} ${nouns.random()}"
            drawer.fontMap = promptFont
            val wp = Writer(drawer)
            wp.box = Rectangle(20.0, 0.0, width - 40.0, height - 40.0)
            wp.newLine()
            wp.text(prompt)


            val result: Gpt2Result = runwayQuery(runwayHost, Gpt2Request(prompt, sequenceLength = 128))

            drawer.fill = ColorRGBa.PINK
            drawer.fontMap = font

            val w = Writer(drawer)
            w.box = Rectangle(20.0, 60.0, width - 40.0, height - 80.0)
            w.newLine()
            w.text(result.text)
        }
    }
}