package photos.brooklyn.learnkotlin.controller

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
}