package photos.brooklyn.learnkotlin.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import photos.brooklyn.LearnKotlinApplication


@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(LearnKotlinApplication::class),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JSONControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun whenCalledWithExisting_shouldGetSomeData() {
        val result = testRestTemplate.getForEntity("/staticjson/dishes", List::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(4,result.body.size)
    }

    @Test
    fun whenCalledWithFeatured_shouldGetOneData() {
        val result = testRestTemplate.getForEntity("/staticjson/dishes?featured=true", List::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(1,result.body.size)
    }

    @Test
    fun whenCalledWithNonExisting_shouldGetNoData() {
        val result = testRestTemplate.getForEntity("/staticjson/foo", List::class.java)
        assertNotNull(result)
        assertEquals(result?.statusCode, HttpStatus.OK)
        assertNull(result.body)
    }

    @Test
    fun whenAddNew_shouldGetNewBack() {
        val result = testRestTemplate.postForEntity("/staticjson/dishes", hashMapOf("name" to "hello"), Map::class.java)
        assertNotNull(result)
        assertThat(result.body["id"] as Int).isGreaterThan(0)
    }


    @Test
    fun whenAddNewToEmpty_shouldGetNewBack() {
        val result = testRestTemplate.postForEntity("/staticjson/feedback", hashMapOf("name" to "hello"), Map::class.java)
        assertNotNull(result)
        assertThat(result.body["id"] as Int).isEqualTo(1)
    }

    @Test
    fun whenUpdateWithNewComment_shouldHaveNewDBComment() {
        // this should already be tested
        val dish = testRestTemplate.getForEntity("/staticjson/dishes/1", MutableMap::class.java).body
        val comments = dish["comments"] as MutableList<Map<String, *>>
        val originalCommentSize = comments.size
        comments.add(mapOf("rating" to 3, "author" to "me"))

        // update
        testRestTemplate.put("/staticjson/dishes/1", dish, Map::class.java)
        // this should already be tested
        val newDish = testRestTemplate.getForEntity("/staticjson/dishes/1", MutableMap::class.java).body
        val newComments: List<*>? = newDish["comments"] as List<*>?
        assertNotNull(newComments)
        assertThat(newComments?.size).isEqualTo(1+originalCommentSize)
    }
}