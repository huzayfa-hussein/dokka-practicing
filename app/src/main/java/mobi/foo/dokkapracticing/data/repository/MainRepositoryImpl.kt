package mobi.foo.dokkapracticing.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mobi.foo.dokkapracticing.data.dto.LaunchDto
import mobi.foo.dokkapracticing.data.remote.ApiServices
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel
import mobi.foo.dokkapracticing.domain.repository.MainRepository
import javax.inject.Inject

/**
 * Implementation of the [MainRepository] interface.
 * This class handles fetching data related to launches from an [ApiServices] instance.
 * @param apiServices An instance of [ApiServices] used to fetch launch data.
 */
class MainRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : MainRepository {

    /**
     * Fetches all launches asynchronously and returns them as a flow of [List] of [LaunchModel].
     * @return A [Flow] emitting a list of [LaunchModel] objects.
     */
    override fun fetchAllLaunches(): Flow<List<LaunchModel>> {
        return flow {
            // Fetch launches from the API service
            val response = apiServices.fetchAllLaunches().body()

            // Emit the list of LaunchModel objects after converting from Dto
            emit(LaunchDto.toDomainList(response))
        }
    }
}