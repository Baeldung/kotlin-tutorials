package com.baeldung.serialization.kotlinx.polymorphism

import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Serializable
abstract class Article { abstract val title: String }

@Serializable
@SerialName("KotlinLibraryArticle")
data class KotlinLibraryArticle(override val title: String, val library: String) : Article()

class OpenPolymorphismSerializationUnitTest {

    @Test
    fun `serializes open polymorphic object`() {
        // given
        val jsonFormat = Json {
            serializersModule = SerializersModule {
                polymorphic(Article::class) {
                    subclass(KotlinLibraryArticle::class)
                }
            }
        }

        val article: Article = KotlinLibraryArticle(
            title = "Class Inheritance with Kotlinx Serialization",
            library = "kotlinx.serialization",
        )

        // when
        val serializedArticle = jsonFormat.encodeToString(article)

        // then
        assertThat(serializedArticle).isEqualTo("""{"type":"KotlinLibraryArticle","title":"Class Inheritance with Kotlinx Serialization","library":"kotlinx.serialization"}""")
    }

    @Test
    fun `serializes Any type`() {
        // given
        val jsonFormat = Json {
            serializersModule = SerializersModule {
                polymorphic(Any::class) {
                    subclass(KotlinLibraryArticle::class)
                }
            }
        }

        val article: Any = KotlinLibraryArticle(
            title = "Class Inheritance with Kotlinx Serialization",
            library = "kotlinx.serialization",
        )

        // when
        val serializedArticle = jsonFormat.encodeToString(PolymorphicSerializer(Any::class), article)

        // then
        assertThat(serializedArticle).isEqualTo("""{"type":"KotlinLibraryArticle","title":"Class Inheritance with Kotlinx Serialization","library":"kotlinx.serialization"}""")
    }

}