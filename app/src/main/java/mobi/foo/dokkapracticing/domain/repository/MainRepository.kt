package mobi.foo.dokkapracticing.domain.repository

import kotlinx.coroutines.flow.Flow
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel

interface MainRepository {

    fun fetchAllLaunches(): Flow<List<LaunchModel>>
}