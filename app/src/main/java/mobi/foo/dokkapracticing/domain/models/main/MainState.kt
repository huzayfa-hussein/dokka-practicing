package mobi.foo.dokkapracticing.domain.models.main


/**
 * MainState represents the state of the main screen in the application.
 * It contains information about the list of launches, loading status, and company details.
 * @property launches List of launch models displayed on the main screen.
 * @property isLoading Boolean indicating whether data is being loaded.
 * @property companyModel Company details displayed on the main screen.
 */
data class MainState(
    val launches: List<LaunchModel> = listOf(),
    val isLoading: Boolean = false,
    val companyModel: CompanyModel = CompanyModel()
)

/*
in MVI pattern we put all of our screen's state in a one single state wrapper class into ui state class,
we treat this class as a single source of truth, only contains immutable properties that cant be changed and if our state changes, then
the goal of the mvi is to replace the whole state with the new instance and the changed fields.
 */