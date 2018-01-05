package photos.brooklyn.learnkotlin.data

data class Promotion(
        val id: Int,
        val name: String,
        val image: String,
        val label: String,
        val price: String,
        val featured: String,
        val description: String
)