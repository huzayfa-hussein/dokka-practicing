package mobi.foo.dokkapracticing.domain.repository

import kotlinx.coroutines.flow.Flow
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel

/**
 * Repository interface responsible for fetching launches.
 */
interface MainRepository {

    /**
     * Fetches all launches.
     *
     * This function returns a [Flow] of [List] of [LaunchModel]. The returned flow represents a stream of data
     * that emits lists of launch models asynchronously. Observing this flow allows clients to receive updates
     * whenever there is a change in the list of launches.
     *
     * @return A [Flow] emitting a list of [LaunchModel]s.
     */
    fun fetchAllLaunches(): Flow<List<LaunchModel>>
}