class LateInitSample {
    companion object{
        private lateinit var password : String
        lateinit var userName : String

        fun setData(pair: Pair<String,String>){
            password = pair.first
            userName = pair.second
        }
    }
}