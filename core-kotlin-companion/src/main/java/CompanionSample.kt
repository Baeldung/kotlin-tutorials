class AppDatabase {
    companion object  {
        @JvmField
        var version : Int =1
        lateinit var database : AppDatabase

        fun getInstance(): AppDatabase {
            if (database == null) {
                database = AppDatabase()
            }
            return database
        }
    }
}