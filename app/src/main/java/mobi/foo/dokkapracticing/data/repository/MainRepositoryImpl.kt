package mobi.foo.dokkapracticing.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import mobi.foo.dokkapracticing.data.dataSource.BaseRemoteDataSource
import mobi.foo.dokkapracticing.data.dto.LaunchDto
import mobi.foo.dokkapracticing.data.remote.ApiServices
import mobi.foo.dokkapracticing.domain.models.main.LaunchModel
import mobi.foo.dokkapracticing.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : MainRepository, BaseRemoteDataSource() {
    override fun fetchAllLaunches(): Flow<List<LaunchModel>> {
        return flow {
            val response = handleApiCall { apiServices.fetchAllLaunches() }

            emit(LaunchDto.toDomainList(response))
        }
    }
}