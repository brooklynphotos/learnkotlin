package photos.brooklyn.learnkotlin.data

data class Leader(
    val id: Int,
    val name: String,
    val image: String,
    val designation: String,
    val abbr: String,
    val featured: String,
    val description: String
)