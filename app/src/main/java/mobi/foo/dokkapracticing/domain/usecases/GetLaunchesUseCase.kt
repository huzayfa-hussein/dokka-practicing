package mobi.foo.dokkapracticing.domain.usecases

import kotlinx.coroutines.flow.Flow
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel
import mobi.foo.dokkapracticing.domain.repository.MainRepository
import javax.inject.Inject

/**
 * Use case responsible for fetching all launches.
 * @param mainRepository The repository to fetch launches from.
 */
class GetLaunchesUseCase
@Inject constructor(private val mainRepository: MainRepository) {

    /**
     * Invokes the use case to fetch all launches.
     * @return A flow emitting a list of [LaunchModel] objects.
     */
    operator fun invoke(): Flow<List<LaunchModel>> =
        mainRepository.fetchAllLaunches()

}