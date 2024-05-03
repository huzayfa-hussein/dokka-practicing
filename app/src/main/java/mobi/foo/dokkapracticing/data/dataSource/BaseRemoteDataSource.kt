package mobi.foo.dokkapracticing.data.dataSource

import retrofit2.HttpException
import retrofit2.Response

open class BaseRemoteDataSource {


    suspend fun <T> handleApiCall(
        request: suspend () -> Response<T>
    ): T? {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                result.body()
            } else {
                null
            }
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            null
        }

    }
}