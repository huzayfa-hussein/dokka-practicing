package mobi.foo.dokkapracticing.data.remote

import mobi.foo.dokkapracticing.data.dto.LaunchDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("/v3/launches")
    suspend fun fetchAllLaunches(): Response<List<LaunchDto>>
}