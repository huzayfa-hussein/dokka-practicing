package mobi.foo.dokkapracticing.domain.usecases

import kotlinx.coroutines.flow.Flow
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel
import mobi.foo.dokkapracticing.domain.repository.MainRepository
import javax.inject.Inject

class GetLaunchesUseCase
@Inject constructor(private val mainRepository: MainRepository) {

    operator fun invoke(): Flow<List<LaunchModel>> =
        mainRepository.fetchAllLaunches()

}