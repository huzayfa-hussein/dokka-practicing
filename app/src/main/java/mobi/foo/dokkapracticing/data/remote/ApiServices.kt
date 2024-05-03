package mobi.foo.dokkapracticing.data.remote

import mobi.foo.dokkapracticing.data.dto.LaunchDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface defining the API services for fetching launch data.
 */
interface ApiServices {

    /**
     * Fetches all launches from the API.
     *
     * @return A Response object containing a list of [LaunchDto] objects.
     */
    @GET("/v3/launches")
    suspend fun fetchAllLaunches(): Response<List<LaunchDto>>
}