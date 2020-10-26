package me.salmon.microblog.models

data class Author(val id: Int,
                  val name: String?,
                  val userName: String?,
                  val email: String?,
                  val avatarUrl: String?,
                  val lat: Float,
                  val long: Float) {

    override fun equals(other: Any?): Boolean {
        (other as? Author)?.let {
            return it.id == id
        }
        return super.equals(other)
    }

}