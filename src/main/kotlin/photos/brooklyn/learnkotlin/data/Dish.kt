package photos.brooklyn.learnkotlin.data

data class Dish(
        val id: Int,
        val name: String,
        val image: String,
        val category: String,
        val label: String,
        val price: String,
        val featured: String,
        val description: String,
        val comments: List<Comment>
)